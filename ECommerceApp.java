import java.util.ArrayList;
import java.util.HashSet;
import java.util.InputMismatchException;
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

        System.out.println("|---------------------------------------------------------|");
        System.out.println("|            Welcome to the HDSM APP!                     |");
        System.out.println("|---------------------------------------------------------|\n\n");

        run(scanner);

        scanner.close();
    }

    public static void run(Scanner scanner) {
        boolean isRunning = true;
        while (isRunning) {
            System.out.println("|---------------------------------------------------------|");
            System.out.println("|                   Main Menu                             |");
            System.out.println("|---------------------------------------------------------|");
            System.out.println("|  Press 1 for Customer Login                             |");
            System.out.println("|  Press 2 for Admin Login                                |");
            System.out.println("|---------------------------------------------------------|\n");
            System.out.print("Enter your choice: ");
            try {
                int choice = scanner.nextInt();
                scanner.nextLine();

                switch (choice) {
                    case 1:
                        handleCustomerLogin(scanner);
                        break;
                    case 2:
                        AdminPage adminPage = new AdminPage(productList);
                        adminPage.main(scanner);
                        break;
                    default:
                        System.out.println("Invalid choice. Try again.");
                        break;
                }
            } catch (InputMismatchException e) {
                System.out.println("Input should be a number.");
                scanner.nextLine(); 
            }
        }
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
        System.out.println("\n|---------------------------------------------------------|");
        System.out.println("|                  Customer Login                         |");
        System.out.println("|---------------------------------------------------------|");
        System.out.println("|  1. Existing User                                       |");
        System.out.println("|  2. New User                                            |");
        System.out.println("|---------------------------------------------------------|\n");
        System.out.print("Enter your choice: ");
        try {
            int choice = scanner.nextInt();
            scanner.nextLine(); 

            switch (choice) {
                case 1:
                    handleExistingUser(scanner);
                    break;
                case 2:
                    handleNewUser(scanner);
                    break;
                default:
                    System.out.println("Invalid choice. Try again.");
                    break;
            }
        } catch (InputMismatchException e) {
            System.out.println("Input should be a number.");
            scanner.nextLine();  
        }
    }

    private static void handleExistingUser(Scanner scanner) {
        System.out.print("Enter your username: ");
        String username = scanner.nextLine();
        System.out.print("Enter your password: ");
        String password = scanner.nextLine();
        user = new User(username, username); 
        System.out.println("\nWELCOME, " + user.getName().toUpperCase());
        customerMenu(scanner);
    }

    private static void handleNewUser(Scanner scanner) {
        System.out.print("Enter your name: ");
        String name = scanner.nextLine();
        System.out.print("Enter your email: ");
        String email = scanner.nextLine();
        String password, confirmPassword;

        do {
            System.out.print("Enter your password: ");
            password = scanner.nextLine();
            System.out.print("Re-enter your password: ");
            confirmPassword = scanner.nextLine();

            if (!password.equals(confirmPassword)) {
                System.out.println("Passwords do not match. Try again.");
            }
        } while (!password.equals(confirmPassword));

        user = new User(name, email);
        System.out.println("Welcome, " + user.getName());
        customerMenu(scanner);
    }

    private static void customerMenu(Scanner scanner) {
        boolean exit = false;
        while (!exit) {
            System.out.println("\n|---------------------------------------------------------|");
            System.out.println("|                   Customer Menu                         |");
            System.out.println("|---------------------------------------------------------|");
            System.out.println("|  1. View Products                                       |");
            System.out.println("|  2. Add to Cart                                         |");
            System.out.println("|  3. Remove from Cart                                    |");
            System.out.println("|  4. View Cart                                           |");
            System.out.println("|  5. Make Payment                                        |");
            System.out.println("|  6. View Order History                                  |");
            System.out.println("|  7. Customer Care                                       |");
            System.out.println("|  8. Logout                                              |");
            System.out.println("|---------------------------------------------------------|");
            System.out.print("Enter your choice: ");
            try {
                int choice = scanner.nextInt();
                scanner.nextLine(); 

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
                        if (cart.getCartItems().isEmpty()) {
                            System.out.println("Order First");
                        } else {
                            makePayment(scanner);
                        }
                        break;
                    case 6:
                        orderHistory.viewOrderHistory();
                        break;
                    case 7:
                        handleCustomerCare(scanner);
                        break;                    
                    case 8:
                        exit = true;
                        System.out.println("Logout successful.");
                        break;
                    default:
                        System.out.println("Invalid choice. Try again.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Input should be a number.");
                scanner.nextLine(); 
            }
        }
    }

    private static void displayProducts() {
        System.out.println("\nAvailable Products:\n");
        System.out.printf("%-5s %-20s %-10s %-30s\n", "No.", "Product Name", "Price", "Description");
        System.out.println("-------------------------------------------------------------");

        HashSet<String> printedProductNames = new HashSet<>();
        int displayIndex = 1;

        for (Product product : productList) {
            if (!printedProductNames.contains(product.getProductName())) {
                printedProductNames.add(product.getProductName());
                System.out.printf("%-5d %-20s $%-9.2f %-30s\n", displayIndex++, product.getProductName(), product.getPrice(), product.getDescription());
            }
        }
        System.out.println();
    }

    private static void addToCart(Scanner scanner) {
        displayProducts();

        System.out.println("\n|---------------------------------------------------------|");
        System.out.println("|                     Add to Cart                         |");
        System.out.println("|---------------------------------------------------------|\n");
        System.out.print("Enter S.No of the product: ");
        try {
            int index = scanner.nextInt();
            scanner.nextLine(); 

            if (index >= 1 && index <= productList.size()) {
                Product selectedProduct = productList.get(index - 1);
                cart.addToCart(selectedProduct);
            } else {
                System.out.println("Invalid S. No.");
            }
        } catch (InputMismatchException e) {
            System.out.println("Input should be a number.");
            scanner.nextLine();  // Consume invalid input
        }
    }

    private static void removeFromCart(Scanner scanner) {
        cart.viewCart();
        System.out.println("\n|---------------------------------------------------------|");
        System.out.println("|                  Remove from Cart                       |");
        System.out.println("|---------------------------------------------------------|\n");
        System.out.print("Enter product index to remove from cart: ");
        try {
            int index = scanner.nextInt();
            scanner.nextLine(); 

            if (index >= 1 && index <= cart.getCartItems().size()) {
                cart.removeFromCart(index - 1);
            } else {
                System.out.println("Invalid product index.");
            }
        } catch (InputMismatchException e) {
            System.out.println("Input should be a number.");
            scanner.nextLine(); 
        }
    }

    private static void makePayment(Scanner scanner) {
        System.out.println("Choose Payment Method:");
        System.out.println("1. Google Pay");
        System.out.println("2. PhonePe");
        System.out.println("3. Paytm");
        System.out.println("4. Credit Card");
        System.out.println("5. Debit Card");
        System.out.print("Enter your choice: ");
        try {
            int paymentChoice = scanner.nextInt();
            PaymentMethod paymentMethod;
            scanner.nextLine();

            if (paymentChoice == 1) {
                System.out.print("Enter UPI ID for Google Pay: ");
                String upiId = scanner.nextLine();
                paymentMethod = new GooglePay(upiId);
            } else if (paymentChoice == 2) {
                System.out.print("Enter UPI ID for PhonePe: ");
                String upiId = scanner.nextLine();
                paymentMethod = new PhonePe(upiId);
            } else if (paymentChoice == 3) {
                System.out.print("Enter UPI ID for Paytm: ");
                String upiId = scanner.nextLine();
                paymentMethod = new paytm(upiId);
            } else if (paymentChoice == 4) {
                paymentMethod = createCardPayment(scanner, "Credit Card");
            } else if (paymentChoice == 5) {
                paymentMethod = createCardPayment(scanner, "Debit Card");
            } else {
                System.out.println("Invalid payment method.");
                return;
            }

            if (paymentMethod != null) {
                paymentMethod.pay();
                System.out.println("\n|-------------------------------------------------------|");
                System.out.println("|                       BILL                            |");
                System.out.println("|-------------------------------------------------------|");
                System.out.println("| ITEM                     | PRICE                      |");
                System.out.println("|--------------------------|----------------------------|");
                double total = 0;
                for (Product product : cart.getCartItems()) {
                    System.out.printf("| %-25s | %-26.2f |\n", product.getProductName(), product.getPrice());
                    total += product.getPrice();
                }
                System.out.println("|--------------------------|----------------------------|");
                System.out.printf("| TOTAL                    | %-26.2f |\n", total);
                System.out.println("|-------------------------------------------------------|\n");

                Order order = new Order(user, cart.getCartItems(), paymentMethod.getClass().getSimpleName());
                orderHistory.addOrder(order);
                cart.clearCart();
            }
        } catch (InputMismatchException e) {
            System.out.println("Input should be a number.");
            scanner.nextLine();  
        }
    }

    private static PaymentMethod createCardPayment(Scanner scanner, String cardType) {
        try {
            System.out.print("Enter Account Holder's Name: ");
            String accountHolderName = scanner.nextLine();
            String cardNumber;
            while (true) {
                System.out.print("Enter " + cardType + " Number (16 digits): ");
                cardNumber = scanner.nextLine();
                if (cardNumber.length() == 16 && cardNumber.matches("\\d+")) {
                    break;
                } else {
                    System.out.println("Invalid card number. Please re-enter.");
                }
            }
            int cvv;
            while (true) {
                System.out.print("Enter CVV number (3 digits): ");
                cvv = scanner.nextInt();
                if (String.valueOf(cvv).length() == 3) {
                    break;
                } else {
                    System.out.println("Invalid CVV. Please re-enter.");
                }
            }
            scanner.nextLine();  
            if (cardType.equals("Credit Card")) {
                return new CreditCard(accountHolderName, cardNumber, cvv);
            } else {
                return new DebitCard(accountHolderName, cardNumber, cvv);
            }
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please try again.");
            scanner.next(); 
            return null;
        }
    }

    private static void handleCustomerCare(Scanner scanner) {
        System.out.println("\n|---------------------------------------------------------|");
        System.out.println("|                                                         |");
        System.out.println("|                 Customer Care                           |");
        System.out.println("|---------------------------------------------------------|");
        System.out.println("|  1. Contact Customer Care                               |");
        System.out.println("|  2. View FAQs                                           |");
        System.out.println("|  3. Leave Feedback                                      |");
        System.out.println("|---------------------------------------------------------|\n");
        System.out.print("Enter your choice: ");
        try {
            int customerCareChoice = scanner.nextInt();
            scanner.nextLine();  

            switch (customerCareChoice) {
                case 1:
                    System.out.println("Please call our customer care at 1233456789 for assistance.");
                    break;
                case 2:
                    viewFAQs();
                    break;
                case 3:
                    leaveFeedback(scanner);
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        } catch (InputMismatchException e) {
            System.out.println("\nInput should be a number.\n");
            scanner.nextLine();  
        }
    }

    private static void viewFAQs() {
        System.out.println("\nFrequently Asked Questions:");
        System.out.println("Q: How can I track my order?");
        System.out.println("A: You can track your order by logging into your account and accessing the order history section.\n");

        System.out.println("Q: What is your return policy?");
        System.out.println("A: Our return policy allows returns within 30 days of purchase, subject to certain conditions.\n");


    }

    private static void leaveFeedback(Scanner scanner) {
        System.out.println("\nLeave Feedback:");
        System.out.println("Please provide your feedback below:");
        String feedback = scanner.nextLine();
        System.out.println("Thank you for your feedback, we will try to fix it as soon as possible.");
    }
}
