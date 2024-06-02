import java.util.ArrayList;

public class Cart {
    private ArrayList<Product> cartItems;

    public Cart() {
        this.cartItems = new ArrayList<>();
    }

    public void addToCart(Product product) {
        cartItems.add(product);
        System.out.println(product.getProductName() + " added to cart.");
    }

    public void removeFromCart(int index) {
        if (index >= 0 && index < cartItems.size()) {
            Product removedProduct = cartItems.remove(index);
            System.out.println(removedProduct.getProductName() + " removed from cart.");
        } else {
            System.out.println("Invalid index. No item removed.");
        }
    }

    public void viewCart() {
        if (cartItems.isEmpty()) {
            System.out.println("Your cart is empty.");
        } else {
            System.out.println("Items in your cart:");
            System.out.printf("%-5s %-20s %-10s %-30s\n", "No.", "Product Name", "Price", "Description");
            System.out.println("-------------------------------------------------------------");
            for (int i = 0; i < cartItems.size(); i++) {
                Product product = cartItems.get(i);
                System.out.printf("%-5d %-20s $%-9.2f %-30s\n", (i + 1), product.getProductName(), product.getPrice(), product.getDescription());
            }
            System.out.println();
        }
    }

    public double calculateTotal() {
        double total = 0;
        for (Product product : cartItems) {
            total += product.getPrice();
        }
        return total;
    }

    public boolean isEmpty() {
        return cartItems.isEmpty();
    }

    public ArrayList<Product> getCartItems() {
        return new ArrayList<>(cartItems);
    }

    public void clearCart() {
        cartItems.clear();
    }
}
