package data;

import java.io.FileNotFoundException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.regex.Pattern;

public class Account {
    public static int accountNumber = 1;
    //protected static String ACCOUNTS_FILE = accountName + ".txt";
    protected static String accountName;
    protected static ArrayList<Expense> account;
    protected static ExpenseList expenseList;
    private static String passwordHash;
    private static final int MIN_PASSWORD_LENGTH = 8;
    private static final Pattern USERNAME_PATTERN = Pattern.compile("[a-zA-Z0-9]+");
    protected int accountSize;



    public Account(String accountName, String password) {
        this.account = new ArrayList<>();
        accountSize = 0;
        this.accountName = accountName;
        this.passwordHash = hashPassword(password);
        accountNumber++;
        this.expenseList = new ExpenseList();

        // Read the expense list from the file with the file path of accountName + ".txt" if it exists
        try (BufferedReader br = new BufferedReader(new FileReader(
                "./src/main/java/storage/" + accountName + ".txt"))) {
            expenseList = new ExpenseList(br);
        } catch (FileNotFoundException e) {
            // The file does not exist, which means there is no saved expense list
        } catch (IOException e) {
            System.out.println("Error: Failed to read expense list from file.");
        }
    }

    public int getAccountSize() {
        return this.accountSize;
    }

    public String getAccountName() {
        return this.accountName;
    }

    public Expense getExpense(int index) {
        return account.get(index);
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setAccountName() {
        this.accountName = accountName;
    }

    public void setPassword(String password) {
        this.passwordHash = hashPassword(password);
    }

    public ExpenseList getExpenseList() { return expenseList; }

    public void signup() {
        // Check if username contains special characters
        if (!USERNAME_PATTERN.matcher(accountName).matches()) {
            System.out.println("Username must contain only letters and numbers.");
            return;
        } else if (passwordHash == null) {
            System.out.println("Password must contain only numbers.");
            return;
        } else if (passwordHash.length() < MIN_PASSWORD_LENGTH) {
            System.out.println("Password must be at least " + MIN_PASSWORD_LENGTH + " characters long.");
            return;
        } else if (isUsernameTaken()) {  // Check if username is taken
            System.out.println("The username is taken, please use another username.");
            return;
        } try (PrintWriter pw = new PrintWriter(new FileWriter(
                "./src/main/java/storage/" + accountName + ".txt", true))) {
            // Save the account to the "accountName.txt" file
            pw.println(accountName);
            pw.close();
        } catch (IOException e) {
            System.out.println("Error: Failed to create username file.");
            return;
        }
        try (PrintWriter pw = new PrintWriter(new FileWriter("./src/main/java/storage/" + accountName + ".txt"))) {
            pw.println(accountName + "," + passwordHash);
            pw.close();
        } catch (IOException e) {
            System.out.println("Error: Failed to create account file.");
            return;
        }
        System.out.printf("User %s has been created\n", accountName);
        System.out.println("Signup successful.");
    }


    public void login() {
        // Check if username and passwordHash match the ones stored in the "username.txt" file
        try {
            FileReader reader = new FileReader("./src/main/java/storage/" + accountName + ".txt");
            BufferedReader bufferedReader = new BufferedReader(reader);

            String line;
            boolean found = false;
            while ((line = bufferedReader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts[0].equals(accountName) && parts[1].equals(passwordHash)) {
                    found = true;
                    break;
                }
            }

            bufferedReader.close();
            reader.close();

            if (found) {
                System.out.println("Login successful.");
            } else {
                System.out.println("Invalid username or passwordHash.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred while logging in.");
            e.printStackTrace();
        }
    }

    private boolean isUsernameTaken() {
        boolean usernameTaken = true;
        try (BufferedReader br = new BufferedReader(new FileReader(
                "./src/main/java/storage/" + accountName + ".txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.equals(accountName)) {
                    usernameTaken = true;
                    break;
                }
            }
        } catch (FileNotFoundException e) {
            // Username file not found, which means the username is not taken
            usernameTaken = false;
            //return false;
        } catch (IOException e) {
            System.out.println("Error: Failed to read username file.");
        }
        return usernameTaken;

    }

    private String hashPassword(String password) {
        // Restrict password to numbers only if hashing is not supported
        char[] passwordChars = password.toCharArray();
        for (char c : passwordChars) {
            if (!Character.isDigit(c)) {
                return password; // Password contains non-digit characters
            }
        }
        return String.valueOf(passwordChars);
    }

    public static void logout() {
        try (PrintWriter pw = new PrintWriter(new FileWriter(
                "./src/main/java/storage/" + accountName + ".txt"))) {
            pw.println(accountName + "," + passwordHash);
            for (Expense expense : expenseList.getExpenseList()) {
                pw.println(expense.toSave());
                pw.println(expense);
            }
            pw.close();
        } catch (IOException e) {
            System.out.println("Error: Failed to save expenses.");
            return;
        }
        expenseList = null;
        System.out.println("Logout successful.");
    }
}


