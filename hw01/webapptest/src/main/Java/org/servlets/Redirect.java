package org.servlets;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Rafa on 16.06.2015.
 */
public class Redirect extends HttpServlet {
    int count  =0;
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ++count;
        request.setAttribute("cc", count);
        request.getRequestDispatcher("index.jsp").forward(request, response);
    }
}
