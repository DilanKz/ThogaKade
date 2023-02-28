package lk.ijse.thogakade.entity;

import javax.persistence.Column;
import javax.persistence.Id;
import java.sql.Date;

public class Orders {

    @Id
    @Column(name = "orderID",length = 50)
    private String id;

    @Column(name = "orderDate")
    private Date date;

    @Column(name = "customerID")
    private String cusID;

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
