package Controlers;

import businessLogic.BusinessFactory;
import businessLogic.DriverService;
import businessLogic.OrderService;
import businessLogic.OrderView;
import model.Driver;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 * Represent urls for work with driver requests
 */
@Controller
@RequestMapping("/driver")
public class DriverController {
    Logger logger = Logger.getLogger(DriverController.class);

    @RequestMapping(value = "/", method = RequestMethod.GET)
    ModelAndView driverPage(HttpSession session) {

        int id = (int) session.getAttribute("id");
        DriverService driverService = null;
        OrderService orderService = null;

        driverService = BusinessFactory.getInstance().getDriverLogic();
        orderService = BusinessFactory.getInstance().getOrderLogic();


        Driver driver = driverService.getById(id);
        if(driver == null) {
           // resp.sendRedirect("/error.jsp");
            return new ModelAndView("redirect:error");
        }

        Integer orderId = driver.getOrderRoute();
        ModelAndView view = new ModelAndView("driver/driver");
        if(orderId != null) {
            List<Driver> friends = new ArrayList<>();
            friends.addAll(driverService.getFriends(id));

            friends.remove(driver);

            List<OrderView> route = orderService.getOrderView(orderId);

            view.addObject("friends", friends);
            view.addObject("route", route);

            String truckId = orderService.getTruckIdByOrder(orderId);
            view.addObject("orderId", orderId);
            view.addObject("truckId", truckId);
        } else {
            view.addObject("route", null);
        }

        view.addObject("driver", driver);
        return view;
    }

    @RequestMapping("/test")
    public ModelAndView test() {
        ModelAndView view = new ModelAndView("driver/testDriver");
        Driver driver = new  Driver();
        driver.setId(42);
        driver.setName("Peter");
        view.addObject("driver", driver);
        return view;
    }
}
