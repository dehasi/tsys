package webservices.servlets;

import businessLogic.BusinessFactory;
import businessLogic.DriverService;
import businessLogic.OrderService;
import model.statuses.BaggageStatus;
import model.statuses.DriverStatus;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import businessLogic.*;
import model.*;
import businessLogic.*;
import model.*;

/**
 * Servlet for REST working
 */
public class DriverWSRSServlet extends HttpServlet {
    Logger logger = Logger.getLogger(DriverWSRSServlet.class);

    private static final String changeDriverStatusURL = "/rest/driver/status/driver";
    private static final String changeOrderStatusURL = "/rest/driver/status/order";

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String url = req.getRequestURI();

        switch (url) {
            case changeDriverStatusURL : {
                changeDriverStatus(req, resp);
                break;
            }
            case changeOrderStatusURL : {
                changeOrderStatus(req, resp);
                break;
            }
            default: {
                resp.setStatus(resp.SC_FORBIDDEN);
            }
        }


    }

    private void changeDriverStatus(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getHeader("id");
        String status = req.getHeader("status");


        try {

            int driverId = Integer.parseInt(id);

            DriverStatus driverStatus = DriverStatus.fromString(status);

            DriverService driverService = BusinessFactory.getInstance().getDriverLogic();

            driverService.setDtiverStatus(driverId, driverStatus);

            resp.setStatus(resp.SC_OK);

        } catch (Exception e) {
            logger.error(e);
            resp.setStatus(resp.SC_BAD_REQUEST);
        }

    }

    private void changeOrderStatus(HttpServletRequest req, HttpServletResponse resp) {
        String id = req.getHeader("id");
        String status = req.getHeader("status");

        try {
            int baggageId = Integer.parseInt(id);

            BaggageStatus baggageStatus = BaggageStatus.fromString(status);

            OrderService service = BusinessFactory.getInstance().getOrderLogic();

            service.changeBaggageStatus(baggageId, baggageStatus);

            resp.setStatus(resp.SC_OK);

        } catch (Exception e) {
            logger.error(e);
            resp.setStatus(resp.SC_BAD_REQUEST);
        }

    }
}
