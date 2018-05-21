package controller;

import dao.UserDAO;
import model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import validator.UserLoginValidator;

@Configuration
@ComponentScan(basePackages="validator")
@Controller
public class LoginController {
    @Autowired
    UserDAO userDAO;

    @Autowired
    UserLoginValidator userLoginValidator;

    public void setUserDAO(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @RequestMapping(value="/LoginForm", method = RequestMethod.GET)
    public ModelAndView getLoginForm() {
        ModelAndView model1 = new ModelAndView("login");
        return model1;
    }

    @RequestMapping(value="/submitLoginForm", method = RequestMethod.POST)
    public ModelAndView submitAdmissionForm(@RequestParam(value="username", required=true) String username,
                                            @RequestParam(value="password", required=true) String password) {

        ModelAndView model;
        if(userLoginValidator.validate(username, password))
            model = new ModelAndView("home"); //tworzenie sesji
        else
            model = new ModelAndView("login");//jakaś wiadomość że błędne haslo lub login
        return model;
    }
}
