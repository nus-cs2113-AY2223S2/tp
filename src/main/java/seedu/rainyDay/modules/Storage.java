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
import seedu.rainyDay.data.SavedData;
import seedu.rainyDay.exceptions.ErrorMessage;
import seedu.rainyDay.exceptions.RainyDayException;

//@@author KN-CY
public class Storage {
    public static final double MAX_VALUE = 21474836.47;
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
    public static SavedData loadFromFile(String filePath) throws FileNotFoundException, RainyDayException {
        setupLogger();
        logger.log(Level.INFO, "starting LoadFromFile");
        Reader reader = new FileReader(filePath);

        JsonElement fileElement = JsonParser.parseReader(reader);
        JsonObject fileObject = fileElement.getAsJsonObject();

        checkValidSavedData(fileObject);

        reader = new FileReader(filePath);
        SavedData savedData = gson.fromJson(reader, SavedData.class);
        roundDouble(savedData);
        return savedData;
    }

    /**
     * Ensures that all data loaded from json is in 2 decimal place.
     *
     * @param savedData The data that is loaded from the Json file.
     */
    private static void roundDouble(SavedData savedData) {
        double budgetGoal = savedData.getBudgetGoal();
        budgetGoal = (double) Math.round(budgetGoal * 100) / 100;
        savedData.setBudgetGoal(budgetGoal);

        FinancialReport financialReport = savedData.getFinancialReport();
        for (int i = 0; i < financialReport.getStatementCount(); i++) {
            double statementValue = financialReport.getFinancialStatement(i).getValue();
            statementValue = (double) Math.round(statementValue * 100) / 100;
            financialReport.getFinancialStatement(i).setValue(statementValue);
        }
    }

    /**
     * Checks if the loaded savedData is valid.
     *
     * @param savedData The user data being loaded of JsonObject type.
     * @throws RainyDayException If savedData is invalid.
     */
    private static void checkValidSavedData(JsonObject savedData) throws RainyDayException {
        // check that UserData has the required fields
        if (!savedData.has("financialReport")) {
            throw new RainyDayException(ErrorMessage.INVALID_SAVED_FINANCIAL_REPORT.toString());
        }
        if (!savedData.has("shortcutCommands")) {
            throw new RainyDayException(ErrorMessage.INVALID_SAVED_SHORTCUT_COMMANDS.toString());
        }
        if (!savedData.has("budgetGoal")) {
            throw new RainyDayException(ErrorMessage.INVALID_SAVED_BUDGET_GOAL.toString());
        }
        double budgetGoal = savedData.get("budgetGoal").getAsDouble();
        if (budgetGoal < 0 || budgetGoal > MAX_VALUE) {
            throw new RainyDayException(ErrorMessage.INVALID_SAVED_BUDGET_GOAL.toString());
        }

        JsonObject financialReport = savedData.getAsJsonObject("financialReport");
        checkValidFinancialReport(financialReport);

        JsonObject shortcutCommands = savedData.getAsJsonObject("shortcutCommands");
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
            throw new RainyDayException(ErrorMessage.INVALID_SAVED_FINANCIAL_STATEMENTS.toString());
        }

        if (!financialReport.has("reportOwner")) {
            throw new RainyDayException(ErrorMessage.INVALID_SAVED_REPORT_OWNER.toString());
        }
        String reportOwner = financialReport.get("reportOwner").getAsString();
        if (reportOwner.isEmpty() || reportOwner.charAt(0) == ' ') {
            throw new RainyDayException(ErrorMessage.INVALID_SAVED_REPORT_OWNER.toString());
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
                throw new RainyDayException(ErrorMessage.INVALID_SAVED_DESCRIPTION.toString());
            }
            String description = financialStatement.get("description").getAsString();
            if (description.isEmpty() || description.contains("-") || description.charAt(0) == ' ') {
                throw new RainyDayException(ErrorMessage.INVALID_SAVED_DESCRIPTION.toString());
            }

            if (!financialStatement.has("flowDirection")) {
                throw new RainyDayException(ErrorMessage.INVALID_SAVED_FLOW_DIRECTION.toString());
            }
            String flowDirection = financialStatement.get("flowDirection").getAsString();
            if (!EnumUtils.isValidEnum(FlowDirection.class, flowDirection)) {
                throw new RainyDayException(ErrorMessage.INVALID_SAVED_FLOW_DIRECTION.toString());
            }

            if (!financialStatement.has("value")) {
                throw new RainyDayException(ErrorMessage.INVALID_SAVED_VALUE.toString());
            }
            double value = financialStatement.get("value").getAsDouble();
            if (value <= 0 || value > 21474836.47) {
                throw new RainyDayException(ErrorMessage.INVALID_SAVED_VALUE.toString());
            }

            if (!financialStatement.has("category")) {
                throw new RainyDayException(ErrorMessage.INVALID_SAVED_CATEGORY.toString());
            }
            String category = financialStatement.get("category").getAsString();
            if (category.isEmpty() || category.contains("-") || category.charAt(0) == ' ') {
                throw new RainyDayException(ErrorMessage.INVALID_SAVED_CATEGORY.toString());
            }

            if (!financialStatement.has("date")) {
                throw new RainyDayException(ErrorMessage.INVALID_SAVED_DATE.toString());
            }

            if (!financialStatement.has("isIgnored")) {
                throw new RainyDayException(ErrorMessage.INVALID_SAVED_IS_IGNORED.toString());
            }
            String isIgnored = financialStatement.get("isIgnored").getAsString();
            if (!isIgnored.equals("true") && !isIgnored.equals("false")) {
                throw new RainyDayException(ErrorMessage.INVALID_SAVED_IS_IGNORED.toString());
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
            if (key.isEmpty() || value.isEmpty() || key.charAt(0) == ' ' || value.charAt(0) == ' ') {
                throw new RainyDayException(ErrorMessage.INVALID_SAVED_SHORTCUT_COMMANDS.toString());
            }

            ShortcutCommand.checkShortcutValidity(shortcutCommandsHashMap, key, value);
            shortcutCommandsHashMap.put(key, value);
        }
    }

    /**
     * Uses JSON serialization to save the FinancialReport object into a JSON file.
     *
     * @param savedData The object containing the UserData to save.
     * @param filePath  The file path where the FinancialReport will be saved to.
     */
    public static void writeToFile(SavedData savedData, String filePath) {
        setupLogger();
        logger.log(Level.INFO, "starting writeToFile");
        try {
            Files.createDirectories(Paths.get("./data"));
            String jsonUserData = gson.toJson(savedData);
            FileWriter fileWriter = new FileWriter(filePath);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            bufferedWriter.write(jsonUserData);
            bufferedWriter.close();
            logger.log(Level.INFO, "writeToFile successfully executed");

        } catch (IOException e) {
            logger.log(Level.SEVERE, "writeToFile failed");
            System.out.println(ErrorMessage.FAILED_FILE_OPERATION);
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
            FileHandler fileHandler = new FileHandler("./logs/Storage.log", true);
            logger.addHandler(fileHandler);
        } catch (Exception e) {
            logger.log(Level.SEVERE, "File logger not working.", e);
        }
    }
}
