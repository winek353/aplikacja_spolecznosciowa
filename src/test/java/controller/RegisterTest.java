package controller;

import static org.mockito.Mockito.mock;
import static
        org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static
        org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static
        org.springframework.test.web.servlet.setup.MockMvcBuilders.*;

import controller.RegisterController;
import dao.UserDAO;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import validator.UserRegisterValidator;

@ContextConfiguration(locations = {"classpath:/spring-configuration-test.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class RegisterTest {
    @Mock
    private UserDAO userDAO;

    @Mock
    private UserRegisterValidator userRegisterValidator;

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @Test
    public void registerPage() throws Exception {
        RegisterController registerController = new RegisterController();
        MockMvc mockMvc = standaloneSetup(registerController).build();
        mockMvc.perform(get("/RegisterForm"))
                .andExpect(view().name("register"));
    }

//    @Test
//    public void invalidUsername() throws Exception {
//        RegisterController registerController = new RegisterController();
//        registerController.setUserDAO(userDAO);
//        registerController.setUserRegisterValidator(userRegisterValidator);
//
//        MockMvc mockMvc = standaloneSetup(registerController).build();
//        mockMvc.perform(post("/submitRegisterForm")
//                .param("username", "")
//                .param("email", "user1@gmail.coma")
//                .param("password", "12345678")
//                .param("confirmedPassword", "12345678"))
//                .andExpect(model().hasErrors());
//    }

//    @Test
//    public void invalidEmail() throws Exception {
//        RegisterController registerController = new RegisterController();
//        registerController.setUserDAO(userDAO);
//        registerController.setUserRegisterValidator(userRegisterValidator);
//
//        MockMvc mockMvc = standaloneSetup(registerController).build();
//        mockMvc.perform(post("/submitRegisterForm")
//                .param("username",  "user2")
//                .param("email", " ")
//                .param("password", "12345678")
//                .param("confirmedPassword", "12345678"))
//                .andExpect(model().hasErrors());
//    }
}
