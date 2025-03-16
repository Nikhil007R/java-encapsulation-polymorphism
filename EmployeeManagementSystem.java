import java.util.ArrayList;
import java.util.List;

// Department interface with required methods
interface Department {
    void assignDepartment(String department);

    String getDepartmentDetails();
}

// Abstract Employee class implementing Department for common department
// operations
abstract class Employee implements Department {
    private int employeeId;
    private String name;
    private double baseSalary;
    private String department; // Encapsulated department info

    // Constructor to initialize common fields
    public Employee(int employeeId, String name, double baseSalary) {
        this.employeeId = employeeId;
        this.name = name;
        this.baseSalary = baseSalary;
    }

    // Getter and setter methods for encapsulation
    public int getEmployeeId() {
        return employeeId;
    }

    public String getName() {
        return name;
    }

    public double getBaseSalary() {
        return baseSalary;
    }

    // Implementation of Department interface methods
    @Override
    public void assignDepartment(String department) {
        this.department = department;
    }

    @Override
    public String getDepartmentDetails() {
        return (department != null ? department : "Not Assigned");
    }

    // Abstract method to calculate salary
    public abstract double calculateSalary();

    // Concrete method to display employee details
    public void displayDetails() {
        System.out.println("Employee ID: " + employeeId);
        System.out.println("Name: " + name);
        System.out.println("Department: " + getDepartmentDetails());
        System.out.println("Salary: " + calculateSalary());
        System.out.println("--------------------------");
    }
}

// FullTimeEmployee calculates salary as a fixed base salary.
class FullTimeEmployee extends Employee {

    public FullTimeEmployee(int employeeId, String name, double baseSalary) {
        super(employeeId, name, baseSalary);
    }

    @Override
    public double calculateSalary() {
        return getBaseSalary();
    }
}

// PartTimeEmployee calculates salary based on work hours and hourly rate.
class PartTimeEmployee extends Employee {
    private int workHours;
    private double hourlyRate;

    public PartTimeEmployee(int employeeId, String name, int workHours, double hourlyRate) {
        super(employeeId, name, 0); // Base salary is not used for part-time employees
        this.workHours = workHours;
        this.hourlyRate = hourlyRate;
    }

    @Override
    public double calculateSalary() {
        return workHours * hourlyRate;
    }
}

// Main class to demonstrate the employee management system

public class EmployeeManagementSystem {
    public static void main(String[] args) {
        List<Employee> employees = new ArrayList<>();

        // Creating full-time employees with Indian names
        FullTimeEmployee fte1 = new FullTimeEmployee(1, "Rahul Sharma", 50000);
        fte1.assignDepartment("IT");
        FullTimeEmployee fte2 = new FullTimeEmployee(2, "Priya Patel", 60000);
        fte2.assignDepartment("HR");

        // Creating part-time employees with Indian names
        PartTimeEmployee pte1 = new PartTimeEmployee(3, "Asha Singh", 80, 250);
        pte1.assignDepartment("Finance");
        PartTimeEmployee pte2 = new PartTimeEmployee(4, "Vikram Gupta", 60, 200);
        pte2.assignDepartment("Marketing");

        // Adding employees to the list
        employees.add(fte1);
        employees.add(fte2);
        employees.add(pte1);
        employees.add(pte2);

        // Processing the list using polymorphism (Employee reference)
        for (Employee emp : employees) {
            emp.displayDetails();
        }
    }
}
