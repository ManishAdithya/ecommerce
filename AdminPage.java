import java.util.ArrayList;
import java.util.Scanner;

class AdminPage {
    private static ArrayList<Product> productList;
    private static ArrayList<User> adminList = new ArrayList<>();
    private static boolean isLoggedIn = false;
    private static User currentAdmin = null;

    public AdminPage(ArrayList<Product> products) {
        productList = products;
    }
    public void main(Scanner scanner) {
        // Add admin users
        adminList.add(new User("samay", "samay"));
        adminList.add(new User("Admin2", "admin2@example.com"));
        adminList.add(new User("Admin3", "admin3@example.com"));
        adminList.add(new User("Admin4", "admin4@example.com"));

        while (true) {
            if (!isLoggedIn) {
                System.out.print("Enter username: ");
                String username = scanner.nextLine();
                System.out.print("Enter password: ");
                String password = scanner.nextLine();

                if (authenticate(username, password)) {
                    isLoggedIn = true;
                    System.out.println("Login successful!");
                    showAdminMenu(scanner);
                } else {
                    System.out.println("Invalid username or password. Try again.");
                }
            } else {
                System.out.println("You are already logged in.");
                showAdminMenu(scanner);
            }
        }
    }

    private static boolean authenticate(String username, String password) {
        for (User admin : adminList) {
            if (admin.getName().equals(username) && admin.getEmail().equals(password)) {
                currentAdmin = admin;
                return true;
            }
        }
        return false;
    }

    private static void showAdminMenu(Scanner scanner) {
        while (isLoggedIn) {
            System.out.println("\nAdmin Menu:");
            System.out.println("1. Add Product");
            System.out.println("2. View Products");
            System.out.println("3. Logout");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline character

            switch (choice) {
                case 1:
                    addProduct(scanner);
                    break;
                case 2:
                    viewProducts();
                    break;
                case 3:
                    isLoggedIn = false;
                    currentAdmin = null;
                    System.out.println("Logout successful.");
                    break;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }

    private static void addProduct(Scanner scanner) {
        System.out.print("Enter product name: ");
        String productName = scanner.nextLine();
        System.out.print("Enter price: ");
        double price = scanner.nextDouble();
        scanner.nextLine(); // Consume newline character
        System.out.print("Enter description: ");
        String description = scanner.nextLine();

        Product product = new Product(productName, price, description);
        productList.add(product);
        System.out.println("Product added successfully!");
    }

    private static void viewProducts() {
        if (productList.isEmpty()) {
            System.out.println("No products available.");
        } else {
            System.out.println("Available Products:");
            for (Product product : productList) {
                System.out.println(product.toString());
            }
        }
    }
}