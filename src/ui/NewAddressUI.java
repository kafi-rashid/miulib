package ui;

import controller.SystemController;
import data.Address;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;

public class NewAddressUI extends JFrame {

	private JPanel contentPane;
	private JTextField txtStreet;
	private JTextField txtCity;
	private JTextField txtState;
	private JTextField txtZip;
	JPanel refreshPanel;

	SystemController controller = SystemController.getInstance();


	public static void showUI(JPanel refreshPanel){
		var add = new NewAddressUI();
		add.refreshPanel = refreshPanel;
		add.setVisible(true);
	}

	private NewAddressUI() {
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 500, 390);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("New Address");
		lblNewLabel.setFont(new Font("Poppins", Font.PLAIN, 20));
		lblNewLabel.setBounds(26, 20, 343, 40);
		contentPane.add(lblNewLabel);
		
		JButton btnAddNew = new JButton("Add Address");
		btnAddNew.setFont(new Font("Poppins", Font.PLAIN, 13));
		btnAddNew.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.addAddress(txtStreet.getText(), txtCity.getText(), txtState.getText(), txtZip.getText());
				setVisible(false);

				if(refreshPanel instanceof AddAuthorUI){
					MainUI.LoadUI(new AddAuthorUI());
				}
				else if(refreshPanel instanceof  AddMemberUI){
					MainUI.LoadUI(new AddMemberUI());
				}
			}
		});
		btnAddNew.setBounds(327, 277, 143, 40);
		contentPane.add(btnAddNew);
		
		txtStreet = new JTextField();
		txtStreet.setFont(new Font("Poppins", Font.PLAIN, 13));
		txtStreet.setBounds(116, 75, 354, 40);
		contentPane.add(txtStreet);
		txtStreet.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Street");
		lblNewLabel_1.setFont(new Font("Poppins", Font.PLAIN, 13));
		lblNewLabel_1.setBounds(26, 75, 75, 40);
		contentPane.add(lblNewLabel_1);
		
		txtCity = new JTextField();
		txtCity.setFont(new Font("Poppins", Font.PLAIN, 13));
		txtCity.setColumns(10);
		txtCity.setBounds(116, 120, 354, 40);
		contentPane.add(txtCity);
		
		JLabel lblNewLabel_1_1 = new JLabel("City");
		lblNewLabel_1_1.setFont(new Font("Poppins", Font.PLAIN, 13));
		lblNewLabel_1_1.setBounds(26, 120, 75, 40);
		contentPane.add(lblNewLabel_1_1);
		
		txtState = new JTextField();
		txtState.setFont(new Font("Poppins", Font.PLAIN, 13));
		txtState.setColumns(10);
		txtState.setBounds(116, 165, 354, 40);
		contentPane.add(txtState);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("State");
		lblNewLabel_1_1_1.setFont(new Font("Poppins", Font.PLAIN, 13));
		lblNewLabel_1_1_1.setBounds(26, 165, 75, 40);
		contentPane.add(lblNewLabel_1_1_1);
		
		txtZip = new JTextField();
		txtZip.setFont(new Font("Poppins", Font.PLAIN, 13));
		txtZip.setColumns(10);
		txtZip.setBounds(116, 210, 354, 40);
		contentPane.add(txtZip);
		
		JLabel lblNewLabel_1_1_1_1 = new JLabel("Zip");
		lblNewLabel_1_1_1_1.setFont(new Font("Poppins", Font.PLAIN, 13));
		lblNewLabel_1_1_1_1.setBounds(26, 210, 75, 40);
		contentPane.add(lblNewLabel_1_1_1_1);
	}
}
