import java.util.ArrayList;

public class Product {
    private String productName;
    private double price;
    private String description;
    private static ArrayList<Product> productList = new ArrayList<>();

    public Product(String productName, double price, String description) {
        this.productName = productName;
        this.price = price;
        this.description = description;
        productList.add(this);
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

    public static ArrayList<Product> getProductList() {
        return productList;
    }

    @Override
    public String toString() {
        return "Product Name: " + productName + ", Price: " + price + ", Description: " + description;
    }
}