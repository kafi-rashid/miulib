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

		JLabel lblNewLabel_1 = new JLabel("First Name");
		lblNewLabel_1.setBounds(27, 79, 126, 16);
		add(lblNewLabel_1);

		JLabel lblNewLabel = new JLabel("Add Author");
		lblNewLabel.setBounds(27, 24, 99, 22);
		lblNewLabel.setVerticalAlignment(SwingConstants.TOP);
		lblNewLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
		add(lblNewLabel);

		JComboBox cmbAddress = new JComboBox();
		cmbAddress.setBounds(27, 349, 400, 27);
		add(cmbAddress);

		JButton btnAddAddress = new JButton("New Address");
		btnAddAddress.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				NewAddressUI.showUI(AddAuthorUI.this);
			}
		});
		btnAddAddress.setBounds(453, 348, 117, 29);
		add(btnAddAddress);

		for(Address a: controller.getAddresses()){
			cmbAddress.addItem(a.toString());
		}
		add(cmbAddress);

		JLabel lblNewLabel_1_1 = new JLabel("Last Name");
		lblNewLabel_1_1.setBounds(27, 159, 126, 16);
		add(lblNewLabel_1_1);

		txtFirstName = new JTextField();
		txtFirstName.setBounds(27, 107, 284, 40);
		add(txtFirstName);
		txtFirstName.setColumns(10);

		txtLastName = new JTextField();
		txtLastName.setBounds(27, 181, 284, 40);
		add(txtLastName);
		txtLastName.setColumns(10);

		JButton btnAddAuthor = new JButton("+ Add Author");
		btnAddAuthor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				var msg = controller.addAuthor(txtFirstName.getText(), txtLastName.getText(), txtPhone.getText(), cmbAddress.getSelectedIndex());
				JOptionPane.showMessageDialog(null, msg);
			}
		});
		btnAddAuthor.setBackground(new Color(72, 61, 139));
		btnAddAuthor.setBounds(27, 445, 148, 40);
		add(btnAddAuthor);

		txtPhone = new JTextField();
		txtPhone.setColumns(10);
		txtPhone.setBounds(27, 255, 284, 40);
		add(txtPhone);

		JLabel lblNewLabel_1_1_1 = new JLabel("Phone");
		lblNewLabel_1_1_1.setBounds(27, 233, 126, 16);
		add(lblNewLabel_1_1_1);

		JLabel lblPhoneNumber_1 = new JLabel("Address");
		lblPhoneNumber_1.setBounds(38, 321, 234, 16);
		add(lblPhoneNumber_1);


	}
}
