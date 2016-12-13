package ru.samara.shop.web;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import ru.samara.shop.LoggerWrapper;
import ru.samara.shop.service.UserService;

import java.io.IOException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@javax.servlet.annotation.WebServlet(name = "userServlet", urlPatterns = "/users")
public class UserServlet extends HttpServlet {
    private static final LoggerWrapper LOG = LoggerWrapper.get(UserServlet.class);

    private WebApplicationContext context;


    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws javax.servlet.ServletException, IOException {
        LOG.debug("Redirect to userList");

        UserService userService = context.getBean(UserService.class);
        request.setAttribute("userList", userService.getAll());


        // запрос идет прямо с сервера
        request.getRequestDispatcher("/userList.jsp").forward(request, response);

        //запрос идет через браузер
//        response.sendRedirect("userList.jsp");
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        // получить контекст spring
        context = WebApplicationContextUtils.getRequiredWebApplicationContext(getServletContext());


    }
}
