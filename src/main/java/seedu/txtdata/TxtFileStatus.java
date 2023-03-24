package seedu.txtdata;

import seedu.expenditure.Expenditure;
import seedu.expenditure.AcademicExpenditure;
import seedu.expenditure.AccommodationExpenditure;
import seedu.expenditure.BorrowExpenditure;
import seedu.expenditure.EntertainmentExpenditure;
import seedu.expenditure.FoodExpenditure;
import seedu.expenditure.LendExpenditure;
import seedu.expenditure.OtherExpenditure;
import seedu.expenditure.TransportExpenditure;
import seedu.expenditure.TuitionExpenditure;
import seedu.expenditure.ExpenditureList;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public abstract class TxtFileStatus {
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
        for (Expenditure expenditure : expenditures) {
            appendToFile(filePath, expenditure.saveInfo());
        }
    }

    // initialize saved list, undone
    public static void initializeExpenditureList(ExpenditureList expenditures) throws FileNotFoundException {
        File f = new File(filePath); // create a File for the given file path
        Scanner s = new Scanner(f); // create a Scanner using the File as the source
        while (s.hasNext()) {
            String saveString = s.nextLine();
            String[] saveData = saveString.split("d/|v/|t/|p/|n/|o/");
            switch (saveData[0]) {
            case "Acad":
                AcademicExpenditure academicExpenditure = new AcademicExpenditure(
                        saveData[1],
                        Double.parseDouble(saveData[2]),
                        LocalDate.parse(saveData[3]));
                expenditures.addExpenditure(academicExpenditure);
                break;
            case "Accom":
                AccommodationExpenditure accommodationExpenditure = new AccommodationExpenditure(
                        saveData[1],
                        Double.parseDouble(saveData[2]),
                        LocalDate.parse(saveData[3]));
                if (saveData[4].equals(AccommodationExpenditure.iconPaid)) {
                    accommodationExpenditure.setPaid();
                }
                expenditures.addExpenditure(accommodationExpenditure);
                break;
            case "B":
                BorrowExpenditure borrowExpenditure = new BorrowExpenditure(
                        saveData[1],
                        saveData[5],
                        Double.parseDouble(saveData[2]),
                        LocalDate.parse(saveData[3]),
                        LocalDate.parse(saveData[6]));
                expenditures.addExpenditure(borrowExpenditure);
                break;
            case "En":
                EntertainmentExpenditure entertainmentExpenditure = new EntertainmentExpenditure(
                        saveData[1],
                        Double.parseDouble(saveData[2]),
                        LocalDate.parse(saveData[3]));
                expenditures.addExpenditure(entertainmentExpenditure);
                break;
            case "F":
                FoodExpenditure foodExpenditure = new FoodExpenditure(
                        saveData[1],
                        Double.parseDouble(saveData[2]),
                        LocalDate.parse(saveData[3]));
                expenditures.addExpenditure(foodExpenditure);
                break;
            case "L":
                LendExpenditure lendExpenditure = new LendExpenditure(
                        saveData[1],
                        saveData[5],
                        Double.parseDouble(saveData[2]),
                        LocalDate.parse(saveData[3]),
                        LocalDate.parse(saveData[6]));
                expenditures.addExpenditure(lendExpenditure);
                break;
            case "O":
                OtherExpenditure otherExpenditure = new OtherExpenditure(
                        saveData[1],
                        Double.parseDouble(saveData[2]),
                        LocalDate.parse(saveData[3]));
                expenditures.addExpenditure(otherExpenditure);
                break;
            case "Tr":
                TransportExpenditure transportExpenditure = new TransportExpenditure(
                        saveData[1],
                        Double.parseDouble(saveData[2]),
                        LocalDate.parse(saveData[3]));
                expenditures.addExpenditure(transportExpenditure);
                break;
            case "Tu":
                TuitionExpenditure tuitionExpenditure = new TuitionExpenditure(
                        saveData[1],
                        Double.parseDouble(saveData[2]),
                        LocalDate.parse(saveData[3]));
                if (saveData[4].equals(TuitionExpenditure.iconPaid)) {
                    tuitionExpenditure.setPaid();
                }
                expenditures.addExpenditure(tuitionExpenditure);
                break;
            default:
                break;
            }
        }
        s.close();
    }

}
