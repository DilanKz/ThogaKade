package lk.ijse.thogakade.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class OrderDetails {

    @Column(name = "orderID")
    private String oId;

    @Column(name = "itemID")
    private String itemID;

    @Column(name = "unitPrice")
    private String unitPrice;

    @Column(name = "Qty")
    private String qty;

    public OrderDetails() {
    }

    public OrderDetails(String oId, String itemID, String unitPrice, String qty) {
        this.oId = oId;
        this.itemID = itemID;
        this.unitPrice = unitPrice;
        this.qty = qty;
    }

    public String getoId() {
        return oId;
    }

    public void setoId(String oId) {
        this.oId = oId;
    }

    public String getItemID() {
        return itemID;
    }

    public void setItemID(String itemID) {
        this.itemID = itemID;
    }

    public String getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(String unitPrice) {
        this.unitPrice = unitPrice;
    }

    public String getQty() {
        return qty;
    }

    public void setQty(String qty) {
        this.qty = qty;
    }

    @Override
    public String toString() {
        return "OrderDetails{" +
                "oId='" + oId + '\'' +
                ", itemID='" + itemID + '\'' +
                ", unitPrice='" + unitPrice + '\'' +
                ", qty='" + qty + '\'' +
                '}';
    }
}
