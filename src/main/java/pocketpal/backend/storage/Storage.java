// @@author nghochi123
package pocketpal.backend.storage;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.time.LocalDateTime;

import pocketpal.backend.constants.Config;
import pocketpal.backend.constants.MiscellaneousConstants;
import pocketpal.data.entry.Category;
import pocketpal.data.entry.Entry;
import pocketpal.frontend.util.CategoryUtil;
import pocketpal.frontend.util.DateTimeUtil;
import pocketpal.frontend.exceptions.InvalidCategoryException;
import pocketpal.frontend.exceptions.InvalidDateException;
import pocketpal.backend.exceptions.InvalidReadFileException;

public class Storage {
    private static final DecimalFormat decimalFormat = new DecimalFormat("0.00");
    private static final Logger logger = Logger.getLogger(Storage.class.getName());
    private final String filePath;
    private final String delimiter;

    public Storage() {
        this(Config.RELATIVE_FILE_NAME);
    }

    /**
     * Alternative constructor for Storage that allows the changing of filePath,
     * used for Testing purposes only
     *
     * @param filePath Path to file that serialized entries will be stored in
     */
    public Storage(String filePath) {
        this.filePath = filePath;
        this.delimiter = Config.DELIMITER;
    }

    private double convertStringToDouble(String stringToBeConverted) throws ParseException {
        logger.entering(Storage.class.getName(), "convertStringToDouble()");
        logger.info("Converting the string " + stringToBeConverted + " to double");
        double parsedDoubleValue = decimalFormat.parse(
            String.format(
                "%.2f", 
                Double.parseDouble(stringToBeConverted)
            )
        ).doubleValue();
        logger.exiting(Storage.class.getName(), "convertStringToDouble()");
        return parsedDoubleValue;
    }

    /**
     * Creates a file and its respective parent directories if the file does
     * not exist, using a predefined file path.
     *
     * @throws IOException If an error occurs in the creation of the new file
     */
    private void makeFileIfNotExists() throws IOException {
        logger.entering(Storage.class.getName(), "makeFileIfNotExists()");
        logger.info("Ensuring the storage file exists");
        File file = new File(this.filePath);
        file.getParentFile().mkdirs(); // Create parent directories in file path
        file.createNewFile(); // Create the actual file
        logger.exiting(Storage.class.getName(), "makeFileIfNotExists()");
        new FileOutputStream(
            file, 
            true
        ).close();
    }

    /**
     * Deserializes an entry line from a stored text file and returns an
     * Entry instance based on the line.
     *
     * @param line String of text to be converted to an Entry instance
     * @return An Entry instance that represents the read line
     */
    private Entry readEntryLine(String line) throws InvalidReadFileException {
        logger.entering(Storage.class.getName(), "readEntryLine()");
        logger.info("Reading entry line: " + line);
        try {
            line = line.trim();
            assert !line.isEmpty() : MiscellaneousConstants.NO_BLANK_STRING_ALLOWED;
            String[] lineArray = line.split(this.delimiter);
            String description = lineArray[0];
            String amountString = lineArray[1];
            String categoryString = lineArray[2];
            String dateTimeString = lineArray[3];
            double amount = convertStringToDouble(amountString);
            Category category = CategoryUtil.convertStringToCategory(
                categoryString
            );
            LocalDateTime dateTime = DateTimeUtil.convertStringToLocalDateTime(
                dateTimeString
            );
            return new Entry(
                description, 
                amount, 
                category, 
                dateTime
            );
        } catch (ArrayIndexOutOfBoundsException e) { // Gets thrown when there is not enough
            // strings separated by the delimiter
            logger.log(Level.WARNING, "Error reading line, check delimiter or number of arguments provided");
            throw new InvalidReadFileException(
                String.format(
                    "%s%s",
                    MiscellaneousConstants.GENERAL_STORAGE_ERROR_MESSAGE,
                    line
                )
            );
        } catch (NumberFormatException | ParseException e) { // Gets thrown if there is an
            // error with parsing the amount
            logger.log(Level.WARNING, "Error reading line, check amount provided");
            throw new InvalidReadFileException(
                String.format(
                    "%s%s", 
                    MiscellaneousConstants.INVALID_AMOUNT_ERROR_MESSAGE,
                    line
                )
            );
        } catch (InvalidCategoryException e) { // Gets thrown when the category from the 
            // storage file is invalid (not part of the valid category list)
            logger.log(Level.WARNING, "Error reading line, check category provided");
            throw new InvalidReadFileException(
                String.format(
                    "%s%s",
                    MiscellaneousConstants.INVALID_CATEGORY_ERROR_MESSAGE,
                    line
                )
            );
        } catch (InvalidDateException e) { // Gets thrown when the date is not provided in
            // the correct format DD MMM YYYY; HH:MM (3 Apr 2023; 21:32)
            logger.log(Level.WARNING, "Error reading line, check date provided");
            throw new InvalidReadFileException(
                String.format(
                    "%s%s", 
                    MiscellaneousConstants.INVALID_DATE_ERROR_MESSAGE,
                    line
                )
            );
        } finally {
            logger.exiting(Storage.class.getName(), "readEntryLine()");
        }
    }


