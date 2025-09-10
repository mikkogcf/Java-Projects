import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 * ClientThreadHandler handles an individual client request to the server
 * in a separate thread. It sends a command, receives the response,
 * and calculates the turnaround time.
 */
public class ClientThreadHandler extends Thread {

	// Client request number for tracking
	private int clientRequestNumber;

	// The user command to send to the server
	private String userCommand;

	// Server connection parameters
	private String ipAddress;
	private int portNumber;

	/**
	 * Constructs a ClientThreadHandler thread for a single client request.
	 *
	 * @param clientRequestNumber unique number for the request
	 * @param userCommand         the command to be executed by the server
	 * @param ipAddress           the server IP address
	 * @param portNumber          the server port number
	 */
	public ClientThreadHandler(int clientRequestNumber, String userCommand, String ipAddress, int portNumber) {
		this.clientRequestNumber = clientRequestNumber;
		this.userCommand = userCommand;
		this.ipAddress = ipAddress;
		this.portNumber = portNumber;
	}

	/**
	 * Executes the request: connects to the server, sends command,
	 * receives and prints the response, and stores elapsed time.
	 */
	@Override
	public void run() {
		try {
			// Connect to the server
			Socket socketClient = new Socket(ipAddress, portNumber);

			// Set up streams for sending and receiving data
			DataOutputStream sendDataToServer = new DataOutputStream(socketClient.getOutputStream());
			BufferedReader serverResponseData = new BufferedReader(
					new InputStreamReader(socketClient.getInputStream()));

			String srd = "";
			long startTime = System.currentTimeMillis(); // Start timing

			// Send the user command to the server
			sendDataToServer.writeBytes(userCommand + "\n");

			// Read the response line by line until ENDSTREAM is received
			while ((srd = serverResponseData.readLine()) != null) {
				if (srd.equals("ENDSTREAM")) {
					socketClient.close(); // Close connection when response ends
					break;
				}
				System.out.println(srd + "\n"); // Display server output
			}

			// Store elapsed time for the request
			ClientSharedData.clientElapsedTime[clientRequestNumber] =
					System.currentTimeMillis() - startTime;

		} catch (Exception e) {
			// Handle exceptions gracefully
			e.printStackTrace();
		}
	}

	/**
	 * Sends a disconnection message to the server to notify client exit.
	 *
	 * @param clientStatus disconnection message (e.g., "DISCONNECTED")
	 * @param ipAddress    the server IP address
	 * @param portNumber   the server port number
	 * @throws Exception if connection or communication fails
	 */
	public static void clientDisconnected(String clientStatus, String ipAddress, int portNumber) throws Exception {
		Socket socketClient = new Socket(ipAddress, portNumber);
		DataOutputStream sendDataToServer = new DataOutputStream(socketClient.getOutputStream());
		sendDataToServer.writeBytes(clientStatus + "\n");
		socketClient.close(); // Cleanly close the socket after sending status
	}
}
