package crud.DAO.impl;

import crud.DAO.WorkersDAO;
import crud.entity.Projects;
import crud.entity.Workers;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class WorkersDAOImpl implements WorkersDAO {
    private static SessionFactory sessionFactory;

    /**
     * Helps to find the correct worker by id
     */
    @Override
    public Workers find(Integer id) {
        sessionFactory = new Configuration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Workers developer = session.get(Workers.class, id);
        transaction.commit();
        session.close();
        return developer;
    }

    /**
     * Helps to find all workers from Workers table
     */
    @Override
    public List<Workers> findAll() {

        sessionFactory = new Configuration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        List<Workers> workers =
                session.createQuery("SELECT i FROM Workers i JOIN FETCH i.position ORDER BY i.id", Workers.class).getResultList();

        transaction.commit();
        session.close();

        return workers;
    }

    /**
     * Adds new Worker
     */
    @Override
    public void save(Workers o) {

        sessionFactory = new Configuration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        Workers worker = new Workers(o.getPosition(), o.getName(), o.getLast_name());
        session.save(worker);

        transaction.commit();
        session.close();

    }

    /**
     * Method with transaction which inserts into Workers and Projects_to_workers tables at the same time
     */
    public void saveReturnVal(Workers worker, Projects project) {
        sessionFactory = new Configuration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        Workers newWorker = new Workers(worker.getPosition(), worker.getName(), worker.getLast_name());
        Projects newProject = new Projects();
        newProject.setId(project.getId());

        Set<Projects> projectsList = new HashSet<>();
        projectsList.add(newProject);

        newWorker.setProjects(projectsList);
        session.save(newWorker);

        transaction.commit();
        session.close();

    }

    /**
     * Edits the Worker by id
     */
    @Override
    public void update(Workers o) {
        sessionFactory = new Configuration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        Workers worker = session.get(Workers.class, o.getId());
        worker.setName(o.getName());
        worker.setLast_name(o.getLast_name());
        worker.setPosition(o.getPosition());

        session.update(worker);

        transaction.commit();
        session.close();
    }

    /**
     * Deletes the Worker by id
     */
    @Override
    public void delete(Workers o) {
        sessionFactory = new Configuration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        Workers worker = session.get(Workers.class, o.getId());
        session.delete(worker);

        transaction.commit();
        session.close();

    }
}
