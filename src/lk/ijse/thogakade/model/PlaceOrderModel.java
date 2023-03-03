package lk.ijse.thogakade.model;


import lk.ijse.thogakade.db.DBConnection;
import lk.ijse.thogakade.to.Order;
import lk.ijse.thogakade.to.PlaceOrder;

import java.sql.SQLException;
import java.time.LocalDate;

public class PlaceOrderModel {

    public static boolean placeOrder(PlaceOrder placeOrder) throws SQLException, ClassNotFoundException {
        ItemModel itemModel=new ItemModel();
        try {
            DBConnection.getInstance().getConnection().setAutoCommit(false);
            boolean isOrderAdded = OrderModel.save(new Order(placeOrder.getOrderId(), LocalDate.now(), placeOrder.getCustomerId()));
            if (isOrderAdded) {
                boolean isUpdated = itemModel.updateQty(placeOrder.getOrderDetails());
                if (isUpdated) {
                    boolean isOrderDetailAdded = OrderDetailModel.saveOrderDetails(placeOrder.getOrderDetails());
                    if (isOrderDetailAdded) {
                        DBConnection.getInstance().getConnection().commit();
                        return true;
                    }
                }
            }
            DBConnection.getInstance().getConnection().rollback();
            return false;
        } finally {
            DBConnection.getInstance().getConnection().setAutoCommit(true);
        }
    }
}
