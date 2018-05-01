package validator;

import dao.UserDAO;
import model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;


@Component
public class UserRegisterValidator implements Validator {

    @Autowired
    private UserDAO userDAO;

    public void setUserDAO(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    public boolean supports(Class aClass) {
        return User.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        User user = (User) o;

        if(user.getUsername().isEmpty()){
            errors.rejectValue("username", "NotEmpty", "username cannot be empty");
        }
        if(user.getEmail().isEmpty()){
            errors.rejectValue("email", "NotEmpty", "email cannot be empty");
        }
//        if(user.getUsername().trim().isEmpty()){
//            errors.rejectValue("username", "NotWhitespace", "username cannot white space");
//        }
//        if(user.getEmail().trim().isEmpty()){
//            errors.rejectValue("username", "NotWhitespace", "email cannot contain white space");
//        }
        if (userDAO.findByUsername(user.getUsername()) != null) {
            errors.rejectValue("username",
                    "Duplicate.userForm.username", "username already taken" );
        }
        if (userDAO.findByEmail(user.getEmail()) != null) {
            errors.rejectValue("email",
                    "Duplicate.userForm.username", "email already in use" );
        }
        if (user.getPassword().length() < 8) {
            errors.rejectValue("password", "Size.userForm.password",
                    "password must be at least 8 characters ");
        }
        if (!user.getConfirmedPassword().equals(user.getPassword())) {
            errors.rejectValue("confirmedPassword", "Diff.userForm.confirmedPassword",
                    "Passwords are not the same");
        }
    }
}
