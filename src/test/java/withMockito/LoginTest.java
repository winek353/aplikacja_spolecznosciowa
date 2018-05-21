package withMockito;

import controller.LoginController;
import controller.RegisterController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

@ContextConfiguration(locations = {"classpath:/spring-configuration-test.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class LoginTest {

    @Test
    public void loginPage() throws Exception {
        LoginController loginController = new LoginController();
        MockMvc mockMvc = standaloneSetup(loginController).build();
        mockMvc.perform(get("/LoginForm"))
                .andExpect(view().name("login"));
    }
}
