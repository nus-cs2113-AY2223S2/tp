//@@author Thunderdragon221
package seedu.duke.save;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import seedu.duke.patient.Patient;
import seedu.duke.stubs.InformationStub;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Map;
import java.util.List;
import java.util.Collections;
import java.util.Hashtable;

/**
 * Class containing test methods to test the methods in the Storage class.
 */

public class StorageTest {

    /** Specifies the directory path to be created */
    private static final String DUMMY_DIR_PATH = "./dummy-data/";

    /** Specifies the file path to be created */
    private static final String DUMMY_FILE_PATH = "./dummy-data/dummy-patient-data.txt";

    private static final String DUMMY_QUEUE_PATH = "/dummy-data/dummy_queue_data.txt";

    private static final String DUMMY_FILE_PATH_TEST = "./dummy-data/dummy-patient-data-2.txt";

    private static final String DUMMY_QUEUE_PATH_TEST = "./dummy-data/dummy_queue_data_2.txt";

    @Test
    public void createDirectory() {
        Path path = Paths.get(DUMMY_DIR_PATH);
        try {
            Files.createDirectories(path);
        } catch (IOException e) {
            Assertions.assertFalse(false);
        }
        Assertions.assertTrue(Files.exists(path));
        Assertions.assertTrue(Files.isDirectory(path));
    }

    @Test
    public void createFile() {
        Path path = Paths.get(DUMMY_FILE_PATH);
        try {
            Files.createFile(path);
        } catch (IOException e) {
            Assertions.assertFalse(false);
        }
        Assertions.assertTrue(Files.exists(path));
        Assertions.assertTrue(Files.isRegularFile(path));
    }

    @Test
    public void emptyField() {
        String data = "";
        Assertions.assertTrue(data.matches("^ *$"));
    }

    @Test
    public void saveData() {
        createDirectory();
        createFile();

        String data = "";
        try {
            FileWriter writer = new FileWriter(DUMMY_FILE_PATH);
            for (Map.Entry<Integer, Patient> entry : InformationStub.getAllPatientData().entrySet()) {
                Patient patient = entry.getValue();
                String name = patient.getName();
                int password = patient.getPassword();
                Hashtable<String, ArrayList<String>>  diagnosisHistory = patient.getPatientDiagnosisHistory();
                int numberOfDiagnoses = diagnosisHistory.size();
                Hashtable<String, ArrayList<String>> medicineHistory = patient.getPatientMedicineHistory();
                int numberOfMedicines = medicineHistory.size();

                data += password + "\n";
                data += name + "\n";
                data += numberOfDiagnoses + "\n";
                List<String> diagnosisDates = Collections.list(diagnosisHistory.keys());
                Collections.sort(diagnosisDates);
                for (String date : diagnosisDates) {
                    data += date + "%";
                    for (String diagnosisString : diagnosisHistory.get(date)) {
                        data += diagnosisString + "%";
                    }
                }
                if (diagnosisDates.size() > 0) {
                    data += "\n";
                }
                data += numberOfMedicines + "\n";
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
            }
            int fileHash = data.hashCode();
            data = fileHash + "\n" + data;
            writer.write(data);
            writer.close();

            Path path = Paths.get(DUMMY_FILE_PATH);
            // Create dummy file with identical data to test corruption checking
            Path pathTest = Paths.get(DUMMY_FILE_PATH_TEST);

            Files.createFile(path);
            FileWriter writerTest = new FileWriter(DUMMY_FILE_PATH_TEST);
            String dataTest = "";
            dataTest += 1 + "\nJerry\n" + 1 + "\n2023/01/01%Fever%\n" + 1 + "\n2023/01/01%Paracetamol%\n";
            int fileHashTest = dataTest.hashCode();
            dataTest = fileHashTest + "\n" + dataTest;
            writerTest.write(dataTest);
            writerTest.close();

            byte[] pathBytes = Files.readAllBytes(path);
            byte[] pathTestBytes = Files.readAllBytes(pathTest);

            Assertions.assertArrayEquals(pathBytes, pathTestBytes);
        } catch (IOException e) {
            Assertions.assertFalse(false);
        }
    }

