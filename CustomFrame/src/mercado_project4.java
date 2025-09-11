import javax.swing.JFrame;

/**
 * The main class for launching the Basketball Player Database GUI application.
 * 
 * This class creates an instance of the CustomFrame, which contains the form to
 * input player information such as name, salary, position, rookie contract,
 * etc. It sets the window title, size, and default close behavior.
 */
public class mercado_project4 {

	/**
	 * The main method – entry point of the program.
	 *
	 * @param args the command-line arguments (not used)
	 */
	public static void main(String[] args) {

		// Create an instance of the custom GUI frame
		CustomFrame myFrame = new CustomFrame();

		// Set the title of the window
		myFrame.setTitle("Basketball Database Input Form");

		// Set the size of the window (width x height)
		myFrame.setSize(600, 800);

		// Ensure the application closes when the window is closed
		myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Make the frame visible on screen
		myFrame.setVisible(true);
	}
}
