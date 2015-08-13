package Controlers;

import businessLogic.CityService;
import businessLogic.TruckService;
import model.City;
import model.Truck;
import model.statuses.TruckStatus;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.util.Map;
import java.util.Set;

/**
 * Class for truck utils
 */
@Component
public class ManagerTruckUtils {

    private static Logger logger = Logger.getLogger(ManagerTruckUtils.class);
    @Autowired
    TruckService truckService;
    @Autowired
    CityService cityService;

    protected ModelAndView mainPage(Map<String,String> requestParams) throws IOException  {

        String action = requestParams.get("action");
        ModelAndView view;
        switch (action) {
            case "show" :{
                view =   show(requestParams);
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

    private ModelAndView delete(Map<String,String> requestParams) throws  IOException {
        try {
            String id = requestParams.get("id");
            Truck truck = truckService.getTruckById(id);
            truckService.deleteTruck(truck);
            return new ModelAndView("redirect:m/truck?action=show&show=all");
        } catch (Exception e) {
            return UtilsController.handleError(e);
        }
    }

    private ModelAndView edit(Map<String,String> requestParams) throws IOException {
        try {
            Set<City> cities = cityService.getAllCities();

            ModelAndView view = new ModelAndView("m/truckEdit");
            view.addObject("cities", cities);

            String id = requestParams.get("id");

            Truck truck = truckService.getTruckById(id);
            view.addObject("truck", truck);
            return view;

        } catch (Exception e) {
            return UtilsController.handleError(e);
        }
    }

    private ModelAndView add() throws IOException {
        try {
            Set<City> cities = cityService.getAllCities();
            ModelAndView view = new ModelAndView("m/truckAdd");
            view.addObject("cities", cities);
            return view;
        }  catch (Exception e) {
            return UtilsController.handleError(e);
        }
    }

    private ModelAndView show(Map<String,String> requestParams) throws IOException {
        String show = requestParams.get("show");
        if(show == null) {
            show = "all";
        }

        try {
            Set<Truck> trucks;

            switch (show){
                case "all" : {
                    trucks = truckService.getAllTrucks();
                    break;
                }
                case "ok" : {
                    trucks = truckService.getOKTrucks();
                    break;
                }
                case "defective" : {
                    trucks = truckService.getDefectiveTrucks();
                    break;
                }
                case "free" : {
                    trucks = truckService.getFreeTrucks();
                    break;
                }
                case "inorder" : {
                    trucks = truckService.getInOrderTrucks();
                    break;
                }
                default: {
                    trucks = truckService.getAllTrucks();
                }
            }
            ModelAndView view = new ModelAndView("m/truck");
            view.addObject("trucks", trucks);
            return view;
        }  catch (Exception e) {
            return UtilsController.handleError(e);
        }
    }


    protected ModelAndView doPost(Map<String,String> requestParams) throws IOException {
        String action = requestParams.get("do");
        if (action == null) {
            logger.error("null in action paramenter");
            return UtilsController.handleError(new Exception("unknown action"));
        }

        switch (action) {
            case "Add": {
                try {
                  //  TruckService truckService = BusinessFactory.getInstance().getTruckLogic();
                //    CityService cityService = BusinessFactory.getInstance().getCityLogic();
                    Truck truck = new Truck();

                    String id = requestParams.get("tid");
                    int duty = Integer.parseInt(requestParams.get("duty"));
                    int capacity = Integer.parseInt(requestParams.get("capacity"));
                    int status = Integer.parseInt(requestParams.get("status"));

                    String cityP = requestParams.get("city");
                    int cityId = Integer.parseInt(cityP);
                    City city = cityService.getCityById(cityId);

                    truck.setCity(city);
                    truck.setCapacity(capacity);
                    truck.setDutySize(duty);
                    truck.setStatus(TruckStatus.fromInt(status));
                    truck.setId(id);
                    truckService.addTruck(truck);

                }  catch (Exception e) {
                    return UtilsController.handleError(e);
                }

            }
            case "Save" :{
                try {
                    String id = requestParams.get("tid");
                    String hiddenId = requestParams.get("hiddenid");
                    if(!id.equals(hiddenId)) {
                        truckService.deleteTruck(truckService.getTruckById(hiddenId));
                    }else {
                        truckService.deleteTruck(truckService.getTruckById(id));
                    }

                    Truck truck = new Truck();

                    int duty = Integer.parseInt(requestParams.get("duty"));
                    int capacity = Integer.parseInt(requestParams.get("capacity"));
                    int status = Integer.parseInt(requestParams.get("status"));

                    String cityP = requestParams.get("city");
                    int cityId = Integer.parseInt(cityP);
                    City city = cityService.getCityById(cityId);

                    truck.setCity(city);
                    truck.setCapacity(capacity);
                    truck.setDutySize(duty);
                    truck.setStatus(TruckStatus.fromInt(status));
                    truck.setId(id);

                    truckService.addTruck(truck);

                }  catch (Exception e) {
                    return UtilsController.handleError(e);
                }
            }
        }
        return new ModelAndView("redirect:/m/truck?action=show&show=all");
    }
}
