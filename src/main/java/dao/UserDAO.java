package dao;

import model.User;

public interface UserDAO {
    void save(User user);
    User findByUsername(String username);
    User findByEmail(String email);
}
