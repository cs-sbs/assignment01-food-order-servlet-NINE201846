package cs.sbs.web.servlet;
import cs.sbs.web.model.Order;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;

public class OrderDetailServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/plain; charset=UTF-8");
        PrintWriter out = response.getWriter();
        String path = request.getPathInfo();
        if (path != null && path.length() > 1) {
            try {
                int orderId = Integer.parseInt(path.substring(1));
                Order order = OrderCreateServlet.getOrderById(orderId);
                if (order == null) {
                    out.println("Error: order not found");
                    return;
                }

                out.println("Order Detail");
                out.println("Order ID: " + order.getId());
                out.println("Customer: " + order.getCustomer());
                out.println("Food: " + order.getFood());
                out.println("Quantity: " + order.getQuantity());
            } catch (NumberFormatException var7) {
                out.println("Error: invalid order ID format");
            }

        } else {
            out.println("Error: invalid order ID");
        }
    }
}

