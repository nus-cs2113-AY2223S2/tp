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
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

import com.opencsv.CSVWriter;


public class Storage {
    private static Logger logger = Logger.getLogger("Storage.log");


    public static void writeToFile(FinancialReport statements, String filePath) {
        setupLogger();
        try {
            FileOutputStream writeData = new FileOutputStream(filePath);
            ObjectOutputStream writeStream = new ObjectOutputStream(writeData);

            writeStream.writeObject(statements);

            writeStream.flush();
            writeStream.close();
            logger.log(Level.INFO, "Data successfully written and writeStream successfully flushed and closed");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static FinancialReport loadFromFile(String filePath)
            throws IOException, ClassNotFoundException, ClassCastException {
        FileInputStream readData = new FileInputStream(filePath);
        ObjectInputStream readStream = new ObjectInputStream(readData);

        FinancialReport statements = (FinancialReport) readStream.readObject();

        readStream.close();
        readData.close();

        return statements;
    }

    public static void writeToCSV(FinancialReport report) {
        String CSVFilePath = "report.csv";

        try {
            FileWriter outputFile = new FileWriter(CSVFilePath);
            CSVWriter tableWriter = new CSVWriter(outputFile);
            String[] tableHeader = {"ID", "Description", "Value", "Category"};
            tableWriter.writeNext(tableHeader);

            for (int i = 0; i < report.getStatementCount(); i++) {
                FinancialStatement currStatement = report.getFinancialStatement(i);

                String statementID = Integer.toString(i + 1);
                String description = currStatement.getDescription();
                String value;
                if (currStatement.getFlowDirection() == FlowDirection.INFLOW) {
                    value = Integer.toString(currStatement.getValue());
                } else {
                    value = Integer.toString(-currStatement.getValue());
                }
                String category = currStatement.getCategory();

                String[] tableRow = {statementID, description, value, category};
                tableWriter.writeNext(tableRow);
            }
            tableWriter.close();

        } catch (IOException e) {
            Ui.csvExportError();
        }
    }

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
