//@@author JeraldChen
package seedu.duke.ui;

import seedu.duke.diagnosis.symptoms.Symptom;
import seedu.duke.diagnosis.symptoms.SymptomHandler;
import seedu.duke.patient.Patient;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Collections;
import java.util.Hashtable;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import static seedu.duke.save.Storage.saveData;

/**
 * This class stores information on all patients registered by DoctorDuke.
 */

//@@author JeraldChen
public class Information {
    //@@author Geeeetyx
    private static final ArrayList<String> queueList = new ArrayList<>();
    //@@author

    private static final HashMap<Integer, Patient> patientsList = new HashMap<>();

    private static final Logger logger = Logger.getLogger(Information.class.getName());

    public static void storePatientInfo(int hash, Patient patient) {
        logger.setLevel(Level.SEVERE);
        logger.log(Level.INFO, "Storing patient information");
        patientsList.put(hash, patient);
    }

    public static Patient getPatientInfo(int hash) {
        logger.setLevel(Level.SEVERE);
        logger.log(Level.INFO, "Retrieving patient information");
        return patientsList.get(hash);
    }

    //@@author Geeeetyx

    /**
     * Prints the diagnosis history of the Patient.
     * @param hash The key to access the patient's details.
     */
    public static void printDiagnosisHistory(int hash) {
        logger.log(Level.INFO, "Printing diagnosis history");

        Patient patient = patientsList.get(hash);
        Hashtable<String, ArrayList<String>> patientDiagnosisHistory = patient.getPatientDiagnosisHistory();

        System.out.println("---------------------------------------------------");
        if (patientDiagnosisHistory.isEmpty()) {
            System.out.println("You have no past diagnoses");
        } else {
            System.out.println("Your diagnosis history is: ");
            for (int i = 0; i < patientDiagnosisHistory.size(); i++) {
                //@@author Thunderdragon221
                List<String> dates = Collections.list(patientDiagnosisHistory.keys());
                Collections.sort(dates);
                for (String date : dates) {
                    System.out.println(date + ": " + patientDiagnosisHistory.get(date));
                }
                //@@Geeeetyx
            }
        }
    }

    //@@author JeraldChen

    /**
     * Resets the diagnosis history of the patient.
     *
     * @param hash The hashed password of the patient.
     */
    public static void resetDiagnosisHistory(int hash) {
        logger.log(Level.INFO, "Resetting diagnosis history");

        Patient patient = patientsList.get(hash);
        patient.getPatientDiagnosisHistory().clear();
        assert patient.getPatientDiagnosisHistory().size() == 0 : "Diagnosis history should be empty";
        System.out.println("---------------------------------------------------");
        System.out.println("Your diagnosis history has been reset.");
        saveData();
    }

    //@@author Thunderdragon221

    /**
     * Checks the existence of a password in Dr Duke.
     *
     * @param hash hashedPassword to check.
     * @return true if hashedPassword exists in Dr Duke, and false otherwise.
     */
    public static boolean checkHash(int hash) {
        logger.log(Level.INFO, "Checking hash");
        return patientsList.containsKey(hash);
    }

    /**
     * Returns all patient data currently stored in Dr Duke.
     *
     * @return Hashmap of all patient data.
     */
    public static HashMap<Integer, Patient> getAllPatientData() {
        logger.log(Level.INFO, "Retrieving all patient data");
        return patientsList;
    }

    //@@author Thunderdragon221

    /**
     * Hashes the password keyed in by the user.
     * @param password password to hash.
     * @return hash of password.
     */
    public static int hashPassword(String password) {
        return password.hashCode();
    }

    //@@author Jeraldchen

    /**
     * Resets the symptom choice of the patient.
     * @param symptoms The symptom choice of the patient.
     */
    public static void resetSymptomChoice(ArrayList<Symptom> symptoms) {
        if (symptoms.size() != 0) {
            symptoms.clear();
            //@@author Geeeetyx
            System.out.println("---------------------------------------------------");
            //@@author JeraldChen
            System.out.println("Your symptom choice has been reset.");
        } else {
            //@@author Geeeetyx
            System.out.println("---------------------------------------------------");
            //@@authorJeraldChen
            System.out.println("You have not entered any symptoms. No symptoms to reset.");
        }
    }
    //@@author JeraldChen
    /**
     * Prints the symptom history of the patient.
     * @param symptoms The patient's array of symptoms to print.
     */
    public static void viewSymptomHistory(ArrayList<Symptom> symptoms) {
        if (symptoms.size() == 0) {
            System.out.println("---------------------------------------------------");
            System.out.println("You have not entered any symptoms.");
        } else {
            System.out.println("---------------------------------------------------");
            SymptomHandler.printSymptoms(symptoms);
        }
    }

