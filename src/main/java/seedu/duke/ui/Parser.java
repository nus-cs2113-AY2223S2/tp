//@@author Jeraldchen
package seedu.duke.ui;

// import seedu.duke.save.Storage;

// import static seedu.duke.ui.Information.patientsList;

import seedu.duke.Duke;
import seedu.duke.diagnosis.symptoms.Symptom;
import seedu.duke.medicine.MedicineManager;
// import seedu.duke.ui.Information;
import java.util.ArrayList;

public class Parser {

    /**
     * Parses the user input for the main menu.
     * @param choice
     */
    public static void parseWelcome (String choice) {
        switch(choice) {
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

    //@@author Thunderdragon221
    public static void parseAccountCommand (String choice) {
        switch(choice) {
        case "1":
            ArrayList<Symptom> symptoms = Menu.getUserSymptoms();
            Menu.displayPossibleIllness(symptoms);
            MedicineManager medicineManager = new MedicineManager();
            medicineManager.analyseIllness(symptoms);
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
