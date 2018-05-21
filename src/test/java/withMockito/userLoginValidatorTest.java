package withMockito;

import dao.UserDAO;
import model.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import validator.PasswordHasher;
import validator.UserLoginValidator;

@RunWith(MockitoJUnitRunner.class)
public class userLoginValidatorTest {
    @Mock
    private UserDAO userDAO;

    @Mock
    private PasswordHasher passwordHasher;

    @Test
    public void correctData(){
        UserLoginValidator userLoginValidator = new UserLoginValidator();
        userLoginValidator.setUserDAO(userDAO);
        userLoginValidator.setPasswordHasher(passwordHasher);

        String username = "correctUsername";
        String email = "correctEmail";
        String password = "correctPassword";

        Mockito.when(userDAO.findByUsername(username)).thenReturn(new User(username, email, password));
        Mockito.when(passwordHasher.validatePassword(password, password)).thenReturn(true);

        Assert.assertTrue(userLoginValidator.validate(username, password));
    }
}
