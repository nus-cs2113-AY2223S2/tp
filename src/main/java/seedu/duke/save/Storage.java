//@@author Thunderdragon221

package seedu.duke.save;

import java.util.ArrayList;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Map;
import java.util.HashMap;
import java.util.Scanner;
import java.io.FileWriter;
import seedu.duke.patient.Patient;

public class Storage {

    /** Specifies the directory path to be created */
    static final String DIR_PATH = "./data/";

    /** Specifies the file path to be created */
    static final String FILE_PATH = "./data/patient-data.txt";

    /**
     * Loads each patient's data into a hashmap of patients by reading from the patient-data file.
     *
     * @return a Hashmap of patients' passwords mapped to their data.
     */
    public static HashMap<String, Patient> loadData() {
        HashMap<String, Patient> patientsList = new HashMap<String, Patient>();
        try {
            createDirectory();
            createFile();
            patientsList = readFile();
        } catch (FileNotFoundException e) {
            System.out.println("ERROR: File not found.");
        } catch (CorruptedDataException e) {
            System.out.println("ERROR: Data file is corrupted.");
        } catch (IOException e) {
            System.out.println("ERROR: Failed to create files for storage");
        }

        return patientsList;
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
     * before creating a Hashmap that maps each patient's password to their data.
     *
     * @return a Hashmap of patients' passwords mapped to their data.
     * @throws FileNotFoundException if data file does not exist.
     * @throws CorruptedDataException if data file is corrupted.
     */
    public static HashMap<String, Patient> readFile() throws FileNotFoundException,
            CorruptedDataException {
        File file = new File(FILE_PATH);
        Scanner scanner = new Scanner(file);

        HashMap<String, Patient> patientsList = new HashMap<String, Patient>();

        while (scanner.hasNextLine()) {
            String password = scanner.nextLine();
            if (endOfFile(password)) {
                break;
            }

            String name = scanner.nextLine();
            if (endOfFile(name)) {
                throw new CorruptedDataException();
            }

            int numberOfEntries = Integer.parseInt(scanner.nextLine());
            ArrayList<String> diagnosisHistory = new ArrayList<String>();

            for (int i = 0; i < numberOfEntries; i++) {
                String diagnosis = scanner.nextLine();
                if (endOfFile(diagnosis)) {
                    throw new CorruptedDataException();
                }
                diagnosisHistory.add(diagnosis);
            }

            Patient patient = new Patient(name, password, diagnosisHistory);
            patientsList.put(password, patient);
        }
        scanner.close();
        return patientsList;
    }

    /**
     * Writes to the patient-data file to save all patients' data DoctorDuke currently has.
     *
     * @param patientsList Hashmap of patients' passwords mapped to their data.
     */
    public static void saveData(HashMap<String, Patient> patientsList) {
        try {
            FileWriter writer = new FileWriter(FILE_PATH);
            for (Map.Entry<String, Patient> entry : patientsList.entrySet()) {
                Patient patient = entry.getValue();
                String name = patient.getName();
                String password = patient.getPassword();
                ArrayList<String> diagnosisHistory = patient.getPatientDiagnosisHistory();
                int numberOfDiagnoses = diagnosisHistory.size();

                writer.write(password + "\n");
                writer.write(name + "\n");
                writer.write(numberOfDiagnoses + "\n");
                for (int i = 0; i < numberOfDiagnoses; i++) {
                    String diagnosis = diagnosisHistory.get(i);
                    writer.write(diagnosis + "\n");
                }
            }
            writer.close();
        } catch (IOException e) {
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