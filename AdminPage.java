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
        adminList.add(new User("samay", "samay@2006"));
        adminList.add(new User("dilip", "dilip@2005"));
        adminList.add(new User("harish", "harish@2006"));
        adminList.add(new User("manish", "manish@2005"));

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
            if (admin.getName().equals(username) && admin.getpassword().equals(password)) {
                currentAdmin = admin;
                return true;
            }
        }
        return false;
    }

    private static void showAdminMenu(Scanner scanner) {
        while (isLoggedIn) {
            System.out.println("\n|---------------------------------------------------------|");
            System.out.println("|                   Admin Menu                            |");
            System.out.println("|---------------------------------------------------------|");
            System.out.println("|  1. View Products                                       |");
            System.out.println("|  2. Add Product                                         |");
            System.out.println("|  3. Logout                                              |");
            System.out.println("|---------------------------------------------------------|");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline character

            switch (choice) {
                case 1:
                    displayProducts();
                    break;
                case 2:
                    addProduct(scanner);
                    break;
                case 4:
                    isLoggedIn = false;
                    currentAdmin = null;
                    System.out.println("Logout successful.");
                    ECommerceApp.main(new String[]{});
                    return;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }

    private static void displayProducts() {
        System.out.println("\nAvailable Products:\n");
        System.out.printf("%-5s %-20s %-10s %-30s\n", "No.", "Product Name", "Price", "Description");
        System.out.println("-------------------------------------------------------------");
        for (int i = 0; i < productList.size(); i++) {
            Product product = productList.get(i);
            System.out.printf("%-5d %-20s $%-9.2f %-30s\n", (i + 1), product.getProductName(), product.getPrice(), product.getDescription());
        }
        System.out.println();
    }

    private static void addProduct(Scanner scanner) {
        System.out.print("Enter product name: ");
        String productName = scanner.nextLine();
        System.out.print("Enter price: ");
        double price = scanner.nextDouble();
        scanner.nextLine(); 
        System.out.print("Enter description: ");
        String description = scanner.nextLine();

        Product product = new Product(productName, price, description);
        productList.add(product);
        System.out.println("Product added successfully!");
    }
}
