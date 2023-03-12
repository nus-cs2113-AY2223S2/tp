package seedu.duke.ui;

// import seedu.duke.save.Storage;

import seedu.duke.Duke;
import seedu.duke.diagnosis.IllnessMatch;
import seedu.duke.diagnosis.symptoms.Symptom;
import seedu.duke.medicine.MedicineManager;

import java.util.ArrayList;

import static seedu.duke.Duke.password;
import static seedu.duke.save.Storage.saveData;
import static seedu.duke.ui.Information.patientsList;

public class Parser {

    /**
     * @param choice
     * @author Jeraldchen
     * Parses the user input for the main menu.
     */
    public static void parseWelcome(String choice) {
        switch (choice) {
        case "1":
            Menu.register();
            break;
        case "2":
            Menu.login();
            break;
        case "3":
            Menu.exit();
            break;
        default:
            System.out.println("Invalid input!");
            break;
        }
    }

    /**
     * @param choice Users choice of input
     * @author Thunderdragon221
     * Parses the user input for the account menu.
     */
    public static void parseAccountCommand(String choice) {
        switch (choice) {
        case "1":
            ArrayList<Symptom> symptoms = Menu.getUserSymptoms();
            Menu.displayPossibleIllness(symptoms);
            MedicineManager medicineManager = new MedicineManager();

            /*
             * @author Geeeetyx
             * Update patient records with new diagnoses. (Medicine not included yet)
             * Saves to file when updated.
             */
            ArrayList<IllnessMatch> possibleIllnesses = medicineManager.analyseIllness(symptoms);

            for (IllnessMatch illnessMatch : possibleIllnesses) {
                patientsList.get(password).updatePatientDiagnosisHistory(illnessMatch.getIllness().getIllnessName());
            }
            saveData();

        /*
         * @author Thunderdragon221
         *     Parses the user input for the account menu.
         *
         *     @param choice
         */
            break;
        case "2":
            Information.printDiagnosisHistory(Duke.getPassword());
            break;
        case "3":
            Menu.exit();
            break;
        default:
            System.out.println("Invalid input!");
            break;
        }
    }
}
