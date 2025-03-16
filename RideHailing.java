import java.util.ArrayList;
import java.util.List;

// GPS interface with methods for getting and updating location
interface GPS {
    String getCurrentLocation();

    void updateLocation(String newLocation);
}

// Abstract Vehicle class implementing GPS and encapsulating vehicle details
abstract class Vehicle implements GPS {
    private String vehicleId;
    private String driverName;
    private double ratePerKm;
    private String currentLocation;

    public Vehicle(String vehicleId, String driverName, double ratePerKm, String initialLocation) {
        this.vehicleId = vehicleId;
        this.driverName = driverName;
        this.ratePerKm = ratePerKm;
        this.currentLocation = initialLocation;
    }

    // Getters for encapsulated fields
    public String getVehicleId() {
        return vehicleId;
    }

    public String getDriverName() {
        return driverName;
    }

    public double getRatePerKm() {
        return ratePerKm;
    }

    // Concrete method to display vehicle details
    public String getVehicleDetails() {
        return "Vehicle ID: " + vehicleId + ", Driver: " + driverName + ", Rate/Km: " + ratePerKm +
                ", Current Location: " + currentLocation;
    }

    // Abstract method for calculating fare based on distance
    public abstract double calculateFare(double distance);

    // Implementation of GPS interface methods
    @Override
    public String getCurrentLocation() {
        return currentLocation;
    }

    @Override
    public void updateLocation(String newLocation) {
        this.currentLocation = newLocation;
    }
}

// Car subclass: calculates fare using the base rate
class Car extends Vehicle {
    public Car(String vehicleId, String driverName, double ratePerKm, String initialLocation) {
        super(vehicleId, driverName, ratePerKm, initialLocation);
    }

    @Override
    public double calculateFare(double distance) {
        return distance * getRatePerKm();
    }
}

// Bike subclass: calculates fare with a 20% discount on the base rate
class Bike extends Vehicle {
    public Bike(String vehicleId, String driverName, double ratePerKm, String initialLocation) {
        super(vehicleId, driverName, ratePerKm, initialLocation);
    }

    @Override
    public double calculateFare(double distance) {
        return distance * getRatePerKm() * 0.8;
    }
}

// Auto subclass: calculates fare with a 10% surcharge on the base rate
class Auto extends Vehicle {
    public Auto(String vehicleId, String driverName, double ratePerKm, String initialLocation) {
        super(vehicleId, driverName, ratePerKm, initialLocation);
    }

    @Override
    public double calculateFare(double distance) {
        return distance * getRatePerKm() * 1.1;
    }
}

// Main class to demonstrate polymorphism in ride-hailing fare calculation
public class RideHailing {
    public static void main(String[] args) {
        List<Vehicle> vehicles = new ArrayList<>();

        // Create instances of different vehicle types
        vehicles.add(new Car("CAR123", "Amit Yadav", 15.0, "Downtown"));
        vehicles.add(new Bike("BIKE456", "Ravi Bishnoi", 10.0, "Uptown"));
        vehicles.add(new Auto("AUTO789", "Aman Rawat", 12.0, "Suburbs"));

        double distance = 10.0; // Distance in kilometers

        // Process each vehicle polymorphically
        for (Vehicle v : vehicles) {
            System.out.println(v.getVehicleDetails());
            System.out.println("Calculated Fare for " + distance + " km: " + v.calculateFare(distance));
            // Update and display current location as demonstration of GPS functionality
            v.updateLocation("Central Station");
            System.out.println("Updated Location: " + v.getCurrentLocation());
            System.out.println("-------------------------------");
        }
    }
}
