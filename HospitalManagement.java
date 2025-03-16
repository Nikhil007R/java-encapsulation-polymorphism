import java.util.ArrayList;
import java.util.List;

// MedicalRecord interface with methods for managing medical records
interface MedicalRecord {
    void addRecord(String record);

    void viewRecords();
}

// Abstract Patient class with encapsulated fields and sensitive data
abstract class Patient implements MedicalRecord {
    private String patientId;
    private String name;
    private int age;
    private String diagnosis; // Sensitive data
    private List<String> records; // Medical history

    public Patient(String patientId, String name, int age) {
        this.patientId = patientId;
        this.name = name;
        this.age = age;
        this.records = new ArrayList<>();
    }

    // Getters and setters for encapsulated data
    public String getPatientId() {
        return patientId;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }

    // Concrete method to display patient details
    public String getPatientDetails() {
        return "Patient ID: " + patientId + ", Name: " + name + ", Age: " + age +
                (diagnosis != null ? ", Diagnosis: " + diagnosis : "");
    }

    // Abstract method to calculate the patient's bill
    public abstract double calculateBill();

    // Implementation of MedicalRecord interface methods
    @Override
    public void addRecord(String record) {
        records.add(record);
    }

    @Override
    public void viewRecords() {
        System.out.println("Medical Records for " + name + ":");
        for (String record : records) {
            System.out.println("- " + record);
        }
    }
}

// InPatient class with specific billing logic
class InPatient extends Patient {
    private int numberOfDays;
    private double dailyCharge;

    public InPatient(String patientId, String name, int age, int numberOfDays, double dailyCharge) {
        super(patientId, name, age);
        this.numberOfDays = numberOfDays;
        this.dailyCharge = dailyCharge;
    }

    @Override
    public double calculateBill() {
        // InPatient bill: (number of days * daily charge) plus a fixed service fee
        return numberOfDays * dailyCharge + 500;
    }
}

// OutPatient class with specific billing logic
class OutPatient extends Patient {
    private double consultationFee;

    public OutPatient(String patientId, String name, int age, double consultationFee) {
        super(patientId, name, age);
        this.consultationFee = consultationFee;
    }

    @Override
    public double calculateBill() {
        // OutPatient bill: consultation fee only
        return consultationFee;
    }
}

// Main class to demonstrate polymorphism in hospital patient management
public class HospitalManagement {
    public static void main(String[] args) {
        List<Patient> patients = new ArrayList<>();

        // Creating InPatient and OutPatient objects with sample data
        Patient inPatient = new InPatient("P001", "Rajesh Kumar", 45, 5, 2000);
        inPatient.setDiagnosis("Pneumonia");
        inPatient.addRecord("Admitted on 2023-01-10");
        inPatient.addRecord("X-Ray and blood tests completed.");

        Patient outPatient = new OutPatient("P002", "Sunita Devi", 30, 300);
        outPatient.setDiagnosis("Flu");
        outPatient.addRecord("Visited on 2023-02-15");
        outPatient.addRecord("Prescribed antiviral medication.");

        patients.add(inPatient);
        patients.add(outPatient);

        // Processing patients polymorphically and displaying billing details
        for (Patient patient : patients) {
            System.out.println(patient.getPatientDetails());
            System.out.println("Total Bill: " + patient.calculateBill());
            patient.viewRecords();
            System.out.println("--------------------------");
        }
    }
}
