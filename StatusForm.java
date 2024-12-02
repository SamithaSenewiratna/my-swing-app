import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class StatusForm extends JFrame {
    private JLabel lblOrderId, lblPhoneNumber, lblSize, lblQty, lblAmount, lblStatus;
    private JTextField txtOrderId, txtPhoneNumber, txtSize, txtQty, txtAmount, txtStatus;
    private JButton btnSearch, btnChangeStatus, btnBack;
    private CustomerCollection customerCollection;

    public StatusForm(CustomerCollection customerCollection) {
        this.customerCollection = customerCollection;

       
        setSize(500, 450);
        setTitle("Order Status");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);
        getContentPane().setBackground(new Color(245, 245, 245));  

        // Order ID 
        lblOrderId = new JLabel("Order ID:");
        lblOrderId.setBounds(30, 30, 100, 25);
        lblOrderId.setForeground(new Color(51, 51, 51)); 
        add(lblOrderId);

        txtOrderId = new JTextField();
        txtOrderId.setBounds(130, 30, 150, 25);
        add(txtOrderId);

          // Back button
        btnBack = new JButton("Back");
        btnBack.setFont(new Font("Arial", Font.BOLD, 14));
        btnBack.setBackground(new Color(233, 89, 89));
        btnBack.setForeground(Color.WHITE);
        btnBack.setBounds(2, 0, 80, 30);
        btnBack.addActionListener(e -> {
            dispose();
            new HomePage(customerCollection).setVisible(true);
        });
        add(btnBack);

        // Search Button
        btnSearch = new JButton("Search");
        btnSearch.setBounds(290, 30, 100, 25);
        btnSearch.setBackground(new Color(0, 128, 128));  
        btnSearch.setForeground(Color.WHITE);
        add(btnSearch);
        btnSearch.addActionListener(evt -> searchOrder());

        // Phone Number Label and TextField
        lblPhoneNumber = new JLabel("Phone No:");
        lblPhoneNumber.setBounds(30, 70, 100, 25);
        lblPhoneNumber.setForeground(new Color(51, 51, 51));  
        add(lblPhoneNumber);

        txtPhoneNumber = new JTextField();
        txtPhoneNumber.setBounds(130, 70, 150, 25);
        txtPhoneNumber.setEditable(false);
        add(txtPhoneNumber);

        // Size Label and TextField
        lblSize = new JLabel("Size:");
        lblSize.setBounds(30, 110, 100, 25);
        lblSize.setForeground(new Color(51, 51, 51)); 
        add(lblSize);

        txtSize = new JTextField();
        txtSize.setBounds(130, 110, 150, 25);
        txtSize.setEditable(false);
        add(txtSize);

        // Quantity Label 
        lblQty = new JLabel("Qty:");
        lblQty.setBounds(30, 150, 100, 25);
        lblQty.setForeground(new Color(51, 51, 51));  
        add(lblQty);

        txtQty = new JTextField();
        txtQty.setBounds(130, 150, 150, 25);
        txtQty.setEditable(false);
        add(txtQty);

        // Amount Label and TextField
        lblAmount = new JLabel("Amount:");
        lblAmount.setBounds(30, 190, 100, 25);
        lblAmount.setForeground(new Color(51, 51, 51));  
        add(lblAmount);

        txtAmount = new JTextField();
        txtAmount.setBounds(130, 190, 150, 25);
        txtAmount.setEditable(false);
        add(txtAmount);

        // Status Label and TextField
        lblStatus = new JLabel("Status:");
        lblStatus.setBounds(30, 230, 100, 25);
        lblStatus.setForeground(new Color(51, 51, 51)); 
        add(lblStatus);

        txtStatus = new JTextField();
        txtStatus.setBounds(130, 230, 150, 25);
        add(txtStatus);

        // Change Status Button
        btnChangeStatus = new JButton("Change Status");
        btnChangeStatus.setBounds(290, 290, 170, 35);
        btnChangeStatus.setBackground(new Color(255, 165, 0)); 
        btnChangeStatus.setForeground(Color.WHITE);
        add(btnChangeStatus);
        btnChangeStatus.addActionListener(evt -> changeStatus());
    }

    // Method search for an order using the order ID
    private void searchOrder() {
        String orderId = txtOrderId.getText().trim();

        if (orderId.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter an Order ID.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Customer customer = customerCollection.searchCustomerByOrderId(orderId);
        if (customer != null) {
            txtPhoneNumber.setText(customer.getPhoneNumber());
            txtSize.setText(customer.getSize());
            txtQty.setText(String.valueOf(customer.getQty()));
            txtAmount.setText(String.valueOf(customer.getAmount()));
            txtStatus.setText(customer.getStatus() != null ? customer.getStatus() : "Processing");
        } else {
            JOptionPane.showMessageDialog(this, "Order not found.", "Error", JOptionPane.ERROR_MESSAGE);
            clearFields();
        }
    }

    // Method change the status an order
    private void changeStatus() {
        String orderId = txtOrderId.getText().trim();
        Customer customer = customerCollection.searchCustomerByOrderId(orderId);

        if (customer == null) {
            JOptionPane.showMessageDialog(this, "Order not found. Please search for a valid order first.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String currentStatus = txtStatus.getText().trim().toLowerCase();

        if (currentStatus.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter a status.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        switch (currentStatus) {
            case "processing":
                txtStatus.setText("Delivering");
                customer.setStatus("Delivering");
                JOptionPane.showMessageDialog(this, "Status updated to 'Delivering'.", "Success", JOptionPane.INFORMATION_MESSAGE);
                break;
            case "delivering":
                txtStatus.setText("Delivered");
                customer.setStatus("Delivered");
                JOptionPane.showMessageDialog(this, "Status updated to 'Delivered'.", "Success", JOptionPane.INFORMATION_MESSAGE);
                break;
            case "delivered":
                JOptionPane.showMessageDialog(this, "Order is already delivered.", "Info", JOptionPane.INFORMATION_MESSAGE);
                break;
            default:
                JOptionPane.showMessageDialog(this, "Invalid status. Valid statuses are 'Processing', 'Delivering', or 'Delivered'.", "Error", JOptionPane.ERROR_MESSAGE);
                break;
        }
    }

    
    private void clearFields() {
        txtPhoneNumber.setText("");
        txtSize.setText("");
        txtQty.setText("");
        txtAmount.setText("");
        txtStatus.setText("");
    }
}
