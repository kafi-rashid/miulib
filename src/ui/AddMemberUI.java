package ui;

import controller.SystemController;
import data.Address;
import data.Member;

import javax.swing.*;

import java.awt.Dimension;
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
		txtFirstName.setBounds(26, 115, 402, 37);
		add(txtFirstName);
		txtFirstName.setColumns(10);

		JLabel lblNewLabel_1 = new JLabel("Add New Member");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_1.setFont(new Font("Poppins", Font.PLAIN, 20));
		lblNewLabel_1.setBounds(26, 20, 400, 40);
		add(lblNewLabel_1);
		
		JLabel lblNewLabel = new JLabel("First Name");
		lblNewLabel.setFont(new Font("Poppins", Font.PLAIN, 13));
		lblNewLabel.setBounds(26, 75, 400, 40);
		add(lblNewLabel);

		txtLastName = new JTextField();
		txtLastName.setColumns(10);
		txtLastName.setBounds(26, 186, 400, 40);
		add(txtLastName);

		JLabel lblLastName = new JLabel("Last Name");
		lblLastName.setFont(new Font("Poppins", Font.PLAIN, 13));
		lblLastName.setBounds(26, 158, 400, 40);
		add(lblLastName);

		JLabel lblPhoneNumber = new JLabel("Phone Number");
		lblPhoneNumber.setFont(new Font("Poppins", Font.PLAIN, 13));
		lblPhoneNumber.setBounds(26, 229, 400, 40);
		add(lblPhoneNumber);

		txtPhone = new JTextField();
		txtPhone.setColumns(10);
		txtPhone.setBounds(26, 257, 400, 40);
		add(txtPhone);

		JLabel lblPhoneNumber_1 = new JLabel("Address");
		lblPhoneNumber_1.setFont(new Font("Poppins", Font.PLAIN, 13));
		lblPhoneNumber_1.setBounds(26, 307, 400, 40);
		add(lblPhoneNumber_1);

		JComboBox cmbAddress = new JComboBox();
		cmbAddress.setBounds(26, 335, 400, 40);
		cmbAddress.setPreferredSize(new Dimension(cmbAddress.getPreferredSize().width, 40));

		for(Address a: systemController.getAddresses()){
			cmbAddress.addItem(a.toString());
		}
		add(cmbAddress);

		JButton btnAddAddress = new JButton("+ New Address");
		btnAddAddress.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				NewAddressUI.showUI(AddMemberUI.this);
			}
		});
		btnAddAddress.setBounds(438, 335, 159, 40);
		add(btnAddAddress);

		JButton btnAddMember = new JButton("Add Member");
		btnAddMember.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				systemController.addMember(txtFirstName.getText(), txtLastName.getText(), txtPhone.getText(), cmbAddress.getSelectedIndex());
				JOptionPane.showMessageDialog(null, "Successfully added", "Success", JOptionPane.PLAIN_MESSAGE);
				MainUI.LoadUI(new AddMemberUI());
			}
		});
		btnAddMember.setBounds(280, 387, 141, 40);
		add(btnAddMember);

	}
}
