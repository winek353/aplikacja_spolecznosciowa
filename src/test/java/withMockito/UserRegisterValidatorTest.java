package withMockito;

import dao.UserDAO;
import model.User;
import org.junit.Before;
import org.junit.Test;

import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.validation.BindingResult;
import validator.UserRegisterValidator;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class UserRegisterValidatorTest {
    @Mock
    private UserDAO mockUserDAO;

    @Mock
    private BindingResult mockBindingResult;

    private UserRegisterValidator userRegisterValidator;

    private User invalidUser;
    private User alreadyRegisteredUser;

    @Before
    public void init(){
        userRegisterValidator = new UserRegisterValidator();
        userRegisterValidator.setUserDAO(mockUserDAO);

        alreadyRegisteredUser = new User();
        alreadyRegisteredUser.setUsername("user1");
        alreadyRegisteredUser.setEmail("user1@gmail.com");
        alreadyRegisteredUser.setPassword("12345678");
        alreadyRegisteredUser.setConfirmedPassword("12345678");

        invalidUser = new User();

        when(mockUserDAO.findByUsername(alreadyRegisteredUser.getUsername())).thenReturn(alreadyRegisteredUser);
        when(mockUserDAO.findByEmail(alreadyRegisteredUser.getEmail())).thenReturn(alreadyRegisteredUser);
    }

    public void setInvalidUser(String username, String email, String password, String confirmedPassword){
        invalidUser.setUsername(username);
        invalidUser.setEmail(email);
        invalidUser.setPassword(password);
        invalidUser.setConfirmedPassword(confirmedPassword);
    }

    @Test
    public void emptyUsername() throws Exception {//nie działa
        setInvalidUser("", "correctEmail@gmail.com",
                "12345678", "12345678");

        userRegisterValidator.validate(invalidUser, mockBindingResult);

        verify(mockBindingResult, times(1))
                .rejectValue("username", "NotEmpty", "username cannot be empty");
    }

    @Test
    public void emptyEmail() throws Exception {//nie działa
        setInvalidUser("", "correctEmail@gmail.com",
                "12345678", "12345678");

        userRegisterValidator.validate(invalidUser, mockBindingResult);

        verify(mockBindingResult, times(1))
                .rejectValue("username", "NotEmpty", "username cannot be empty");
    }

    @Test
    public void usernameAlreadyTaken(){
        setInvalidUser(alreadyRegisteredUser.getUsername(), "correctEmail@gmail.com",
                "12345678", "12345678");

        userRegisterValidator.validate(invalidUser, mockBindingResult);

        verify(mockBindingResult, times(1))
                .rejectValue("username", "Duplicate.userForm.username",  "username already taken" );
    }

    @Test
    public void emailAlreadyInUse(){
        setInvalidUser("correctUsername", alreadyRegisteredUser.getEmail(),
                "12345678", "12345678");

        userRegisterValidator.validate(invalidUser, mockBindingResult);

        verify(mockBindingResult, times(1))
                .rejectValue("email", "Duplicate.userForm.username", "email already in use" );
    }

    @Test
    public void shortPassword(){
        setInvalidUser("correctUsername", "correctEmail@gmail.com",
                "1234567", "1234567");

        userRegisterValidator.validate(invalidUser, mockBindingResult);

        verify(mockBindingResult, times(1))
                .rejectValue("password", "Size.userForm.password", "password must be at least 8 characters ");
    }

    @Test
    public void wrongConfirmedPassword(){
        setInvalidUser("correctUsername", "correctEmail@gmail.com",
                "12345678", "123456789");

        userRegisterValidator.validate(invalidUser, mockBindingResult);

        verify(mockBindingResult, times(1))
                .rejectValue("confirmedPassword", "Diff.userForm.confirmedPassword", "Passwords are not the same");
    }

    @Test
    public void correctData(){
        setInvalidUser("correctUsername", "correctEmail@gmail.com",
                "12345678", "12345678");

        userRegisterValidator.validate(invalidUser, mockBindingResult);

        verifyZeroInteractions(mockBindingResult);
    }
}