    /**
     * Deserializes all the entries in a stored text file into a List of Entry
     * instances.
     *
     * @return An Entry[] List that represents all the entries that have been read from the stored text file
     * @throws IOException If an error occurs in the reading from the file
     */
    public List<Entry> readFromDatabase() throws IOException, InvalidReadFileException {
        logger.entering(Storage.class.getName(), "readFromDatabase()");
        logger.info("Reading database file");
        List<Entry> entries = new ArrayList<>();
        makeFileIfNotExists(); // Ensure the file exists before reading, create if needed
        BufferedReader csvReader = new BufferedReader(
                new FileReader(this.filePath)
        );
        try {
            String row;
            while ((row = csvReader.readLine()) != null) { // While there are unread lines,
                // process lines and create entries
                entries.add(
                    readEntryLine(row)
                );
            }
        } finally {
            csvReader.close();
        }
        logger.exiting(Storage.class.getName(), "readFromDatabase()");
        return entries;
    }

    /**
     * Resets the stored text file by deleting it and recreating it again.
     *
     * @throws IOException If an error occurs in the deletion or creation of the
     *                     file
     */
    public void reset() throws IOException {
        logger.entering(Storage.class.getName(), "reset()");
        logger.info("Resetting database file");
        File toBeDeleted = new File(this.filePath);
        toBeDeleted.delete(); // Delete the existing storage file
        makeFileIfNotExists(); // Replace the storage file with a new storage file
        logger.exiting(Storage.class.getName(), "reset()");
    }

    /**
     * Serializes an Entry instance into a String that can be stored into
     * a stored text file.
     *
     * @param entry Entry instance to be converted into a String
     * @return A String that represents the Entry instance
     */
    private String writeEntryLine(Entry entry) {
        logger.entering(Storage.class.getName(), "writeEntryLine()");
        logger.info("Writing entry line");
        decimalFormat.setRoundingMode(RoundingMode.DOWN);
        String description = entry.getDescription();
        String amountString = decimalFormat.format(
            entry.getAmount()
        );
        String categoryString = entry.getCategoryString();
        String dateTimeString = entry.getDateTimeString();
        String returnString = String.join(
                this.delimiter,
                description,
                amountString,
                categoryString,
                dateTimeString
        );
        returnString += System.lineSeparator();
        logger.exiting(Storage.class.getName(), "writeEntryLine()");
        return returnString;
    }

    /**
     * Serializes all the entries in a Entry[] List and writes the result
     * into a stored text file.
     *
     * @param entries An Entry[] List that is to be serialized and written into
     *                the stored text file
     * @throws IOException If an error occurs in the writing to the file
     */
    public void writeToDatabase(List<Entry> entries) throws IOException {
        logger.entering(Storage.class.getName(), "writeToDatabase()");
        logger.info("Writing to database file");
        makeFileIfNotExists(); // Ensure the file exists before writing, create if needed
        FileWriter csvWriter = new FileWriter(this.filePath);
        for (Entry entry : entries) {
            String csvRow = "";
            csvRow += writeEntryLine(entry);
            csvWriter.append(csvRow);
        }
        csvWriter.flush();
        csvWriter.close();
        logger.exiting(Storage.class.getName(), "writeToDatabase()");
    }
}
// @@author
