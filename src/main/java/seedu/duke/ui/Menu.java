/**
 * @author JeraldChen
 */

package seedu.duke.ui;

// import java.util.HashMap;

import seedu.duke.Duke;
import seedu.duke.diagnosis.Diagnosis;
import seedu.duke.diagnosis.IllnessMatch;
import seedu.duke.diagnosis.symptoms.Symptom;
import seedu.duke.patient.Patient;
import seedu.duke.save.Storage;

import java.util.ArrayList;
import java.util.Scanner;

public class Menu {
    public static ArrayList <Symptom> symptoms = new ArrayList<>();

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
     * @author Geeeetyx, JeraldChen
     */
    public static void register() {
        System.out.println("Please enter your name: ");
        String name = new Scanner(System.in).nextLine();
        System.out.println("Please enter your password: ");
        String password = new Scanner(System.in).nextLine();
        if (password.equals("") || name.equals("")) {
            System.out.println("Registration failed! Name and/or Password cannot be empty.");
        } else {
            System.out.println("Please re-enter your password: ");
            String password2 = new Scanner(System.in).nextLine();
            if (password.equals(password2)) {
                System.out.println("Registration successful!");
                ArrayList<String> diagnosisHistory = new ArrayList<>();
                Information.storePatientInfo(password, new Patient(name, password, diagnosisHistory));
            } else {
                System.out.println("Registration failed!");
            }
        }
    }

