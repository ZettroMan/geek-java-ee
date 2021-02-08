package ru.geekbrains;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.Arrays;


@WebServlet(urlPatterns = {"/main"})
public class MainPageServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("activePageNumber", 0);
        getServletContext().getRequestDispatcher("/page_header").include(req, resp);
        resp.getWriter().println("<h1>Главная страница<h1>");

    }
}
