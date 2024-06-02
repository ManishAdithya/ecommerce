import java.util.ArrayList;
import java.util.Date;


public class Order {
    private User user;
    private ArrayList<Product> products;
    private Date orderDate;
    private String paymentMethod;

    public Order(User user, ArrayList<Product> products, String paymentMethod) {
        this.user = user;
        this.products = new ArrayList<>(products);
        this.orderDate = new Date();
        this.paymentMethod = paymentMethod;
    }

    public User getUser() {
        return user;
    }

    public ArrayList<Product> getProducts() {
        return products;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Order Details:\n");
        sb.append("User: ").append(user.getName()).append("\n");
        sb.append("Order Date: ").append(orderDate).append("\n");
        sb.append("Payment Method: ").append(paymentMethod).append("\n");
        sb.append("Products:\n");
        for (Product product : products) {
            sb.append(product).append("\n");
        }
        return sb.toString();
    }
}

