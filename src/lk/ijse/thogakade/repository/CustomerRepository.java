package lk.ijse.thogakade.repository;

import lk.ijse.thogakade.entity.Customer;
import lk.ijse.thogakade.util.SessionFactoryConfiguration;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class CustomerRepository {
    private  Session session= SessionFactoryConfiguration.getInstance().getSession();
    private Transaction transaction;

    public CustomerRepository() {
    }

    public boolean addCustomer(Customer customer){
        transaction = session.beginTransaction();
        try {
            session.save(customer);
            transaction.commit();
            session.close();
            return true;
        }catch (Exception e){
            transaction.rollback();
            e.printStackTrace();
        }
        return false;
    }

    public boolean updateCustomer(Customer customer){
        transaction = session.beginTransaction();

        try {
            session.update(customer);
            transaction.commit();
            session.close();
            return true;
        }catch (Exception e){
            transaction.rollback();
            e.printStackTrace();
        }

        return false;
    }

    public boolean deleteCustomer(Customer customer){
        transaction = session.beginTransaction();

        try {
            session.delete(customer);
            transaction.commit();
            session.close();
            return true;
        }catch (Exception e){
            transaction.rollback();
            e.printStackTrace();
        }

        return false;
    }

    public Customer getCustomer(String id){
        try{
            return session.get(Customer.class, id);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
