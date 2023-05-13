package ui;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;
import javax.swing.table.DefaultTableCellRenderer;

import controller.SystemController;
import data.CheckoutBooks;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.time.format.DateTimeFormatter;
import javax.swing.ListSelectionModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CheckoutBookListUI extends JPanel {
    private JTable tblBookList;
    private SystemController controller = SystemController.getInstance();
    private JTextField memberIdTextField;

    /**
     * Create the panel.
     */
    @SuppressWarnings("serial")
	public CheckoutBookListUI() {
        setLayout(null);

        JLabel lblNewLabel = new JLabel("View Checkout History");
        lblNewLabel.setHorizontalAlignment(SwingConstants.LEFT);
        lblNewLabel.setFont(new Font("Poppins", Font.PLAIN, 20));
        lblNewLabel.setBounds(26, 20, 462, 40);
        add(lblNewLabel);

        JLabel lblMemberId = new JLabel("Member ID:");
        lblMemberId.setFont(new Font("Poppins", Font.PLAIN, 13));
        lblMemberId.setBounds(26, 75, 80, 40);
        add(lblMemberId);

        memberIdTextField = new JTextField();
        memberIdTextField.setBounds(110, 72, 120, 40);
        add(memberIdTextField);
        memberIdTextField.setColumns(10);

        JButton btnRetrieveHistory = new JButton("Retrieve History");
        btnRetrieveHistory.setFont(new Font("Poppins", Font.PLAIN, 13));
        btnRetrieveHistory.setBounds(240, 72, 140, 40);
        add(btnRetrieveHistory);

        btnRetrieveHistory.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String memberIdText = memberIdTextField.getText();
                try {
                    int memberId = Integer.parseInt(memberIdText);
                    refreshCheckoutHistory(memberId);
                } catch (NumberFormatException ex) {
                    // Handle invalid member ID input
                    System.out.println("Invalid member ID: " + memberIdText);
                }
            }
        });

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(26, 130, 640, 416);
        add(scrollPane);

        tblBookList = new JTable();
        tblBookList.setFillsViewportHeight(true);
        tblBookList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tblBookList.setFont(new Font("Poppins", Font.PLAIN, 13));
        tblBookList.getTableHeader().setFont(new Font("Poppins", Font.PLAIN, 13));
        tblBookList.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            private final MatteBorder border = new MatteBorder(1, 1, 0, 0, Color.BLACK);
            private final EmptyBorder emptyBorder = new EmptyBorder(1, 1, 0, 0);

            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                Component cellComponent = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                ((JComponent) cellComponent).setBorder(BorderFactory.createCompoundBorder(border, emptyBorder));
                return cellComponent;
            }
        });
        scrollPane.setViewportView(tblBookList);
    }

    private void refreshCheckoutHistory(int memberId) {
        var books = controller.getCheckoutHistory(memberId);
        String[][] data = new String[books.size()][6];

        var i = 0;
        for (CheckoutBooks checkout : books) {
            data[i][0] = checkout.book.title;
            data[i][1] = checkout.book.ISBN;
            data[i][2] = checkout.book.author.toString();
            data[i][3] = String.valueOf(checkout.member.MemberId);
            data[i][4] = checkout.member.getFirstName();
            data[i][5] = checkout.dueDate.format(DateTimeFormatter.ISO_DATE);

            i++;
        }

        String[] columnNames = {"Book Title", "ISBN", "Author", "Member ID", "Member Name", "Due Date"};
        tblBookList.setModel(new javax.swing.table.DefaultTableModel(data, columnNames));
        tblBookList.repaint();
    }
}
