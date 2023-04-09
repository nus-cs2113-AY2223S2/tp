/**
 * Provides the HelpManual to guide users who are operating BadMaths.
 *
 */

package seedu.badmaths;

import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.InputStream;
import java.io.BufferedReader;

import java.util.logging.LogManager;
import java.util.logging.Logger;
import java.util.logging.Level;


public class HelpManual {
    protected static String filePath = "/HelpManual.txt"; // file path relative to the classpath
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
     * This method reads the content from HelpManual.txt file.
     */
    public static void readHelpManual() {
        setUpLogger();
        try {
            logger.log(Level.INFO, "going to start processing");
            InputStream inputStream = HelpManual.class.getResourceAsStream(filePath);

            // assert that the input stream is not null
            assert inputStream != null : "Invalid file: " + filePath;

            InputStreamReader read = new InputStreamReader(inputStream, "UTF-8");
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

        } catch (Exception e) {
            System.out.println("Error while loading files. Please try again.");
            logger.severe("Error while loading Help Manual. Please try again.");
            e.printStackTrace();
        }
    }
}
