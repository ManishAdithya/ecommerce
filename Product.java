
public class Product {
    private String productName;
    private double price;
    private String description;

    public Product(String productName, double price, String description) {
        this.productName = productName;
        this.price = price;
        this.description = description;
    }

    public String getProductName() {
        return productName;
    }

    public double getPrice() {
        return price;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return "Product Name: " + productName + ", Price: " + price + ", Description: " + description;
    }
}
