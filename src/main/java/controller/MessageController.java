package controller;

import dao.MessageDAO;
import dao.UserDAO;
import model.Message;
import model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@Configuration
@Controller
public class MessageController {
    @Autowired
    UserDAO userDAO;

    @Autowired
    MessageDAO messageDAO;

    @RequestMapping(value="/messages", method = RequestMethod.GET)
    public ModelAndView getMessages(HttpSession session) {
        ModelAndView model;
        if(session.getAttribute("loggedInUserId") != null){
            model = new ModelAndView("messages");
            model.addObject("user",userDAO.findById((int) session.getAttribute("loggedInUserId")));
        }
        else{
            model = new ModelAndView("login");
            model.addObject("msg", "you are not logged in");
        }
        return  model;
    }

    @RequestMapping(value="/messageForm", method = RequestMethod.GET)
    public ModelAndView getMessageForm(HttpSession session) {
        ModelAndView model;
        if(session.getAttribute("loggedInUserId") != null){
            model = new ModelAndView("createMessage");
        }
        else{
            model = new ModelAndView("login");
            model.addObject("msg", "you are not logged in");
        }
        return  model;
    }

    @RequestMapping(value="/sendMessage", method = RequestMethod.POST)
    public ModelAndView sendMessage(@RequestParam(value="recipient", required=true) String recipientUsername,
                                    @RequestParam(value="message", required=true) String messageText,
                                    HttpSession session) {
        ModelAndView model;
        User user = userDAO.findById((int) session.getAttribute("loggedInUserId"));
        User recipient = userDAO.findByUsername(recipientUsername);
        if(session.getAttribute("loggedInUserId") != null){
            if(userDAO.isUsernameInDatabase(recipientUsername)){
                Message message = new Message(messageText, user.getUsername());
                messageDAO.save(message, recipient);
                model = new ModelAndView("home");
            }
            else
                model = new ModelAndView("home");
        }
        else{
            model = new ModelAndView("login");
            model.addObject("msg", "you are not logged in");
        }
        return  model;
    }
}
