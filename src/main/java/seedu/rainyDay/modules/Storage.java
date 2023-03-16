package seedu.rainyDay.modules;

import seedu.rainyDay.data.FinancialReport;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

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
