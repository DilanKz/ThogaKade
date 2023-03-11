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
    private String date;


    @ManyToOne(targetEntity = Customer.class)
    @JoinColumn(name = "customerID" )
    private Customer customer;

    @OneToMany(mappedBy = "orders",targetEntity = OrderDetails.class)
    private List<OrderDetails> orderDetails;


    public Orders() {
    }

    public Orders(String id, String date, double price) {
        this.id = id;
        this.date = date;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }


    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public List<OrderDetails> getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(List<OrderDetails> orderDetails) {
        this.orderDetails = orderDetails;
    }

    @Override
    public String toString() {
        return "Orders{" +
                "id='" + id + '\'' +
                ", date=" + date + '\'' +
                '}';
    }
}
