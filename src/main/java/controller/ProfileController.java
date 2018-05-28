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
    @RequestMapping(value="/changeProfile", method = RequestMethod.POST)
    public ModelAndView changeProfile(//@RequestParam(value="sex", required=false) char sex,
                                      @RequestParam(value="aboutMe", required=false) String aboutMe,
                                      HttpSession session) {
        ModelAndView model;
        User user = userDAO.findById((int) session.getAttribute("loggedInUserId"));
        System.out.println(aboutMe);
        if(session.getAttribute("loggedInUserId") != null){
            model = new ModelAndView("profile");
            if(aboutMe != null){
                user.setAboutMe(aboutMe);
            }
//            if(sex == 'm' || sex == 'f'){
//                user.setSex(sex);
//            }
            userDAO.update(user);
        }
        else{
            model = new ModelAndView("login");
            model.addObject("msg", "you are not logged in");
        }
        return model;
    }
}
