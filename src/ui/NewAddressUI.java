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
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("New Address");
		lblNewLabel.setBounds(6, 6, 121, 16);
		contentPane.add(lblNewLabel);
		
		JButton btnAddNew = new JButton("Add New");
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
		btnAddNew.setBounds(305, 220, 117, 29);
		contentPane.add(btnAddNew);
		
		txtStreet = new JTextField();
		txtStreet.setBounds(116, 66, 191, 26);
		contentPane.add(txtStreet);
		txtStreet.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Street");
		lblNewLabel_1.setBounds(29, 71, 75, 16);
		contentPane.add(lblNewLabel_1);
		
		txtCity = new JTextField();
		txtCity.setColumns(10);
		txtCity.setBounds(116, 104, 191, 26);
		contentPane.add(txtCity);
		
		JLabel lblNewLabel_1_1 = new JLabel("City");
		lblNewLabel_1_1.setBounds(29, 109, 75, 16);
		contentPane.add(lblNewLabel_1_1);
		
		txtState = new JTextField();
		txtState.setColumns(10);
		txtState.setBounds(116, 142, 191, 26);
		contentPane.add(txtState);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("State");
		lblNewLabel_1_1_1.setBounds(29, 147, 75, 16);
		contentPane.add(lblNewLabel_1_1_1);
		
		txtZip = new JTextField();
		txtZip.setColumns(10);
		txtZip.setBounds(116, 180, 191, 26);
		contentPane.add(txtZip);
		
		JLabel lblNewLabel_1_1_1_1 = new JLabel("Zip");
		lblNewLabel_1_1_1_1.setBounds(29, 185, 75, 16);
		contentPane.add(lblNewLabel_1_1_1_1);
	}
}
