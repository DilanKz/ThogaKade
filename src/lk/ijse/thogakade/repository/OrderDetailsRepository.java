package lk.ijse.thogakade.repository;

import lk.ijse.thogakade.entity.OrderDetails;
import lk.ijse.thogakade.entity.Orders;
import lk.ijse.thogakade.util.SessionFactoryConfiguration;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class OrderDetailsRepository {
    private Transaction transaction;

    public boolean addOrderDetails(OrderDetails orderDetails){
        Session session= SessionFactoryConfiguration.getInstance().getSession();
        transaction = session.beginTransaction();
        try {
            session.save(orderDetails);
            transaction.commit();
            session.close();
            return true;
        }catch (Exception e){
            transaction.rollback();
            e.printStackTrace();
        }
        return false;
    }
}
