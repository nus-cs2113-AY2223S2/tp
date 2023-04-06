package data;

import command.CommandAdd;
import parser.Parser;
import storage.Storage;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.FileWriter;
import java.io.File;

import java.util.regex.Pattern;

public class Account {
    public static int accountNumber = 1;
    public static ExpenseList account;
    //protected static String ACCOUNTS_FILE = accountName + ".txt";
    protected static String accountName;
    protected static Storage storage;
    private static String passwordHash;
    private static final int MIN_PASSWORD_LENGTH = 8;
    private static final Pattern USERNAME_PATTERN = Pattern.compile("[a-zA-Z0-9]+");

    public Account(String accountName, String password) {
        this.accountName = accountName;
        this.passwordHash = hashPassword(password);
        accountNumber++;
        this.account = new ExpenseList();
        storage = new Storage(account);

        // Read the expense list from the file with the file path of accountName + ".txt" if it exists
        try (BufferedReader br = new BufferedReader(new FileReader(
                "./src/main/java/storage/" + accountName + ".txt"))) {
            account = new ExpenseList(br);
        } catch (FileNotFoundException e) {
            // The file does not exist, which means there is no saved expense list
        } catch (IOException e) {
            System.out.println("Error: Failed to read expense list from file.");
        }
    }

    public Account() {
        this.account = new ExpenseList();
    }

    public String getAccountName() {
        return this.accountName;
    }
    public static String getPasswordHash() {
        return passwordHash;
    }

    public void setAccountName() {
        this.accountName = accountName;
    }

    public void setPassword(String password) {
        this.passwordHash = hashPassword(password);
    }

    public ExpenseList getExpenseList() {
        return account;
    }

    public void signup() {
        // Check if username contains special characters
        if (!USERNAME_PATTERN.matcher(accountName).matches()) {
            System.out.println("Username must contain only letters and numbers.");
            return;
        } else if (passwordHash == null) {
            System.out.println("Password must be specified.");
            return;
        } else if (passwordHash.length() < MIN_PASSWORD_LENGTH) {
            System.out.println("Password must be at least " + MIN_PASSWORD_LENGTH + " characters long.");
            return;
        } else if (isUsernameTaken()) {  // Check if username is taken
            System.out.println("The username is taken, please use another username.");
            return;
        } /*try (PrintWriter pw = new PrintWriter(new FileWriter(
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
            pw.close();*/
        try {
            storage.createFile("./src/main/java/storage/" + accountName + ".txt");
            storage.createFile("./src/main/java/storage/" + accountName + ".json");
            PrintWriter pw = new PrintWriter(new PrintWriter("./src/main/java/storage/" + accountName + ".txt"));
            pw.println(accountName + "," + passwordHash);
            pw.close();
        } catch (IOException e) {
            System.out.println("Error: Failed to create account file.");
            return;
        }
        System.out.printf("User %s has been created\n", accountName);
        System.out.println("Signup successful.");
    }

    public String login() {
        File file = new File("./src/main/java/storage/" + accountName + ".txt");
        if (!file.exists()) {
            return "Log In Failed. Invalid login credentials";
        } else {
            // Check if username and password match the ones stored in the "username.txt" file
            boolean found = false;
            try {
                FileReader reader = new FileReader("./src/main/java/storage/" + accountName + ".txt");
                BufferedReader bufferedReader = new BufferedReader(reader);
                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    String[] parts = line.split(",");
                    if (parts[0].equals(accountName) && parts[1].equals(passwordHash)) {
                        found = true;
                        break;
                    }
                }
                while ((line = bufferedReader.readLine()) != null && found) {
                    String[] parts2 = line.split(" ");
                    Parser parser = new Parser();
                    if (!(parts2[0].equals(accountName)) && found) {
                        new CommandAdd(account.getExpenseList(),
                                parser.extractAddParameters(line), new Currency()).executeLogIn();
                    }
                }
                //TODO: modify loadExpenses to ExpenseList instead of ArrayList<Expense>
                //storage.loadExpenses("./src/main/java/storage/" + accountName + ".json");
                bufferedReader.close();
                reader.close();

                if (found) {
                    //System.out.println("Login successful.");
                    return "Login successful.";
                } else {
                    //System.out.println("Invalid username or password.");
                    return "Invalid username or password.";
                }
            } catch (FileNotFoundException e) {
                System.out.println("Sorry, there is no username found.");
            } catch (IOException e) {
                System.out.println("An error occurred while logging in.");
                e.printStackTrace();
            }
        }
        return "Login successfully.";
    }

    private boolean isUsernameTaken() {
        File file = new File("./src/main/java/storage/" + accountName + ".txt");
        if (!file.exists()) {
            return false;
        } else {
            try (BufferedReader br = new BufferedReader(new FileReader(file))) {
                String line;
                while ((line = br.readLine()) != null) {
                    String[] parts = line.split(",");
                    if (parts[0].equalsIgnoreCase(accountName)) {
                        return true;
                    }
                }
                return false;
            } catch (FileNotFoundException e) {
                // Username file not found, which means the username is not taken
                return false;
            } catch (IOException e) {
                System.out.println("Error: Failed to read username file.");
                return false;
            }
        }
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

    public static void save() {
        try (PrintWriter pw = new PrintWriter(new FileWriter(
                "./src/main/java/storage/" + accountName + ".txt"))) {
            pw.println(accountName + "," + passwordHash);
            for (Expense expense : account.getExpenseList()) {
                pw.println(expense.toAdd());
            }
            storage.saveExpenses("./src/main/java/storage/" + accountName + ".json");
            account.clear();
            pw.close();
        } catch (IOException e) {
            System.out.println("Error: Failed to save expenses.");
            return;
        }
        account = null;
        System.out.println("Saved successfully.");
    }
}


