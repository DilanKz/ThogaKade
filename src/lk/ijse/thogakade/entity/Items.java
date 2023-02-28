package lk.ijse.thogakade.entity;


import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Items {
    @Id
    @GenericGenerator(name = "ItemID", strategy = "lk.ijse.thogakade.ids.ItemIDGenerator")
    @GeneratedValue(generator = "ItemID")
    @Column(name = "item_id",length = 50)
    private String id;

    @Column(name = "item_name")
    private String name;

    @Column(name = "item_price")
    private double price;

    @Column(name = "item_qty")
    private int qty;

    public Items() {
    }

    public Items(String id, String name, double price, int qty) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.qty = qty;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    @Override
    public String toString() {
        return "Items{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", qty=" + qty +
                '}';
    }
}
