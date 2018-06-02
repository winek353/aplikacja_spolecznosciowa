package model;


import dao.FriendRequestDAO;
import dao.UserDAO;
import dao.UserDAOImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.context.support.XmlWebApplicationContext;

import javax.persistence.*;

@Entity
@Table(name = "friend_request")
public class FriendRequest {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="friend_request_id")
    private int id;

    @Column(name="requester_id")
    private int requesterId;

//	@Column(name="friend_request_status")
//	private FriendRequestStatus friendRequestStatus;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="user_id")
    private User recipient;

    @Column(name="requester_username")
    private String requesterUsername;

    public FriendRequest() {
    }

    public FriendRequest(String requesterUsername) {
        this.requesterUsername = requesterUsername;
    }

    public FriendRequest(int requesterId, User recipient, String requesterUsername) {
        this.requesterId = requesterId;
        this.recipient = recipient;
        this.requesterUsername = requesterUsername;
    }

    public String getRequesterUsername() {
        return requesterUsername;
    }

    public void setRequesterUsername(String requesterUsername) {
        this.requesterUsername = requesterUsername;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRequesterId() {
        return requesterId;
    }

    public void setRequesterId(int requesterId) {
        this.requesterId = requesterId;
    }

    public User getRecipient() {
        return recipient;
    }

    public void setRecipient(User recipient) {
        this.recipient = recipient;
    }
}
