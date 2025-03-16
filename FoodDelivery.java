import java.util.ArrayList;
import java.util.List;

// Discountable interface with methods for discount functionality
interface Discountable {
    // Returns the discount amount applicable on the total price
    double applyDiscount();

    // Returns a description of the discount applied
    String getDiscountDetails();
}

// Abstract FoodItem class with encapsulated fields and abstract method to
// calculate total price
abstract class FoodItem {
    private String itemName;
    private double price;
    private int quantity;

    public FoodItem(String itemName, double price, int quantity) {
        this.itemName = itemName;
        this.price = price;
        this.quantity = quantity;
    }

    // Getters for encapsulated fields
    public String getItemName() {
        return itemName;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    // Abstract method to calculate the total price of the food item
    public abstract double calculateTotalPrice();

    // Concrete method to return item details
    public String getItemDetails() {
        return "Item: " + itemName + ", Price: " + price + ", Quantity: " + quantity;
    }
}

// VegItem class extends FoodItem and implements Discountable
class VegItem extends FoodItem implements Discountable {

    public VegItem(String itemName, double price, int quantity) {
        super(itemName, price, quantity);
    }

    // Total price for veg items is simply price * quantity
    @Override
    public double calculateTotalPrice() {
        return getPrice() * getQuantity();
    }

    // Applies a 5% discount on the total price
    @Override
    public double applyDiscount() {
        return calculateTotalPrice() * 0.05;
    }

    @Override
    public String getDiscountDetails() {
        return "5% discount on Veg Item";
    }
}

// NonVegItem class extends FoodItem and implements Discountable
class NonVegItem extends FoodItem implements Discountable {

    public NonVegItem(String itemName, double price, int quantity) {
        super(itemName, price, quantity);
    }

    // Total price for non-veg items includes an additional 10% charge
    @Override
    public double calculateTotalPrice() {
        return getPrice() * getQuantity() * 1.10; // 10% extra charge
    }

    // Applies a 3% discount on the total price
    @Override
    public double applyDiscount() {
        return calculateTotalPrice() * 0.03;
    }

    @Override
    public String getDiscountDetails() {
        return "3% discount on Non-Veg Item";
    }
}

// Main class to demonstrate polymorphism in order processing for online food
// delivery
public class FoodDelivery {
    public static void main(String[] args) {
        List<FoodItem> orderItems = new ArrayList<>();

        // Adding veg and non-veg items to the order
        orderItems.add(new VegItem("Paneer Tikka", 250, 2));
        orderItems.add(new NonVegItem("Chicken Biryani", 300, 1));
        orderItems.add(new VegItem("Veg Burger", 150, 3));
        orderItems.add(new NonVegItem("Mutton Curry", 500, 1));

        // Processing order items polymorphically
        for (FoodItem item : orderItems) {
            System.out.println(item.getItemDetails());
            double totalPrice = item.calculateTotalPrice();
            double discount = 0;
            String discountDetails = "No discount applicable";

            // Check if item supports discounting
            if (item instanceof Discountable) {
                Discountable discountableItem = (Discountable) item;
                discount = discountableItem.applyDiscount();
                discountDetails = discountableItem.getDiscountDetails();
            }
            double finalPrice = totalPrice - discount;
            System.out.println("Total Price (with additional charges if any): " + totalPrice);
            System.out.println("Discount (" + discountDetails + "): " + discount);
            System.out.println("Final Price: " + finalPrice);
            System.out.println("--------------------------");
        }
    }
}
