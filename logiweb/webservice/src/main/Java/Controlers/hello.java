package Controlers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * It's just a dimple controller for spring testing
 */
@Controller
public class hello {

    @RequestMapping("/jsp")
    public ModelAndView returnJSP() {
        return new ModelAndView("success");
    }

    @RequestMapping("/hello")
    public String sayHelloToOpenShift(){
        return "hello";
    }

}
