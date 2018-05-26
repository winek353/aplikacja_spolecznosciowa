

import controller.LoginController;
import dao.UserDAO;
import model.User;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import validator.UserLoginValidator;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

@ContextConfiguration(locations = {"classpath:/spring-configuration-test.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class LoginTest {
    @Mock
    private UserDAO userDAO;

    @Mock
    private UserLoginValidator userLoginValidator;

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @Test
    public void loginPage() throws Exception {
        LoginController loginController = new LoginController();
        MockMvc mockMvc = standaloneSetup(loginController).build();
        mockMvc.perform(get("/LoginForm"))
                .andExpect(view().name("login"));
    }

    @Test
    public void invalidUsernameOrPassword() throws Exception {
        String username = "username";
        String password = "password";

        LoginController loginController = new LoginController();
        loginController.setUserDAO(userDAO);
        loginController.setUserLoginValidator(userLoginValidator);

        Mockito.when(userDAO.findByUsername(username)).thenReturn(null);
        Mockito.when(userLoginValidator.validate(username,password)).thenReturn(false);

        MockMvc mockMvc = standaloneSetup(loginController).build();
        mockMvc.perform(post("/submitLoginForm")
                .param("username", username)
                .param("password", password))
                .andExpect(view().name("login"));
    }
}
