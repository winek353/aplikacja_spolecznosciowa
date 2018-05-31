package dao;

import model.Event;
import model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;

public class EventDAOImpl implements EventDAO{
    @Autowired
    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void save(Event event, User host) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        host.getEvents().add(event);
        session.update(host);
        session.persist(event);
        tx.commit();
        session.close();
    }
}
