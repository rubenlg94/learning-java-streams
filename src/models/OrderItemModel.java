package models;

public class OrderItemModel {

    private int orderId;
    private int productId;
    private int quantity;

    public OrderItemModel(int orderId, int productId, int quantity) {
        this.setOrderId(orderId);
        this.setProductId(productId);
        this.setQuantity(quantity);
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
