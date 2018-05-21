package validator;

import dao.UserDAO;
import model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserLoginValidator{

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private PasswordHasher passwordHasher;

    public void setUserDAO(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public void setPasswordHasher(PasswordHasher passwordHasher) {
        this.passwordHasher = passwordHasher;
    }

    public boolean validate(String username, String password) {
        User user = userDAO.findByUsername(username);

        if (user == null) {
            return false;
        }
        else if(!passwordHasher.validatePassword(password, user.getPassword())){
            return false;
        }
        return true;
    }
}
