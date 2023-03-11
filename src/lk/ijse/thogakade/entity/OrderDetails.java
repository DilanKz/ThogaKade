package lk.ijse.thogakade.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class OrderDetails implements Serializable {

    @Id
    @ManyToOne(targetEntity = Orders.class)
    @JoinColumn(name = "orderID")
    private Orders orders;

    @Id
    @ManyToOne(targetEntity = Items.class)
    @JoinColumn(name = "item_id")
    private Items items;

    @Column(name = "Qty")
    private int qty;

    @Column(name = "Price")
    private double price;

    public OrderDetails() {
    }

    public OrderDetails(Orders orders, Items items, int qty, double price) {
        this.orders = orders;
        this.items = items;
        this.qty = qty;
        this.price = price;
    }

    public Orders getOrders() {
        return orders;
    }

    public void setOrders(Orders orders) {
        this.orders = orders;
    }

    public Items getItems() {
        return items;
    }

    public void setItems(Items items) {
        this.items = items;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
