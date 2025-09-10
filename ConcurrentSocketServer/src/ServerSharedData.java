import java.util.ArrayList;

/**
 * ServerSharedData is a shared data container used by all server threads
 * to manage global state like the count of connected clients and their IP addresses.
 */
public class ServerSharedData {

    /**
     * Tracks the number of active clients connected to the server.
     * Incremented on new client connection, decremented on disconnection.
     */
    public static int activeClientCount = 0;

    /**
     * Shared list to keep track of connected client IP addresses.
     * Used to avoid duplicate logging and manage client session tracking.
     */
    public static ArrayList<String> ClientIPList = new ArrayList<>();
}
