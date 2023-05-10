package ui;

import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import controller.SystemController;
import data.Book;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ListSelectionModel;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;

public class UpdateBookCopyUI extends JPanel {
    private JTable jtblBookList;
    private JTable tblBookList;
    SystemController controller = SystemController.getInstance();
    private JTextField txtAvailableCopies;
    private JButton btnUpdateCopies;

    /**
     * Create the panel.
     */
    public UpdateBookCopyUI() {
        setLayout(null);

        JLabel lblNewLabel = new JLabel("Update Copies of book");
        lblNewLabel.setHorizontalAlignment(SwingConstants.LEFT);
        lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
        lblNewLabel.setBounds(26, 10, 370, 32);
        add(lblNewLabel);

        var books = controller.getBooks();
        String[][] data = new String[books.size()][5];

        var i = 0;
        for (Book book : books) {
            data[i][0] = book.title;
            data[i][1] = book.title;
            data[i][2] = book.ISBN;
            data[i][3] = book.author.toString();
            data[i][4] = String.valueOf(book.getCopyOfBooks());

            i++;
        }
        String[] columnNames = {"Book ID", "Title", "ISBN", "Authors", "Availability"};
        tblBookList = new JTable(data, columnNames);
        tblBookList.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
			@Override
			public void valueChanged(ListSelectionEvent e) {
				var selectedRow = tblBookList.getSelectedRow();
                if(selectedRow == -1){
                    btnUpdateCopies.setEnabled(false);
                    return;
                }
                btnUpdateCopies.setEnabled(true);
                var selectedBook = controller.getBooks().get(selectedRow);
                txtAvailableCopies.setText(String.valueOf(selectedBook.getCopyOfBooks()));
			}
        });
        tblBookList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tblBookList.setBounds(26, 54, 664, 312);

        add(tblBookList);
        
        txtAvailableCopies = new JTextField();
        txtAvailableCopies.setBounds(512, 381, 179, 26);
        add(txtAvailableCopies);
        txtAvailableCopies.setColumns(10);
        
        JLabel lblNewLabel_2 = new JLabel("Available copies");
        lblNewLabel_2.setBounds(365, 386, 135, 16);
        add(lblNewLabel_2);
        
        btnUpdateCopies = new JButton("Update copies");
        btnUpdateCopies.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
                var msg = controller.updateBookCopies(tblBookList.getSelectedRow(), txtAvailableCopies.getText());
                JOptionPane.showMessageDialog(null, msg);
                MainUI.LoadUI(new UpdateBookCopyUI());
        	}
        });
        btnUpdateCopies.setBounds(522, 419, 168, 42);
        add(btnUpdateCopies);
    }

}
