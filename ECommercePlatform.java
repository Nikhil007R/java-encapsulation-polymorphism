import java.util.ArrayList;
import java.util.List;

// Taxable interface with required tax methods
interface Taxable {
    double calculateTax();

    String getTaxDetails();
}

// Abstract Product class with encapsulated fields and an abstract discount
// method
abstract class Product {
    private int productId;
    private String name;
    private double price;

    // Constructor to initialize product details
    public Product(int productId, String name, double price) {
        this.productId = productId;
        this.name = name;
        this.price = price;
    }

    // Getters and setters for encapsulation
    public int getProductId() {
        return productId;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    // Abstract method to calculate discount on the product
    public abstract double calculateDiscount();

    // Method to display basic product details
    public void displayDetails() {
        System.out.println("Product ID: " + productId);
        System.out.println("Name: " + name);
        System.out.println("Price: " + price);
        System.out.println("Discount: " + calculateDiscount());
    }
}

// Electronics class: discount is 10% and tax is 15% of price.
class Electronics extends Product implements Taxable {
    public Electronics(int productId, String name, double price) {
        super(productId, name, price);
    }

    @Override
    public double calculateDiscount() {
        return getPrice() * 0.10; // 10% discount
    }

    @Override
    public double calculateTax() {
        return getPrice() * 0.15; // 15% tax
    }

    @Override
    public String getTaxDetails() {
        return "15% tax for Electronics";
    }
}

// Clothing class: discount is 15% and tax is 12% of price.
class Clothing extends Product implements Taxable {
    public Clothing(int productId, String name, double price) {
        super(productId, name, price);
    }

    @Override
    public double calculateDiscount() {
        return getPrice() * 0.15; // 15% discount
    }

    @Override
    public double calculateTax() {
        return getPrice() * 0.12; // 12% tax
    }

    @Override
    public String getTaxDetails() {
        return "12% tax for Clothing";
    }
}

// Groceries class: discount is 5% of price and it is non-taxable.
class Groceries extends Product {
    public Groceries(int productId, String name, double price) {
        super(productId, name, price);
    }

    @Override
    public double calculateDiscount() {
        return getPrice() * 0.05; // 5% discount
    }
}

// Main class to demonstrate polymorphism and final price calculation
public class ECommercePlatform {
    public static void main(String[] args) {
        List<Product> products = new ArrayList<>();

        // Adding various product types to the list
        products.add(new Electronics(101, "Smartphone", 20000));
        products.add(new Clothing(102, "Designer Kurta", 3000));
        products.add(new Groceries(103, "Organic Rice", 150));
        products.add(new Electronics(104, "Laptop", 50000));
        products.add(new Clothing(105, "Silk Saree", 4000));
        products.add(new Groceries(106, "Fresh Milk", 50));

        // Calculate and print final price for each product: (price + tax - discount)
        for (Product prod : products) {
            double tax = 0;
            // Check if product is taxable
            if (prod instanceof Taxable) {
                tax = ((Taxable) prod).calculateTax();
            }
            double discount = prod.calculateDiscount();
            double finalPrice = prod.getPrice() + tax - discount;

            System.out.println("Product: " + prod.getName());
            System.out.println("Base Price: " + prod.getPrice());
            System.out.println("Tax: " + tax);
            System.out.println("Discount: " + discount);
            System.out.println("Final Price: " + finalPrice);
            System.out.println("--------------------------");
        }
    }
}
