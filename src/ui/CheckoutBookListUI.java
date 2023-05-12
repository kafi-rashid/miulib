package ui;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import controller.SystemController;
import data.CheckoutBooks;

import java.awt.Font;
import java.time.format.DateTimeFormatter;
import javax.swing.ListSelectionModel;

public class CheckoutBookListUI extends JPanel {
    private JTable table;
    private JTable jtblBookList;
    private JTable tblBookList;
    SystemController controller = SystemController.getInstance();

    /**
     * Create the panel.
     */
    public CheckoutBookListUI() {
        setLayout(null);

        JLabel lblNewLabel = new JLabel("View Checkout History");
        lblNewLabel.setHorizontalAlignment(SwingConstants.LEFT);
        lblNewLabel.setFont(new Font("Poppins", Font.PLAIN, 20));
        lblNewLabel.setBounds(26, 20, 462, 40);
        add(lblNewLabel);

        var books = controller.getCheckoutBooks();
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
        tblBookList = new JTable(data, columnNames);
        tblBookList.setFillsViewportHeight(true);
        tblBookList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tblBookList.setBounds(26, 75, 640, 431);

        add(tblBookList);

    }
}
