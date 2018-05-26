

import dao.UserDAO;
import model.User;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import validator.PasswordHasher;
import validator.UserLoginValidator;

@RunWith(MockitoJUnitRunner.class)
public class UserLoginValidatorTest {
    @Mock
    private UserDAO userDAO;

    @Mock
    private PasswordHasher passwordHasher;

    private UserLoginValidator userLoginValidator;

    @Before
    public void init(){
        userLoginValidator = new UserLoginValidator();
        userLoginValidator.setUserDAO(userDAO);
        userLoginValidator.setPasswordHasher(passwordHasher);
    }

    @Test
    public void correctData(){
        String username = "correctUsername";
        String email = "correctEmail";
        String password = "correctPassword";

        Mockito.when(userDAO.findByUsername(username)).thenReturn(new User(username, email, password));
        Mockito.when(passwordHasher.validatePassword(password, password)).thenReturn(true);

        Assert.assertTrue(userLoginValidator.validate(username, password));
    }

    @Test
    public void wrongPassword() throws Exception {
        String username = "correctUsername";
        String email = "correctEmail";
        String password = "wrongPassword";

        Mockito.when(userDAO.findByUsername(username)).thenReturn(new User(username, email, password));
        Mockito.when(passwordHasher.validatePassword(password, password)).thenReturn(false);

        Assert.assertFalse(userLoginValidator.validate(username, password));
    }

    @Test
    public void wrongUsername() throws Exception {
        String username = "wrongUsername";
        String email = "correctEmail";
        String password = "correctPassword";

        Mockito.when(userDAO.findByUsername(username)).thenReturn(null);
        Mockito.when(passwordHasher.validatePassword(password, password)).thenReturn(true);

        Assert.assertFalse(userLoginValidator.validate(username, password));
    }
}
