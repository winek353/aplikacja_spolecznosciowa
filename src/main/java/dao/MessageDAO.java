package dao;

import model.Message;
import model.User;

public interface MessageDAO {
    void save(Message message, User recipient);
}
