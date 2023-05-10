package ui;

import controller.SystemController;
import data.Address;
import data.Member;

import javax.swing.*;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.Random;

public class AddMemberUI extends JPanel {
	private JTextField txtFirstName;
	private JTextField txtLastName;
	private JTextField txtPhone;
	private SystemController systemController = SystemController.getInstance();

	/**
	 * Create the panel.
	 */
	public AddMemberUI() {
		setBackground(SystemColor.window);
		setLayout(null);

		txtFirstName = new JTextField();
		txtFirstName.setBounds(17, 109, 664, 37);
		add(txtFirstName);
		txtFirstName.setColumns(10);

		JLabel lblNewLabel = new JLabel("First Name");
		lblNewLabel.setBounds(28, 87, 91, 16);
		add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Add New Member");
		lblNewLabel_1.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
		lblNewLabel_1.setBounds(17, 6, 676, 37);
		add(lblNewLabel_1);

		txtLastName = new JTextField();
		txtLastName.setColumns(10);
		txtLastName.setBounds(17, 180, 664, 37);
		add(txtLastName);

		JLabel lblLastName = new JLabel("Last Name");
		lblLastName.setBounds(28, 158, 91, 16);
		add(lblLastName);

		JLabel lblPhoneNumber = new JLabel("Phone Number");
		lblPhoneNumber.setBounds(28, 229, 234, 16);
		add(lblPhoneNumber);

		txtPhone = new JTextField();
		txtPhone.setColumns(10);
		txtPhone.setBounds(17, 251, 664, 37);
		add(txtPhone);

		JLabel lblPhoneNumber_1 = new JLabel("Address");
		lblPhoneNumber_1.setBounds(28, 307, 234, 16);
		add(lblPhoneNumber_1);

		JComboBox cmbAddress = new JComboBox();
		cmbAddress.setBounds(17, 335, 554, 27);

		for(Address a: systemController.getAddresses()){
			cmbAddress.addItem(a.toString());
		}
		add(cmbAddress);

		JButton btnAddAddress = new JButton("New Address");
		btnAddAddress.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				NewAddressUI.showUI(AddMemberUI.this);
			}
		});
		btnAddAddress.setBounds(572, 334, 117, 29);
		add(btnAddAddress);

		JButton btnAddMember = new JButton("Add Member");
		btnAddMember.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				systemController.addMember(txtFirstName.getText(), txtLastName.getText(), txtPhone.getText(), cmbAddress.getSelectedIndex());
				JOptionPane.showMessageDialog(null, "Successfully added", "Success", JOptionPane.PLAIN_MESSAGE);
				MainUI.LoadUI(new AddMemberUI());
			}
		});
		btnAddMember.setBounds(475, 479, 200, 50);
		add(btnAddMember);

	}
}
