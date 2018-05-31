package model;

import javax.persistence.*;
import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="event")
public class Event {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="event_id")
    private int id;

    @Column(name="event_name")
    private String eventName;

    @Column(name="host")
    private String hostUsername;

    @Column(name="start_date")
    private Date startDate;//Date z sql.date

    @ManyToMany(mappedBy = "events")
    private Set<User> userProfiles = new HashSet<>();

    public Event() {
    }

    public Event(String eventName, String hostUsername, Date startDate) {
        this.eventName = eventName;
        this.hostUsername = hostUsername;
        this.startDate = startDate;
    }
}
