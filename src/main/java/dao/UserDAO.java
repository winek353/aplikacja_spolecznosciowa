package dao;

import model.User;

public interface UserDAO {
    void save(User user);
    void update(User user);
    User findByUsername(String username);
    User findByEmail(String email);
    User findById(int id);
    void addFriend(User user, User friend);
    boolean isUsernameInDatabase(String username);
}
