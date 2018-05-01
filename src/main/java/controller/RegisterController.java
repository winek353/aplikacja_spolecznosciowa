package controller;

import dao.UserDAO;
import model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import validator.UserRegisterValidator;

import javax.validation.Valid;

@Configuration
@ComponentScan(basePackages="validator")
@Controller
public class RegisterController {

    @Autowired
    UserDAO userDAO;

    @Autowired
    private UserRegisterValidator userRegisterValidator;

    public void setUserDAO(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public void setUserRegisterValidator(UserRegisterValidator userRegisterValidator) {
        this.userRegisterValidator = userRegisterValidator;
    }

    @InitBinder
    protected void initBinder(final WebDataBinder binder)
    {
        binder.setValidator(userRegisterValidator);
    }

    @RequestMapping(value="/RegisterForm", method = RequestMethod.GET)
    public ModelAndView getRegisterForm() {
        ModelAndView model1 = new ModelAndView("register");
        return model1;
    }

    @RequestMapping(value="/submitRegisterForm", method = RequestMethod.POST)
    public ModelAndView submitAdmissionForm(@ModelAttribute("user")
                                                @Valid User user, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            System.out.println(bindingResult.getAllErrors());
            ModelAndView model = new ModelAndView("register");
            //model.addObject("user", "ok");
            return model;
        }
        //System.out.println(user);
        userDAO.save(user);

        ModelAndView model = new ModelAndView("login");
        return model;
    }
}
