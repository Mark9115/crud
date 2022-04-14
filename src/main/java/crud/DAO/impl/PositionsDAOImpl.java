package crud.DAO.impl;

import crud.DAO.PositionsDAO;
import crud.entity.Positions;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class PositionsDAOImpl implements PositionsDAO {

    @Override
    public Positions find(String s){
        return null;
    }

    /**
     * Selecting all the information from Positions table
     */
    @Override
    public List<Positions> findAll(){

        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        @SuppressWarnings("unchecked")
        List<Positions> positions = (List<Positions>) session.createQuery("FROM Positions ").list();

        transaction.commit();
        session.close();

        return positions;
    }

    @Override
    public void save(Positions o){
    }

    @Override
    public void update(Positions o){
    }

    @Override
    public void delete(Positions o){
    }
}
