package seedu.myledger;

import seedu.expenditure.ExpenditureList;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class MyLedger {
    private ExpenditureList expenditures;

    private void start() {
        expenditures = new ExpenditureList();
    }

    private void run() {

    }

    public static void main(String[] args) {

        try {
            fileAvailability();
        } catch (FileNotFoundException e) {
            System.out.println("File does not exist.");
        }
    }

    public static void runMyLedger() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("What is your name?");
        Scanner in = new Scanner(System.in);
        System.out.println("Hello " + in.nextLine());
    }

    public static void fileAvailability() throws FileNotFoundException {
        File txtFile = createFile();

        if (txtFile.exists()) {
            // Runs the program
            runMyLedger();
        }
    }

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
            System.out.println("File already exists!");
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

}
