package lk.ijse.thogakade.repository;

import lk.ijse.thogakade.entity.OrderDetails;
import lk.ijse.thogakade.entity.Orders;
import lk.ijse.thogakade.util.SessionFactoryConfiguration;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.io.Serializable;
import java.util.ArrayList;

public class OrdersRepository {
    private Session session= SessionFactoryConfiguration.getInstance().getSession();
    private Transaction transaction;

    public boolean addOrder(Orders orders, ArrayList<OrderDetails> orderDetailsList){
        transaction = session.beginTransaction();
        OrderDetailsRepository orderDetailsRepository = new OrderDetailsRepository();
        try {
            String save = (String) session.save(orders);
            transaction.commit();
            session.close();

            if (save.equals(" ")){

                for (OrderDetails orderDetails:orderDetailsList){
                    if (!orderDetailsRepository.addOrderDetails(orderDetails)){
                        return false;
                    }
                }
                return true;
            }
        }catch (Exception e){
            transaction.rollback();
            e.printStackTrace();
        }
        return false;
    }

}
