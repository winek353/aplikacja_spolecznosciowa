import dao.UserDAO;
import model.FriendRequest;
import model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.validation.BindingResult;
import validator.FriendRequestValidator;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class FriendRequestValidatorTest {
    @Mock
    private UserDAO userDAO;

    @Mock
    private BindingResult bindingResult;

    FriendRequestValidator friendRequestValidator;

    public FriendRequestValidatorTest() {
        this.friendRequestValidator = new FriendRequestValidator();
    }

    @Test
    public void nullRecipient() throws Exception {
        friendRequestValidator.setUserDAO(userDAO);

        FriendRequest friendRequest = new FriendRequest(0, null, "requesterUsername");

        friendRequestValidator.validate(friendRequest, bindingResult);

        verify(bindingResult, times(1))
                .rejectValue( "recipient","null recipient", "friend does not exist");
    }

    @Test
    public void friendWithSelf() throws Exception {
        friendRequestValidator.setUserDAO(userDAO);

        User recipient = new User("user", "email@gmail.com",
                "password");
        FriendRequest friendRequest = new FriendRequest(recipient.getId(), recipient, recipient.getUsername());

        friendRequestValidator.validate(friendRequest, bindingResult);

        verify(bindingResult, times(1))
                .rejectValue( "recipient","friend with self", "cannot send friend request to yourself");
    }

    @Test
    public void alreadyFriends() throws Exception {
        friendRequestValidator.setUserDAO(userDAO);

        User recipient = new User("user1", "email1@gmail.com",
                "password");
        User requester = new User("user2", "email2@gmail.com",
                "password");
        requester.setId(1);

        FriendRequest friendRequest = new FriendRequest(requester.getId(), recipient, requester.getUsername());

        Mockito.when(userDAO.areFriends(friendRequest.getRequesterId(), friendRequest.getRecipient().getId()))
                .thenReturn(true);

        friendRequestValidator.validate(friendRequest, bindingResult);

        verify(bindingResult, times(1))
                .rejectValue( "recipient","already friends", "you are already friend with " +
                        friendRequest.getRecipient().getUsername());
    }

    @Test
    public void alreadySent() throws Exception {
        friendRequestValidator.setUserDAO(userDAO);

        User recipient = new User("user1", "email1@gmail.com",
                "password");
        User requester = new User("user2", "email2@gmail.com",
                "password");
        requester.setId(1);

        FriendRequest friendRequest = new FriendRequest(requester.getId(), recipient, requester.getUsername());

        Mockito.when(userDAO.isFriendRequestSent(friendRequest.getRecipient().getId(), friendRequest.getRequesterId()))
                .thenReturn(true);

        friendRequestValidator.validate(friendRequest, bindingResult);

        verify(bindingResult, times(1))
                .rejectValue( "recipient","already sent", "you already sent friend request to " +
                        friendRequest.getRecipient().getUsername());
    }
}
