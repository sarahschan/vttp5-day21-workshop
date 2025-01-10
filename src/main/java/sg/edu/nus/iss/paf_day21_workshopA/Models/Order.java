package sg.edu.nus.iss.paf_day21_workshopA.Models;

import org.springframework.jdbc.support.rowset.SqlRowSet;

public class Order {
    
    private int orderId;
    private int customerId;
    private String orderDate;
    private String shippedDate;
    private float shippingFee;
    private String paymentType;
    private int statusId;

    public Order() {
    }

    public static Order toOrder(SqlRowSet rs) {
        Order order = new Order();
        order.setOrderId(rs.getInt("id"));
        order.setCustomerId(rs.getInt("customer_id"));
        order.setOrderDate(rs.getString("order_date") == null ? "" : rs.getString("order_date"));
        order.setShippedDate(rs.getString("shipped_date") == null ? "" : rs.getString("shipped_date"));
        order.setShippingFee(rs.getFloat("shipping_fee"));
        order.setPaymentType(rs.getString("payment_type") == null ? "" : rs.getString("payment_type"));
        order.setStatusId(rs.getInt("status_id"));

        return order;
    }


    @Override
    public String toString() {
        return "Order [orderId=" + orderId + ", customerId=" + customerId + ", shippingFee=" + shippingFee
                + ", paymentType=" + paymentType + ", statusId=" + statusId + "]";
    }


    public int getOrderId() {
        return orderId;
    }
    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }
    public int getCustomerId() {
        return customerId;
    }
    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }
    public String getOrderDate() {
        return orderDate;
    }
    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }
    public String getShippedDate() {
        return shippedDate;
    }
    public void setShippedDate(String shippedDate) {
        this.shippedDate = shippedDate;
    }
    public float getShippingFee() {
        return shippingFee;
    }
    public void setShippingFee(float shippingFee) {
        this.shippingFee = shippingFee;
    }
    public String getPaymentType() {
        return paymentType;
    }
    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }
    public int getStatusId() {
        return statusId;
    }
    public void setStatusId(int statusId) {
        this.statusId = statusId;
    }
    
}
