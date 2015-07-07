package webservices.servlets;

import businessLogic.BusinessFactory;
import businessLogic.CityLogic;
import com.google.gson.Gson;
import model.City;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Created by Rafa on 07.07.2015.
 */
public class ManagerCreateOrder extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }

    @Override
    protected void doHead(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doHead(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("do");
        if (action == null) {

        }

        switch (action) {
            case "getCities": {
                try {
                    CityLogic cityLogic = BusinessFactory.getInstance().getCityLogic();
                    Set<City> cities = cityLogic.getAllCities();
                    List<City> cityList = new ArrayList<>(cities);
                    Gson gson = new Gson();
                    String json = gson.toJson(cityList);

                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                    req.setAttribute("error", e);
                    RequestDispatcher rd = req.getRequestDispatcher("/error.jsp");
                    rd.forward(req, resp);

                } catch (SQLException e) {
                    e.printStackTrace();
                    req.setAttribute("error", e);
                    RequestDispatcher rd = req.getRequestDispatcher("/error.jsp");
                    rd.forward(req, resp);
                }

            }
        }
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
