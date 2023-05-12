package ui;

import controller.SystemController;
import data.Member;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;

import java.awt.FlowLayout;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.JTable;
import javax.swing.border.BevelBorder;
import javax.swing.border.CompoundBorder;
import javax.swing.border.MatteBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MemberListUI extends JPanel {
	private JTable table;
	private JTable table_1;
	private SystemController controller = SystemController.getInstance();

	/**
	 * Create the panel.
	 */
	public MemberListUI() {
		setLayout(null);
		
		JLabel lblNewLabel = new JLabel("List of Members");
		lblNewLabel.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel.setFont(new Font("Poppins", Font.PLAIN, 20));
		lblNewLabel.setBounds(26, 20, 400, 40);
		add(lblNewLabel);
		
		JButton btnAddMember = new JButton("+ Add Member");
		btnAddMember.setFont(new Font("Poppins", Font.PLAIN, 13));
		btnAddMember.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MainUI.LoadUI(new AddMemberUI());
			}
		});
		btnAddMember.setBackground(new Color(72, 61, 139));
		btnAddMember.setBounds(26, 520, 170, 40);
		add(btnAddMember);
					
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(27, 79, 640, 429);
		add(scrollPane);
		

		var members = controller.getMembers();
		String[][] data = new String[members.size()][5];

		var i = 0;
		for(Member member:members){
			data[i][0] = String.valueOf(member.MemberId);
			data[i][1] = member.getFirstName();
			data[i][2] = member.getLastName();
			data[i][3] = member.getPhone();
			data[i][4] = member.getAddress().toString();

			i++;
		}

        String[] columnNames = { "Member ID", "First Name", "Last Name", "Phone #" };
		table = new JTable(data, columnNames);
		table.setFont(new Font("Lucida Grande", Font.PLAIN, 13));
		table.getTableHeader().setFont(new Font("Lucida Grande", Font.BOLD, 13));
		MatteBorder border = new MatteBorder(1, 1, 1, 1, Color.BLACK);
		table.setBorder(border);
		scrollPane.setViewportView(table);

	}
}
