package lk.ijse.thogakade.repository;

import lk.ijse.thogakade.entity.Customer;
import lk.ijse.thogakade.entity.Items;
import lk.ijse.thogakade.util.SessionFactoryConfiguration;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;

public class ItemRepository {
    private  Session session=SessionFactoryConfiguration.getInstance().getSession();
    private Transaction transaction;

    public ItemRepository() {
    }

    public List<Items> getAll(){
        try{
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Items> customerCriteriaQuery = builder.createQuery(Items.class);
            customerCriteriaQuery.from(Items.class);

            return session.createQuery(customerCriteriaQuery).getResultList();

        }catch (Exception e){
            e.printStackTrace();
        }

        return null;
    }

    public boolean addItem(Items Items){
        transaction = session.beginTransaction();
        try {
            session.save(Items);
            transaction.commit();
            session.close();

            return true;
        }catch (Exception e){
            transaction.rollback();
            e.printStackTrace();
        }
        return false;
    }

    public boolean updateItem(Items Items){
        transaction = session.beginTransaction();

        try {
            session.update(Items);
            transaction.commit();
            session.close();
            return true;
        }catch (Exception e){
            transaction.rollback();
            e.printStackTrace();
        }

        return false;
    }

    public boolean deleteItem(Items Items){
        transaction = session.beginTransaction();

        try {
            session.delete(Items);
            transaction.commit();
            session.close();
            return true;
        }catch (Exception e){
            transaction.rollback();
            e.printStackTrace();
        }

        return false;
    }

    public Items getItem(String id){
        try{
            return session.get(Items.class, id);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
