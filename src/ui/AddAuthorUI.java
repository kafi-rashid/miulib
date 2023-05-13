package ui;

import javax.swing.*;
import java.awt.Font;

import controller.SystemController;
import data.Address;
import data.Author;

import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AddAuthorUI extends JPanel {
	private JTextField txtFirstName;
	private JTextField txtLastName;
	private JTextField txtPhone;
	private SystemController controller = SystemController.getInstance();

	/**
	 * Create the panel.
	 */
	public AddAuthorUI() {
		setLayout(null);

		JLabel lblNewLabel = new JLabel("Add Author");
		lblNewLabel.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel.setFont(new Font("Poppins", Font.PLAIN, 20));
		lblNewLabel.setBounds(26, 20, 400, 40);
		add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("First Name");
		lblNewLabel_1.setFont(new Font("Poppins", Font.PLAIN, 13));
		lblNewLabel_1.setBounds(26, 75, 400, 40);
		add(lblNewLabel_1);


		JComboBox cmbAddress = new JComboBox();
		cmbAddress.setBounds(26, 332, 400, 40);
		add(cmbAddress);

		JButton btnAddAddress = new JButton("+ New Address");
		btnAddAddress.setFont(new Font("Poppins", Font.PLAIN, 13));
		btnAddAddress.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				NewAddressUI.showUI(AddAuthorUI.this);
			}
		});
		btnAddAddress.setBounds(438, 333, 139, 40);
		add(btnAddAddress);

		for(Address a: controller.getAddresses()){
			cmbAddress.addItem(a.toString());
		}
		add(cmbAddress);

		JLabel lblNewLabel_1_1 = new JLabel("Last Name");
		lblNewLabel_1_1.setFont(new Font("Poppins", Font.PLAIN, 13));
		lblNewLabel_1_1.setBounds(26, 148, 400, 40);
		add(lblNewLabel_1_1);

		txtFirstName = new JTextField();
		txtFirstName.setBounds(26, 107, 400, 40);
		add(txtFirstName);
		txtFirstName.setColumns(10);

		txtLastName = new JTextField();
		txtLastName.setBounds(26, 181, 400, 40);
		add(txtLastName);
		txtLastName.setColumns(10);

		JButton btnAddAuthor = new JButton("Add Author");
		btnAddAuthor.setFont(new Font("Poppins", Font.PLAIN, 13));
		btnAddAuthor.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        String firstName = txtFirstName.getText().trim();
		        String lastName = txtLastName.getText().trim();
		        String phone = txtPhone.getText().trim();
		        int addressIndex = cmbAddress.getSelectedIndex();

		        // Validate the fields
		        if (firstName.isEmpty() || lastName.isEmpty() || phone.isEmpty()) {
		            JOptionPane.showMessageDialog(null, "Please fill in all fields", "Error", JOptionPane.ERROR_MESSAGE);
		        } else {
		            // Additional validation for the phone field
		            if (!phone.matches("\\d+")) {
		                JOptionPane.showMessageDialog(null, "Phone should contain digits only", "Error", JOptionPane.ERROR_MESSAGE);
		                return; // Stop further execution if phone validation fails
		            }

		            var msg = controller.addAuthor(firstName, lastName, phone, addressIndex);
		            JOptionPane.showMessageDialog(null, msg);

		            // Clear the fields
		            txtFirstName.setText("");
		            txtLastName.setText("");
		            txtPhone.setText("");
		            cmbAddress.setSelectedIndex(0);
		        }
		    }
		});

		btnAddAuthor.setBackground(new Color(72, 61, 139));
		btnAddAuthor.setBounds(278, 380, 148, 40);
		add(btnAddAuthor);

		txtPhone = new JTextField();
		txtPhone.setColumns(10);
		txtPhone.setBounds(26, 255, 400, 40);
		add(txtPhone);

		JLabel lblNewLabel_1_1_1 = new JLabel("Phone");
		lblNewLabel_1_1_1.setFont(new Font("Poppins", Font.PLAIN, 13));
		lblNewLabel_1_1_1.setBounds(26, 221, 400, 40);
		add(lblNewLabel_1_1_1);

		JLabel lblPhoneNumber_1 = new JLabel("Address");
		lblPhoneNumber_1.setFont(new Font("Poppins", Font.PLAIN, 13));
		lblPhoneNumber_1.setBounds(26, 297, 400, 40);
		add(lblPhoneNumber_1);


	}
}
