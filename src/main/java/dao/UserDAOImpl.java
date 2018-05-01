package dao;

import model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.List;

public class UserDAOImpl implements UserDAO {

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void save(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));

        Session session = this.sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        session.persist(user);
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

}
