import javax.swing.*;
import java.awt.*;

class DeleteForm extends JFrame {
    private JButton btnBack, btnSearch, btnDelete;
    private JTextField txtOrderId;
    private JLabel lblPhoneNumber, lblSize, lblQty, lblAmount, lblStatus;
    private JLabel lblPhoneNumberValue, lblSizeValue, lblQtyValue, lblAmountValue, lblStatusValue;
    private CustomerCollection customerCollection;

    
    DeleteForm(CustomerCollection customerCollection) {
        this.customerCollection = customerCollection;

        // Frame settings
        setTitle("Delete Customer");
        setSize(460, 380);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

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

        // Order ID label and text field
        JLabel lblOrderId = new JLabel("Enter Order ID:");
        lblOrderId.setFont(new Font("Arial", Font.BOLD, 15));
        lblOrderId.setBounds(10, 40, 130, 35);
        add(lblOrderId);

        txtOrderId = new JTextField();
        txtOrderId.setBounds(130, 40, 150, 35);
        add(txtOrderId);

        // Search button
        btnSearch = new JButton("Search");
        btnSearch.setFont(new Font("Arial", Font.BOLD, 16));
        btnSearch.setBackground(new Color(0, 128, 128));
        btnSearch.setForeground(Color.WHITE);
        btnSearch.setBounds(300, 40, 100, 30);
        btnSearch.addActionListener(e -> searchCustomer());
        add(btnSearch);

        // Labels for customer details
        lblPhoneNumber = createLabel("Phone No:", 100);
        lblPhoneNumberValue = createValueLabel(100);

        lblSize = createLabel("Size:", 140);
        lblSizeValue = createValueLabel(140);

        lblQty = createLabel("Qty:", 180);
        lblQtyValue = createValueLabel(180);

        lblAmount = createLabel("Amount:", 220);
        lblAmountValue = createValueLabel(220);

        lblStatus = createLabel("Status:", 260);
        lblStatusValue = createValueLabel(260);

        // Delete button
        btnDelete = new JButton("Delete");
        btnDelete.setFont(new Font("Arial", Font.BOLD, 16));
        btnDelete.setBackground(new Color(255, 69, 0));
        btnDelete.setForeground(Color.WHITE);
        btnDelete.setBounds(300, 260, 100, 30);
        btnDelete.addActionListener(e -> deleteCustomer());
        add(btnDelete);
    }

    // Method to search for a customer
    private void searchCustomer() {
        String orderId = txtOrderId.getText().trim();
        if (orderId.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Order ID cannot be empty", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Customer customer = customerCollection.searchCustomerByOrderId(orderId);
        if (customer != null) {
            lblPhoneNumberValue.setText(customer.getPhoneNumber());
            lblSizeValue.setText(customer.getSize());
            lblQtyValue.setText(String.valueOf(customer.getQty()));
            lblAmountValue.setText(String.valueOf(customer.getAmount()));
            lblStatusValue.setText(customer.getStatus());
        } else {
            JOptionPane.showMessageDialog(this, "Customer not found", "Error", JOptionPane.ERROR_MESSAGE);
            clearCustomerDetails();
        }
    }

    // Method to delete a customer
    private void deleteCustomer() {
        String orderId = txtOrderId.getText().trim();
        if (orderId.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Order ID cannot be empty", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        boolean success = customerCollection.deleteCustomer(orderId);
        if (success) {
            JOptionPane.showMessageDialog(this, "Customer deleted successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
            clearCustomerDetails();
        } else {
            JOptionPane.showMessageDialog(this, "Customer not found", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Helper method to create labels for displaying customer details
    private JLabel createLabel(String text, int y) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("Arial", Font.BOLD, 15));
        label.setBounds(10, y, 130, 35);
        add(label);
        return label;
    }

    // Helper method to create value labels
    private JLabel createValueLabel(int y) {
        JLabel label = new JLabel("");
        label.setFont(new Font("Arial", Font.BOLD, 15));
        label.setBounds(130, y, 200, 35);
        add(label);
        return label;
    }

    // Helper method to clear displayed customer details
    private void clearCustomerDetails() {
        lblPhoneNumberValue.setText("");
        lblSizeValue.setText("");
        lblQtyValue.setText("");
        lblAmountValue.setText("");
        lblStatusValue.setText("");
    }
}
