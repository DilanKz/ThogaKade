package lk.ijse.thogakade.entity;


import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;

@Entity
public class Customer {
    @Id
    @GenericGenerator(name = "customerID", strategy = "lk.ijse.thogakade.ids.CustomerIDGenerator")
    @GeneratedValue(generator = "customerID")
    @Column(name = "customerID",length = 50)
    private String id;

    @Column(name = "customerName")
    private String name;

    @Column(name = "customerAddress")
    private String address;

    @Column(name = "customerSalary")
    private double salary;

    @OneToMany(mappedBy = "customer",targetEntity = Orders.class)
    private List<Orders> ordersList;

    public Customer() {
    }

    public Customer(String id, String name, String address, double salary) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.salary = salary;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", salary=" + salary +
                '}';
    }
}
