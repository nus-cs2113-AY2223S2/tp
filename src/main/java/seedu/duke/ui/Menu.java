//@@author Jeraldchen

package seedu.duke.ui;

// import java.util.HashMap;
import java.util.ArrayList;
import java.util.Scanner;
import seedu.duke.save.Storage;
import seedu.duke.patient.Patient;

import static seedu.duke.patient.Patient.patientDiagnosisHistory;
import static seedu.duke.ui.Information.patientsList;
import seedu.duke.Duke;

public class Menu {

    /**
     * Shows the welcome menu.
     */
    public static void showWelcomeMenu() {
        System.out.println("What would you like to do? Please enter the number:");
        System.out.println("1. Register");
        System.out.println("2. Login");
        System.out.println("3. Exit");
    }

    /**
     * Registers a new user.
     */
    public static void register() {
        System.out.println("Please enter your name: ");
        String name = new Scanner(System.in).nextLine();
        System.out.println("Please enter your password: ");
        String password = new Scanner(System.in).nextLine();
        System.out.println("Please re-enter your password: ");
        String password2 = new Scanner(System.in).nextLine();
        if (password.equals(password2)) {
            System.out.println("Registration successful!");
            new Patient(name, password, patientDiagnosisHistory);
            patientsList.put(password, new Patient(name, password, patientDiagnosisHistory));
        } else {
            System.out.println("Registration failed!");
        }
    }

    /**
     * Logs in a user.
     */
    public static void login() {
        System.out.println("Please enter your name: ");
        String name = new Scanner(System.in).nextLine();
        System.out.println("Please enter your password: ");
        String password = new Scanner(System.in).nextLine();
        if (patientsList.containsKey(password)) {
            Duke.setPassword(password);
            System.out.println("Login successful!");
            System.out.println("Welcome " + name + "!");
        } else {
            System.out.println("Login failed! Please register first or key in the correct information.");
        }
    }

    /**
     * Exits the program.
     */
    public static void exit() {
        System.out.println("Thank you for using Dr Duke!");
        Storage.saveData();
        System.exit(0);
    }

    //@@author Thunderdragon221

    /**
     * Shows the account menu.
     */
    public static void showAccountMenu() {
        System.out.println("What would you like to do? Please enter the number:");
        System.out.println("1. Report symptoms");
        System.out.println("2. View diagnosis history");
        System.out.println("3. Exit");
    }

    public static ArrayList<String> getUserSymptoms() {
        ArrayList<String> symptoms = new ArrayList<>();
        System.out.println("Here is the list of possible symptoms:");
        // to add command to display all possible symptoms
        System.out.println("Please enter a symptom.");
        Scanner scanner = new Scanner(System.in);
        String symptom = scanner.nextLine();
        // to add code to process symptom user keyed in
        while (true) {
            System.out.println("Do you have any other symptoms? [Y/N]");
            String decision = scanner.nextLine();
            if (decision.equals("Y") || decision.equals("y")) {
                System.out.println("Please enter a symptom.");
                symptom = scanner.nextLine();
                // to add code to process symptom user keyed in
            } else if (decision.equals("N") || decision.equals("n")) {
                break;
            } else {
                System.out.println("Invalid command! Please indicate Y/N.");
            }
        }
        return symptoms;
    }
}
