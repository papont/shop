package ru.samara.shop.web;

import ru.samara.shop.LoggerWrapper;

import java.io.IOException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@javax.servlet.annotation.WebServlet(name = "UserServlet", urlPatterns = "/user")
public class UserServlet extends HttpServlet {
    private static final LoggerWrapper LOG = LoggerWrapper.get(UserServlet.class);

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws javax.servlet.ServletException, IOException {
        // запрос идет прямо с сервера
        //request.getRequestDispatcher("/userList.jsp").forward(request, response);
        LOG.debug("Redirect to userList");

        //запрос идет через браузер
        response.sendRedirect("userList.jsp");
    }
}
