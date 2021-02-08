package ru.geekbrains;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet(urlPatterns = {"/catalog"})
public class CatalogServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("pageHeader", "<p>Main  <b>Catalog</b>  Product  Cart  Order</p>");
        getServletContext().getRequestDispatcher("/page_header").include(req, resp);
        resp.getWriter().println("<h1>Catalog page<h1>");

    }
}
