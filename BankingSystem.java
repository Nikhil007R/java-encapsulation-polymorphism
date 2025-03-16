import java.util.ArrayList;
import java.util.List;

// Abstract BankAccount class with encapsulated fields and concrete deposit/withdraw methods
abstract class BankAccount {
    private String accountNumber;
    private String holderName;
    private double balance;

    public BankAccount(String accountNumber, String holderName, double balance) {
        this.accountNumber = accountNumber;
        this.holderName = holderName;
        this.balance = balance;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public String getHolderName() {
        return holderName;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        balance += amount;
    }

    public void withdraw(double amount) {
        if (amount <= balance) {
            balance -= amount;
        } else {
            System.out.println("Insufficient funds for account " + accountNumber);
        }
    }

    // Abstract method to calculate interest
    public abstract double calculateInterest();
}

// Loanable interface with loan-related methods
interface Loanable {
    void applyForLoan(double amount);

    double calculateLoanEligibility();
}

// SavingsAccount with interest calculation and loan functionality
class SavingsAccount extends BankAccount implements Loanable {
    public SavingsAccount(String accountNumber, String holderName, double balance) {
        super(accountNumber, holderName, balance);
    }

    @Override
    public double calculateInterest() {
        // 4% annual interest
        return getBalance() * 0.04;
    }

    @Override
    public void applyForLoan(double amount) {
        double eligibility = calculateLoanEligibility();
        if (amount <= eligibility) {
            System.out.println("Loan approved for " + amount + " on account " + getAccountNumber());
        } else {
            System.out.println("Loan amount exceeds eligibility for account " + getAccountNumber());
        }
    }

    @Override
    public double calculateLoanEligibility() {
        // For example, eligible for 50% of the current balance
        return getBalance() * 0.50;
    }
}

// CurrentAccount with its own interest calculation
class CurrentAccount extends BankAccount {
    public CurrentAccount(String accountNumber, String holderName, double balance) {
        super(accountNumber, holderName, balance);
    }

    @Override
    public double calculateInterest() {
        // 1% annual interest
        return getBalance() * 0.01;
    }
}

// Main class to demonstrate polymorphism in the banking system
public class BankingSystem {
    public static void main(String[] args) {
        List<BankAccount> accounts = new ArrayList<>();

        // Create SavingsAccount and CurrentAccount objects
        BankAccount savings = new SavingsAccount("SA1001", "Anita Sharma", 10000);
        BankAccount current = new CurrentAccount("CA2001", "Rohit Kumar", 15000);

        accounts.add(savings);
        accounts.add(current);

        // Process accounts polymorphically and calculate interest and loan eligibility
        // dynamically
        for (BankAccount account : accounts) {
            System.out.println("Account Number: " + account.getAccountNumber());
            System.out.println("Holder: " + account.getHolderName());
            System.out.println("Balance: " + account.getBalance());
            System.out.println("Interest: " + account.calculateInterest());
            if (account instanceof Loanable) {
                Loanable loanable = (Loanable) account;
                System.out.println("Loan Eligibility: " + loanable.calculateLoanEligibility());
                // Demonstrate applying for a loan
                loanable.applyForLoan(2000);
            }
            System.out.println("--------------------------");
        }
    }
}
