package cs.sbs.web.model;

public class Order {
    private int id;
    private String customer;
    private String food;
    private int quantity;

    public Order(int id, String customer, String food, int quantity) {
        this.id = id;
        this.customer = customer;
        this.food = food;
        this.quantity = quantity;
    }

    public int getId() {
        return this.id;
    }

    public String getCustomer() {
        return this.customer;
    }

    public String getFood() {
        return this.food;
    }

    public int getQuantity() {
        return this.quantity;
    }
}

