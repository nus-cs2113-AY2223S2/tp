package seedu.badMaths;

import java.io.File;
import java.io.IOException;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.BufferedReader;


//import java.nio.file.Files;
//import java.nio.file.Paths;
import java.util.logging.LogManager;
import java.util.logging.Logger;
import java.util.logging.Level;


public class HelpManual {
    protected static String filePath = "src/main/resources/HelpManual.txt";
    protected static String logFilePath = "Help";
    private static final Logger logger = Logger.getLogger(logFilePath);

    public static void setUpLogger() {
        LogManager.getLogManager().reset();
        logger.setLevel(Level.ALL);
        try {
            if (!new File(logFilePath).exists()) {
                new File(logFilePath).createNewFile();
            }
        } catch (IOException e) {
            logger.log(Level.SEVERE, "File logger not working", e);
        }
    }

    /**
     * This method reads the content from docs/HelpManual.txt file.
     */
    public static void readHelpManual() {
        setUpLogger();
        try {
            logger.log(Level.INFO, "going to start processing");
            File file = new File(filePath);

            // assert that the file exists and is a regular file
            assert file.exists() && file.isFile() : "Invalid file: " + filePath;

            if (file.isFile() && file.exists()) {
                logger.log(Level.INFO, "Help manual file found at: " + file.getAbsolutePath());
                InputStreamReader read = new InputStreamReader(new FileInputStream(file), "UTF-8");
                BufferedReader bufferedReader = new BufferedReader(read);

                String lineTxt;
                StringBuilder content = new StringBuilder();

                while ((lineTxt = bufferedReader.readLine()) != null) {
                    content.append(lineTxt).append("\n");
                    System.out.println(lineTxt);
                }
                bufferedReader.close();
                read.close();
                
                // Log successful read to console and log file
                logger.log(Level.INFO, "Successfully read Help Manual file.");

            } else {
                System.out.println("Cannot find Help Manual. Please try again.");
                logger.log(Level.WARNING, "Cannot find Help Manual. Please try again.");
            }
        } catch (Exception e) {
            System.out.println("Error while loading files. Please try again.");
            logger.severe("Error while loading Help Manual. Please try again.");
            e.printStackTrace();
        }
    }
}
