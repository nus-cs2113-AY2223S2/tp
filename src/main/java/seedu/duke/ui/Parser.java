package seedu.duke.ui;

import seedu.duke.Duke;
import seedu.duke.diagnosis.IllnessMatch;
import seedu.duke.diagnosis.symptoms.Symptom;
import seedu.duke.medicine.MedicineManager;
import seedu.duke.patient.Patient;

import java.util.ArrayList;

import static seedu.duke.Duke.password;
import static seedu.duke.save.Storage.saveData;

public class Parser {

    /**
     * Parses the user input for the main menu.
     * @author Jeraldchen
     * @param choice Users choice of input.
     */
    public static void parseWelcome(String choice) {
        assert choice != null : "Choice cannot be null";
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
     * Parses the user input for the account menu.
     * @author Thunderdragon221, Geeeetyx
     *
     * @param choice Users choice of input.
    */
    public static void parseAccountCommand(String choice) {
        assert choice != null : "Choice cannot be null";
        switch (choice) {
        case "1":
            ArrayList<Symptom> symptoms = Menu.getUserSymptoms();
            Menu.displayPossibleIllness(symptoms);
            MedicineManager medicineManager = new MedicineManager();
            ArrayList<IllnessMatch> possibleIllnesses = medicineManager.analyseIllness(symptoms);
            Patient user = Information.getPatientInfo(password);
            for (IllnessMatch illnessMatch : possibleIllnesses) {
                user.updatePatientDiagnosisHistory(illnessMatch.getIllness().getIllnessName());
            }
            saveData();
            break;
        case "2":
            Information.printDiagnosisHistory(Duke.getPassword());
            break;
        case "3":
            Information.resetDiagnosisHistory(Duke.getPassword());
            break;
        case "4":
            Information.resetSymptomChoice(Menu.symptoms);
            break;
        case "5":
            Menu.exit();
            break;
        default:
            System.out.println("Invalid input!");
            break;
        }
    }
}
