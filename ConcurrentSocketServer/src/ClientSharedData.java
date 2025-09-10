/**
 * ClientSharedData is a utility class that stores shared data
 * accessible by multiple client threads, specifically for tracking
 * the elapsed time of each client request.
 */
public class ClientSharedData {

	/**
	 * An array to store the turn-around time (in milliseconds) for each client request.
	 * Each thread populates its corresponding index with the time it took to complete.
	 */
	public static long[] clientElapsedTime;

}
