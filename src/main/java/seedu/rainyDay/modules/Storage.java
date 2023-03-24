package seedu.rainyDay.modules;

import seedu.rainyDay.data.FinancialReport;
import seedu.rainyDay.data.FinancialStatement;
import seedu.rainyDay.data.FlowDirection;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

import com.opencsv.CSVWriter;


public class Storage {
    private static Logger logger = Logger.getLogger("Storage.log");

    //@@author ChongQiRong
    /**
     * Uses serialization to save the FinancialReport object into a file.
     *
     * @param report The object containing the FinancialReport to save.
     * @param filePath The file path where the FinancialReport will be saved to.
     */
    public static void writeToFile(FinancialReport report, String filePath) {
        setupLogger();
        try {
            Files.createDirectories(Paths.get("./data"));
            FileOutputStream writeData = new FileOutputStream(filePath);
            ObjectOutputStream writeStream = new ObjectOutputStream(writeData);

            writeStream.writeObject(report);

            writeStream.flush();
            writeStream.close();
            logger.log(Level.INFO, "Data successfully written and writeStream successfully flushed and closed");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //@@author KN-CY
    /**
     * Uses deserialization to load the FinancialReport from a serialized file.
     *
     * @param filePath The file path where the FinancialReport will be loaded from.
     * @return The FinancialReport after deserialization from the file.
     * @throws IOException If there is an error loading the TaskList object from the file.
     * @throws ClassNotFoundException If there is an error loading the TaskList object from the file.
     * @throws ClassCastException If the serialized object is of a different class type.
     */
    public static FinancialReport loadFromFile(String filePath)
            throws IOException, ClassNotFoundException, ClassCastException {
        FileInputStream readData = new FileInputStream(filePath);
        ObjectInputStream readStream = new ObjectInputStream(readData);

        FinancialReport statements = (FinancialReport) readStream.readObject();

        readStream.close();
        readData.close();

        return statements;
    }

    //@@author KN-CY
    /**
     * Fills up the CSVWriter with the contents of the financial statements within the FinancialReport.
     *
     * @param report The FinancialReport which contains the financial statements to be saved into CSV.
     */
    private static void fillTableBody(FinancialReport report, CSVWriter tableWriter) {
        for (int i = 0; i < report.getStatementCount(); i++) {
            FinancialStatement currStatement = report.getFinancialStatement(i);

            String statementID = Integer.toString(i + 1);
            String description = currStatement.getDescription();
            String value;
            if (currStatement.getFlowDirection() == FlowDirection.INFLOW) {
                value = Double.toString(currStatement.getValue());
            } else {
                value = Double.toString(-currStatement.getValue());
            }
            String category = currStatement.getCategory();
            String date = currStatement.getDate().toString();

            String[] tableRow = {statementID, description, value, category, date};
            tableWriter.writeNext(tableRow);
        }
    }

    //@@author KN-CY
    /**
     * Writes to a file in .csv format.
     *
     * @param report The FinancialReport which contains the financial statements to be saved into a .csv file.
     * @throws IOException When there is an error writing to the .csv file.
     */
    public static void writeToCSV(FinancialReport report) throws IOException {
        Files.createDirectories(Paths.get("./data"));
        String CSVFilePath = "./data/report.csv";

        FileWriter outputFile = new FileWriter(CSVFilePath);
        CSVWriter tableWriter = new CSVWriter(outputFile);

        String[] tableHeader = {"ID", "Description", "Amount", "Category", "Date"};
        tableWriter.writeNext(tableHeader);
        fillTableBody(report, tableWriter);

        tableWriter.close();
    }
    
    /**
     * Sets up logger for logging
     */
    private static void setupLogger() {
        LogManager.getLogManager().reset();
        logger.setLevel(Level.INFO);
        try {
            FileHandler fileHandler = new FileHandler("Storage.log");
            logger.addHandler(fileHandler);
        } catch (Exception e) {
            logger.log(Level.SEVERE, "File logger not working.", e);
        }
    }
}
