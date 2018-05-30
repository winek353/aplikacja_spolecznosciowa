package dao;

import model.Message;
import model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;

public class MessageDAOImpl implements MessageDAO {
    @Autowired
    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void save(Message message, User recipient) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        recipient.getMessages().add(message);
        session.update(recipient);
        session.persist(message);
        tx.commit();
        session.close();
    }
}
