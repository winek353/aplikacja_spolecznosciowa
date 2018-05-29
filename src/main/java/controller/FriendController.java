package controller;

import dao.FriendRequestDAO;
import dao.UserDAO;
import model.FriendRequest;
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
public class FriendController {
    @Autowired
    FriendRequestDAO friendRequestDAO;

    @Autowired
    UserDAO userDAO;

    @RequestMapping(value="/friends", method = RequestMethod.GET)
    public ModelAndView getLoginForm(HttpSession session) {
        ModelAndView model;
        if(session.getAttribute("loggedInUserId") != null){
            model = new ModelAndView("friends");
            model.addObject("user",userDAO.findById((int) session.getAttribute("loggedInUserId")));
        }
        else{
            model = new ModelAndView("login");
            model.addObject("msg", "you are not logged in");
        }
        return  model;
    }

    @RequestMapping(value="/sendFriendRequest", method = RequestMethod.GET)
    public ModelAndView addFriend(@RequestParam(value="friendName", required=true) String friendName,
                                            HttpSession session) {
        ModelAndView model;
        if(session.getAttribute("loggedInUserId") != null){
            User user = userDAO.findById((int) session.getAttribute("loggedInUserId"));
            User friend = userDAO.findByUsername(friendName);
            model = new ModelAndView("home");
            model.addObject("msg", "friend request sent to " + friendName);
            FriendRequest friendRequest = new FriendRequest();
            friendRequest.setRequesterId(user.getId());
            friendRequest.setRecipient(friend);
            friendRequest.setRequesterUsername(user.getUsername());
            friendRequestDAO.save(friendRequest);
        }
        else{
            model = new ModelAndView("login");
            model.addObject("msg", "you are not logged in");
        }
        return  model;
    }
}
