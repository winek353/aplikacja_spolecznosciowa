package dao;

import model.Event;
import model.User;

public interface EventDAO{
    void save(Event event, User host);
}
