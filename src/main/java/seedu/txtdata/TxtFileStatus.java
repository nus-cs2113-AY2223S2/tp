package seedu.txtdata;

import seedu.exceptions.TxtFileException;
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
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;

public abstract class TxtFileStatus {
    private static final String directoryPath = "myLedger_data";
    private static final String filePath = "myLedger_data/myLedger_inputs.txt";
    private static final int INDEX_TYPE = 0;
    private static final int INDEX_DESCRIPTION = 1;
    private static final int INDEX_VALUE = 2;
    private static final int INDEX_DATE = 3;
    private static final int INDEX_IS_PAID = 4;
    private static final int INDEX_NAME = 5;
    private static final int INDEX_DEADLINE = 6;
    private static final int INDEX_REPEAT_DATE = 7;

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

    /**
     * Initializes expenditureList using text file.
     * If text file has been corrupted such that it can't be read,
     * ignores that save line is ignored.
     * @param expenditures
     * @throws FileNotFoundException
     */
    public static void initializeExpenditureList(ExpenditureList expenditures) throws FileNotFoundException {
        File f = new File(filePath); // create a File for the given file path
        Scanner s = new Scanner(f); // create a Scanner using the File as the source
        parseTxtFile(s, expenditures);
    }

    /**
     * @author Chick3nBoy
     */
    public static void parseTxtFile(Scanner s, ExpenditureList expenditures) {
        while (s.hasNext()) {
            try {
                String saveString = s.nextLine();
                String[] saveData = saveString.split("d/|v/|t/|p/|n/|o/|r/");
                checkNegative(saveData[INDEX_VALUE]);
                switch (saveData[INDEX_TYPE]) {
                case "Acad":
                    initializeAcademicExpenditure(saveData, expenditures);
                    break;
                case "Accom":
                    initializeAccommodationExpenditure(saveData, expenditures);
                    break;
                case "B":
                    initializeBorrowExpenditure(saveData, expenditures);
                    break;
                case "En":
                    initializeEntertainmentExpenditure(saveData, expenditures);
                    break;
                case "F":
                    initializeFoodExpenditure(saveData, expenditures);
                    break;
                case "L":
                    initializeLendExpenditure(saveData, expenditures);
                    break;
                case "O":
                    initializeOtherExpenditure(saveData, expenditures);
                    break;
                case "Tr":
                    initializeTransportExpenditure(saveData, expenditures);
                    break;
                case "Tu":
                    initializeTuitionExpenditure(saveData, expenditures);
                    break;
                default:
                    throw new TxtFileException();
                }
            } catch (TxtFileException t) {
                System.out.println(t.getMessage());
            } catch (DateTimeParseException | NumberFormatException | ArrayIndexOutOfBoundsException e) {
                System.out.println(
                        "TxtFile has been corrupted, the corrupted entry has been deleted");
            }
        }
        s.close();
    }

    /**
     * @author Chick3nBoy
     */
    public static void checkNegative(String stringDouble) throws TxtFileException {
        if (Double.parseDouble(stringDouble) <= 0.01) {
            throw new TxtFileException();
        }
    }

    /**
     * @author Leo Zheng Rui Darren
     */
    public static void checkRepeatDate(String stringRepeatDate, String stringFirstDate) throws TxtFileException {
        LocalDate repeatDate = LocalDate.parse(stringRepeatDate);
        LocalDate firstDate = LocalDate.parse(stringFirstDate);
        compareMonth(repeatDate, firstDate);
        compareDay(repeatDate, firstDate);
    }

    public static void compareMonth(LocalDate repeatDate, LocalDate firstDate) throws TxtFileException {
        int repeatMonth = repeatDate.getMonthValue();
        int firstMonth = firstDate.getMonthValue();
        boolean isSameMonth = (repeatMonth == firstMonth);
        if (!isSameMonth) {
            throw new TxtFileException();
        }
    }

    public static void compareDay(LocalDate repeatDate, LocalDate firstDate) throws TxtFileException {
        int repeatDay = repeatDate.getDayOfMonth();
        int firstDay = firstDate.getDayOfMonth();
        boolean isSameDay = (repeatDay == firstDay);
        if (!isSameDay) {
            throw new TxtFileException();
        }
    }

    public static void initializeAcademicExpenditure(String[] saveData, ExpenditureList expenditures) {
        AcademicExpenditure academicExpenditure = new AcademicExpenditure(
                saveData[INDEX_DESCRIPTION],
                Double.parseDouble(saveData[INDEX_VALUE]),
                LocalDate.parse(saveData[INDEX_DATE]));
        expenditures.addExpenditure(academicExpenditure);
    }

