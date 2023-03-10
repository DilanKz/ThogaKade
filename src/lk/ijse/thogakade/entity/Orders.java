package lk.ijse.thogakade.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;

@Entity
public class Orders {

    @Id
    @GenericGenerator(name = "orderID", strategy = "lk.ijse.thogakade.ids.OrdersIDGenerator")
    @GeneratedValue(generator = "orderID")
    @Column(name = "orderID",length = 50)
    private String id;

    @Column(name = "orderDate")
    private Date date;

    @Column(name = "price")
    private double price;

    @ManyToOne(targetEntity = Customer.class)
    @JoinColumn(name = "customerID" )
    private Customer customer;

    @OneToMany(mappedBy = "orders",targetEntity = OrderDetails.class)
    private List<OrderDetails> orderDetails;


    public Orders() {
    }

    public Orders(String id, Date date, double price) {
        this.id = id;
        this.date = date;
        this.price = price;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public double getCusID() {
        return price;
    }

    public void setCusID(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Orders{" +
                "id='" + id + '\'' +
                ", date=" + date +
                ", cusID='" + price + '\'' +
                '}';
    }
}
