package Controlers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.util.Map;

/**
 * Main manager controoler
 */

@Controller
@RequestMapping("/m")
@Secured("ROLE_ADMIN")
public class ManagerController {
    @Autowired
    private ManagerDriverUtils driverUtils;
    @Autowired
    private ManagerTruckUtils truckUtils;
    @Autowired
    private ManagerOrderUtils orderUtils;

    @RequestMapping("/")
    public String mainPage() {
        return "m/manager";
    }

    @RequestMapping(value = "/driver", method = RequestMethod.GET)
    public ModelAndView driverWork(@RequestParam Map<String,String> requestParams) throws IOException {
        return driverUtils.mainPage(requestParams);
    }

    @RequestMapping(value = "/driver", method = RequestMethod.POST)
    public ModelAndView driverAddEdit(@RequestParam Map<String,String> requestParams) throws IOException {
        return driverUtils.doPost(requestParams);
    }


    @RequestMapping(value = "/truck", method = RequestMethod.GET)
    public ModelAndView truckWork(@RequestParam Map<String,String> requestParams) throws IOException {
        return truckUtils.mainPage(requestParams);
    }

    @RequestMapping(value = "/truck", method = RequestMethod.POST)
    public ModelAndView truckAddEdit(@RequestParam Map<String,String> requestParams) throws IOException {
        return truckUtils.doPost(requestParams);
    }

    @RequestMapping(value = "/order", method = RequestMethod.GET)
    public ModelAndView orderWork(@RequestParam Map<String,String> requestParams) throws IOException {
        return orderUtils.mainPage(requestParams);
    }

    @RequestMapping(value = "/createorder", method = RequestMethod.GET)
    public ModelAndView createOrder(@RequestParam Map<String,String> requestParams) throws IOException {
        return orderUtils.doGet(requestParams);
    }

    @RequestMapping(value = "/createorder/get", method = RequestMethod.POST)
    public @ResponseBody
    String getStuffForOrder(@RequestParam Map<String,String> requestParams) throws IOException {
        return orderUtils.getStuff(requestParams);
    }

    @RequestMapping(value = "/createorder/put", method = RequestMethod.POST)
    public ModelAndView createOrderPost(@RequestParam Map<String,String> requestParams) throws IOException {
        return orderUtils.createOrder(requestParams);
    }


    public ManagerDriverUtils getDriverUtils() {
        return driverUtils;
    }

    public void setDriverUtils(ManagerDriverUtils driverUtils) {
        this.driverUtils = driverUtils;
    }

    public ManagerTruckUtils getTruckUtils() {
        return truckUtils;
    }

    public void setTruckUtils(ManagerTruckUtils truckUtils) {
        this.truckUtils = truckUtils;
    }

    public ManagerOrderUtils getOrderUtils() {
        return orderUtils;
    }

    public void setOrderUtils(ManagerOrderUtils orderUtils) {
        this.orderUtils = orderUtils;
    }
}
