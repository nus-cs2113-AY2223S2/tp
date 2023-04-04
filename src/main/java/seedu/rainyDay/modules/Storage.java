package seedu.rainyDay.modules;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonParser;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import org.apache.commons.lang3.EnumUtils;
import seedu.rainyDay.command.ShortcutCommand;
import seedu.rainyDay.data.FinancialReport;
import seedu.rainyDay.data.FinancialStatement;
import seedu.rainyDay.data.FlowDirection;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;

import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

import com.opencsv.CSVWriter;
import seedu.rainyDay.data.UserData;
import seedu.rainyDay.exceptions.RainyDayException;

//@@author KN-CY
public class Storage {
    private static Logger logger = Logger.getLogger(Storage.class.getName());
    private static Gson gson =
            new GsonBuilder().setPrettyPrinting().registerTypeAdapter(LocalDate.class, new LocalDateAdapter()).create();

    /**
     * Custom adaptor for serializing and deserializing LocalDate objects with Gson
     */
    private static class LocalDateAdapter implements JsonSerializer<LocalDate>, JsonDeserializer<LocalDate> {
        private final DateTimeFormatter dateFormat = DateTimeFormatter.ISO_LOCAL_DATE;

        @Override
        public JsonElement serialize(LocalDate date, Type typeOfSrc, JsonSerializationContext context) {
            return new JsonPrimitive(date.format(dateFormat));
        }

        @Override
        public LocalDate deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
                throws JsonParseException {
            return LocalDate.parse(json.getAsString(), dateFormat);
        }
    }

    /**
     * Uses deserialization to load the FinancialReport from a serialized file.
     *
     * @param filePath The file path where the FinancialReport will be loaded from.
     * @return The FinancialReport after deserialization from the file.
     * @throws FileNotFoundException If the file specified by the filePath is not found.
     */
    public static UserData loadFromFile(String filePath) throws FileNotFoundException, RainyDayException {
        setupLogger();
        logger.log(Level.INFO, "starting LoadFromFile");
        Reader reader = new FileReader(filePath);

        JsonElement fileElement = JsonParser.parseReader(reader);
        JsonObject fileObject = fileElement.getAsJsonObject();

        checkValidUserData(fileObject);

        reader = new FileReader(filePath);
        UserData userData = gson.fromJson(reader, UserData.class);
        return userData;
    }

    /**
     * Checks if the loaded userData is valid.
     *
     * @param userData The user data being loaded of JsonObject type.
     * @throws RainyDayException If userData is invalid.
     */
    private static void checkValidUserData(JsonObject userData) throws RainyDayException {
        // check that UserData has the required fields
        if (!userData.has("financialReport")) {
            throw new RainyDayException("");
        }
        if (!userData.has("shortcutCommands")) {
            throw new RainyDayException("");
        }
        if (!userData.has("budgetGoal")) {
            throw new RainyDayException("");
        }
        JsonObject financialReport = userData.getAsJsonObject("financialReport");
        checkValidFinancialReport(financialReport);

        JsonObject shortcutCommands = userData.getAsJsonObject("shortcutCommands");
        checkValidShortcutCommands(shortcutCommands);
    }


    /**
     * Checks if the financialReport in the UserData is valid.
     *
     * @param financialReport The financialReport being loaded of JsonObject type.
     * @throws RainyDayException If financialReport is invalid.
     */
    private static void checkValidFinancialReport(JsonObject financialReport) throws RainyDayException {
        if (!financialReport.has("financialStatements")) {
            throw new RainyDayException("");
        }
        if (!financialReport.has("reportOwner")) {
            throw new RainyDayException("");
        }

        JsonArray financialStatements = financialReport.getAsJsonArray("financialStatements");
        checkValidFinancialStatements(financialStatements);
    }

    /**
     * Checks if the financialStatements in the UserData is valid.
     *
     * @param financialStatements The financialStatements being loaded of JsonObject type.
     * @throws RainyDayException If financialStatements is invalid.
     */
    private static void checkValidFinancialStatements(JsonArray financialStatements) throws RainyDayException {
        // check that every financial statement in the statements has the required fields
        for (JsonElement financialStatementElement : financialStatements) {
            JsonObject financialStatement = financialStatementElement.getAsJsonObject();
            if (!financialStatement.has("description")) {
                throw new RainyDayException("");
            }

            if (!financialStatement.has("flowDirection")) {
                throw new RainyDayException("");
            }
            String flowDirection = financialStatement.get("flowDirection").getAsString();
            if (!EnumUtils.isValidEnum(FlowDirection.class, flowDirection)) {
                throw new RainyDayException("");
            }

            if (!financialStatement.has("value")) {
                throw new RainyDayException("");
            }
            double value = financialStatement.get("value").getAsDouble();
            if (value <= 0) {
                throw new RainyDayException("");
            }

            if (!financialStatement.has("category")) {
                throw new RainyDayException("");
            }
            if (!financialStatement.has("date")) {
                throw new RainyDayException("");
            }

            if (!financialStatement.has("isIgnored")) {
                throw new RainyDayException("");
            }
            String isIgnored = financialStatement.get("isIgnored").getAsString();
            if (!isIgnored.equals("true") && !isIgnored.equals("false")) {
                throw new RainyDayException("");
            }
        }
    }

    /**
     * Checks if the shortcutCommands in the UserData is valid.
     *
     * @param shortcutCommands The shortcutCommands being loaded of JsonObject type.
     * @throws RainyDayException If shortcutCommands is invalid.
     */
    private static void checkValidShortcutCommands(JsonObject shortcutCommands) throws RainyDayException {
        HashMap<String, String> shortcutCommandsHashMap = new HashMap<>();
        for (String key : shortcutCommands.keySet()) {
            String value = shortcutCommands.get(key).getAsString();
            ShortcutCommand.checkShortcutValidity(shortcutCommandsHashMap, key, value);
            shortcutCommandsHashMap.put(key, value);
        }
    }


    /**
     * Uses JSON serialization to save the FinancialReport object into a JSON file.
     *
     * @param userData The object containing the UserData to save.
     * @param filePath The file path where the FinancialReport will be saved to.
     */
    public static void writeToFile(UserData userData, String filePath) {
        setupLogger();
        logger.log(Level.INFO, "starting writeToFile");
        try {
            Files.createDirectories(Paths.get("./data"));
            String jsonUserData = gson.toJson(userData);
            FileWriter fileWriter = new FileWriter(filePath);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            bufferedWriter.write(jsonUserData);
            bufferedWriter.close();
            logger.log(Level.INFO, "writeToFile successfully executed");

        } catch (IOException e) {
            logger.log(Level.SEVERE, "writeToFile failed");
            e.printStackTrace();
        }

    }

    /**
     * Fills up the CSVWriter with the contents of the financial statements within the FinancialReport.
     *
     * @param report      The FinancialReport which contains the financial statements to be saved into CSV.
     * @param tableWriter The CSV writer object.
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

    /**
     * Writes to a file in .csv format.
     *
     * @param report The FinancialReport which contains the financial statements to be saved into a .csv file.
     * @throws IOException When there is an error writing to the .csv file.
     */
    public static void writeToCSV(FinancialReport report) throws IOException {
        setupLogger();
        logger.log(Level.INFO, "starting writeToCSV");
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
            FileHandler fileHandler = new FileHandler("Storage.log", true);
            logger.addHandler(fileHandler);
        } catch (Exception e) {
            logger.log(Level.SEVERE, "File logger not working.", e);
        }
    }
}
