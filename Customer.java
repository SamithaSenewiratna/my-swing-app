public class Customer {
    private String orderId;
    private String phoneNumber;
    private String size;
    private int qty;
    private double amount;
     private String status;

    
  

    public Customer(String orderId, String phoneNumber, String size, int qty, double amount) {
        this.orderId = orderId;
        this.phoneNumber = phoneNumber;
        this.size = size;
        this.qty = qty;
        this.amount = amount;
    }

    public String getOrderId() {
        return orderId;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getSize() {
        return size;
    }

    public int getQty() {
        return qty;
    }

    public double getAmount() {
        return amount;
    }

  public String getStatus() {
        return status;
    }

    public void setViewCustomerValues(String phoneNumber, int qty, double amount) {
        this.phoneNumber = phoneNumber;
        this.qty = qty;
        this.amount = amount;
    }
    
    public void setStatus(String status) {
    this.status = status; 
}





    
}
