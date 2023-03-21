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
import java.util.Map;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * This class reads and writes information to and from the patient-data file.
 * @author Thunderdragon221
 */
public class Storage {

    /** Specifies the directory path to be created */
    static final String DIR_PATH = "./data/";

    /** Specifies the file path to be created */
    static final String FILE_PATH = "./data/patient-data.txt";

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
    public static void createDirectory() throws IOException {
        Path path = Paths.get(DIR_PATH);
        Files.createDirectories(path);
    }

    /**
     * Creates the patient-data file used to store the data in the patientsList.
     *
     * @throws IOException if createNewFile() is unsuccessful.
     */
    public static void createFile() throws IOException {
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
    public static void readFile() throws FileNotFoundException, CorruptedDataException {
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

            int hash = Integer.parseInt(password);
            Patient patient = new Patient(name, hash, diagnosisHistory);
            Information.storePatientInfo(hash, patient);
        }
        scanner.close();
    }

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

                writer.write(password + "\n");
                writer.write(name + "\n");
                writer.write(numberOfDiagnoses + "\n");
                for (String diagnosis : diagnosisHistory) {
                    writer.write(diagnosis + "\n");
                }
            }
            writer.close();
        } catch (IOException e) {
            logger.log(Level.WARNING, "Unable to save data to file");
            System.out.println("ERROR: Unable to save data to file.");
        }
    }

    /**
     * Checks whether the end of the file has been reached.
     *
     * @param data Current line being read from the patient-data file.
     * @return true if the line is empty, and false otherwise.
     */
    public static boolean endOfFile(String data) {
        return data.matches("^ *$");
    }
}
