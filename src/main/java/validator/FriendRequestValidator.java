package validator;

import dao.UserDAO;
import model.FriendRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class FriendRequestValidator  implements Validator {
    @Autowired
    UserDAO userDAO;

    public void setUserDAO(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return false;
    }

    @Override
    public void validate(Object o, Errors errors) {
        FriendRequest friendRequest = (FriendRequest) o;


        if(friendRequest.getRecipient() == null)
            errors.rejectValue( "recipient","null recipient", "friend does not exist");
        else if(friendRequest.getRecipient().getId() == friendRequest.getRequesterId())
            errors.rejectValue( "recipient","friend with self", "cannot send friend request to yourself");
        else if(userDAO.areFriends(friendRequest.getRequesterId(), friendRequest.getRecipient().getId()))
            errors.rejectValue( "recipient","already friends", "you are already friend with " +
                    friendRequest.getRecipient().getUsername());
        else if(userDAO.isFriendRequestSent(friendRequest.getRecipient().getId(), friendRequest.getRequesterId()))
            errors.rejectValue( "recipient","already sent", "you already sent friend request to " +
                    friendRequest.getRecipient().getUsername());
    }
}
