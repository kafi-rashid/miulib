package ui;


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

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComponent;

import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;

public class ReturnBookUI extends JPanel {

    private JTable tblBookList;
    private SystemController controller = SystemController.getInstance();

    /**
     * Create the panel.
     */
    @SuppressWarnings("serial")
	public ReturnBookUI() {
        setLayout(null);

        JLabel lblNewLabel = new JLabel("Return Book");
        lblNewLabel.setHorizontalAlignment(SwingConstants.LEFT);
        lblNewLabel.setFont(new Font("Poppins", Font.PLAIN, 20));
        lblNewLabel.setBounds(26, 20, 400, 40);
        add(lblNewLabel);

        refreshBookList();

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(26, 75, 640, 409);
        add(scrollPane);
        tblBookList.setFont(new Font("Poppins", Font.PLAIN, 13));
        tblBookList.getTableHeader().setFont(new Font("Poppins", Font.PLAIN, 13));
        tblBookList.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            private final MatteBorder border = new MatteBorder(1, 1, 0, 0, Color.BLACK);
            private final EmptyBorder emptyBorder = new EmptyBorder(1, 1, 0, 0);

            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                Component cellComponent = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                ((JComponent) cellComponent).setBorder(BorderFactory.createCompoundBorder(border, emptyBorder));

                // Check if the book is overdue
                boolean isOverdue = controller.isBookOverdue(row);
                if (isOverdue) {
//                    cellComponent.setForeground(Color.RED);
                } else {
                    cellComponent.setBackground(table.getBackground());
                }

                return cellComponent;
            }
        });
        scrollPane.setViewportView(tblBookList);

        JButton btnReturnBook = new JButton("Return Book");
        btnReturnBook.setFont(new Font("Poppins", Font.PLAIN, 13));
        btnReturnBook.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                var selectedRow = tblBookList.getSelectedRow();
                if (selectedRow == -1) {
                    JOptionPane.showMessageDialog(null, "Select a book from the table to return");
                    return;
                }
                controller.returnBook(selectedRow);
                JOptionPane.showMessageDialog(null, "Book returned successfully");
                refreshBookList(); // Update the book list after returning a book
            }
        });
        btnReturnBook.setBounds(26, 496, 139, 40);
        add(btnReturnBook);
    }
    
    public boolean isOverdue(LocalDate dueDate) {
        LocalDate currentDate = LocalDate.now();
        return currentDate.isAfter(dueDate);
    }

    private void refreshBookList() {
        var books = controller.getCheckoutBooks();
        String[][] data = new String[books.size()][8];

        var i = 0;
        for (CheckoutBooks checkout : books) {
            data[i][0] = checkout.book.title;
            data[i][1] = checkout.book.ISBN;
            data[i][2] = checkout.book.author.toString();
            data[i][3] = String.valueOf(checkout.member.MemberId);
            data[i][4] = checkout.member.toString();
            data[i][5] = checkout.checkoutDate.toString();
            data[i][6] = checkout.dueDate.toString();
            data[i][7] = isOverdue(checkout.dueDate) ? "Yes" : "No";
            i++;
        }

        String[] columnNames = {"Book Title", "ISBN", "Author", "Mem ID", "Name", "Checkout", "Due", "Is Overdue"};
        if (tblBookList != null) {
            tblBookList.setModel(new javax.swing.table.DefaultTableModel(data, columnNames));
            tblBookList.repaint();
        } else {
            tblBookList = new JTable(data, columnNames);
        }
    }
}