    public static void initializeAccommodationExpenditure(String[] saveData, ExpenditureList expenditures)
            throws TxtFileException {
        checkRepeatDate(saveData[INDEX_REPEAT_DATE], saveData[INDEX_DATE]);
        AccommodationExpenditure accommodationExpenditure = new AccommodationExpenditure(
                saveData[INDEX_DESCRIPTION],
                Double.parseDouble(saveData[INDEX_VALUE]),
                LocalDate.parse(saveData[INDEX_DATE]),
                LocalDate.parse(saveData[INDEX_REPEAT_DATE]));
        if (saveData[INDEX_IS_PAID].equals(AccommodationExpenditure.iconPaid)) {
            accommodationExpenditure.setPaid();
        } else if (saveData[INDEX_IS_PAID].equals(AccommodationExpenditure.iconUnpaid)) {
            accommodationExpenditure.resetPaid();
        } else {
            throw new TxtFileException();
        }
        expenditures.addExpenditure(accommodationExpenditure);
    }

    public static void initializeBorrowExpenditure(String[] saveData, ExpenditureList expenditures) {
        BorrowExpenditure borrowExpenditure = new BorrowExpenditure(
                saveData[INDEX_DESCRIPTION],
                saveData[INDEX_NAME],
                Double.parseDouble(saveData[INDEX_VALUE]),
                LocalDate.parse(saveData[INDEX_DATE]),
                LocalDate.parse(saveData[INDEX_DEADLINE]));
        expenditures.addExpenditure(borrowExpenditure);
    }

    public static void initializeEntertainmentExpenditure(String[] saveData, ExpenditureList expenditures) {
        EntertainmentExpenditure entertainmentExpenditure = new EntertainmentExpenditure(
                saveData[INDEX_DESCRIPTION],
                Double.parseDouble(saveData[INDEX_VALUE]),
                LocalDate.parse(saveData[INDEX_DATE]));
        expenditures.addExpenditure(entertainmentExpenditure);
    }

    public static void initializeFoodExpenditure(String[] saveData, ExpenditureList expenditures) {
        FoodExpenditure foodExpenditure = new FoodExpenditure(
                saveData[INDEX_DESCRIPTION],
                Double.parseDouble(saveData[INDEX_VALUE]),
                LocalDate.parse(saveData[INDEX_DATE]));
        expenditures.addExpenditure(foodExpenditure);
    }

    public static void initializeLendExpenditure(String[] saveData, ExpenditureList expenditures) {
        LendExpenditure lendExpenditure = new LendExpenditure(
                saveData[INDEX_DESCRIPTION],
                saveData[INDEX_NAME],
                Double.parseDouble(saveData[INDEX_VALUE]),
                LocalDate.parse(saveData[INDEX_DATE]),
                LocalDate.parse(saveData[INDEX_DEADLINE]));
        expenditures.addExpenditure(lendExpenditure);
    }

    public static void initializeOtherExpenditure(String[] saveData, ExpenditureList expenditures) {
        OtherExpenditure otherExpenditure = new OtherExpenditure(
                saveData[INDEX_DESCRIPTION],
                Double.parseDouble(saveData[INDEX_VALUE]),
                LocalDate.parse(saveData[INDEX_DATE]));
        expenditures.addExpenditure(otherExpenditure);
    }

    public static void initializeTransportExpenditure(String[] saveData, ExpenditureList expenditures) {
        TransportExpenditure transportExpenditure = new TransportExpenditure(
                saveData[INDEX_DESCRIPTION],
                Double.parseDouble(saveData[INDEX_VALUE]),
                LocalDate.parse(saveData[INDEX_DATE]));
        expenditures.addExpenditure(transportExpenditure);
    }

    public static void initializeTuitionExpenditure(String[] saveData, ExpenditureList expenditures)
            throws TxtFileException {
        checkRepeatDate(saveData[INDEX_REPEAT_DATE], saveData[INDEX_DATE]);
        TuitionExpenditure tuitionExpenditure = new TuitionExpenditure(
                saveData[INDEX_DESCRIPTION],
                Double.parseDouble(saveData[INDEX_VALUE]),
                LocalDate.parse(saveData[INDEX_DATE]),
                LocalDate.parse(saveData[INDEX_REPEAT_DATE]));
        if (saveData[INDEX_IS_PAID].equals(TuitionExpenditure.iconPaid)) {
            tuitionExpenditure.setPaid();
        } else if (saveData[INDEX_IS_PAID].equals(TuitionExpenditure.iconUnpaid)) {
            tuitionExpenditure.resetPaid();
        } else {
            throw new TxtFileException();
        }
        expenditures.addExpenditure(tuitionExpenditure);
    }
}
