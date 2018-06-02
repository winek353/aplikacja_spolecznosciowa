package controller;

import dao.FriendRequestDAO;
import dao.UserDAO;
import model.FriendRequest;
import model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.DataBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.servlet.ModelAndView;
import validator.FriendRequestValidator;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Configuration
@Controller
public class FriendController {
    @Autowired
    FriendRequestDAO friendRequestDAO;

    @Autowired
    UserDAO userDAO;

    @Autowired
    FriendRequestValidator friendRequestValidator;

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
    public ModelAndView sendFriendRequest(@RequestParam(value="friendName", required=true) String friendName,
                                            HttpSession session) {

        ModelAndView model;
        if(session.getAttribute("loggedInUserId") != null){
            User user = userDAO.findById((int) session.getAttribute("loggedInUserId"));
            User friend = userDAO.findByUsername(friendName);
            FriendRequest friendRequest = new FriendRequest(user.getId(), friend, user.getUsername());

            BindingResult result =new DataBinder(friendRequest).getBindingResult();
            friendRequestValidator.validate(friendRequest, result);

            if (result.hasErrors()){
                model = new ModelAndView("home");//wyświetlić error
                model.addObject("errors", result.getAllErrors());
                System.out.println(result.getAllErrors());
            }
            else {
                model = new ModelAndView("home");
//                check if friend sent already message
                if(userDAO.isFriendRequestSent(friendRequest.getRecipient().getId(), friendRequest.getRequesterId())){
                    FriendRequest friendRequest1 = friendRequestDAO
                            .getFriendRequest(friendRequest.getRecipient().getId(), friendRequest.getRequesterId());
                    userDAO.addFriend(user, friend);
                    friendRequestDAO.delete(friendRequest);
                    model.addObject("msg", "you are now friend with " + friendName);
                }
                else{
                    model.addObject("msg", "friend request sent to " + friendName);
                    friendRequestDAO.save(friendRequest);
                }

            }
        }
        else{
            model = new ModelAndView("login");
            model.addObject("msg", "you are not logged in");
        }
        return  model;
    }
    @RequestMapping(value="/addFriend", method = RequestMethod.GET)
    public ModelAndView addFriend(@RequestParam(value="friendRequestId", required=true) int friendRequestId,
                                  HttpSession session) {
        ModelAndView model;
        if(session.getAttribute("loggedInUserId") != null){
            model = new ModelAndView("home");
            FriendRequest friendRequest = friendRequestDAO.getFriendRequest(friendRequestId);
            User user = userDAO.findById(friendRequest.getRecipient().getId());
            User friend = userDAO.findById(friendRequest.getRequesterId());
            userDAO.addFriend(user, friend);
            friendRequestDAO.delete(friendRequest);
            model.addObject("msg", "you are now friend with " + friend.getUsername());
        }
        else{
            model = new ModelAndView("login");
            model.addObject("msg", "you are not logged in");
        }
        return  model;
    }
}
