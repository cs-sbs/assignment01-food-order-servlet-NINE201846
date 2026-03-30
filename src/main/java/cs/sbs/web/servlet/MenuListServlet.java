package cs.sbs.web.servlet;

import cs.sbs.web.model.MenuItem;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet("/menu")
public class MenuListServlet extends HttpServlet {
    private static final List<MenuItem> MENU = new ArrayList<>();

    static {
        MENU.add(new MenuItem("Fried Rice", 8));
        MENU.add(new MenuItem("Fried Noodles", 9));
        MENU.add(new MenuItem("Burger", 10));
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/plain; charset=UTF-8");
        PrintWriter out = response.getWriter();
        String name = request.getParameter("name");
        List<MenuItem> result;

        if (name == null || name.isBlank()) {
            result = MENU;
        } else {
            result = MENU.stream()
                    .filter(item -> item.getName().toLowerCase().contains(name.toLowerCase()))
                    .collect(Collectors.toList());
        }

        out.println("Menu List:");
        if (result.isEmpty()) {
            out.println("No items found.");
        } else {
            for (int i = 0; i < result.size(); i++) {
                MenuItem item = result.get(i);
                out.printf("%d. %s - $%d%n", i + 1, item.getName(), (int) item.getPrice());
            }
        }
    }
}