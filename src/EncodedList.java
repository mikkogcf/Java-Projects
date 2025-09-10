import java.util.Iterator;

/**
 * EncodedList is a specialized list that stores elements as EncodingValues
 * and supports automatic compression using a TokenMap.
 * <p>
 * This list maintains a fully encoded state after any modification that changes
 * its contents (add, set, remove, or addBytes). It encodes pairs of elements
 * using the least token value and scans from right-to-left for tie-breaking.
 */
class EncodedList {
    private TokenMap map;                  // Map to get token values for pairs
    private EncodingValue[] data;          // Internal array storing elements
    private int size;                      // Current number of elements
    private final int DEFAULT_CAPACITY = 10; // Default initial array size

    /**
     * Constructs an EncodedList using the specified TokenMap.
     *
     * @param map the TokenMap used for encoding pairs
     */
    public EncodedList(TokenMap map) {
        this.map = map;
        this.data = new EncodingValue[DEFAULT_CAPACITY]; // Initialize with default capacity
        this.size = 0;                                   // Start empty
    }

    /**
     * Ensures that the internal array can hold at least the specified minimum
     * number of elements. Resizes the array if necessary.
     *
     * @param minCapacity the desired minimum capacity
     */
    public void ensureCapacity(int minCapacity) {
        if (data.length < minCapacity) {
            EncodingValue[] newData = new EncodingValue[minCapacity]; // Allocate new array with requested capacity
            for (int i = 0; i < size; i++) {
                newData[i] = data[i]; // Copy existing elements
            }
            data = newData; // Replace internal array
        }
    }

    /**
     * Returns the number of elements currently stored in the list.
     *
     * @return the size of the list
     */
    public int size() {
        return size; // Return current number of elements
    }

    /**
     * Returns an iterator over the elements in this list.
     *
     * @return an Iterator
     */
    public Iterator<EncodingValue> iterator() {
        return new MyEncodingIterator(); // Return new iterator instance
    }

    /**
     * Inner class implementing an iterator for EncodedList.
     */
    @SuppressWarnings("unchecked")
    class MyEncodingIterator implements Iterator<EncodingValue> {
        private int index = 0; // Current index for iteration

        @Override
        public boolean hasNext() {
            return index < size; // Check if more elements remain
        }

        @Override
        public EncodingValue next() {
            return (EncodingValue) data[index++]; // Return current element and increment index
        }
    }

    /**
     * Returns an array containing all elements in proper sequence.
     * Creates a new array so the internal array is not exposed.
     *
     * @return array containing all elements
     */
    public Object[] toArray() {
        EncodingValue[] array = new EncodingValue[size]; // Create new array of current size
        for (int i = 0; i < size; i++) {
            array[i] = data[i]; // Copy elements into new array
        }
        return array;
    }

    /**
     * Appends the specified element to the end of the list.
     *
     * @param element element to be added
     * @return true
     */
    public boolean add(EncodingValue element) {
        add(size(), element);
        return true;
    }

    /**
     * Removes all elements from the list.
     * Resets the internal array to the default capacity.
     */
    public void clear() {
        data = new EncodingValue[DEFAULT_CAPACITY]; // Allocate new array with default capacity
        size = 0;
    }

    /**
     * Returns the element at the specified position in the list.
     *
     * @param index index of the element to return
     * @return element at the specified index
     * @throws IndexOutOfBoundsException if index < 0 or index >= size
     */
    public EncodingValue get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        } else {
            return data[index]; // Return element at index
        }
    }

    /**
     * Replaces the element at the specified position with the specified element.
     *
     * @param index   index of the element to replace
     * @param element element to set
     * @return the previous element at the specified position
     * @throws IndexOutOfBoundsException if index < 0 or index >= size
     */
    public EncodingValue set(int index, EncodingValue element) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        } else {
            EncodingValue old = data[index];
            data[index] = element;
            encodeFully(); // Maintain encoded list
            return old;
        }
    }

    /**
     * Inserts the specified element at the specified position in the list.
     * Shifts subsequent elements to the right to make space.
     *
     * @param index   position at which to insert the element
     * @param element element to insert
     * @throws IndexOutOfBoundsException if index < 0 or index > size
     */
    public void add(int index, EncodingValue element) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException();
        } else {
            if (size >= data.length) {
                ensureCapacity(Math.max(DEFAULT_CAPACITY, size * 2)); // Double capacity if full
            }
            for (int i = size; i > index; i--) {
                data[i] = data[i - 1]; // Shift elements right
            }
            data[index] = element;
            size++;
            encodeFully();
        }
    }

    /**
     * Adds an array of raw bytes to the list as ByteValues.
     * Fully encodes the list after addition.
     *
     * @param rawData array of bytes to add
     */
    public void addBytes(byte[] rawData) {
        ensureCapacity(size + rawData.length); // Resize if needed
        for (byte b : rawData) {
            data[size++] = new ByteValue(b); // Convert to ByteValue and append
        }
        encodeFully(); // Maintain encoded list
    }

    /**
     * Removes the element at the specified position.
     * Shifts subsequent elements to the left to fill the gap.
     *
     * @param index index of the element to remove
     * @return the removed element
     * @throws IndexOutOfBoundsException if index < 0 or index >= size
     */
    public EncodingValue remove(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        } else {
            EncodingValue removed = data[index];
            for (int i = index; i < size - 1; i++) {
                data[i] = data[i + 1]; // Shift elements left
            }
            size--;
            encodeFully(); // Maintain encoded list
            return removed;
        }
    }

    /**
     * Returns the index of the first occurrence of the specified element,
     * or -1 if not found.
     *
     * @param o element to search for
     * @return index of first occurrence or -1 if not found
     */
    public int indexOf(Object o) {
        for (int i = 0; i < size; i++) {
            if (data[i].equals(o)) {
                return i; // Return index of first match
            }
        }
        return -1; // Element not found
    }

    /**
     * Fully encodes the list using the TokenMap.
     * Repeatedly compresses the best right-to-left pair until no more pairs can be encoded.
     */
    private void encodeFully() {
        boolean changed;

        do {
            changed = false;
            int bestValue = Integer.MAX_VALUE;
            int bestIndex = -1;
            TokenValue bestToken = null;

            // Find best pair to encode (smallest token value, right-to-left)
            for (int i = size - 2; i >= 0; i--) {
                TokenValue token = map.getToken(data[i], data[i + 1]);
                if (token != null && token.value() < bestValue) {
                    bestValue = token.value();
                    bestIndex = i;
                    bestToken = token;
                }
            }

            // Compress best pair if found
            if (bestToken != null) {
                data[bestIndex] = bestToken; // Replace pair with token
                for (int j = bestIndex + 1; j < size - 1; j++) {
                    data[j] = data[j + 1]; // Shift elements left
                }
                size--; // Reduce size since two became one
                changed = true; // Repeat scanning
            }

        } while (changed);
    }
}
