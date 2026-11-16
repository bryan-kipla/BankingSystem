import java.util.ArrayList;
import java.util.List;

interface Account {
    void deposit(double amount);
    void withdraw(double amount);
    double calculateInterest();
    void viewBalance();
}

class SavingsAccount implements Account {
    private String accountNumber;
    private double balance;
    private double interestRate;

    public SavingsAccount(String accountNumber,
                          double balance,
                          double interestRate) {
        this.accountNumber = accountNumber;
        this.balance = balance;
        this.interestRate = interestRate;
    }

    @Override
    public void deposit(double amount) {
        balance += amount;
        System.out.println("Deposited: " + amount);
    }

    @Override
    public void withdraw(double amount) {
        if(amount <= balance) {
            balance -= amount;
            System.out.println("Withdrawn: " + amount);
        } else {
            System.out.println("Insufficient Balance");
        }
    }

    @Override
    public double calculateInterest() {
        return balance * interestRate / 100;
    }

    @Override
    public void viewBalance() {
        System.out.println("Savings Account Balance: " + balance);
    }

    public void addInterest() {
        double interest = calculateInterest();
        balance += interest;
        System.out.println("Interest Added: " + interest);
    }
}

class CurrentAccount implements Account {
    private String accountNumber;
    private double balance;
    private double overdraftLimit;

    public CurrentAccount(String accountNumber,
                          double balance,
                          double overdraftLimit) {
        this.accountNumber = accountNumber;
        this.balance = balance;
        this.overdraftLimit = overdraftLimit;
    }

    @Override
    public void deposit(double amount) {
        balance += amount;
        System.out.println("Deposited: " + amount);
    }

    @Override
    public void withdraw(double amount) {
        if(balance + overdraftLimit >= amount) {
            balance -= amount;
            System.out.println("Withdrawn: " + amount);
        } else {
            System.out.println("Overdraft Limit Exceeded");
        }
    }

    @Override
    public double calculateInterest() {
        return 0;
    }

    @Override
    public void viewBalance() {
        System.out.println("Current Account Balance: " + balance);
    }

    public void checkOverdraft() {
        System.out.println("Overdraft Limit: " + overdraftLimit);
    }
}

class Bank {
    private List<Account> accounts;

    public Bank() {
        accounts = new ArrayList<>();
    }

    public void addAccount(Account account) {
        accounts.add(account);
        System.out.println("Account Added Successfully");
    }

    public void displayAccounts() {
        System.out.println("\nAccount Details");

        for(Account account : accounts) {
            account.viewBalance();
        }
    }
}

public class BankingSystem {

    public static void main(String[] args) {

        Bank bank = new Bank();

        SavingsAccount savings =
                new SavingsAccount("SA001",10000,5);

        CurrentAccount current =
                new CurrentAccount("CA001",5000,2000);

        bank.addAccount(savings);
        bank.addAccount(current);

        savings.deposit(2000);
        savings.withdraw(1000);
        savings.addInterest();
        savings.viewBalance();

        System.out.println();

        current.deposit(1500);
        current.withdraw(7000);
        current.checkOverdraft();
        current.viewBalance();

        bank.displayAccounts();
    }
}
