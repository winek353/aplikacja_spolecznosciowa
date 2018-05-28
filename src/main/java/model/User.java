package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="user_profile")
public class User {
	public User() {
	}

	public User(String username, String email, String password) {
		this.username = username;
		this.email = email;
		this.password = password;
	}

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="user_profile_id")
	private int id;

	@Column(name="username")
	private String username;

	@Column(name="email")
	private String email;

	@Column(name="password")
	private String password; //salt:hash

	@Transient
	private String confirmedPassword;

	//optional informations
	@Column(name="sex")
	private char sex;

	@Column(name="about_me")
	private String aboutMe;






	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getConfirmedPassword() {
		return confirmedPassword;
	}

	public void setConfirmedPassword(String confirmedPassword) {
		this.confirmedPassword = confirmedPassword;
	}

    public char getSex() {
        return sex;
    }

    public void setSex(char sex) {
        this.sex = sex;
    }

    public String getAboutMe() {
        return aboutMe;
    }

    public void setAboutMe(String aboutMe) {
        this.aboutMe = aboutMe;
    }

    @Override
	public String toString() {
		return "User{" +
				"id=" + id +
				", username='" + username + '\'' +
				", email='" + email + '\'' +
				", password='" + password + '\'' +
				", confirmedPassword='" + confirmedPassword + '\'' +
				'}';
	}

	//	//optional informations
