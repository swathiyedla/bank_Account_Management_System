package javaProject;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
public class bankAccountManagementSystem {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
        List<BankAccount> accounts = new ArrayList<>();
        accounts.add(new BankAccount("1001", "swathi", 20000));
        accounts.add(new BankAccount("1002", "venkat", 21600));
        accounts.add(new BankAccount("1003", "uma", 11000));
        accounts.add(new BankAccount("1004", "swetha", 25000));
        accounts.add(new BankAccount("1005", "ram", 30500));

        System.out.println("1. Withdraw");
        System.out.println("2. Deposit");
        System.out.println("3. Check Balance");
        System.out.println("4. Open New Account");
        System.out.println("Enter your choice:");
        int type = sc.nextInt();
        int amount = 0;

        if (type == 1 || type == 2) {
            System.out.println("Enter amount:");
            amount = sc.nextInt();
        }

        BankAccount B;

        if (type == 4) {
            B = openNewAccount(sc, accounts);
        } else {
            System.out.println("Enter Account Number:");
            String actno = sc.next(); // Fix: Use String for account number

            B = findAccount(actno, accounts);
            if (B == null) {
                System.out.println("Account not found.");
                return;
            }
        }

        B.show();
        B.transactions(type, amount);
    }

    private static BankAccount openNewAccount(Scanner sc, List<BankAccount> accounts) {
        System.out.println("Enter Account Holder Name:");
        String name = sc.next();

        System.out.println("Enter Initial Deposit Amount:");
        int initialBalance = sc.nextInt();

        String newAccountNumber = generateAccountNumber(accounts);
        BankAccount newAccount = new BankAccount(newAccountNumber, name, initialBalance);
        accounts.add(newAccount);

        System.out.println("New account opened successfully.");
        
        System.out.println("Initial Balance: Rs." + initialBalance);

        return newAccount;
    }

    private static BankAccount findAccount(String actno, List<BankAccount> accounts) {
        for (BankAccount account : accounts) {
            if (account.actno.equals(actno)) {
                return account;
            }
        }
        return null;
    }

    private static String generateAccountNumber(List<BankAccount> accounts) {
        // Generating a simple sequential account number for the example
    	 return String.valueOf(1000 + accounts.size() + 1);
    }
}

class BankAccount {
    String actno;
    String name;
    int balance;

    public BankAccount(String actno, String name, int balance) {
        this.actno = actno;
        this.name = name;
        this.balance = balance;
    }

    public void transactions(int type, int amount) {
        switch (type) {
            case 1:
                try {
                    System.out.println("Available Balance: Rs." + balance);
                    if (amount > balance) {
                        throw new InsufficientBalance();
                    }
                    balance = balance - amount;

                    System.out.println("Withdrawal successful. New balance: Rs." + balance);

                } catch (InsufficientBalance e) {
                    System.out.println("Insufficient balance");
                    return;
                }
                break;
            case 2:
                System.out.println("Available Balance: Rs. " + balance);
                if (amount > 0) {
                    balance += amount;

                    System.out.println("Deposit successful. New balance: Rs." + balance);
                } else {
                    System.out.println("Invalid amount for deposit.");
                }

                break;
            case 3:
                System.out.println("Your current balance: Rs." + balance);
                break;
            case 4:
                break; 
            default:
                System.out.println("Enter correct transaction");
                
        }
    }

    public void show() {
        System.out.println("Account Number: " + actno);
        System.out.println("Account Holder: "+ name);
    }

    private class InsufficientBalance extends Exception {
	}

}
