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

    private static final String DUMMY_FILE_PATH_TEST = "./dummy-data/dummy-patient-data-2.txt";

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

        try {
            FileWriter writer = new FileWriter(DUMMY_FILE_PATH);
            for (Map.Entry<Integer, Patient> entry : InformationStub.getAllPatientData().entrySet()) {
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
                List<String> dates = Collections.list(medicineHistory.keys());
                Collections.sort(dates);
                for (String date : dates) {
                    writer.write(date + " ");
                    for (String medString : medicineHistory.get(date)) {
                        writer.write(medString + " ");
                    }
                }
                writer.write("\n");
            }
            writer.close();

            Path path = Paths.get(DUMMY_FILE_PATH);
            Path pathTest = Paths.get(DUMMY_FILE_PATH_TEST);

            Files.createFile(path);
            FileWriter writerTest = new FileWriter(DUMMY_FILE_PATH_TEST);
            writerTest.write(1 + "\n");
            writerTest.write("Jerry\n");
            writerTest.write(1 + "\n");
            writerTest.write("Fever\n");
            writerTest.write(1 + "\n");
            writerTest.write("2023/01/01 Paracetamol \n");

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

            while (scanner.hasNextLine()) {
                String password = scanner.nextLine();
                String name = scanner.nextLine();
                int hash = Integer.parseInt(password);

                int numberOfEntries = Integer.parseInt(scanner.nextLine());
                ArrayList<String> diagnosisHistory = new ArrayList<>();
                for (int i = 0; i < numberOfEntries; i++) {
                    String diagnosis = scanner.nextLine();
                    diagnosisHistory.add(diagnosis);
                }

                int numberOfMedicineEntries = Integer.parseInt(scanner.nextLine());
                Hashtable<String, ArrayList<String>> medicineHistory = new Hashtable();
                for (int entry = 0; entry < numberOfMedicineEntries; entry++) {
                    String dateMedicineString = scanner.nextLine();
                    String[] splitDateMedicineStrings = dateMedicineString.split(" ");
                    ArrayList<String> medicines = new ArrayList<>();
                    for (int medStringCount = 1; medStringCount < splitDateMedicineStrings.length; medStringCount++) {
                        medicines.add(splitDateMedicineStrings[medStringCount]);
                    }
                    medicineHistory.put(splitDateMedicineStrings[0], medicines);
                }

                Assertions.assertEquals(name, "Jerry");
                Assertions.assertEquals(hash, 1);
                Assertions.assertEquals(numberOfEntries, 1);
                Assertions.assertEquals(diagnosisHistory.size(), 1);
                Assertions.assertEquals(diagnosisHistory.get(0), "Fever");
                Assertions.assertEquals(numberOfMedicineEntries, 1);
                Assertions.assertEquals(medicineHistory.size(), 1);
                Assertions.assertTrue(medicineHistory.containsKey("2023/01/01"));
                Assertions.assertEquals(medicineHistory.get("2023/01/01").size(), 1);
                Assertions.assertTrue(medicineHistory.get("2023/01/01").get(0).equals("Paracetamol"));
            }
            scanner.close();
        } catch (IOException e) {
            Assertions.assertFalse(false);
        }
    }
}
