package Controlers;

import businessLogic.DriverView;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Driver;

/**
 * controoler for ralking with clients
 */
@RestController
@RequestMapping("/rest")
//@Produces(MediaType.APPLICATION_JSON)
//@Consumes(MediaType.APPLICATION_JSON)
public class RemoteServiceController {
    private static Logger logger = Logger.getLogger(RemoteServiceController.class);

    @Autowired
    private DriverUtils driverUtils;

    @RequestMapping(value = "/driver/status")
    ResponseEntity<String> updateDriverStatus(@RequestParam String id, @RequestParam String status) {
        logger.info("requested /driver/status");
        return driverUtils.changeDriverStatus(id, status);
    }

    @RequestMapping(value = "/baggage/status")
    ResponseEntity<String> updateBaggageStatus(@RequestParam String id,@RequestParam String status) {
        logger.info("requested /baggage/status");
        return driverUtils.changeOrderStatus(id, status);
    }

    @RequestMapping(value = "/driver/view")
    @ResponseBody
    String getDriverView(@RequestParam String login, @RequestParam String password) {
        logger.info("requested /driver/view");
//        DriverView view =driverUtils.getDriverView(login);

//        logger.info(view);
        String res = driverUtils.getDriverViewJSON(login);
        return  res;
    }


    public DriverUtils getDriverUtils() {
        return driverUtils;
    }

    public void setDriverUtils(DriverUtils driverUtils) {
        this.driverUtils = driverUtils;
    }
}
