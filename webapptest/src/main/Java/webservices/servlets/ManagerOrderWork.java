package webservices.servlets;

import businessLogic.BusinessFactory;
import businessLogic.OrderService;
import businessLogic.OrderRouteView;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Set;

/**
 * Created by Rafa on 05.07.2015.
 */
public class ManagerOrderWork extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String action = req.getParameter("action");
        switch (action) {
            case "show" :{
                show( req,  resp);
                break;
            }
            case "add" :{
                add(req, resp);
                break;
            }
            default:{
                show(req, resp);
            }
        }
    }

    private void add(HttpServletRequest req, HttpServletResponse resp) {

    }

    private void show(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String show = req.getParameter("show");
        req.setAttribute("show", show);
        try {
            req.setAttribute("show", show);
            OrderService orderService = BusinessFactory.getInstance().getOrderLogic();
            Set<OrderRouteView> orders = null;

            switch (show){
                case "all" : {
                    orders = orderService.getAllRouteViews();
                    break;
                }
                case "done" : {
                    orders = orderService.getRouteViewsByStatus(1);
                    break;
                }
                case "notdone" : {
                    orders = orderService.getRouteViewsByStatus(0);
                    break;
                }
                default: {
                    orders = orderService.getAllRouteViews();
                }
            }

            req.setAttribute("orders", orders);
            RequestDispatcher rd = req.getRequestDispatcher("order.jsp");
            rd.forward(req, resp);

        }  catch (Exception e) {
            ServletHelper.handleError(req, resp, e);
        }
    }

}
