package cs.sbs.web.servlet;

import cs.sbs.web.model.Order;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;


public class OrderCreateServlet extends HttpServlet {
    private static final List<Order> ORDERS = new ArrayList<>();
    private static final AtomicInteger NEXT_ID = new AtomicInteger(1001);

    public static Order getOrderById(int id) {
        return ORDERS.stream().filter(o -> o.getId() == id).findFirst().orElse(null);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/plain; charset=UTF-8");
        PrintWriter out = response.getWriter();

        String customer = request.getParameter("customer");
        String food = request.getParameter("food");
        String quantityStr = request.getParameter("quantity");

        // 异常处理：参数为空
        if (customer == null || customer.isBlank() ||
                food == null || food.isBlank() ||
                quantityStr == null || quantityStr.isBlank()) {
            out.println("Error: all form parameters (customer, food, quantity) are required.");
            return;
        }

        // 异常处理：数量不是合法数字
        int quantity;
        try {
            quantity = Integer.parseInt(quantityStr);
            if (quantity <= 0) {
                out.println("Error: quantity must be a positive number.");
                return;
            }
        } catch (NumberFormatException e) {
            out.println("Error: quantity must be a valid number");
            return;
        }

        int orderId = NEXT_ID.getAndIncrement();
        ORDERS.add(new Order(orderId, customer, food, quantity));
        out.println("Order Created: " + orderId);
    }
}