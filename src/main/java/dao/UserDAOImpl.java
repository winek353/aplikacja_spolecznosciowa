package dao;

import model.FriendRequest;
import model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import validator.PasswordHasher;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.List;

public class UserDAOImpl implements UserDAO {
    @Autowired
    private PasswordHasher passwordHasher;

    @Autowired
    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void save(User user) {

        try {
            user.setPassword(passwordHasher.generatePasswordHash(user.getPassword()));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
        }

        Session session = this.sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        session.persist(user);
        tx.commit();
        session.close();
    }

    @Override
    public void update(User user) {
        Session session = this.sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        session.update(user);
        tx.commit();
        session.close();
    }

    @Override
    public User findByUsername(String username) {
        Session session = this.sessionFactory.openSession();
        List<User> user = session
                .createQuery("from User up where up.username='" + username + "'")
                .list();
        session.close();
        if(user.isEmpty())
            return null;
        else
            return user.get(0);
    }

    @Override
    public User findByEmail(String email) {
        Session session = this.sessionFactory.openSession();
        List<User> user = session
                .createQuery("from User up where up.email='" +email + "'")
                .list();
        session.close();
        if(user.isEmpty())
            return null;
        else
            return user.get(0);
    }

    @Override
    public User findById(int id) {
        Session session = this.sessionFactory.openSession();
        User user = (User) session.get(User.class, id);
        session.close();
        return user;
    }

    @Override
    public void addFriend(User user, User friend) {
        Session session = this.sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        user.getFriends().add(friend);
        friend.getFriends().add(user);
        session.update(user);
        session.update(friend);
        tx.commit();
        session.close();
    }

    @Override
    public boolean isUsernameInDatabase(String username) {
        Session session = this.sessionFactory.openSession();
        List<User> user = session
                .createQuery("from User up where up.username='" + username + "'")
                .list();
        session.close();
        if(user.isEmpty())
            return false;
        else
            return true;
    }

    @Override
    public boolean areFriends(int id1, int id2) {
        Session session = this.sessionFactory.openSession();
        User user1 = (User) session.get(User.class, id1);
        User user2 = (User) session.get(User.class, id2);
        session.close();
        if(user1.getFriends().contains(user2))
            return true;
        else
            return false;
    }

    @Override
    public boolean isFriendRequestSent(int userId, int friendId) {
        Session session = this.sessionFactory.openSession();
        User user = (User) session.get(User.class, userId);
        for(FriendRequest friendRequest :user.getFriendRequests()) {
            if(friendRequest.getRequesterId() == friendId)
                return true;
        }
        return false;
    }



}
