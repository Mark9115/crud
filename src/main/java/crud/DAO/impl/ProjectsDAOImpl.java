package crud.DAO.impl;

import crud.DAO.ProjectsDAO;
import crud.entity.Projects;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class ProjectsDAOImpl implements ProjectsDAO {

    @Override
    public Projects find(String s){
        return null;
    }

    /**
     * Selecting all the information from Projects table
     */
    @Override
    public List<Projects> findAll(){

        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        @SuppressWarnings("unchecked")
        List<Projects> positions = (List<Projects>) session.createQuery("FROM Projects").list();

        transaction.commit();
        session.close();

        return positions;
    }

    @Override
    public void save(Projects o){
    }

    @Override
    public void update(Projects o){
    }

    @Override
    public void delete(Projects o){
    }
}
