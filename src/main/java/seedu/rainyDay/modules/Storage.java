package seedu.rainyDay.modules;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

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
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

import com.opencsv.CSVWriter;

//@@author KN-CY
public class Storage {
    private static Logger logger = Logger.getLogger("Storage.log");
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
     * Uses JSON serialization to save the FinancialReport object into a JSON file.
     *
     * @param report   The object containing the FinancialReport to save.
     * @param filePath The file path where the FinancialReport will be saved to.
     */
    public static void writeToFile(FinancialReport report, String filePath) {
        try {
            String jsonReport = gson.toJson(report);
            FileWriter fileWriter = new FileWriter(filePath);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            bufferedWriter.write(jsonReport);
            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Uses deserialization to load the FinancialReport from a serialized file.
     *
     * @param filePath The file path where the FinancialReport will be loaded from.
     * @return The FinancialReport after deserialization from the file.
     * @throws FileNotFoundException If the file specified by the filePath is not found.
     * @throws JsonParseException    If Json is given in invalid format and is unable to be parsed.
     */
    public static FinancialReport loadFromFile(String filePath) throws FileNotFoundException
            , JsonParseException {
        Reader reader = new FileReader(filePath);
        FinancialReport statements = gson.fromJson(reader, FinancialReport.class);

        return statements;
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
