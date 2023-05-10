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

		txtFirstName = new JTextField();
		txtFirstName.setBounds(17, 151, 651, 37);
		add(txtFirstName);
		txtFirstName.setColumns(10);

		JLabel lblNewLabel = new JLabel("First Name");
		lblNewLabel.setBounds(28, 129, 91, 16);
		add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Edit Member");
		lblNewLabel_1.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
		lblNewLabel_1.setBounds(17, 6, 630, 37);
		add(lblNewLabel_1);

		txtLastName = new JTextField();
		txtLastName.setColumns(10);
		txtLastName.setBounds(17, 222, 651, 37);
		add(txtLastName);

		JLabel lblLastName = new JLabel("Last Name");
		lblLastName.setBounds(28, 200, 91, 16);
		add(lblLastName);

		JLabel lblPhoneNumber = new JLabel("Phone Number");
		lblPhoneNumber.setBounds(28, 271, 234, 16);
		add(lblPhoneNumber);

		txtPhone = new JTextField();
		txtPhone.setColumns(10);
		txtPhone.setBounds(17, 293, 651, 37);
		add(txtPhone);

		JLabel lblPhoneNumber_1 = new JLabel("Address");
		lblPhoneNumber_1.setBounds(28, 349, 234, 16);
		add(lblPhoneNumber_1);

		JComboBox cmbAddress = new JComboBox();
		cmbAddress.setBounds(17, 377, 514, 27);

		for(Address a: controller.getAddresses()){
			cmbAddress.addItem(a.toString());
		}
		add(cmbAddress);

		JButton btnAddAddress = new JButton("New Address");
		btnAddAddress.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				NewAddressUI.showUI(EditExistingMemberUI.this);
			}
		});
		btnAddAddress.setBounds(551, 376, 117, 29);
		add(btnAddAddress);

		JButton btnAddMember = new JButton("Edit Member");
		btnAddMember.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.editMember(selectedMember, txtFirstName.getText(), txtLastName.getText(), txtPhone.getText(), cmbAddress.getSelectedIndex());
				JOptionPane.showMessageDialog(null, "Successfully edited", "Success", JOptionPane.PLAIN_MESSAGE);
				MainUI.LoadUI(new EditExistingMemberUI());
			}
		});
		btnAddMember.setBounds(460, 467, 200, 50);
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
		cmbMembers.setBounds(17, 67, 641, 27);
		for(Member member: controller.getMembers()){
			cmbMembers.addItem(member.getFirstName());
		}
		add(cmbMembers);

	}

}
