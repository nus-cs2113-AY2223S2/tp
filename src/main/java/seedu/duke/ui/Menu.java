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
    public static ArrayList<Symptom> symptoms = new ArrayList<>();

    /**
     * Shows the welcome menu.
     */
    public static void showWelcomeMenu() {
        System.out.println("---------------------------------------------------");
        System.out.println("What would you like to do? Please enter the number:");
        System.out.println("1. Register");
        System.out.println("2. Login");
        System.out.println("3. Exit");
        System.out.println("---------------------------------------------------");
    }

    /**
     * Registers a new user.
     *
     * @author Geeeetyx, JeraldChen
     */
    public static void register() {
        String name = "";
        Scanner scanner = new Scanner(System.in);
        while (true) {
            if (name.equals("")) {
                System.out.println("---------------------------------------------------");
                System.out.println("Please enter your name: ");
                name = scanner.nextLine();
                if (name.equals("")) {
                    System.out.println("---------------------------------------------------");
                    System.out.println("Registration failed! Name cannot be empty.");
                    continue;
                }
            }
            System.out.println("---------------------------------------------------");
            System.out.println("Please enter your password: ");
            String password = scanner.nextLine();

            password = password.replaceAll("\\s", "");
            int hash = Information.hashPassword(password);

            if (password.equals("")) {
                System.out.println("---------------------------------------------------");
                System.out.println("Registration failed! Password cannot be empty.");
            } else if (Information.checkHash(hash)) {
                System.out.println("--------------------------------------------------------");
                System.out.println("Password is already used. Please enter another password.");
            } else {
                System.out.println("---------------------------------------------------");
                System.out.println("Please re-enter your password: ");
                String password2 = new Scanner(System.in).nextLine();
                if (password.equals(password2)) {
                    System.out.println("---------------------------------------------------");
                    System.out.println("Registration successful!");
                    ArrayList<String> diagnosisHistory = new ArrayList<>();
                    Information.storePatientInfo(hash, new Patient(name, hash, diagnosisHistory));
                    break;
                } else {
                    System.out.println("---------------------------------------------------");
                    System.out.println("Registration failed! Passwords do not match.");
                }
            }
        }
    }

    /**
     * Logs in a user.
     *
     * @author Geeeetyx, JeraldChen
     */
    public static void login() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("---------------------------------------------------");
        System.out.println("Please enter your name: ");
        String name = new Scanner(System.in).nextLine();
        System.out.println("---------------------------------------------------");
        System.out.println("Please enter your password: ");
        String password = scanner.nextLine();
        password = password.replaceAll("\\s", "");
        int hash = Information.hashPassword(password);
        if (Information.checkHash(hash) && Information.getPatientInfo(hash).getName().equals(name)) {
            Duke.setPassword(hash);
            System.out.println("---------------------------------------------------");
            System.out.println("Login successful!");
            System.out.println("Welcome " + name + "!");
        } else {
            System.out.println("----------------------------------------------------------------------");
            System.out.println("Login failed! Please register first or key in the correct information.");
        }
    }

    /**
     * @author Geeeetyx, JeraldChen
     * Exits the program.
     */
    public static void exit() {
        System.out.println("---------------------------------------------------");
        System.out.println("Thank you for using");
        String logo = " ____         ____        _        \n"
                + "|  _ \\  ___  |  _ \\ _  _ | | _____ \n"
                + "| | | |/ _ \\ | | | | | | | |/ / _ \\\n"
                + "| |_| | |    | |_| | |_| |   <  __/\n"
                + "|____/|_|    |____/ \\__,_|_|\\_\\___|\n";
        System.out.print(logo);
        System.out.println("---------------------------------------------------");
        Storage.saveData();
        System.exit(0);
    }

    /**
     * Shows the account menu
     *
     * @author Thunderdragon221, Geeeetyx
     */
    public static void showAccountMenu() {
        System.out.println("---------------------------------------------------");
        System.out.println("What would you like to do? Please enter the number:");
        System.out.println("1. Report symptoms");
        System.out.println("2. View diagnosis history");
        System.out.println("3. Reset diagnosis history");
        System.out.println("4. Reset symptoms");
        System.out.println("5. View Medicine history");
        System.out.println("6. Exit");
        System.out.println("---------------------------------------------------");
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
     *
     * @author tanyizhe, JeraldChen
     * @param scanner  takes in user input
     * @param symptoms list of symptoms
     */
    private static void addSymptomToSymptomList(Scanner scanner, ArrayList<Symptom> symptoms) {
        String symptomChoiceAlphabets;
        symptomChoiceAlphabets = scanner.nextLine();
        symptomChoiceAlphabets = symptomChoiceAlphabets.toUpperCase();
        symptomChoiceAlphabets = symptomChoiceAlphabets.trim();
        String[] symptomChoices = symptomChoiceAlphabets.split("(?!^)");
        parseSymptomInput(symptoms, symptomChoices);
    }

    /**
     * Parses user's input to a Symptom enumerator.
     *
     * @author Jeraldchen, Geeeetyx
     * @param symptoms       an ArrayList of symptoms.
     * @param symptomChoices an array of strings containing the user's input.
     */
    private static void parseSymptomInput(ArrayList<Symptom> symptoms, String[] symptomChoices) {
        for (String symptomChoice : symptomChoices) {
            switch (symptomChoice) {
            case "A":
                addSymptoms(Symptom.FEVER, symptoms);
                break;
            case "B":
                addSymptoms(Symptom.DRY_COUGH, symptoms);
                break;
            case "C":
                addSymptoms(Symptom.COUGH_WITH_PHLEGM, symptoms);
                break;
            case "D":
                addSymptoms(Symptom.THROAT_IRRITATION, symptoms);
                break;
            case "E":
                addSymptoms(Symptom.LOSS_OF_TASTE_OR_SMELL, symptoms);
                break;
            case "F":
                addSymptoms(Symptom.RUNNY_NOSE, symptoms);
                break;
            case "G":
                addSymptoms(Symptom.HEAD_ACHE, symptoms);
                break;
            case "H":
                addSymptoms(Symptom.CHILLS, symptoms);
                break;
            case "I":
                addSymptoms(Symptom.FATIGUE, symptoms);
                break;
            case "J":
                addSymptoms(Symptom.SNEEZING, symptoms);
                break;
            case "K":
                addSymptoms(Symptom.BLOCKED_NOSE, symptoms);
                break;
            case "L":
                addSymptoms(Symptom.ITCHY_EYE, symptoms);
                break;
            case "M":
                addSymptoms(Symptom.RED_EYES, symptoms);
                break;
            case "N":
                addSymptoms(Symptom.DIARRHOEA, symptoms);
                break;
            case "O":
                addSymptoms(Symptom.STOMACH_ACHE, symptoms);
                break;
            case "P":
                addSymptoms(Symptom.WET_STOOL, symptoms);
                break;
            case "Q":
                addSymptoms(Symptom.HARD_LUMPY_STOOL, symptoms);
                break;
            case "R":
                addSymptoms(Symptom.NAUSEA, symptoms);
                break;
            case "S":
                addSymptoms(Symptom.VOMITING, symptoms);
                break;
            case "T":
                addSymptoms(Symptom.SLEEPLESSNESS, symptoms);
                break;
            case "U":
                addSymptoms(Symptom.BLURRED_VISION, symptoms);
                break;
            case "V":
                addSymptoms(Symptom.SENSITIVITY_TO_LIGHT_AND_SOUND, symptoms);
                break;
            case "W":
                addSymptoms(Symptom.MUSCLE_ACHE, symptoms);
                break;
            case "X":
                addSymptoms(Symptom.BACK_ACHE, symptoms);
                break;
            case " ":
                break;
            default:
                System.out.println("Invalid symptom choice!");
            }
        }
    }

    /**
     * Adds the users' inputted symptom into the symptoms array.
     *
     * @author Brennanzuz
     * @param symptom  The symptom indicated by the user. Defined and passed from the case statement.
     * @param symptoms List of symptoms
     */
    private static void addSymptoms(Symptom symptom, ArrayList<Symptom> symptoms) {
        if (!symptoms.contains(symptom)) {
            assert symptom != null : symptom + " should not be null";
            symptoms.add(symptom);
        } else {
            System.out.println("You have already entered " + symptom + "!");
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
        //TODO: Put all these in a dictionary with the symptom's display name hashed to the actual Symptom.
        System.out.println("---------------------------------------------------");
        System.out.println("Here is the list of possible symptoms:");
        System.out.println("a. Fever");
        System.out.println("b. Dry Cough");
        System.out.println("c. Cough with Phlegm");
        System.out.println("d. Throat Irritation");
        System.out.println("e. Loss of Taste or Smell");
        System.out.println("f. Runny nose");
        System.out.println("g. Headache");
        System.out.println("h. Chills");
        System.out.println("i. Fatigue");
        System.out.println("j. Sneezing");
        System.out.println("k. Blocked Nose");
        System.out.println("l. Itchy eyes");
        System.out.println("m. Red eyes");
        System.out.println("n. Diarrhoea");
        System.out.println("o. Stomachache");
        System.out.println("p. Wet Stools");
        System.out.println("q. Hard or Lumpy Stools");
        System.out.println("r. Nausea");
        System.out.println("s. Vomiting");
        System.out.println("t. Sleeplessness");
        System.out.println("u. Blurred Vision");
        System.out.println("v. Sensitivity to Light and Sound");
        System.out.println("w. Muscle ache");
        System.out.println("x. Backache");
        System.out.println("\nPlease enter a symptom.");
        System.out.println("---------------------------------------------------");
    }

    /**
     * Displays the possible illnesses that the user may have based on the symptoms he/she has entered.
     *
     * @author tanyizhe, Jeraldchen, Geeeetyx
     * @param symptoms ArrayList of symptoms the user has entered.
     */
    public static void displayPossibleIllness(ArrayList<Symptom> symptoms) {
        ArrayList<IllnessMatch> possibleIllnesses = Diagnosis.getPossibleIllnesses(symptoms);

        if (!possibleIllnesses.isEmpty()) {
            System.out.println("---------------------------------------------------");
            System.out.println("You may have: ");
            for (IllnessMatch illnessMatch : possibleIllnesses) {
                System.out.println(illnessMatch.getIllness().getIllnessName() + "    Match: "
                        + illnessMatch.getSimilarityPercentage() * 100 + "%");
            }
            System.out.println("---------------------------------------------------");
        } else { // no illnesses found
            System.out.println("------------------------------------------------------------");
            System.out.println("Unable to diagnose illness. Please consult a Doctor instead.");
            System.out.println("------------------------------------------------------------");
        }
    }

}
