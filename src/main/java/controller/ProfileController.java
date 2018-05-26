package controller;

import dao.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@Configuration
@ComponentScan(basePackages="validator")
@Controller
public class ProfileController {
    @Autowired
    UserDAO userDAO;

    @RequestMapping(value="/profile", method = RequestMethod.GET)
    public ModelAndView getProfile(HttpSession session) {
        ModelAndView model;
        if(session.getAttribute("loggedInUserId") != null){
            model = new ModelAndView("profile");
            model.addObject("profile",userDAO.findById((int) session.getAttribute("loggedInUserId")));
        }
        else{
            model = new ModelAndView("login");
            model.addObject("msg", "you are not logged in");
        }
        return model;
    }
}
