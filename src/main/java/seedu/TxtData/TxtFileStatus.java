package seedu.TxtData;

import seedu.Expenditure.AcademicExpenditure;
import seedu.Expenditure.Expenditure;
import seedu.Expenditure.FoodExpenditure;
import seedu.Expenditure.ExpenditureList;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class TxtFileStatus {
    private static final String directoryPath = "myLedger_data";
    private static final String filePath = "myLedger_data/myLedger_inputs.txt";


    public static void getSaveFile() throws IOException {
        File directory = new File(directoryPath);
        if (!directory.exists()) {
            directory.mkdir();
        }
        File saveFile = new File(filePath);
        if (!saveFile.exists()) {
            saveFile.createNewFile();
        }
    }

    public static void checkFile() throws FileNotFoundException {
        try {
            getSaveFile();
        } catch (IOException e) {
            System.out.println("Save File Error");
        }
    }

    public static void appendToFile(String filePath, String textToAppend) throws IOException {
        FileWriter fw = new FileWriter(filePath, true); // create a FileWriter in append mode
        fw.write(textToAppend);
        fw.close();
    }

    public static void writeToFile(String filePath, String textToAdd) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        fw.write(textToAdd);
        fw.close();
    }

    public static void saveExpenditureList(ArrayList<Expenditure> expenditures) throws IOException {
        writeToFile(filePath, "");
        for (int i = 0; i < expenditures.size(); i += 1) {
            Expenditure expenditure = expenditures.get(i);
            appendToFile(filePath, expenditure.saveInfo());
        }
    }

    //initialize saved list, undone
    public static void initializeExpenditureList(ExpenditureList expenditures) throws FileNotFoundException {
        File f = new File(filePath); // create a File for the given file path
        Scanner s = new Scanner(f); // create a Scanner using the File as the source
        while (s.hasNext()) {
            String saveString = s.nextLine();
            String[] saveData = saveString.split("d/|v/|t/");
            switch (saveData[0]) {
            case "Acad":
                AcademicExpenditure academicExpenditure = new AcademicExpenditure(
                        saveData[1],
                        Double.parseDouble(saveData[2]),
                        LocalDate.parse(saveData[3]));
                expenditures.addExpenditure(academicExpenditure);
                break;
            case "Accom":
                break;
            case "B":
                break;
            case "En":
                break;
            case "F":
                FoodExpenditure foodExpenditure = new FoodExpenditure(
                        saveData[1],
                        Double.parseDouble(saveData[2]),
                        LocalDate.parse(saveData[3]));
                expenditures.addExpenditure(foodExpenditure);
                break;
            case "L":
                break;
            case "O":
                break;
            case "Tr":
                break;
            case "Tu":
                break;
            default:
                break;
            }
        }
    }
}

