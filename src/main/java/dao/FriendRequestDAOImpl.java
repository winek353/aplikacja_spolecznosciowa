package dao;

import model.FriendRequest;
import model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;

public class FriendRequestDAOImpl implements FriendRequestDAO{
    @Autowired
    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void save(FriendRequest friendRequest) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        session.persist(friendRequest);
        tx.commit();
        session.close();
    }

    @Override
    public FriendRequest getFriendRequest(int friendRequestId) {
        Session session = sessionFactory.openSession();
        FriendRequest friendRequest = (FriendRequest) session.get(FriendRequest.class, friendRequestId);
        session.close();
        return friendRequest;
    }

    @Override
    public void delete(FriendRequest friendRequest) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        session.delete(friendRequest);
        tx.commit();
        session.close();
    }
}