//	@Column(name="sex")
//	private char sex;
//
//	@Column(name="about_me")
//	private String aboutMe;
//
//	//friend request
//	@OneToMany(fetch = FetchType.EAGER, mappedBy="recipient")
//    private Set<FriendRequest> friendRequests;
//
//
//	//friends
//	@ManyToMany(fetch = FetchType.EAGER, cascade= {CascadeType.ALL})
//	@JoinTable(name="user_colleague",
//		joinColumns={@JoinColumn(name="user_id")},
//		inverseJoinColumns={@JoinColumn(name="colleague_id")})
//	private Set<User> friends = new HashSet<User>();
//
//	//messages
//	@ManyToMany(fetch = FetchType.EAGER, cascade = { CascadeType.ALL })
//    @JoinTable(name = "user_profile_message",
//        joinColumns = { @JoinColumn(name = "user_profile_id") },
//        inverseJoinColumns = { @JoinColumn(name = "message_id") }
//    )
//    private Set<Message> messages = new HashSet<Message>();
//
//	//events
//	@ManyToMany(fetch = FetchType.EAGER, cascade = { CascadeType.ALL })
//    @JoinTable(name = "user_profile_event",
//        joinColumns = { @JoinColumn(name = "user_profile_id") },
//        inverseJoinColumns = { @JoinColumn(name = "event_id") }
//    )
//
//
//
//    private Set<Event> events = new HashSet<>();
//
//	public User()  {
//
//	}
//
//	public User(String userName, String email, String password)  {
//		this.userName = userName;
//		this.email = email;
//		try {
//			this.password = PasswordHasher.generatePasswordHash(password);
//		} catch (NoSuchAlgorithmException e) {
//			e.printStackTrace();
//		} catch (InvalidKeySpecException e) {
//			e.printStackTrace();
//		}
//	}
//
//	public  FriendRequest getFriendRequest (int requesterId) {
//		for(FriendRequest friendRequest : friendRequests) {
//			if(friendRequest.getRequesterId() == requesterId)
//				return friendRequest;
//		}
//		return null; //exception???
//	}
//
//	public Set<Event> getEvents() {
//		return events;
//	}
//
//	private static HibernateFacade<User> hibernateFacade
//		= new HibernateFacade<>(User.class);
//	@Transient
//	public static HibernateFacade<User> getHibernateFacade() {
//		//return new HibernateFacade<>(User.class);
//		return hibernateFacade;
//	}
//
//	public void setEvents(Set<Event> events) {
//		this.events = events;
//	}
//
//	public Set<FriendRequest> getFriendRequests() {
//		return friendRequests;
//	}
//
//	public char getSex() {
//		return sex;
//	}
//
//	public void setSex(char sex) {
//		this.sex = sex;
//	}
//
//	public String getAboutMe() {
//		return aboutMe;
//	}
//
//	public void setAboutMe(String aboutMe) {
//		this.aboutMe = aboutMe;
//	}
//
//	public void setFriendRequests(Set<FriendRequest> friendRequests) {
//		this.friendRequests = friendRequests;
//	}
//
//	public Set<User> getFriends() {
//		return friends;
//	}
//
//	public Set<Message> getMessages() {
//		return messages;
//	}
//
//	public void setMessages(Set<Message> messages) {
//		this.messages = messages;
//	}
//
//	public void setfriendss(Set<User> friends) {
//		this.friends = friends;
//	}
//
//	public int getId() {
//		return id;
//	}
//
//	public void setId(int id) {
//		this.id = id;
//	}
//
//	public String getUserName() {
//		return userName;
//	}
//
//	public void setUserName(String userName) {
//		this.userName = userName;
//	}
//
//	public String getEmail() {
//		return email;
//	}
//
//	public void setEmail(String email) {
//		this.email = email;
//	}
////
////	public void updateInfo(User updatedUserProfile) {
////		this.sex = updatedUserProfile.sex;
////	}
////
//	public boolean isFriendRequestSent(int requesterId) {
//
//		for(FriendRequest friendRequest : this.friendRequests) {
//			if(friendRequest.getRequesterId() == requesterId)
//				return true;
//		}
//		return false;
//	}
//
//	public String getPassword() {
//		return password;
//	}
//
//	public void setPassword(String password) {
//		this.password = password;
//	}
//
//	@Override
//	public String toString() {
//		return "User [id=" + id + ", userName=" + userName + ", email=" + email + "]";
//	}
//
//	public static boolean isEmailInDatabase (String email) {
//		SessionFactory factory = new Configuration()
//				.configure("hibernate.cfg.xml")
//				.addAnnotatedClass(User.class)
//				.buildSessionFactory();
//		Session session = factory.getCurrentSession();
//		boolean result = false;
//		try {
//		session.beginTransaction();
//
//		result = !session.createQuery("from User up where up.email='" +email + "'").list().isEmpty();
//
//		session.getTransaction().commit();
//		}finally {
//			factory.close();
//		}
//		return result;
//	}
//	public static boolean isUserNameInDatabase (String userName) {
//		if(userName == null)
//			return false;
//
//		SessionFactory factory = new Configuration()
//				.configure("hibernate.cfg.xml")
//				.addAnnotatedClass(User.class)
//				.buildSessionFactory();
//		Session session = factory.getCurrentSession();
//		boolean result = false;
//		try {
//		session.beginTransaction();
//
//
//		result = !session.createQuery("from User up where up.userName='" + userName + "'").list().isEmpty();
//
//		session.getTransaction().commit();
//		}finally {
//			factory.close();
//		}
//		return result;
//	}
//	public boolean isFriend (int friendId) {
//		SessionFactory factory = new Configuration()
//				.configure("hibernate.cfg.xml")
//				.addAnnotatedClass(User.class)
//				.buildSessionFactory();
//		Session session = factory.getCurrentSession();
//		boolean result = false;
//		try {
//		session.beginTransaction();
//
//		User userProfile = session.get(User.class, this.getId());
//		User friendProfile = session.get(User.class, friendId);
//		result = userProfile.friends.contains(friendProfile);
//
//		session.getTransaction().commit();
//		}finally {
//			factory.close();
//		}
//		return result;
//	}
//
//	public void addFriend (User friendProfile) {
//		SessionFactory factory = new Configuration()
//				.configure("hibernate.cfg.xml")
//				.addAnnotatedClass(User.class)
//				.buildSessionFactory();
//
//		Session session = factory.getCurrentSession();
//		try {
//			session.beginTransaction();
//
//			User me = session.get(User.class, this.getId());
//			User friend = session.get(User.class, friendProfile.getId());
//
//			 me.getFriends().add(friend);
//
//			session.getTransaction().commit();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		finally {
//			factory.close();
//		}
//	}
//
//	public static User getUserProfileFromDatabase (String userName) {
//		SessionFactory factory = new Configuration()
//				.configure("hibernate.cfg.xml")
//				.addAnnotatedClass(User.class)
//				.buildSessionFactory();
//		Session session = factory.getCurrentSession();
//		List <User> profile;
//		try {
//		session.beginTransaction();
//
//		profile = session.createQuery("from User up where up.userName='" + userName + "'").list();
//
//		session.getTransaction().commit();
//		}finally {
//			factory.close();
//		}
//		if(profile.isEmpty())//do przerobienia
//			return null;
//		else {
//			return profile.get(0);
//		}
//	}
//
//	public void updateProfileInfoInDatabase () {
//		SessionFactory factory = new Configuration()
//				.configure("hibernate.cfg.xml")
//				.addAnnotatedClass(User.class)
//				.buildSessionFactory();
//
//		Session session = factory.getCurrentSession();
//		try {
//			session.beginTransaction();
//
//			User userProfileToUpdate = session.get(User.class, this.getId());
//
//			if(this.sex == 'm' || this.sex == 'f')
//				userProfileToUpdate.sex = this.sex;
//			if(this.aboutMe != null && this.aboutMe != "")
//				userProfileToUpdate.aboutMe = this.aboutMe;
//
//			session.getTransaction().commit();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		finally {
//			factory.close();
//		}
//	}
}
