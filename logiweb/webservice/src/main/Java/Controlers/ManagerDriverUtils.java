package Controlers;

import businessLogic.BusinessFactory;
import businessLogic.CityService;
import businessLogic.DriverService;
import businessLogic.UserService;
import model.City;
import model.Driver;
import model.User;
import model.statuses.DriverStatus;
import model.statuses.UserStatus;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.util.*;

/**
 * Controller for driver managering
 */
@Component
public class ManagerDriverUtils {
    Logger logger = Logger.getLogger(ManagerDriverUtils.class);

    @Autowired
    DriverService driverService; // = BusinessFactory.getInstance().getDriverLogic();
    @Autowired
    CityService cityService;
    @Autowired
    UserService userService;

    ModelAndView mainPage(Map<String,String> requestParams) throws IOException {
        logger.info("we are in manager driver");
        ModelAndView view;  // = new ModelAndView("manager/driver");
        String action = requestParams.get("action");
        switch (action) {
            case "show" :{
                view = show(requestParams);
                break;
            }
            case "add" :{
                view = add();
                break;
            }
            case "edit" :{
                view = edit(requestParams);
                break;
            }
            case "delete" :{
                view = delete(requestParams);
                break;
            }
            default:{
                view = show(requestParams);
            }
        }
        return view;
    }

    private ModelAndView show(Map<String, String> requestParams) throws IOException {
        ModelAndView view = new ModelAndView("m/driver");
        String show = requestParams.get("show");

        try {
           // DriverService driverService = BusinessFactory.getInstance().getDriverLogic();

            Set<Driver> drivers ;

            switch (show){
                case "all" : {
                    drivers = driverService.getAllDrivers();
                    break;
                }
                case "free" : {
                    drivers = driverService.getFreeDrivers();
                    break;
                }
                case "inorder" : {
                    drivers = driverService.getInOrderDrivers();
                    break;
                }
                default: {
                    drivers = driverService.getAllDrivers();
                }
            }
            if (drivers != null){
                List<Driver> dlist = new ArrayList<>(drivers);
                Collections.sort(dlist);
                view.addObject("drivers", dlist);
            }

        } catch (Exception e) {
            return UtilsController.handleError(e);
        }
        return view;
    }

    private ModelAndView add() throws IOException {
        ModelAndView view = new ModelAndView("m/driverAdd");
        try {
            //CityService cityService = BusinessFactory.getInstance().getCityLogic();
            Set<City> cities = cityService.getAllCities();
            view.addObject("cities",cities);

        }  catch (Exception e) {
            return UtilsController.handleError(e);
        }
        return view;
    }

    private ModelAndView edit(Map<String, String> requestParams) throws IOException {
        ModelAndView view = new ModelAndView("m/driverEdit");
        try {
            //DriverService driverService = BusinessFactory.getInstance().getDriverLogic();
           // CityService cityService = BusinessFactory.getInstance().getCityLogic();
            Set<City> cities = cityService.getAllCities();

            view.addObject("cities",cities);
            String id = requestParams.get("id");
            int driverId = Integer.parseInt(id);
            Driver driver =  driverService.getById(driverId);
            view.addObject("driver", driver);

        } catch (Exception e) {
            return UtilsController.handleError(e);
        }
        return view;
    }

    private ModelAndView delete(Map<String, String> requestParams) throws IOException {
        ModelAndView view = new ModelAndView("redirect:/m/driver?action=show&show=all");
        try {
           // DriverService driverService = BusinessFactory.getInstance().getDriverLogic();

            String id = requestParams.get("id");
            int driverId = Integer.parseInt(id);
            Driver driver =  driverService.getById(driverId);
            driverService.deleteDriver(driver);

        } catch (Exception e) {
            return UtilsController.handleError(e);
        }
        return view;
    }

    public ModelAndView doPost(Map<String, String> requestParams) throws IOException {

        String action = requestParams.get("do");
        if (action == null) {
            logger.error("null in action paramenter");
            return UtilsController.handleError(new Exception("unknown action"));
        }
        switch (action) {
            case "Add": {

                try {
                 //   DriverService driverService = BusinessFactory.getInstance().getDriverLogic();
                //    CityService cityService = BusinessFactory.getInstance().getCityLogic();
                    Driver driver = new Driver();
                    String name = requestParams.get("name");
                    String lastName = requestParams.get("lastname");
                    String cityP = requestParams.get("city");
                    int cityId = Integer.parseInt(cityP);
                    City city = cityService.getCityById(cityId);

                    driver.setName(name);
                    driver.setLastName(lastName);
                    driver.setOrderRoute(null);
                    driver.setStatus(DriverStatus.REST);
                    driver.setHoursWorked(0);
                    driver.setCity(city);

                    driverService.addNewDriver(driver);
                    Integer id = driver.getId();
                    User user = new User();
                    user.setId(id);
                    user.setLogin(id.toString());
                    user.setPasswordHash("password".hashCode());
                    user.setStatus(UserStatus.DRIVER);
                  //  UserService userService = BusinessFactory.getInstance().getUserLogic();
                    userService.addUser(user);

                } catch (Exception e) {
                    return UtilsController.handleError(e);
                }
                break;
            }
            case "Save" :{
                try {
                   // DriverService driverService = BusinessFactory.getInstance().getDriverLogic();
                  //  CityService cityService = BusinessFactory.getInstance().getCityLogic();
                    String id = requestParams.get("id");
                    int driverId = Integer.parseInt(id);
                    Driver driver =  driverService.getById(driverId);

                    String name = requestParams.get("name");
                    String lastName = requestParams.get("lastname");
                    String cityP = requestParams.get("city");
                    int cityId = Integer.parseInt(cityP);
                    City city = cityService.getCityById(cityId);

                    driver.setName(name);
                    driver.setLastName(lastName);
                    driver.setCity(city);
                    driverService.updateDriver(driver);
                }  catch (Exception e) {
                    return UtilsController.handleError(e);
                }
            }
        }
        return new ModelAndView("redirect:/m/driver?action=show&show=all");
    }
}
