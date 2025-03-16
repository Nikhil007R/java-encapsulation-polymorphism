import java.util.ArrayList;
import java.util.List;

// Insurable interface with methods to calculate and get insurance details
interface Insurable {
    double calculateInsurance();

    String getInsuranceDetails();
}

// Abstract Vehicle class with encapsulated fields and an abstract rental cost
// method
abstract class Vehicle {
    private String vehicleNumber;
    private String type;
    private double rentalRate;

    public Vehicle(String vehicleNumber, String type, double rentalRate) {
        this.vehicleNumber = vehicleNumber;
        this.type = type;
        this.rentalRate = rentalRate;
    }

    // Getters and setters
    public String getVehicleNumber() {
        return vehicleNumber;
    }

    public void setVehicleNumber(String vehicleNumber) {
        this.vehicleNumber = vehicleNumber;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getRentalRate() {
        return rentalRate;
    }

    public void setRentalRate(double rentalRate) {
        this.rentalRate = rentalRate;
    }

    // Abstract method for calculating rental cost over a number of days
    public abstract double calculateRentalCost(int days);
}

// Car class extends Vehicle and implements Insurable
class Car extends Vehicle implements Insurable {
    private String insurancePolicyNumber; // sensitive detail

    public Car(String vehicleNumber, double rentalRate, String insurancePolicyNumber) {
        super(vehicleNumber, "Car", rentalRate);
        this.insurancePolicyNumber = insurancePolicyNumber;
    }

    @Override
    public double calculateRentalCost(int days) {
        return getRentalRate() * days;
    }

    @Override
    public double calculateInsurance() {
        // Example: 5% of one day's rental rate as insurance fee
        return getRentalRate() * 0.05;
    }

    @Override
    public String getInsuranceDetails() {
        return "Car Insurance Policy: " + insurancePolicyNumber;
    }
}

// Bike class extends Vehicle and implements Insurable
class Bike extends Vehicle implements Insurable {
    private String insurancePolicyNumber;

    public Bike(String vehicleNumber, double rentalRate, String insurancePolicyNumber) {
        super(vehicleNumber, "Bike", rentalRate);
        this.insurancePolicyNumber = insurancePolicyNumber;
    }

    @Override
    public double calculateRentalCost(int days) {
        return getRentalRate() * days;
    }

    @Override
    public double calculateInsurance() {
        // Example: 3% of one day's rental rate
        return getRentalRate() * 0.03;
    }

    @Override
    public String getInsuranceDetails() {
        return "Bike Insurance Policy: " + insurancePolicyNumber;
    }
}

// Truck class extends Vehicle and implements Insurable
class Truck extends Vehicle implements Insurable {
    private String insurancePolicyNumber;

    public Truck(String vehicleNumber, double rentalRate, String insurancePolicyNumber) {
        super(vehicleNumber, "Truck", rentalRate);
        this.insurancePolicyNumber = insurancePolicyNumber;
    }

    @Override
    public double calculateRentalCost(int days) {
        // Trucks incur an extra 20% cost
        return getRentalRate() * days * 1.2;
    }

    @Override
    public double calculateInsurance() {
        // Example: 10% of one day's rental rate
        return getRentalRate() * 0.10;
    }

    @Override
    public String getInsuranceDetails() {
        return "Truck Insurance Policy: " + insurancePolicyNumber;
    }
}

// Main class to demonstrate polymorphism in the vehicle rental system
public class VehicleRentalSystem {
    public static void main(String[] args) {
        List<Vehicle> vehicles = new ArrayList<>();

        // Creating vehicles with sample data
        vehicles.add(new Car("KA01AB1234", 1000, "CAR-INS-001"));
        vehicles.add(new Bike("KA02CD5678", 500, "BIKE-INS-002"));
        vehicles.add(new Truck("KA03EF9101", 2000, "TRUCK-INS-003"));

        int rentalDays = 5;

        // Iterating over vehicles to calculate and display rental and insurance costs
        for (Vehicle v : vehicles) {
            System.out.println("Vehicle Number: " + v.getVehicleNumber());
            System.out.println("Type: " + v.getType());
            System.out.println("Rental Cost for " + rentalDays + " days: " + v.calculateRentalCost(rentalDays));
            if (v instanceof Insurable) {
                Insurable ins = (Insurable) v;
                System.out.println("Insurance Cost: " + ins.calculateInsurance());
                System.out.println("Insurance Details: " + ins.getInsuranceDetails());
            }
            System.out.println("--------------------------");
        }
    }
}
