package ui;

import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import controller.SystemController;
import data.Address;
import data.Member;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

public class EditExistingMemberUI extends JPanel {
	private JTextField txtFirstName;
	private JTextField txtLastName;
	private JTextField txtPhone;
	private SystemController controller = SystemController.getInstance();
	Member selectedMember;

	/**
	 * Create the panel.
	 */
	public EditExistingMemberUI() {
		setBackground(SystemColor.window);
		setLayout(null);

		JLabel lblNewLabel_1 = new JLabel("Edit Member");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_1.setFont(new Font("Poppins", Font.PLAIN, 20));
		lblNewLabel_1.setBounds(26, 20, 400, 40);
		add(lblNewLabel_1);
		
		txtFirstName = new JTextField();
		txtFirstName.setBounds(26, 179, 400, 40);
		add(txtFirstName);
		txtFirstName.setColumns(10);

		JLabel lblNewLabel = new JLabel("First Name");
		lblNewLabel.setFont(new Font("Poppins", Font.PLAIN, 13));
		lblNewLabel.setBounds(26, 145, 400, 40);
		add(lblNewLabel);

		txtLastName = new JTextField();
		txtLastName.setColumns(10);
		txtLastName.setBounds(26, 259, 400, 40);
		add(txtLastName);

		JLabel lblLastName = new JLabel("Last Name");
		lblLastName.setFont(new Font("Poppins", Font.PLAIN, 13));
		lblLastName.setBounds(26, 221, 400, 40);
		add(lblLastName);

		JLabel lblPhoneNumber = new JLabel("Phone Number");
		lblPhoneNumber.setFont(new Font("Poppins", Font.PLAIN, 13));
		lblPhoneNumber.setBounds(26, 299, 400, 40);
		add(lblPhoneNumber);

		txtPhone = new JTextField();
		txtPhone.setColumns(10);
		txtPhone.setBounds(26, 330, 400, 40);
		add(txtPhone);

		JLabel lblPhoneNumber_1 = new JLabel("Address");
		lblPhoneNumber_1.setFont(new Font("Poppins", Font.PLAIN, 13));
		lblPhoneNumber_1.setBounds(26, 368, 400, 40);
		add(lblPhoneNumber_1);

		JComboBox cmbAddress = new JComboBox();
		cmbAddress.setBounds(26, 409, 400, 40);

		for(Address a: controller.getAddresses()){
			cmbAddress.addItem(a.toString());
		}
		add(cmbAddress);

		JButton btnAddAddress = new JButton("+ New Address");
		btnAddAddress.setFont(new Font("Poppins", Font.PLAIN, 13));
		btnAddAddress.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				NewAddressUI.showUI(EditExistingMemberUI.this);
			}
		});
		btnAddAddress.setBounds(438, 410, 153, 40);
		add(btnAddAddress);

		JButton btnAddMember = new JButton("Save Member");
		btnAddMember.setFont(new Font("Poppins", Font.PLAIN, 13));
		btnAddMember.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.editMember(selectedMember, txtFirstName.getText(), txtLastName.getText(), txtPhone.getText(), cmbAddress.getSelectedIndex());
				JOptionPane.showMessageDialog(null, "Successfully edited", "Success", JOptionPane.PLAIN_MESSAGE);
				MainUI.LoadUI(new EditExistingMemberUI());
			}
		});
		btnAddMember.setBounds(249, 469, 177, 40);
		add(btnAddMember);
		
		JComboBox cmbMembers = new JComboBox();
		cmbMembers.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				selectedMember = controller.getSelectedMember(cmbMembers.getSelectedIndex());
				txtFirstName.setText(selectedMember.getFirstName());
				txtLastName.setText(selectedMember.getLastName());
				txtPhone.setText(selectedMember.getPhone());
				cmbAddress.setSelectedItem(controller.getAddressIndex(selectedMember.getAddress()));
			}
		});
		cmbMembers.setBounds(26, 108, 400, 40);
		for(Member member: controller.getMembers()){
			cmbMembers.addItem(member.getFirstName());
		}
		add(cmbMembers);
		
		JLabel lblNewLabel_2 = new JLabel("Select Member");
		lblNewLabel_2.setFont(new Font("Poppins", Font.PLAIN, 13));
		lblNewLabel_2.setBounds(26, 75, 400, 40);
		add(lblNewLabel_2);

	}

}
