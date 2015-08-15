package Controlers;


import businessLogic.UserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;



@Controller
public class LoginController {
    private static Logger logger = Logger.getLogger(LoginController.class);

    @Autowired
    UserService userService;

    @RequestMapping(value = "/login")
    String login(HttpSession session, @RequestParam String j_username, @RequestParam String j_password) {
        session.setAttribute("locale", "EN");


        logger.info("logging begin");
        if(userService.isValidUser(j_username, j_password)) {
            switch (userService.getUserStatus(j_username)){
                case DRIVER: {
                    logger.info("driver logged");
                    session.setAttribute("status", "DRIVER");
                    session.setAttribute("id", Integer.parseInt(j_username));
                    return "redirect:driver/";
                }
                case MANAGER:{
                    logger.info("manager logged");
                    session.setAttribute("status", "MANAGER");
                    session.setAttribute("id", j_username);

                    return "redirect:m/";
                }
                default: {
                    logger.error("wrong login");
                    return "redirect:login.jsp";
                }
            }
        }else {
            return "redirect:login.jsp";
        }
    }
    @RequestMapping(value = "/logout")
    ModelAndView logout() {
        return new ModelAndView("redirect:login.jsp");
    }

    public LoginController() {
    }

    public UserService getUserService() {
        return userService;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }
}
