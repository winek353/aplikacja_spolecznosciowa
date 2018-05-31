package controller;

import dao.UserDAO;
import model.User;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

@ContextConfiguration(locations = {"classpath:/spring-configuration-test.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class ProfileTest {

    MockHttpSession session;

    @Mock
    private UserDAO userDAO;

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @Test
    public void profileInfoPage() throws Exception {
        //given
        session = new MockHttpSession();
        session.setAttribute("loggedInUserId", 1);

        Mockito.when(userDAO.findById(1)).thenReturn(new User());

        ProfileController profileController = new ProfileController();
        profileController.setUserDAO(userDAO);

        MockMvc mockMvc = standaloneSetup(profileController).build();

        //when then
        mockMvc.perform(get("/profile")
                .session(session))
                .andExpect(view().name("profileInfo"));
    }

    @Test
    public void changeProfile() throws Exception {
        //given
        session = new MockHttpSession();
        session.setAttribute("loggedInUserId", 1);

        Mockito.when(userDAO.findById(1)).thenReturn(new User());

        ProfileController profileController = new ProfileController();
        profileController.setUserDAO(userDAO);

        MockMvc mockMvc = standaloneSetup(profileController).build();

        //when then
        mockMvc.perform(post("/changeProfile")
                .session(session))
                .andExpect(view().name("profileInfo"));

        Mockito.verify(userDAO, Mockito.times(1)).update(Mockito.any());
    }
}
