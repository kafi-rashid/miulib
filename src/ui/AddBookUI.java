package ui;

import javax.swing.*;
import java.awt.Font;

import controller.SystemController;
import data.Address;
import data.Author;
import data.Book;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AddBookUI extends JPanel {
    private JTextField txtTitle;
    private JTextField txtISBN;
    private JTextField txtAvailability;
    SystemController controller = SystemController.getInstance();
    JComboBox cmbMaxDuration;

    /**
     * Create the panel.
     */
    public AddBookUI() {
        setLayout(null);

        JLabel lblNewLabel = new JLabel("Add Book");
		lblNewLabel.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel.setFont(new Font("Poppins", Font.PLAIN, 20));
		lblNewLabel.setBounds(26, 20, 400, 40);
        add(lblNewLabel);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(249, 10, 2, 2);
        add(scrollPane);

        JLabel lblNewLabel_1 = new JLabel("Title");
        lblNewLabel_1.setFont(new Font("Poppins", Font.PLAIN, 13));
        lblNewLabel_1.setHorizontalAlignment(SwingConstants.LEFT);
        lblNewLabel_1.setBounds(26, 75, 400, 32);
        add(lblNewLabel_1);

        txtTitle = new JTextField();
        txtTitle.setFont(new Font("Poppins", Font.PLAIN, 12));
        txtTitle.setBounds(26, 104, 400, 40);
        add(txtTitle);
        txtTitle.setColumns(10);

        txtISBN = new JTextField();
        txtISBN.setFont(new Font("Poppins", Font.PLAIN, 12));
        txtISBN.setBounds(26, 183, 400, 40);
        add(txtISBN);
        txtISBN.setColumns(10);

        JLabel lblNewLabel_2 = new JLabel("ISBN");
        lblNewLabel_2.setFont(new Font("Poppins", Font.PLAIN, 13));
        lblNewLabel_2.setHorizontalAlignment(SwingConstants.LEFT);
        lblNewLabel_2.setBounds(26, 150, 400, 40);
        add(lblNewLabel_2);

        JLabel lblNewLabel_3 = new JLabel("Author(s)");
        lblNewLabel_3.setFont(new Font("Poppins", Font.PLAIN, 13));
        lblNewLabel_3.setHorizontalAlignment(SwingConstants.LEFT);
        lblNewLabel_3.setBounds(26, 225, 400, 40);
        add(lblNewLabel_3);

        JComboBox cmbAuthors = new JComboBox();
        cmbAuthors.setBounds(26, 256, 400, 40);
        for (Author a : controller.getAuthors()) {
            cmbAuthors.addItem(a.toString());
        }
        add(cmbAuthors);

        JLabel lblNewLabel_4 = new JLabel("Number of Copy");
        lblNewLabel_4.setHorizontalAlignment(SwingConstants.LEFT);
        lblNewLabel_4.setFont(new Font("Poppins", Font.PLAIN, 13));
        lblNewLabel_4.setBounds(26, 306, 400, 40);
        add(lblNewLabel_4);

        txtAvailability = new JTextField();
        txtAvailability.setFont(new Font("Poppins", Font.PLAIN, 12));
        txtAvailability.setBounds(26, 335, 400, 40);
        add(txtAvailability);
        txtAvailability.setColumns(10);

        JButton btnAddAuthors = new JButton("Add Author");
        btnAddAuthors.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		MainUI.LoadUI(new AddAuthorUI());
        	}
        });
        btnAddAuthors.setFont(new Font("Poppins", Font.PLAIN, 13));
        btnAddAuthors.setBounds(441, 257, 146, 40);
        add(btnAddAuthors);

        JButton btnSave = new JButton("Add Book");
        btnSave.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                var avaiableBooks = 0;
                try {
                    avaiableBooks = Integer.parseInt(txtAvailability.getText());
                }catch (Exception ex){
                    JOptionPane.showMessageDialog(null, "Number of copies must be a number", "Available books" , JOptionPane.ERROR_MESSAGE);
                    return;
                }
                //adding book
                var selectedAuthor = controller.getAuthors().get(cmbAuthors.getSelectedIndex());
                var days = 21;
                if(cmbMaxDuration.getSelectedIndex() == 1){
                    days = 7;
                }
                controller.addBook(txtTitle.getText(), txtISBN.getText(), selectedAuthor, avaiableBooks, days);
                JOptionPane.showMessageDialog(null, "Success");
            }
        });
        btnSave.setFont(new Font("Poppins", Font.PLAIN, 13));
        btnSave.setBounds(271, 463, 155, 38);
        add(btnSave);
        
        cmbMaxDuration = new JComboBox();
        cmbMaxDuration.setBounds(26, 413, 400, 40);
        cmbMaxDuration.addItem("21 days");
        cmbMaxDuration.addItem("7 days");
        add(cmbMaxDuration);
        
        JLabel lblNewLabel_4_1 = new JLabel("Maximum Checkout Duration");
        lblNewLabel_4_1.setHorizontalAlignment(SwingConstants.LEFT);
        lblNewLabel_4_1.setFont(new Font("Poppins", Font.PLAIN, 13));
        lblNewLabel_4_1.setBounds(26, 385, 400, 40);
        add(lblNewLabel_4_1);

    }
}