    /**
     * Logs in a user.
     * @author Geeeetyx, JeraldChen
     */
    public static void login() {
        System.out.println("Please enter your name: ");
        String name = new Scanner(System.in).nextLine();
        System.out.println("Please enter your password: ");
        String password = new Scanner(System.in).nextLine();
        if (Information.checkPassword(password) && Information.getPatientInfo(password).getName().equals(name)) {
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
     * Shows the account menu
     * @author Thunderdragon221
     */
    public static void showAccountMenu() {
        System.out.println("What would you like to do? Please enter the number:");
        System.out.println("1. Report symptoms");
        System.out.println("2. View diagnosis history");
        System.out.println("3. Reset diagnosis history");
        System.out.println("4. Reset symptoms");
        System.out.println("5. Exit");
    }

    /**
     * Reads in a list of symptoms the user experiences.
     *
     * @return ArrayList of symptoms the user experiences.
     */
    public static ArrayList<Symptom> getUserSymptoms() {
        Scanner scanner = new Scanner(System.in);
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
     * @param scanner  takes in user input
     * @param symptoms list of symptoms
     */
    private static void addSymptomToSymptomList(Scanner scanner, ArrayList<Symptom> symptoms) {
        String symptomChoiceAlphabets;
        symptomChoiceAlphabets = scanner.nextLine(); //@@author Jeraldchen
        symptomChoiceAlphabets = symptomChoiceAlphabets.toUpperCase(); //@@author Jeraldchen
        symptomChoiceAlphabets = symptomChoiceAlphabets.trim();  //@@author Jeraldchen
        String[] symptomChoices = symptomChoiceAlphabets.split("(?!^)"); //@@author Jeraldchen
        parseSymptomInput(symptoms, symptomChoices);
    }

    /**
     * Parses user's input to a Symptom enumerator.
     * @@author Jeraldchen
     * @param symptoms      an ArrayList of symptoms.
     * @param symptomChoices an array of strings containing the user's input.
     */
    private static void parseSymptomInput(ArrayList<Symptom> symptoms , String[] symptomChoices) {
        for (String symptomChoice : symptomChoices) {
            if (symptomChoice.equals("A")) {
                if (!symptoms.contains(Symptom.FEVER)) {
                    assert Symptom.FEVER != null : "Fever should not be null";
                    symptoms.add(Symptom.FEVER);
                } else {
                    System.out.println("You have already entered this symptom!");
                }
            } else if (symptomChoice.equals("B")) {
                if (!symptoms.contains(Symptom.DRY_COUGH)) {
                    assert Symptom.DRY_COUGH != null : "Dry cough should not be null";
                    symptoms.add(Symptom.DRY_COUGH);
                } else {
                    System.out.println("You have already entered this symptom!");
                }
            } else if (symptomChoice.equals("C")) {
                if (!symptoms.contains(Symptom.LOSS_OF_TASTE_OR_SMELL)) {
                    assert Symptom.LOSS_OF_TASTE_OR_SMELL != null : "Loss of taste or smell should not be null";
                    symptoms.add(Symptom.LOSS_OF_TASTE_OR_SMELL);
                } else {
                    System.out.println("You have already entered this symptom!");
                }
            } else if (symptomChoice.equals("D")) {
                if (!symptoms.contains(Symptom.RUNNY_NOSE)) {
                    assert Symptom.RUNNY_NOSE != null : "Runny Nose should not be null";
                    symptoms.add(Symptom.RUNNY_NOSE);
                } else {
                    System.out.println("You have already entered this symptom!");
                }
            } else if (symptomChoice.equals("E")) {
                if (!symptoms.contains(Symptom.HEAD_ACHE)) {
                    assert Symptom.HEAD_ACHE != null : "Aching muscles should not be null";
                    symptoms.add(Symptom.HEAD_ACHE);
                } else {
                    System.out.println("You have already entered this symptom!");
                }
            } else if (symptomChoice.equals("F")) {
                if (!symptoms.contains(Symptom.CHILLS)) {
                    assert Symptom.CHILLS != null : "Sore throat should not be null";
                    symptoms.add(Symptom.CHILLS);
                } else {
                    System.out.println("You have already entered this symptom!");
                }
            } else if (symptomChoice.equals("G")) {
                if (!symptoms.contains(Symptom.FATIGUE)) {
                    assert Symptom.FATIGUE != null : "Sore throat should not be null";
                    symptoms.add(Symptom.FATIGUE);
                } else {
                    System.out.println("You have already entered this symptom!");
                }
            } else if (symptomChoice.equals("H")) {
                if (!symptoms.contains(Symptom.SNEEZING)) {
                    assert Symptom.SNEEZING != null : "Sore throat should not be null";
                    symptoms.add(Symptom.SNEEZING);
                } else {
                    System.out.println("You have already entered this symptom!");
                }
            } else if (symptomChoice.equals("I")) {
                if (!symptoms.contains(Symptom.BLOCKED_NOSE)) {
                    assert Symptom.BLOCKED_NOSE != null : "Sore throat should not be null";
                    symptoms.add(Symptom.BLOCKED_NOSE);
                } else {
                    System.out.println("You have already entered this symptom!");
                }
            } else {
                System.out.println("Invalid symptom choice!");
            }

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
        System.out.println("c. Red Eyes");
        System.out.println("d. Runny nose");
        System.out.println("e. Headache");
        System.out.println("f. Chills");
        System.out.println("g. Fatigue");
        System.out.println("h. Sneezing");
        System.out.println("i. Blocked Nose");
        System.out.println("\nPlease enter a symptom.");
    }

    /**
     * Displays the possible illnesses that the user may have based on the symptoms he/she has entered.
     * @author tanyizhe
     * @param symptoms ArrayList of symptoms the user has entered.
     */
    public static void displayPossibleIllness(ArrayList<Symptom> symptoms) {
        ArrayList<IllnessMatch> possibleIllnesses = Diagnosis.getPossibleIllnesses(symptoms);
        if (possibleIllnesses.size() != 0) {
            System.out.println("You may have: ");
            for (IllnessMatch illnessMatch : possibleIllnesses) {
                System.out.println(illnessMatch.getIllness().getIllnessName() + " "
                        + illnessMatch.getSimilarityPercentage() * 100 + "%");
            }
        } else { // no illnesses found
            System.out.println("Unable to diagnose illness. Please consult a Doctor instead."); //@@author Jeraldchen
        }
    }

}
