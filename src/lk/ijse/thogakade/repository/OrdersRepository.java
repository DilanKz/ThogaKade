package lk.ijse.thogakade.repository;

import lk.ijse.thogakade.entity.Customer;
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
        OrderDetailsRepository orderDetailsRepository = new OrderDetailsRepository();
            saveOrder(orders);


            if (true){

                for (OrderDetails orderDetails:orderDetailsList){

                    if (!orderDetailsRepository.addOrderDetails(orderDetails)){
                        return false;
                    }
                }
                return true;
            }
        return false;
    }

    public String saveOrder(Orders orders){
        try {
            transaction = session.beginTransaction();
            String save = (String) session.save(orders);
            transaction.commit();
            session.close();
            return save;
        }catch (Exception e){
            transaction.rollback();
            e.printStackTrace();
        }
        return null;
    }

    public Orders getOrder(String id){
        transaction = session.beginTransaction();
        try {
            return session.get(Orders.class, id);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

}
