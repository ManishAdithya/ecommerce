import java.util.ArrayList;
import java.util.Scanner;

public class ECommerceApp {
    private static ArrayList<Product> productList = new ArrayList<>();
    private static Cart cart;
    private static User user;
    private static OrderHistory orderHistory;

    public static void main(String[] args) {
        cart = new Cart();
        orderHistory = new OrderHistory();
        initializeProducts();

        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to the HDSM APP!");
        boolean isLoggedIn = false;

        while (!isLoggedIn) {
            System.out.println("Press 1 for Customer Login");
            System.out.println("Press 2 for Admin Login");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline character

            switch (choice) {
                case 1:
                    handleCustomerLogin(scanner);
                    customerMenu(scanner);
                    isLoggedIn = true;
                    break;
                case 2:
                    AdminPage adminPage = new AdminPage(productList);
                    adminPage.main(scanner);
                    isLoggedIn = true;
                    break;
                default:
                    System.out.println("Invalid choice. Try again.");
                    break;
            }
        }

        scanner.close();
    }

    private static void initializeProducts() {
        productList.add(new Product("Laptop", 1500.0, "High-performance laptop"));
        productList.add(new Product("Smartphone", 800.0, "Latest model smartphone"));
        productList.add(new Product("Headphones", 150.0, "Noise-cancelling headphones"));
        productList.add(new Product("Smartwatch", 200.0, "Feature-rich smartwatch"));
        productList.add(new Product("Camera", 500.0, "DSLR camera"));
        productList.add(new Product("Printer", 100.0, "Wireless printer"));
        productList.add(new Product("Tablet", 300.0, "High-resolution tablet"));
        productList.add(new Product("Monitor", 200.0, "4K monitor"));
        productList.add(new Product("Keyboard", 50.0, "Mechanical keyboard"));
        productList.add(new Product("Mouse", 30.0, "Wireless mouse"));
    }

    private static void handleCustomerLogin(Scanner scanner) {
        System.out.print("Enter your name: ");
        String name = scanner.nextLine();
        System.out.print("Enter your email: ");
        String email = scanner.nextLine();
        user = new User(name, email);
        System.out.println("Welcome, " + user.getName());
    }

    private static void customerMenu(Scanner scanner) {
        boolean exit = false;
        while (!exit) {
            System.out.println("\nCustomer Menu:");
            System.out.println("1. View Products");
            System.out.println("2. Add to Cart");
            System.out.println("3. Remove from Cart");
            System.out.println("4. View Cart");
            System.out.println("5. Make Payment");
            System.out.println("6. View Order History");
            System.out.println("7. Logout");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline character

            switch (choice) {
                case 1:
                    displayProducts();
                    break;
                case 2:
                    addToCart(scanner);
                    break;
                case 3:
                    removeFromCart(scanner);
                    break;
                case 4:
                    cart.viewCart();
                    break;
                case 5:
                    makePayment(scanner);
                    break;
                case 6:
                    orderHistory.viewOrderHistory();
                    break;
                case 7:
                    exit = true;
                    System.out.println("Logout successful.");
                    break;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }

    private static void displayProducts() {
        System.out.println("\nAvailable Products:");
        for (int i = 0; i < productList.size(); i++) {
            System.out.println((i + 1) + ". " + productList.get(i));
        }
    }

    private static void addToCart(Scanner scanner) {
        displayProducts();
        System.out.print("Enter product index to add to cart: ");
        int index = scanner.nextInt();
        scanner.nextLine(); // Consume newline character

        if (index >= 1 && index <= productList.size()) {
            Product selectedProduct = productList.get(index - 1);
            cart.addToCart(selectedProduct);
        } else {
            System.out.println("Invalid product index.");
        }
    }

    private static void removeFromCart(Scanner scanner) {
        cart.viewCart();
        System.out.print("Enter product index to remove from cart: ");
        int index = scanner.nextInt();
        scanner.nextLine(); // Consume newline character

        if (index >= 1 && index <= cart.getCartItems().size()) {
            cart.removeFromCart(index - 1);
        } else {
            System.out.println("Invalid product index.");
        }
    }

    private static void makePayment(Scanner scanner) {
        if (cart.getCartItems().isEmpty()) {
            System.out.println("Your cart is empty. Add items to cart before payment.");
            return;
        }

        System.out.println("Select payment method:");
        System.out.println("1. Google Pay");
        System.out.println("2. PhonePe");
        System.out.print("Enter your choice: ");
        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume newline character

        PaymentMethod paymentMethod;
        switch (choice) {
            case 1:
                paymentMethod = new GooglePay();
                break;
            case 2:
                paymentMethod = new PhonePe();
                break;
            default:
                System.out.println("Invalid payment method choice.");
                return;
        }

        Order order = new Order(user, cart.getCartItems(), paymentMethod.getClass().getSimpleName());
        paymentMethod.pay();
        orderHistory.addOrder(order);
        cart.clearCart();
    }
}