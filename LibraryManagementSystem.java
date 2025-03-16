import java.util.ArrayList;
import java.util.List;

// Interface Reservable with methods for reservation handling
interface Reservable {
    void reserveItem(String reserverName);

    boolean checkAvailability();
}

// Abstract LibraryItem class with encapsulated fields and methods
abstract class LibraryItem {
    private int itemId;
    private String title;
    private String author;

    public LibraryItem(int itemId, String title, String author) {
        this.itemId = itemId;
        this.title = title;
        this.author = author;
    }

    // Getters for encapsulated fields
    public int getItemId() {
        return itemId;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    // Concrete method to get item details
    public String getItemDetails() {
        return "ID: " + itemId + ", Title: " + title + ", Author: " + author;
    }

    // Abstract method to be implemented by subclasses for loan duration
    public abstract int getLoanDuration();
}

// Book subclass with specific loan duration and reservation implementation
class Book extends LibraryItem implements Reservable {
    private String reservedBy; // Encapsulated reservation detail

    public Book(int itemId, String title, String author) {
        super(itemId, title, author);
        this.reservedBy = null;
    }

    @Override
    public int getLoanDuration() {
        return 21; // Loan duration for a book is 21 days
    }

    @Override
    public void reserveItem(String reserverName) {
        if (checkAvailability()) {
            reservedBy = reserverName;
            System.out.println("Book reserved by " + reserverName);
        } else {
            System.out.println("Book is already reserved by " + reservedBy);
        }
    }

    @Override
    public boolean checkAvailability() {
        return reservedBy == null;
    }
}

// Magazine subclass with specific loan duration and reservation implementation
class Magazine extends LibraryItem implements Reservable {
    private String reservedBy; // Encapsulated reservation detail

    public Magazine(int itemId, String title, String author) {
        super(itemId, title, author);
        this.reservedBy = null;
    }

    @Override
    public int getLoanDuration() {
        return 7; // Loan duration for a magazine is 7 days
    }

    @Override
    public void reserveItem(String reserverName) {
        if (checkAvailability()) {
            reservedBy = reserverName;
            System.out.println("Magazine reserved by " + reserverName);
        } else {
            System.out.println("Magazine is already reserved by " + reservedBy);
        }
    }

    @Override
    public boolean checkAvailability() {
        return reservedBy == null;
    }
}

// DVD subclass with specific loan duration and reservation implementation
class DVD extends LibraryItem implements Reservable {
    private String reservedBy; // Encapsulated reservation detail

    public DVD(int itemId, String title, String author) {
        super(itemId, title, author);
        this.reservedBy = null;
    }

    @Override
    public int getLoanDuration() {
        return 14; // Loan duration for a DVD is 14 days
    }

    @Override
    public void reserveItem(String reserverName) {
        if (checkAvailability()) {
            reservedBy = reserverName;
            System.out.println("DVD reserved by " + reserverName);
        } else {
            System.out.println("DVD is already reserved by " + reservedBy);
        }
    }

    @Override
    public boolean checkAvailability() {
        return reservedBy == null;
    }
}

// Main class demonstrating polymorphism with LibraryItem references
public class LibraryManagementSystem {
    public static void main(String[] args) {
        List<LibraryItem> items = new ArrayList<>();

        // Create various library items
        LibraryItem book = new Book(1, "The Great Gatsby", "F. Scott Fitzgerald");
        LibraryItem magazine = new Magazine(2, "National Geographic", "Various");
        LibraryItem dvd = new DVD(3, "Inception", "Christopher Nolan");

        items.add(book);
        items.add(magazine);
        items.add(dvd);

        // Demonstrate polymorphism and reservation functionality
        for (LibraryItem item : items) {
            System.out.println(item.getItemDetails());
            System.out.println("Loan Duration: " + item.getLoanDuration() + " days");
            if (item instanceof Reservable) {
                Reservable reservableItem = (Reservable) item;
                System.out.println("Available: " + reservableItem.checkAvailability());
                reservableItem.reserveItem("John Doe");
                System.out.println("Available after reservation: " + reservableItem.checkAvailability());
            }
            System.out.println("-------------------------");
        }
    }
}
