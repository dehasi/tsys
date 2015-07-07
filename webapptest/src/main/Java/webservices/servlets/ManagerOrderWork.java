package webservices.servlets;

import businessLogic.BusinessFactory;
import businessLogic.OrderLogic;
import businessLogic.OrderRouteView;
import businessLogic.OrderView;
import model.OrderRoute;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
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
            case "edit" :{
                edit(req, resp);
                break;
            }
            case "delete" :{
                delete(req, resp);
                break;
            }
            default:{
                show(req, resp);
            }
        }
    }

    private void delete(HttpServletRequest req, HttpServletResponse resp) {

    }

    private void edit(HttpServletRequest req, HttpServletResponse resp) {

    }

    private void add(HttpServletRequest req, HttpServletResponse resp) {

    }

    private void show(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String show = req.getParameter("show");
        req.setAttribute("show", show);
        try {
            req.setAttribute("show", show);
            OrderLogic orderLogic = BusinessFactory.getInstance().getOrderLogic();
            Set<OrderRouteView> orders = null;

            switch (show){
                case "all" : {
                    orders = orderLogic.getAllRouteViews();
                    break;
                }

                default: {
                    orders = orderLogic.getAllRouteViews();
                }
            }

            req.setAttribute("orders", orders);
            RequestDispatcher rd = req.getRequestDispatcher("order.jsp");
            rd.forward(req, resp);

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            req.setAttribute("error", e);
            RequestDispatcher rd = req.getRequestDispatcher("/error.jsp");
            rd.forward(req, resp);
        } catch (Exception e) {
            e.printStackTrace();
            req.setAttribute("error", e);
            RequestDispatcher rd = req.getRequestDispatcher("/error.jsp");
            rd.forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPut(req, resp);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doDelete(req, resp);
    }
}