    //@@author Geeeetyx
    /**
     * Deletes a set of selected symptoms from an array containing symptoms
     * previously selected by the patient.
     *
     * @param symptoms The array of symptoms to delete the chosen symptoms from.
     */
    //@@author JeraldChen
    public static boolean deleteSymptom(ArrayList<Symptom> symptoms) {
        boolean hasDeleted = false;
        Scanner scanner = new Scanner(System.in);
        if (symptoms.size() == 0) {
            System.out.println("You have not entered any symptoms.");
            return false;
        }
        System.out.println("---------------------------------------------------");
        System.out.println("Here is the list of your symptoms:");
        for (int i = 0; i < symptoms.size(); i++) {
            System.out.println((i + 1) + ". " + SymptomHandler.toString(symptoms.get(i)));
        }
        System.out.println("Please enter the numbers of the symptom you want to delete.");
        System.out.println("Please put a space between each number to delete multiple symptoms.");
        System.out.println("---------------------------------------------------");

        try {
            List<Integer> deleteIndexIntegerList = parseAndSortInputs(scanner);
            for (int deleteIndex : deleteIndexIntegerList) {
                if (deleteIndex > symptoms.size() || deleteIndex < 0) {
                    System.out.println("'" + deleteIndex + "' is not a valid symptom number.");
                } else {
                    symptoms.remove(deleteIndex - 1);
                    hasDeleted = true;
                }
            }
            if (hasDeleted) {
                displayUpdatedSymptomList(symptoms);
            }
        } catch (IndexOutOfBoundsException e) {
            displayInvalidSymptomChoiceErrorMessage();
        } catch (NumberFormatException e) {
            System.out.println("Please enter only numbers that match the symptom.");
            System.out.println("Returning to main menu.");
        }
        return hasDeleted;
    }

    //@@author tanyizhe
    /**
     * Displays updated symptom list. Displays an alert if list of symptoms is empty.
     * @param symptoms list of symptoms user has entered.
     */
    private static void displayUpdatedSymptomList(ArrayList<Symptom> symptoms) {
        System.out.println("Successfully deleted symptom(s)!");
        System.out.println("Here is the updated list of your symptoms:");
        if (symptoms.isEmpty()) {
            System.out.println("---------- Your symptom list is empty! ----------");
        } else {
            for (int i = 0; i < symptoms.size(); i++) {
                System.out.println((i + 1) + ". " + SymptomHandler.toString(symptoms.get(i)));
            }
        }
    }
    /**
     * Sorts input indexes in a descending order. Removes duplicate values.
     * @param scanner Scans user input.
     * @return List of integers in descending order representing indexes to be deleted.
     */
    private static List<Integer> parseAndSortInputs(Scanner scanner) {
        String[] deleteIndexes = scanner.nextLine().split(" ");
        List<String> deleteSortedIndexes = Arrays.asList(deleteIndexes);
        List<Integer> deleteIndexIntegerList = deleteSortedIndexes.stream()
                .map(Integer::parseInt)
                .distinct()
                .collect(Collectors.toList());
        Collections.sort(deleteIndexIntegerList, Collections.reverseOrder());
        return deleteIndexIntegerList;
    }

    //@@author JeraldChen
    /**
     * Displays invalid symptom choice error message.
     */
    private static void displayInvalidSymptomChoiceErrorMessage() {
        System.out.println("Invalid number! Please enter a valid symptom number.");
        System.out.println("Returning to main menu.");
    }


    //@@author Geeeetyx
    public static ArrayList<String> getQueueList() {
        return queueList;
    }

    public static void addToQueue(String password) {
        queueList.add(password);
    }

    public static void printPatientQueueNumber() {
        int queueNumber = queueList.size();
        System.out.println("---------------------------------------------------");
        System.out.println("This is your queue number");
        System.out.println("---------------------------------------------------");
        System.out.println(queueNumber);
    }
    //@@author
}
