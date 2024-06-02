import java.util.ArrayList;

public class OrderHistory {
    private ArrayList<Order> orders;

    public OrderHistory() {
        this.orders = new ArrayList<>();
    }

    public void addOrder(Order order) {
        orders.add(order);
        System.out.println("Order added to history.");
    }

    public void viewOrderHistory() {
        if (orders.isEmpty()) {
            System.out.println("No orders in history.");
        } else {
            System.out.println("Order History:");
            for (Order order : orders) {
                System.out.println("\n\n");
                System.out.println("-----------------------------------");
                System.out.println(order);
                System.out.println("-----------------------------------");
            }
        }
    }
}
