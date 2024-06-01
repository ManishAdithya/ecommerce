import java.util.Scanner;

public class ECommerceApp {
    private static Product[] predefinedProducts;
    private static Cart cart;
    private static User user;
    private static OrderHistory orderHistory;

    public static void main(String[] args) {
        cart = new Cart();
        orderHistory = new OrderHistory();
        predefinedProducts = new Product[]{
            new Product("Laptop", 1500.0, "High-performance laptop"),
            new Product("Smartphone", 800.0, "Latest model smartphone"),
            new Product("Headphones", 150.0, "Noise-cancelling headphones"),
            new Product("Smartwatch", 200.0, "Feature-rich smartwatch"),
            new Product("Camera", 500.0, "DSLR camera"),
            new Product("Printer", 100.0, "Wireless printer"),
            new Product("Tablet", 300.0, "High-resolution tablet"),
            new Product("Monitor", 200.0, "4K monitor"),
            new Product("Keyboard", 50.0, "Mechanical keyboard"),
            new Product("Mouse", 30.0, "Wireless mouse")
        };

        Scanner scanner = new Scanner(System.in);

        // User login/registration
        System.out.print("Enter your name: ");
        String name = scanner.nextLine();
        System.out.print("Enter your email: ");
        String email = scanner.nextLine();
        user = new User(name, email);
        System.out.println("Welcome, " + user.getName());

        // Main application loop
        while (true) {
            try {
                System.out.println("\nWelcome to the E-Commerce App");
                System.out.println("1. Buy Items");
                System.out.println("2. View Cart");
                System.out.println("3. Remove Item from Cart");
                System.out.println("4. Make Payment");
                System.out.println("5. View Order History");
                System.out.println("6. Exit");
                System.out.print("Enter your choice: ");
                int choice = scanner.nextInt();

                switch (choice) {
                    case 1:
                        displayProducts();
                        System.out.print("Enter product number to add to cart: ");
                        int productNumber = scanner.nextInt();
                        if (productNumber > 0 && productNumber <= predefinedProducts.length) {
                            cart.addToCart(predefinedProducts[productNumber - 1]);
                        } else {
                            System.out.println("Invalid product number.");
                        }
                        break;
                    case 2:
                        cart.viewCart();
                        break;
                    case 3:
                        cart.viewCart();
                        System.out.print("Enter item number to remove from cart: ");
                        int itemNumber = scanner.nextInt();
                        cart.removeFromCart(itemNumber - 1);
                        break;
                    case 4:
                        System.out.println("Choose Payment Method:");
                        System.out.println("1. Google Pay");
                        System.out.println("2. PhonePe");
                        System.out.print("Enter your choice: ");
                        int paymentChoice = scanner.nextInt();
                        PaymentMethod paymentMethod;
                        if (paymentChoice == 1) {
                            paymentMethod = new GooglePay();
                        } else if (paymentChoice == 2) {
                            paymentMethod = new PhonePe();
                        } else {
                            System.out.println("Invalid payment method.");
                            break;
                        }
                        paymentMethod.pay();
                        Order order = new Order(user, cart.getCartItems(), paymentMethod.getClass().getSimpleName());
                        orderHistory.addOrder(order);
                        cart.clearCart();
                        break;
                    case 5:
                        orderHistory.viewOrderHistory();
                        break;
                    case 6:
                        System.out.println("Thank you for using the E-Commerce App. Goodbye!");
                        scanner.close();
                        System.exit(0);
                        break;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            } catch (Exception e) {
                System.out.println("An error occurred: " + e.getMessage());
                scanner.next();  // Clear the invalid input
            }
        }
    }

    private static void displayProducts() {
        System.out.println("\nAvailable Products:");
        for (int i = 0; i < predefinedProducts.length; i++) {
            System.out.println((i + 1) + ". " + predefinedProducts[i]);
        }
    }
}
