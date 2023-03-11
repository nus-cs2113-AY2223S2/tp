//@@author Jeraldchen

package seedu.duke.ui;

// import java.util.HashMap;
import java.util.Scanner;
import seedu.duke.save.Storage;
import seedu.duke.patient.Patient;

import static seedu.duke.patient.Patient.patientDiagnosisHistory;
import static seedu.duke.ui.Information.patientsList;

public class Menu {

    /**
     * Shows the main menu.
     */
    public static void showMenu() {
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
        Storage.saveData(patientsList);
        System.exit(0);
    }
}
