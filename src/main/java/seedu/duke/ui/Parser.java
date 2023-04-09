//@@author JeraldChen
package seedu.duke.ui;

import seedu.duke.Duke;
import seedu.duke.diagnosis.IllnessMatch;
import seedu.duke.diagnosis.symptoms.Symptom;
import seedu.duke.medicine.MedicineManager;
import seedu.duke.patient.Patient;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;
import java.time.LocalDateTime;

import static seedu.duke.save.Storage.saveData;
import static seedu.duke.save.Storage.saveQueue;

public class Parser {

    /**
     * Parses the user input for the main menu.
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

    //@@author Thunderdragon221, Geeeetyx, tanyizhe
    /**
     * Parses the user input for the account menu.
     *
     * @param choice Users choice of input.
    */
    public static void parseAccountCommand(String choice){
        assert choice != null : "Choice cannot be null";
        Patient user = Information.getPatientInfo(Duke.getPassword());
        MedicineManager medicineManager = new MedicineManager();

        switch (choice) {
        case "1":
            try {
                //@@author tanyizhe
                ArrayList<Symptom> symptoms = Menu.getUserSymptoms();
                if (symptoms.isEmpty()) {
                    System.out.println("You have not entered any symptoms!");
                } else {
                    Menu.displayPossibleIllness(symptoms);
                    //@@author Geeeetyx
                    System.out.println("Below are some recommended medications for you to purchase:");
                    System.out.println("-----------------------------------------------------------");
                    //@@author tanyizhe
                    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
                    LocalDateTime now = LocalDateTime.now();
                    ArrayList<IllnessMatch> possibleIllnesses = medicineManager.analyseIllness(symptoms);
                    ArrayList<String> diagnoses = new ArrayList<>();
                    formatMedicineDate(user, medicineManager, dtf, now, possibleIllnesses, diagnoses);
                    if (diagnoses.size() > 0) {
                        user.updatePatientDiagnosisHistory(dtf.format(now), diagnoses);
                    }
                    saveData();
                    saveQueue();
                }
            } catch (Exception e) {
                System.out.println("Invalid input!");
            }
            break;
        //@@author Thunderdragon221
        case "2":
            Information.printDiagnosisHistory(Duke.getPassword());
            break;
        case "3":
            Information.resetDiagnosisHistory(Duke.getPassword());
            break;
        //@@author Jeraldchen
        case "4":
            Information.viewSymptomHistory(Menu.symptoms);
            break;
        case "5":
            boolean deleted = Information.deleteSymptom(Menu.symptoms);
            if (deleted) {
                //@@author Geeeetyx
                ArrayList<Symptom> symptoms = Menu.symptoms;
                if (!symptoms.isEmpty()) {
                    System.out.println("---------------------------------------------------");
                    System.out.println("Below is your new diagnosis:");
                    Menu.displayPossibleIllness(symptoms);
                }
                ArrayList<IllnessMatch> possibleIllnesses = medicineManager.analyseIllness(symptoms);
                //@@author Thunderdragon221
                ArrayList<String> diagnoses = new ArrayList<>();
                DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
                LocalDateTime now = LocalDateTime.now();
                //@@author Geeeetyx
                formatMedicineDate(user, medicineManager, dtf, now, possibleIllnesses, diagnoses);
                //@@author Thunderdragon221
                if (diagnoses.size() > 0) {
                    user.updatePatientDiagnosisHistory(dtf.format(now), diagnoses);
                }
                saveData();
                saveQueue();
            }
            break;
        case "6":
            Information.resetSymptomChoice(Menu.symptoms);
            break;
        //@@author tanyizhe
        case "7":
            user.printPatientMedicineHistory();
            break;
        case "8":
            medicineManager.listMedicines();
            break;
        case "9":
            Scanner phraseScanner = new Scanner(System.in);
            Menu.displayFindMedicinePrompt();
            String phrase = phraseScanner.nextLine();
            Menu.displayFindMedicineMessage(phrase);
            medicineManager.findMedicine(phrase);
            break;
        //@@author Geeeetyx
        case "10": //show queue number
            Information.printPatientQueueNumber();
            break;
        case "0":
            Menu.exit();
            break;
        default:
            System.out.println("Invalid input!");
            break;
        }
    }
    //@@author tanyizhe
    private static void formatMedicineDate(Patient user, MedicineManager medicineManager,
                                           DateTimeFormatter dtf, LocalDateTime now,
                                           ArrayList<IllnessMatch> possibleIllnesses,
                                           ArrayList<String> diagnoses) {
        for (IllnessMatch illnessMatch : possibleIllnesses) {
            diagnoses.add(illnessMatch.getIllness().getIllnessName());
            ArrayList<String> medicineArrayList = medicineManager
                    .getRelevantMedicationInString(illnessMatch.getIllness().getIllnessName());
            if (!(medicineArrayList == null)) {
                user.updatePatientMedicineHistory(dtf.format(now), medicineArrayList);
            }
        }
    }
}
