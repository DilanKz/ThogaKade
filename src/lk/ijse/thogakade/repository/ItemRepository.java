package lk.ijse.thogakade.repository;

import lk.ijse.thogakade.entity.Items;
import lk.ijse.thogakade.util.SessionFactoryConfiguration;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class ItemRepository {
    private  Session session=SessionFactoryConfiguration.getInstance().getSession();
    private Transaction transaction;

    public ItemRepository() {
    }

    public boolean addItem(Items Items){
        transaction = session.beginTransaction();
        try {
            session.save(Items);
            transaction.commit();
            session.close();
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
