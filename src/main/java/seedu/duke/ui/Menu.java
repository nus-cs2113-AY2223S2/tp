//@@author Jeraldchen

package seedu.duke.ui;

// import java.util.HashMap;
import java.util.ArrayList;
import java.util.Scanner;

import seedu.duke.diagnosis.Diagnosis;
import seedu.duke.diagnosis.IllnessMatch;
import seedu.duke.diagnosis.symptoms.Symptom;
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

    /**
     * @author Thunderdragon221
     *     Shows the account menu.
     */
    public static void showAccountMenu() {
        System.out.println("What would you like to do? Please enter the number:");
        System.out.println("1. Report symptoms");
        System.out.println("2. View diagnosis history");
        System.out.println("3. Exit");
    }

    /**
     * Reads in a list of symptoms the user experiences.
     *
     * @return ArrayList of symptoms the user experiences.
     */
    public static ArrayList<Symptom> getUserSymptoms() {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Symptom> symptoms = new ArrayList<>();
        displaySymptomList();
        addSymptomToSymptomList(scanner, symptoms);
        while (true) {
            System.out.println("Do you have any other symptoms? [Y/N]");
            String decision = scanner.nextLine();
            if (decision.equals("Y") || decision.equals("y")) {
                displaySymptomList();
                addSymptomToSymptomList(scanner, symptoms);
            } else if (decision.equals("N") || decision.equals("n")) {
                System.out.println("Your entered symptoms are: ");
                System.out.println(symptoms);
                break;
            } else {
                System.out.println("Invalid command! Please indicate Y/N.");
            }
        }
        return symptoms;
    }

    /**
     * Checks if symptom is valid, and adds it to the list of symptoms.
     * @author tanyizhe
     * @param scanner takes in user input
     * @param symptoms list of symptoms
     */
    private static void addSymptomToSymptomList(Scanner scanner, ArrayList<Symptom> symptoms) {
        Symptom symptomToBeAdded;
        String symptomChoice;
        symptomChoice = scanner.nextLine();
        symptomToBeAdded = parseSymptomInput(symptoms, scanner, symptomChoice);
        if (symptomToBeAdded != null) {
            symptoms.add(symptomToBeAdded);
        }
    }

    /**
     * Parses user's input to a Symptom enumerator.
     * @param symptoms an ArrayList of symptoms.
     * @param scanner Scans user's input.
     * @param symptomChoice A string that the user has typed in based on the displayed menu of symptoms.
     */
    private static Symptom parseSymptomInput(ArrayList<Symptom> symptoms, Scanner scanner, String symptomChoice) {
        switch (symptomChoice) {
        case "a":
        case "A":
            if (!symptoms.contains(Symptom.FEVER)) {
                return Symptom.FEVER;
            } else {
                System.out.println("You have already entered this symptom!");
                return null;
            }
        case "b":
        case "B":
            if (!symptoms.contains(Symptom.DRY_COUGH)) {
                return Symptom.DRY_COUGH;
            } else {
                System.out.println("You have already entered this symptom!");
                return null;
            }
        case "c":
        case "C":
            if (!symptoms.contains(Symptom.COUGH_WITH_PHLEGM)) {
                return Symptom.COUGH_WITH_PHLEGM;
            } else {
                System.out.println("You have already entered this symptom!");
                return null;
            }
        case "d":
        case "D":
            if (!symptoms.contains(Symptom.RUNNY_NOSE)) {
                return Symptom.RUNNY_NOSE;
            } else {
                System.out.println("You have already entered this symptom!");
                return null;
            }
        case "e":
        case "E":
            if (!symptoms.contains(Symptom.HEAD_ACHE)) {
                return Symptom.HEAD_ACHE;
            } else {
                System.out.println("You have already entered this symptom!");
                return null;
            }
        case "f":
        case "F":
            if (!symptoms.contains(Symptom.CHILLS)) {
                return Symptom.CHILLS;
            } else {
                System.out.println("You have already entered this symptom!");
                return null;
            }
        case "g":
        case "G":
            if (!symptoms.contains(Symptom.FATIGUE)) {
                return Symptom.FATIGUE;
            } else {
                System.out.println("You have already entered this symptom!");
                return null;
            }
        default:
            System.out.println("Invalid input!");
            return null;
        }

    }

    /**
     * Displays a list of symptoms for users to pick out their symptoms from.
     */
    private static void displaySymptomList() {
        /*
        change to use this method instead of hardcoding when
        we have better sorting (alphabetical order on enums)
        can display symptoms and (is it possible to .toString enums?)
        have more relevant symptoms (e.g. for ADHD you don't go to clinic)

        int count = 1;
        System.out.println("Please enter a symptom.");
        for (Symptom symptom : Symptom.values()) {
            System.out.println(count + ". ");
            System.out.println(symptom);
        }
         */
        System.out.println("Here is the list of possible symptoms:");
        System.out.println("a. Fever");
        System.out.println("b. Dry Cough");
        System.out.println("c. Cough with phlegm");
        System.out.println("d. Runny nose");
        System.out.println("e. Headache");
        System.out.println("f. Chills");
        System.out.println("g. Fatigue");
        System.out.println("\nPlease enter a symptom.");
    }
    public static void displayPossibleIllness (ArrayList<Symptom> symptoms) {
        ArrayList<IllnessMatch> possibleIllnesses =  Diagnosis.getPossibleIllnesses(symptoms);
        System.out.println("You may have: ");
        for (IllnessMatch illnessMatch : possibleIllnesses) {
            System.out.println(illnessMatch.getIllness().getIllnessName() + " "
                        + illnessMatch.getSimilarityPercentage() * 100 + "%");
        }
    }
}
