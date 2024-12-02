import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

class HomePage extends JFrame {

    private JLabel titleLabel;
    private JButton btnSearch, btnStatus, btnReports, btnDelete, btnPlaceOrder;
    private ImageIcon imageIcon;
    private CustomerCollection customerCollection;

   
    HomePage(CustomerCollection customerCollection) {
        this.customerCollection = customerCollection;

        setSize(500, 600);
        setTitle("Fashion Shop");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        // Title Label
        titleLabel = new JLabel("Fashion Shop", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 40));
        titleLabel.setBounds(0, 20, 500, 60);
        titleLabel.setOpaque(true);
        titleLabel.setBackground(new Color(0, 102, 204));
        titleLabel.setForeground(Color.WHITE);
        add(titleLabel);

        // Search Button
        btnSearch = new JButton("Search");
        btnSearch.setFont(new Font("Arial", Font.BOLD, 14));
        btnSearch.setBounds(35, 120, 180, 45);
        btnSearch.addActionListener(evt -> {
            String[] options = { "Search by Phone Number", "Search by Order ID", "Cancel" };
            int choice = JOptionPane.showOptionDialog(
                    null,
                    "Choose one of the options:",
                    "Search Customer",
                    JOptionPane.DEFAULT_OPTION,
                    JOptionPane.INFORMATION_MESSAGE,
                    null,
                    options,
                    options[0]
            );
            switch (choice) {
                case 0: 
                    new SearchCustomerForm(customerCollection).setVisible(true);
                    break;
                case 1: 
                    new SearchOrderForm(customerCollection).setVisible(true);
                    break;
                case 2: 
                    break;
                default:
                    System.out.println("Dialog closed without selection");
            }
            dispose(); 
        });
        add(btnSearch);

        // Status Button
        btnStatus = new JButton("Status");
        btnStatus.setFont(new Font("Arial", Font.BOLD, 14));
        btnStatus.setBounds(35, 190, 180, 45);
        btnStatus.addActionListener(evt -> {
            dispose();
            new StatusForm(customerCollection).setVisible(true);
        });
        add(btnStatus);

        // Reports Button
        btnReports = new JButton("Report");
        btnReports.setFont(new Font("Arial", Font.BOLD, 14));
        btnReports.setBounds(35, 260, 180, 45);
        btnReports.addActionListener(evt -> {
            dispose();
            new ViewReportsHomePage(customerCollection).setVisible(true);
        });
        add(btnReports);

        // Delete Button
        btnDelete = new JButton("Delete");
        btnDelete.setFont(new Font("Arial", Font.BOLD, 14));
        btnDelete.setBounds(35, 330, 180, 45);
        btnDelete.addActionListener(evt -> {
            dispose();
            new DeleteForm(customerCollection).setVisible(true);
        });
        add(btnDelete);

        // Place Order Button
        btnPlaceOrder = new JButton("Place Order");
        btnPlaceOrder.setFont(new Font("Arial", Font.BOLD, 25));
        btnPlaceOrder.setBounds(35, 430, 180, 65);
        btnPlaceOrder.setBackground(new Color(39, 186, 196));
        btnPlaceOrder.addActionListener(evt -> {
            dispose();
            new AddCustomerForm(customerCollection).setVisible(true);
        });
        add(btnPlaceOrder);

        // Background Image
        imageIcon = new ImageIcon("C:/Users/samitha senewirathna/Desktop/my swing app/img1.jpg");
        JLabel labelImage = new JLabel(imageIcon);
        labelImage.setBounds(250, 120, 200, 375);
        add(labelImage);
    }

   
}
