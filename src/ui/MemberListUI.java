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
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableModel;

import java.awt.FlowLayout;
import javax.swing.JTextField;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComponent;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JTable;
import javax.swing.border.BevelBorder;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
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
	@SuppressWarnings("serial")
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
		scrollPane.setBounds(26, 75, 640, 429);
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
		table.setFont(new Font("Poppins", Font.PLAIN, 13));
		table.getTableHeader().setFont(new Font("Poppins", Font.PLAIN, 13));

        
		table.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            private final MatteBorder border = new MatteBorder(1, 1, 0, 0, Color.BLACK);
            private final EmptyBorder emptyBorder = new EmptyBorder(1, 1, 0, 0);

            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                Component cellComponent = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                ((JComponent) cellComponent).setBorder(BorderFactory.createCompoundBorder(border, emptyBorder));
                return cellComponent;
            }
        });
        
//		MatteBorder border = new MatteBorder(1, 1, 1, 1, Color.BLACK);
//		table.setBorder(border);
		scrollPane.setViewportView(table);
	}
}
