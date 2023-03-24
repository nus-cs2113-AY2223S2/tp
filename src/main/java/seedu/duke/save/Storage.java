package seedu.duke.save;

import seedu.duke.patient.Patient;
import seedu.duke.ui.Information;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

//@@author Thunderdragon221
/**
 * This class reads and writes information to and from the patient-data file.
 */
public class Storage {

    /** Specifies the directory path to be created */
    private static final String DIR_PATH = "./data/";

    /** Specifies the file path to be created */
    private static final String FILE_PATH = "./data/patient-data.txt";

    private static Logger logger = Logger.getLogger(Storage.class.getName());
    /**
     * Loads each patient's data into a hashmap of patients in the Information class
     * by reading from the patient-data file.
     */
    public static void loadData() {
        try {
            createDirectory();
            createFile();
            readFile();
        } catch (FileNotFoundException e) {
            System.out.println("ERROR: File not found.");
        } catch (CorruptedDataException e) {
            System.out.println("ERROR: Data file is corrupted.");
        } catch (IOException e) {
            System.out.println("ERROR: Failed to create files for storage");
        }
    }

    /**
     * Creates the directory used to store the patient-data file.
     *
     * @throws IOException if createDirectories() is unsuccessful.
     */
    private static void createDirectory() throws IOException {
        Path path = Paths.get(DIR_PATH);
        Files.createDirectories(path);
    }

    /**
     * Creates the patient-data file used to store the data in the patientsList.
     *
     * @throws IOException if createNewFile() is unsuccessful.
     */
    private static void createFile() throws IOException {
        File file = new File(FILE_PATH);
        file.createNewFile();
    }

    /**
     * Reads from the patient-data file and converts the data back into the patient data
     * before storing all read data into Information.patientsList.
     *
     * @throws FileNotFoundException if data file does not exist.
     * @throws CorruptedDataException if data file is corrupted.
     */
    private static void readFile() throws FileNotFoundException, CorruptedDataException {
        File file = new File(FILE_PATH);
        Scanner scanner = new Scanner(file);

        while (scanner.hasNextLine()) {
            String password = scanner.nextLine();
            if (endOfFile(password)) {
                break;
            }

            String name = scanner.nextLine();
            if (endOfFile(name)) {
                logger.log(Level.WARNING, "Corrupted data file");
                throw new CorruptedDataException();
            }

            int numberOfEntries = Integer.parseInt(scanner.nextLine());
            ArrayList<String> diagnosisHistory = new ArrayList<>();

            for (int i = 0; i < numberOfEntries; i++) {
                String diagnosis = scanner.nextLine();
                if (endOfFile(diagnosis)) {
                    logger.log(Level.WARNING, "Corrupted data file");
                    throw new CorruptedDataException();
                }
                diagnosisHistory.add(diagnosis);
            }
            Hashtable<String, ArrayList<String>> medicineHistory = readMedicineHistoryFromFile(scanner);

            int hash = Integer.parseInt(password);
            Patient patient = new Patient(name, hash, diagnosisHistory, medicineHistory);
            Information.storePatientInfo(hash, patient);
        }
        scanner.close();
    }
    //@@author tanyizhe
    /**
     * Reads medicine history data from data storage file.
     * @param scanner Scanner that scans user input.
     * @return Hashtable with key String and value ArrayList of Strings recording Medicine History of patient.
     * @throws CorruptedDataException Exception occurs when file has records more medicines than expected.
     */
    private static Hashtable<String, ArrayList<String>> readMedicineHistoryFromFile(Scanner scanner)
            throws CorruptedDataException {

        int numberOfMedicineEntries = Integer.parseInt(scanner.nextLine());
        Hashtable<String, ArrayList<String>> medicineHistory = new Hashtable();

        for (int entry = 0; entry < numberOfMedicineEntries; entry++) {
            String dateMedicineString = scanner.nextLine();
            if (endOfFile(dateMedicineString)) {
                logger.log(Level.WARNING, "Corrupted data file");
                throw new CorruptedDataException();
            }
            String[] splitDateMedicineStrings = dateMedicineString.split(" ");
            ArrayList<String> medicines = new ArrayList<>();
            for (int medStringCount = 1; medStringCount < splitDateMedicineStrings.length; medStringCount++) {
                medicines.add(splitDateMedicineStrings[medStringCount]);
            }
            medicineHistory.put(splitDateMedicineStrings[0], medicines);
        }
        return medicineHistory;
    }
    //@@author Thunderdragon221
    /**
     * Writes to the patient-data file to save all patients' data DoctorDuke currently has.
     */
    public static void saveData() {
        try {
            FileWriter writer = new FileWriter(FILE_PATH);
            for (Map.Entry<Integer, Patient> entry : Information.getAllPatientData().entrySet()) {
                Patient patient = entry.getValue();
                String name = patient.getName();
                int password = patient.getPassword();
                ArrayList<String> diagnosisHistory = patient.getPatientDiagnosisHistory();
                int numberOfDiagnoses = diagnosisHistory.size();
                Hashtable<String, ArrayList<String>> medicineHistory = patient.getPatientMedicineHistory();
                int numberOfMedicines = medicineHistory.size();

                writer.write(password + "\n");
                writer.write(name + "\n");
                writer.write(numberOfDiagnoses + "\n");
                for (String diagnosis : diagnosisHistory) {
                    writer.write(diagnosis + "\n");
                }
                writer.write(numberOfMedicines + "\n");
                writeMedicineHistory(writer, medicineHistory);
            }
            writer.close();
        } catch (IOException e) {
            logger.log(Level.WARNING, "Unable to save data to file");
            System.out.println("ERROR: Unable to save data to file.");
        }
    }
    //@@author tanyizhe
    /**
     * Writes medicine history of patient to data file for storage.
     * @param writer Writer that writes onto a file.
     * @param medicineHistory Hashtable with key String with value of patient's medicine History
     * @throws IOException Exception thrown when file cannot be written on or found.
     */
    private static void writeMedicineHistory(FileWriter writer, Hashtable<String,
                ArrayList<String>> medicineHistory) throws IOException {
        List<String> dates = Collections.list(medicineHistory.keys());
        Collections.sort(dates);
        for (String date : dates) {
            writer.write(date + " ");
            for (String medString : medicineHistory.get(date)) {
                writer.write(medString + " ");
            }
            writer.write("\n");
        }
    }
    //@@author Thunderdragon221
    /**
     * Checks whether the end of the file has been reached.
     *
     * @param data Current line being read from the patient-data file.
     * @return true if the line is empty, and false otherwise.
     */
    private static boolean endOfFile(String data) {
        return data.matches("^ *$");
    }
}
