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

    @Column(name = "customerID")
    private String cusID;

    @ManyToOne
    @JoinColumn(name = "customerID")
    private Customer customer;

    @OneToMany(mappedBy = "orders")
    private List<OrderDetails> orderDetails;


    public Orders() {
    }

    public Orders(String id, Date date, String cusID) {
        this.id = id;
        this.date = date;
        this.cusID = cusID;
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

    public String getCusID() {
        return cusID;
    }

    public void setCusID(String cusID) {
        this.cusID = cusID;
    }

    @Override
    public String toString() {
        return "Orders{" +
                "id='" + id + '\'' +
                ", date=" + date +
                ", cusID='" + cusID + '\'' +
                '}';
    }
}
