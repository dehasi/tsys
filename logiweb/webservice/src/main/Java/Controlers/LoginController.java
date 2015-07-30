package Controlers;

import businessLogic.BusinessFactory;
import businessLogic.UserService;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.io.PrintWriter;

/**
 * Created by Rafa on 27.07.2015.
 */
@Controller
//@RequestMapping("*/")
public class LoginController {
    private static Logger logger = Logger.getLogger(LoginController.class);

    @RequestMapping(value = "/login") //, method = RequestMethod.POST
    String login(HttpSession session, @RequestParam String login, @RequestParam String password) {
        session.setAttribute("locale", "EN");

        UserService userService  = BusinessFactory.getInstance().getUserLogic();
        logger.info("logging begin");
        if(userService.isValidUser(login, password)) {
            switch (userService.getUserStatus(login)){
                case DRIVER: {
                    logger.info("driver logged");
                    session.setAttribute("status", "DRIVER");
                    session.setAttribute("id", Integer.parseInt(login));
                    return "redirect:driver/";
                }
                case MANAGER:{
                    logger.info("manager logged");
                    session.setAttribute("status", "MANAGER");
                    session.setAttribute("id", login);

                    return "redirect:m/";
                }
                default: {
                    logger.error("wrong login");
                    return "redirect:login";
                }
            }
        }else {
            return "redirect:login";
        }
    }
    @RequestMapping(value = "/logout")
    ModelAndView logout() {
        return new ModelAndView("redirect:");
    }
}