    @Test
    public void loadData() {
        createDirectory();
        createFile();
        saveData();

        try {
            File file = new File(DUMMY_FILE_PATH);
            Scanner scanner = new Scanner(file);
            String dataCompare = "";

            while (scanner.hasNextLine()) {
                int fileHash = Integer.parseInt(scanner.nextLine());
                String password = scanner.nextLine();
                String name = scanner.nextLine();
                int hash = Integer.parseInt(password);
                dataCompare += hash + "\n" + name + "\n";

                int numberOfEntries = Integer.parseInt(scanner.nextLine());
                dataCompare += numberOfEntries + "\n";
                Hashtable<String, ArrayList<String>>  diagnosisHistory = new Hashtable<>();
                for (int i = 0; i < numberOfEntries; i++) {
                    String dateDiagnosisString = scanner.nextLine();
                    dataCompare += dateDiagnosisString + "\n";
                    String[] splitDateDiagnosisStrings = dateDiagnosisString.split("%");
                    ArrayList<String> diagnoses = new ArrayList<>();
                    for (int diagnosisStringCount = 1; diagnosisStringCount < splitDateDiagnosisStrings.length;
                         diagnosisStringCount++) {
                        diagnoses.add(splitDateDiagnosisStrings[diagnosisStringCount]);
                    }
                    String date = splitDateDiagnosisStrings[0];
                    diagnosisHistory.put(date, diagnoses);
                }

                int numberOfMedicineEntries = Integer.parseInt(scanner.nextLine());
                dataCompare += numberOfMedicineEntries + "\n";
                Hashtable<String, ArrayList<String>> medicineHistory = new Hashtable();
                for (int entry = 0; entry < numberOfMedicineEntries; entry++) {
                    String dateMedicineString = scanner.nextLine();
                    dataCompare += dateMedicineString + "\n";
                    String[] splitDateMedicineStrings = dateMedicineString.split("%");
                    ArrayList<String> medicines = new ArrayList<>();
                    for (int medStringCount = 1; medStringCount < splitDateMedicineStrings.length; medStringCount++) {
                        medicines.add(splitDateMedicineStrings[medStringCount]);
                    }
                    medicineHistory.put(splitDateMedicineStrings[0], medicines);
                }
                int fileHashCompare = dataCompare.hashCode();

                Assertions.assertEquals(name, "Jerry");
                Assertions.assertEquals(hash, 1);
                Assertions.assertEquals(numberOfEntries, 1);
                Assertions.assertEquals(diagnosisHistory.size(), 1);
                Assertions.assertTrue(diagnosisHistory.containsKey("2023/01/01"));
                Assertions.assertEquals(diagnosisHistory.get("2023/01/01").size(), 1);
                Assertions.assertTrue(diagnosisHistory.get("2023/01/01").get(0).equals("Fever"));
                Assertions.assertEquals(numberOfMedicineEntries, 1);
                Assertions.assertEquals(medicineHistory.size(), 1);
                Assertions.assertTrue(medicineHistory.containsKey("2023/01/01"));
                Assertions.assertEquals(medicineHistory.get("2023/01/01").size(), 1);
                Assertions.assertTrue(medicineHistory.get("2023/01/01").get(0).equals("Paracetamol"));
                Assertions.assertEquals(fileHash, fileHashCompare);
            }
            scanner.close();
        } catch (IOException e) {
            Assertions.assertFalse(false);
        }
    }

    @Test
    public void saveQueue() {
        createDirectory();
        createFile();

        String data = "";
        try {
            FileWriter writer = new FileWriter(DUMMY_QUEUE_PATH);
            ArrayList<String> queueList = InformationStub.getQueueList();
            for (String queueNumber : queueList) {
                data += queueNumber + "\n";
            }
            int fileHash = data.hashCode();
            data = fileHash + "\n" + data;
            writer.write(data);
            writer.close();

            Path path = Paths.get(DUMMY_QUEUE_PATH);
            // Create dummy file with identical data to test corruption checking
            Path pathTest = Paths.get(DUMMY_QUEUE_PATH_TEST);

            Files.createFile(path);
            FileWriter writerTest = new FileWriter(DUMMY_QUEUE_PATH_TEST);
            String dataTest = "1\n";
            int fileHashTest = dataTest.hashCode();
            dataTest = fileHashTest + "\n" + dataTest;
            writerTest.write(dataTest);
            writerTest.close();

            byte[] pathBytes = Files.readAllBytes(path);
            byte[] pathTestBytes = Files.readAllBytes(pathTest);

            Assertions.assertArrayEquals(pathBytes, pathTestBytes);
        } catch (IOException e) {
            Assertions.assertFalse(false);
        }
    }

    @Test
    public void loadQueue() {
        createDirectory();
        createFile();
        saveQueue();

        try {
            File file = new File(DUMMY_QUEUE_PATH);
            Scanner scanner = new Scanner(file);
            String dataCompare = "";

            while (scanner.hasNextLine()) {
                int fileHash = Integer.parseInt(scanner.nextLine());
                ArrayList<String> queueList = new ArrayList<>();
                while (scanner.hasNextLine()) {
                    String line = scanner.nextLine();
                    dataCompare += line + "\n";
                    queueList.add(line);
                }
                int fileHashCompare = dataCompare.hashCode();

                Assertions.assertEquals(queueList.size(), 1);
                Assertions.assertEquals(queueList.get(0), "1");
                Assertions.assertEquals(fileHash, fileHashCompare);
            }
            scanner.close();
        } catch (IOException e) {
            Assertions.assertFalse(false);
        }
    }
}
