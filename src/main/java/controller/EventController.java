package controller;

import dao.EventDAO;
import dao.UserDAO;
import model.Event;
import model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.sql.Date;

@Configuration
@Controller
public class EventController {
    @Autowired
    EventDAO eventDAO;

    @Autowired
    UserDAO userDAO;

    @RequestMapping(value="/events", method = RequestMethod.GET)
    public ModelAndView getEvents(HttpSession session) {
        ModelAndView model;
        if(session.getAttribute("loggedInUserId") != null){
            model = new ModelAndView("events");
        }
        else{
            model = new ModelAndView("login");
            model.addObject("msg", "you are not logged in");
        }
        return  model;
    }

    @RequestMapping(value="/eventForm", method = RequestMethod.GET)
    public ModelAndView getEventForm(HttpSession session) {
        ModelAndView model;
        if(session.getAttribute("loggedInUserId") != null){
            model = new ModelAndView("createEvent");
        }
        else{
            model = new ModelAndView("login");
            model.addObject("msg", "you are not logged in");
        }
        return  model;
    }

    @RequestMapping(value="/createEvent", method = RequestMethod.POST)
    public ModelAndView createEvent(@RequestParam(value="eventName", required=true) String eventName,
                                    @RequestParam(value="eventDate", required=true) Date eventDate,
                                    HttpSession session) {
        ModelAndView model;
        User user = userDAO.findById((int) session.getAttribute("loggedInUserId"));
        if(session.getAttribute("loggedInUserId") != null){
            Event event = new Event(eventName, user.getUsername(), eventDate);
            eventDAO.save(event, user);

            model = new ModelAndView("home");
            model.addObject("msg", "event created");
        }
        else{
            model = new ModelAndView("login");
            model.addObject("msg", "you are not logged in");
        }
        return  model;
    }
}
