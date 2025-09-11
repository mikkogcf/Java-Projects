/*
 * Student Name: John Micah Mercado
 * Student ID Number: N01611717
 * Submission Date: 14 April 2025
 */

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;

/**
 * A GUI application for managing a basketball player database. Allows input of
 * personal info, contract status, salary, position, and age, then saves the
 * information into a CSV file.
 */
public class CustomFrame extends JFrame {

	private static final long serialVersionUID = 1L;

	// GUI Components
	public JLabel topLabel, firstNameLabel, lastNameLabel, rookieContractLabel, salaryLabel, positionLabel, ageLabel;

	public JTextField firstNameTextField, lastNameTextField;

	public JCheckBox checkBox;

	public JRadioButton option1, option2, option3, option4;

	public ButtonGroup salaryOptionGroup;

	public JComboBox<String> comboBox;

	public JSpinner ageSpinner;

	public JButton submitButton, clearButton;

	/**
	 * Constructor to initialize the frame and add components with layout.
	 */
	public CustomFrame() {
		this.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(5, 10, 5, 10); // padding around components

		// Top Label
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridwidth = 2;
		topLabel = new JLabel("Basketball Player Database");
		this.add(topLabel, gbc);

		// First Name Label & TextField
		gbc.gridwidth = 1;
		gbc.gridx = 0;
		gbc.gridy = 1;
		firstNameLabel = new JLabel("First Name:");
		this.add(firstNameLabel, gbc);

		gbc.gridx = 1;
		firstNameTextField = new JTextField(10);
		this.add(firstNameTextField, gbc);

		// Last Name Label & TextField
		gbc.gridx = 0;
		gbc.gridy = 2;
		lastNameLabel = new JLabel("Last Name:");
		this.add(lastNameLabel, gbc);

		gbc.gridx = 1;
		lastNameTextField = new JTextField(10);
		this.add(lastNameTextField, gbc);

		// Rookie Contract Checkbox
		gbc.gridx = 0;
		gbc.gridy = 3;
		rookieContractLabel = new JLabel("Rookie Contract?");
		this.add(rookieContractLabel, gbc);

		gbc.gridx = 1;
		checkBox = new JCheckBox();
		this.add(checkBox, gbc);

		// Salary Radio Buttons
		gbc.gridx = 0;
		gbc.gridy = 4;
		salaryLabel = new JLabel("Salary (per year):");
		this.add(salaryLabel, gbc);

		gbc.gridx = 1;
		option1 = new JRadioButton("< $500k");
		this.add(option1, gbc);

		gbc.gridy = 5;
		option2 = new JRadioButton("$500k - $5m");
		this.add(option2, gbc);

		gbc.gridy = 6;
		option3 = new JRadioButton("$5m - $25m");
		this.add(option3, gbc);

		gbc.gridy = 7;
		option4 = new JRadioButton("> $25m");
		this.add(option4, gbc);

		salaryOptionGroup = new ButtonGroup();
		salaryOptionGroup.add(option1);
		salaryOptionGroup.add(option2);
		salaryOptionGroup.add(option3);
		salaryOptionGroup.add(option4);

		// Position Dropdown (ComboBox)
		gbc.gridx = 0;
		gbc.gridy = 8;
		positionLabel = new JLabel("Position:");
		this.add(positionLabel, gbc);

		gbc.gridx = 1;
		String[] positions = { "PG", "SG", "SF", "PF", "C" };
		comboBox = new JComboBox<>(positions);
		this.add(comboBox, gbc);

		// Age Spinner
		gbc.gridx = 0;
		gbc.gridy = 9;
		ageLabel = new JLabel("Age:");
		this.add(ageLabel, gbc);

		gbc.gridx = 1;
		ageSpinner = new JSpinner(new SpinnerNumberModel(18, 0, 100, 1));
		this.add(ageSpinner, gbc);

		// Submit Button
		gbc.gridx = 0;
		gbc.gridy = 10;
		submitButton = new JButton("Submit");
		this.add(submitButton, gbc);
		submitButton.addActionListener(new MyActionListener());

		// Clear Button
		gbc.gridx = 1;
		clearButton = new JButton("Clear");
		this.add(clearButton, gbc);
		clearButton.addActionListener(new MyActionListener());
	}

	/**
	 * ActionListener for Submit and Clear buttons. Handles writing to file and
	 * resetting the form.
	 */
	public class MyActionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == submitButton) {
				try {
					File file = new File("players.csv");
					boolean isEmpty = !file.exists() || file.length() == 0;

					// FileWriter in append mode
					FileWriter newFile = new FileWriter(file, true);
					PrintWriter out = new PrintWriter(newFile);

					// Write header only if file is new/empty
					if (isEmpty) {
						out.println("FirstName,LastName,RookieContract,Salary,Position,Age");
					}

					// Get selected salary
					String salary = "null";
					if (option1.isSelected())
						salary = option1.getText();
					else if (option2.isSelected())
						salary = option2.getText();
					else if (option3.isSelected())
						salary = option3.getText();
					else if (option4.isSelected())
						salary = option4.getText();

					// Write player data to file
					out.println(firstNameTextField.getText() + "," + lastNameTextField.getText() + ","
							+ checkBox.isSelected() + "," + salary + "," + comboBox.getSelectedItem() + ","
							+ ageSpinner.getValue());
					out.close();

					// Reset form fields
					firstNameTextField.setText("");
					lastNameTextField.setText("");
					checkBox.setSelected(false);
					comboBox.setSelectedIndex(0);
					ageSpinner.setValue(18);
					salaryOptionGroup.clearSelection();

				} catch (IOException e1) {
					System.err.println("File error: " + e1.getMessage());
				}

			} else if (e.getSource() == clearButton) {
				// Reset all fields when Clear is clicked
				firstNameTextField.setText("");
				lastNameTextField.setText("");
				checkBox.setSelected(false);
				comboBox.setSelectedIndex(0);
				ageSpinner.setValue(18);
				salaryOptionGroup.clearSelection();
			}
		}
	}
}
