package data;

import command.CommandAdd;
import parser.Parser;

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
    //private static final String ACCOUNTS_FILE = accountName + ".txt";
    public static ExpenseList account;
    protected static String accountName;
    private static String passwordHash;
    private static final int MIN_PASSWORD_LENGTH = 8;
    private static final Pattern USERNAME_PATTERN = Pattern.compile("[a-zA-Z0-9]+");


    public Account(String accountName, String passwordHash) {
        this.accountName = accountName;
        this.passwordHash = hashPassword(passwordHash);
        accountNumber++;
        this.account = new ExpenseList();

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

    public String getAccountName() {
        return this.accountName;
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

    public ExpenseList getExpenseList() { return account; }

    public void signup() {
        // Check if username contains special characters
        if (!USERNAME_PATTERN.matcher(accountName).matches()) {
            System.out.println("Username must contain only letters and numbers.");
            return;
        } else if (passwordHash == null) {
            System.out.println("Password must be specified");
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


    public String login() {
        File file = new File("./src/main/java/storage/" + accountName + ".txt");
        if (!file.exists()) {
            return "Log In Failed. Invalid login credentials.";
        } else {
            boolean found = false;
            // Check if username and passwordHash match the ones stored in the "username.txt" file
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
                bufferedReader.close();
                reader.close();
                if (found) {
                    return "Login successful.";
                } else {
                    return "Invalid username or password.";
                }
            } catch (IOException e) {
                e.printStackTrace();
                return "An error occurred while logging in.";
            }
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
            for (Expense expense : account.getExpenseList()) {
                pw.println(expense.toAdd());
            }
            account.clear();
            pw.close();
        } catch (IOException e) {
            System.out.println("Error: Failed to save expenses.");
            return;
        }
        account = null;
        System.out.println("Logout successful.");
    }
}


