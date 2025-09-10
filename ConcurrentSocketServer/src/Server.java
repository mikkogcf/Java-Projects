import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * The Server class sets up a multi-threaded TCP server that listens for
 * client connections, spawns threads for each client, and maintains 
 * a list of active client handler threads.
 */
public class Server {

    /**
     * The main method to start the server. Prompts for a port number,
     * creates a server socket, and handles incoming client connections.
     *
     * @param args the command-line arguments (not used)
     */
    public static void main(String[] args) {
        // Create a Scanner to capture user input for the port number
        Scanner in = new Scanner(System.in); 
        int portNumber = 0;

        // List to track all currently active client threads
        ArrayList<ConcurrentServer> allActiveClients = new ArrayList<>();

        // Prompt the user until a valid integer port number is entered
        while (true) {
            System.out.printf("Enter port number: ");
            if (in.hasNextInt()) {
                portNumber = in.nextInt(); // Store the valid port number
                break; // Exit the loop once a valid number is received
            } else {
                System.out.println("Please enter a valid port number.");
                in.next(); // Consume invalid input (non-integer) to avoid infinite loop
            }
        }

        // Close the Scanner after use to prevent resource leak
        in.close();

        // Inform that the server is now listening on the given port
        System.out.printf("Server is listening on port number %d...\n", portNumber);

        // Attempt to create a server socket on the specified port
        try (ServerSocket ss = new ServerSocket(portNumber)) {

            // Inform the user that the server is waiting for clients if none are currently connected
            if (ServerSharedData.activeClientCount == 0) {
                System.out.println("No clients are connected. Waiting for new clients...");
            }

            // Infinite loop to accept and handle client connections
            while (true) {
                // Accept an incoming client connection (blocking call)
                Socket socketClient = ss.accept();

                // Create a new thread (ConcurrentServer) to handle this client
                ConcurrentServer clientHandling = new ConcurrentServer(socketClient);

                // Add the thread to the list of active clients
                allActiveClients.add(clientHandling);

                // Start the thread to handle client communication
                clientHandling.start();

                // Remove any client threads from the list that are no longer alive
                for (int i = allActiveClients.size() - 1; i >= 0; i--) {
                    if (!allActiveClients.get(i).isAlive()) {
                        allActiveClients.remove(i);
                    }
                }
            }

        } catch (Exception e) {
            // Print the exception stack trace if any error occurs during server operation
            e.printStackTrace();
        }
    }
}
