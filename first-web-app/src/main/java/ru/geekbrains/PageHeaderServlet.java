package ru.geekbrains;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(urlPatterns = "/page_header")
public class PageHeaderServlet extends HttpServlet {

    private static class MenuItem {
        String path;
        String value;

        public MenuItem(String path, String value) {
            this.path = path;
            this.value = value;
        }
    }

    private static MenuItem[] menu = new MenuItem[5];

    static {
        menu[0] = new MenuItem("first-web-app/main", "Главная");
        menu[1] = new MenuItem("first-web-app/catalog", "Каталог");
        menu[2] = new MenuItem("first-web-app/product", "Продукт");
        menu[3] = new MenuItem("first-web-app/cart", "Корзина");
        menu[4] = new MenuItem("first-web-app/order", "Заказ");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int activePageNumber = (Integer) req.getAttribute("activePageNumber");
        PrintWriter writer = resp.getWriter();
        writer.println("<div style=\"display:flex; justify-content:space-around; font-size:18px;" +
                " height:24px; align-items:center; padding-top:16px; width:50%\">");

        for (int i = 0; i < menu.length; i++) {
            if(i != activePageNumber) {
                writer.printf("<a href=\"../%s\">%s</a>", menu[i].path, menu[i].value);
            } else {
                writer.printf("<b>%s</b>", menu[i].value);
            }
        }
        writer.println("</div>");
    }
}
