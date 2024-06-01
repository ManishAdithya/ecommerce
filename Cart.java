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
            for (int i = 0; i < cartItems.size(); i++) {
                System.out.println((i + 1) + ". " + cartItems.get(i));
            }
        }
    }

    public void makePayment(String paymentMethod) {
        if (cartItems.isEmpty()) {
            System.out.println("Your cart is empty. Add items to cart before payment.");
            return;
        }
        System.out.println("Payment done using " + paymentMethod + ". Order will arrive after 7 days.");
        cartItems.clear();
    }


    public ArrayList<Product> getCartItems() {
        return new ArrayList<>(cartItems);
    }

    public void clearCart() {
        cartItems.clear();
    }
}
