//@@author Thunderdragon221
package seedu.duke.save;

import seedu.duke.medicine.MedicineManager;
import seedu.duke.patient.Patient;
import seedu.duke.ui.Information;
import seedu.duke.ui.Menu;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * This class reads and writes information to and from the patient-data file.
 */
public class Storage {
    //@@author Geeeetyx
    private static final String QUEUE_FILE_PATH = "./data/queue_data.txt";
    //@@author Thunderdragon221

    /** Specifies the directory path to be created */
    private static final String DIR_PATH = "./data/";

    /** Specifies the file path to be created */
    private static final String FILE_PATH = "./data/patient-data.txt";

    /** Stores the data read in from patient-data file to check hash similar to a checksum */
    private static String dataCompare = "";

    /** Stores the data read in from queue_data file to check hash similar to a checksum */
    private static String queueDataCompare = "";

    private static final Logger logger = Logger.getLogger(Storage.class.getName());
    /**
     * Loads each patient's data into a hashmap of patients in the Information class
     * by reading from the patient-data file.
     */
    public static void loadData() {
        try {
            logger.setLevel(Level.SEVERE);
            createDirectory();
            createFile();
            readFile();
            //@@author Geeeetyx
            createQueueFile();
            readQueueFile();
            //@@author Thunderdragon221
        } catch (FileNotFoundException e) {
            System.out.println("ERROR: File not found.");
        } catch (CorruptedDataException e) {
            System.out.println("ERROR: Data file is corrupted. Clear all data files " +
                    "or restore data to uncorrupted state before trying again.");
            Menu.exitWithoutSaving();
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
        String hashString;

        if (!scanner.hasNextLine()) {
            scanner.close();
            return;
        }

        hashString = scanner.nextLine();
        if (((!hashString.matches("^[0-9]+$")) && (!hashString.matches("^-[0-9]+$"))) ||
                hashString.matches("^0{2,}$")) {
            logger.log(Level.INFO, "Corrupted data file");
            throw new CorruptedDataException();
        }
        int fileHash = Integer.parseInt(hashString);

        ArrayList<Patient> patientsList = new ArrayList<>();
        while (scanner.hasNextLine()) {
            String password = scanner.nextLine();
            dataCompare += password + "\n";
            if (emptyField(password)) {
                break;
            } else if ((!password.matches("^[0-9]+$")) && (!password.matches("^-[0-9]+$"))) {
                logger.log(Level.INFO, "Corrupted data file");
                throw new CorruptedDataException();
            }

            if (!scanner.hasNextLine()) {
                logger.log(Level.INFO, "Corrupted data file");
                throw new CorruptedDataException();
            }
            String name = scanner.nextLine();
            dataCompare += name + "\n";
            if (emptyField(name)) {
                logger.log(Level.INFO, "Corrupted data file");
                throw new CorruptedDataException();
            }

            if (!scanner.hasNextLine()) {
                logger.log(Level.INFO, "Corrupted data file");
                throw new CorruptedDataException();
            }
            String line = scanner.nextLine();
            if (emptyField(line) || (!line.matches("[0-9]+"))) {
                logger.log(Level.INFO, "Corrupted data file");
                throw new CorruptedDataException();
            }
            int numberOfEntries = Integer.parseInt(line);
            dataCompare += numberOfEntries + "\n";
            Hashtable<String, ArrayList<String>> diagnosisHistory = new Hashtable<>();

            for (int i = 0; i < numberOfEntries; i++) {
                if (!scanner.hasNextLine()) {
                    logger.log(Level.INFO, "Corrupted data file");
                    throw new CorruptedDataException();
                }
                String dateDiagnosisString = scanner.nextLine();
                dataCompare += dateDiagnosisString + "\n";
                if (emptyField(dateDiagnosisString)) {
                    logger.log(Level.INFO, "Corrupted data file");
                    throw new CorruptedDataException();
                }
                String[] splitDateDiagnosisStrings = dateDiagnosisString.split("%");
                ArrayList<String> diagnoses = new ArrayList<>();
                for (int diagnosisStringCount = 1; diagnosisStringCount < splitDateDiagnosisStrings.length;
                     diagnosisStringCount++) {
                    diagnoses.add(splitDateDiagnosisStrings[diagnosisStringCount]);
                }
                String date = splitDateDiagnosisStrings[0];
                if (!date.matches("^[0-9]{4}/[0-9]{2}/[0-9]{2}$")) {
                    logger.log(Level.INFO, "Corrupted data file");
                    throw new CorruptedDataException();
                }
                int year = Integer.parseInt(date.substring(0, 4));
                int month = Integer.parseInt(date.substring(5, 7));
                int day = Integer.parseInt(date.substring(8, 10));
                if (!isValidDate(year, month, day)) {
                    logger.log(Level.INFO, "Corrupted data file");
                    throw new CorruptedDataException();
                }
                diagnosisHistory.put(date, diagnoses);
            }
            Hashtable<String, ArrayList<String>> medicineHistory = readMedicineHistoryFromFile(scanner);

            int hash = Integer.parseInt(password);
            Patient patient = new Patient(name, hash, diagnosisHistory, medicineHistory);
            patientsList.add(patient);
        }

        int dataCompareHash = dataCompare.hashCode();
        if (dataCompareHash != fileHash) {
            logger.log(Level.INFO, "Corrupted data file");
            throw new CorruptedDataException();
        }

        for (Patient patient : patientsList) {
            Information.storePatientInfo(patient.getPassword(), patient);
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

        //@@author Thunderdragon221
        String line = scanner.nextLine();
        if (emptyField(line) || (!line.matches("[0-9]+"))) {
            logger.log(Level.INFO, "Corrupted data file");
            throw new CorruptedDataException();
        }
        //@@author tanyizhe
        int numberOfMedicineEntries = Integer.parseInt(line);
        dataCompare += numberOfMedicineEntries + "\n";
        Hashtable<String, ArrayList<String>> medicineHistory = new Hashtable();

        for (int entry = 0; entry < numberOfMedicineEntries; entry++) {
            //@@author Thunderdragon221
            if (!scanner.hasNextLine()) {
                logger.log(Level.INFO, "Corrupted data file");
                throw new CorruptedDataException();
            }
            //@@author tanyizhe
            String dateMedicineString = scanner.nextLine();
            dataCompare += dateMedicineString + "\n";
            if (emptyField(dateMedicineString)) {
                logger.log(Level.INFO, "Corrupted data file");
                throw new CorruptedDataException();
            }
            String[] splitDateMedicineStrings = dateMedicineString.split("%");
            ArrayList<String> medicines = new ArrayList<>();
            for (int medStringCount = 1; medStringCount < splitDateMedicineStrings.length; medStringCount++) {
                medicines.add(splitDateMedicineStrings[medStringCount]);
            }
            //@@author Thunderdragon221
            String date = splitDateMedicineStrings[0];
            if (!date.matches("^[0-9]{4}/[0-9]{2}/[0-9]{2}$")) {
                logger.log(Level.INFO, "Corrupted data file");
                throw new CorruptedDataException();
            }
            int year = Integer.parseInt(date.substring(0, 4));
            int month = Integer.parseInt(date.substring(5, 7));
            int day = Integer.parseInt(date.substring(8, 10));
            if (!isValidDate(year, month, day)) {
                logger.log(Level.INFO, "Corrupted data file");
                throw new CorruptedDataException();
            }


            MedicineManager medicineManager = new MedicineManager();
            for (String medicine : medicines) {
                if (!medicineManager.isValidMedicine(medicine)) {
                    logger.log(Level.INFO, "Corrupted data file");
                    throw new CorruptedDataException();
                }
            }

            //@@author tanyizhe
            medicineHistory.put(date, medicines);
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
            String data = "";
            for (Map.Entry<Integer, Patient> entry : Information.getAllPatientData().entrySet()) {
                Patient patient = entry.getValue();
                String name = patient.getName();
                int password = patient.getPassword();
                Hashtable<String, ArrayList<String>> diagnosisHistory = patient.getPatientDiagnosisHistory();
                int numberOfDiagnoses = diagnosisHistory.size();
                Hashtable<String, ArrayList<String>> medicineHistory = patient.getPatientMedicineHistory();
                int numberOfMedicines = medicineHistory.size();

                data += password + "\n" + name + "\n" + numberOfDiagnoses + "\n";
                List<String> dates = Collections.list(diagnosisHistory.keys());
                Collections.sort(dates);
                for (String date : dates) {
                    data += date + "%";
                    for (String diagnosisString : diagnosisHistory.get(date)) {
                        data += diagnosisString + "%";
                    }
                }
                if (dates.size() > 0) {
                    data += "\n";
                }
                data += numberOfMedicines + "\n";
                data = writeMedicineHistory(data, medicineHistory);
            }
            int fileHash = data.hashCode();
            data = fileHash + "\n" + data;
            writer.write(data);
            writer.close();
        } catch (IOException e) {
            logger.log(Level.WARNING, "Unable to save data to file");
            System.out.println("ERROR: Unable to save data to file.");
        }
    }
    //@@author tanyizhe
    /**
     * Writes medicine history of patient to data file for storage.
     * @param data that writes onto a file.
     * @param medicineHistory Hashtable with key String with value of patient's medicine History
     * @return updated data to be written to file
     */
    private static String writeMedicineHistory(String data, Hashtable<String,
            ArrayList<String>> medicineHistory) {
        List<String> dates = Collections.list(medicineHistory.keys());
        Collections.sort(dates);
        for (String date : dates) {
            data += date + "%";
            for (String medString : medicineHistory.get(date)) {
                data += medString + "%";
            }
        }
        if (dates.size() > 0) {
            data += "\n";
        }
        return data;
    }
    //@@author Thunderdragon221
    /**
     * Checks whether an empty field is scanned.
     *
     * @param data Current line being read from the patient-data file.
     * @return true if the line is empty, and false otherwise.
     */
    private static boolean emptyField(String data) {
        return data.matches("^ *$");
    }

    /**
     * Checks whether the date is valid.
     *
     * @param year year of the date in integer YYYY format.
     * @param month month of the date in integer MM format.
     * @param day day of the date in integer DD format.
     * @return true if the date is valid and false otherwise.
     */
    private static boolean isValidDate(int year, int month, int day) {
        boolean isValid = true;

        try {
            LocalDate.of(year, month, day);
        } catch (DateTimeException e) {
            isValid = false;
        }

        return isValid;
    }

    //@@author Geeeetyx
    private static void createQueueFile() throws IOException {
        File file = new File(QUEUE_FILE_PATH);
        file.createNewFile();
    }

    public static void saveQueue() {
        try {
            FileWriter writer = new FileWriter(QUEUE_FILE_PATH);
            ArrayList<String> queueList = Information.getQueueList();
            String data = "";
            for (String currentQueueNumber : queueList) {
                data += currentQueueNumber + "\n";
            }
            //@@author Thunderdragon221
            int fileHash = data.hashCode();
            data = fileHash + "\n" + data;
            writer.write(data);
            writer.close();
            //@@author Geeeetyx
        } catch (IOException e) {
            System.out.println("ERROR: Unable to save queue to file");
        }
    }

    private static void readQueueFile() throws FileNotFoundException, CorruptedDataException {
        File file = new File(QUEUE_FILE_PATH);
        Scanner scanner = new Scanner(file);
        //@@author Thunderdragon221
        String hashString;
        if (!scanner.hasNextLine()) {
            scanner.close();
            return;
        }
        hashString = scanner.nextLine();
        if (((!hashString.matches("^[0-9]+$")) && (!hashString.matches("^-[0-9]+$"))) ||
                hashString.matches("^0{2,}$")) {
            logger.log(Level.INFO, "Corrupted data file");
            throw new CorruptedDataException();
        }
        int fileHash = Integer.parseInt(hashString);
        //@@author Geeeetyx
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            Information.addToQueue(line);
            queueDataCompare += line + "\n";
        }
        //@@author Thunderdragon221
        int queueDataHashCompare = queueDataCompare.hashCode();
        if (fileHash != queueDataHashCompare) {
            logger.log(Level.INFO, "Corrupted data file");
            throw new CorruptedDataException();
        }
        scanner.close();
    }
}
