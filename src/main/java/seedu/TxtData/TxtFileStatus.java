package seedu.TxtData;

import seedu.MyLedger.MyLedger;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class TxtFileStatus {
    public static File createFile() {
        Path path = Paths.get(System.getProperty("user.home"), "my_ledger_data");
        checkIfFolderExists(path);
        Path textFilePath = checkIfFileExists();
        File textFile = textFilePath.toFile();
        return textFile;
    }

    public static Path checkIfFileExists() {
        Path filePath = Paths.get(System.getProperty("user.home"), "my_ledger_data", "my_ledger_inputs.txt");
        try {
            Files.createFile(filePath);
        } catch(IOException e) {
            System.out.println("Existing File Found!");
        }
        return filePath;
    }

    public static void checkIfFolderExists(Path path) {
        boolean directoryExists = Files.exists(path);
        if (!directoryExists) {
            try {
                Files.createDirectory(path);
            } catch (IOException e) {
                System.out.println("Folder cannot be created!");
            }
        }
    }

    public static void fileAvailability() throws FileNotFoundException {
        File txtFile = createFile();

        if (txtFile.exists()) {
            // Runs the program
            MyLedger.runMyLedger();
        }
    }
}
