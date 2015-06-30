package businessLogic;

import DAO.BaggageDAOImpl;
import DAO.OrderRouteDAOImpl;

/**
 * Created by Rafa on 30.06.2015.
 */
public class OrderLogic {
    BaggageDAOImpl baggageDAO = null;
    OrderRouteDAOImpl orderRouteDAO = null;

    public OrderLogic(BaggageDAOImpl baggageDAO, OrderRouteDAOImpl orderRouteDAO) {
        this.baggageDAO = baggageDAO;
        this.orderRouteDAO = orderRouteDAO;
    }


}
