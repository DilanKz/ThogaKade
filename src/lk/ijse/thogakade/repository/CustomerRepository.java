package lk.ijse.thogakade.repository;

import lk.ijse.thogakade.entity.Customer;
import lk.ijse.thogakade.util.SessionFactoryConfiguration;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;

public class CustomerRepository {
    private  Session session= SessionFactoryConfiguration.getInstance().getSession();
    private Transaction transaction;

    public CustomerRepository() {
    }

    public List<Customer> getAll(){
        try{
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Customer> customerCriteriaQuery = builder.createQuery(Customer.class);
            customerCriteriaQuery.from(Customer.class);

            return session.createQuery(customerCriteriaQuery).getResultList();

        }catch (Exception e){
            e.printStackTrace();
        }

        return null;
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
    public List<String> getIds(){
        String hql = "SELECT id from Customer ";
        Query<String> query = session.createQuery(hql);
        List<String> results = query.list();
        return results;
    }
}
