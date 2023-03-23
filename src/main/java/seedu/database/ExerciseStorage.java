package seedu.database;

import com.opencsv.CSVWriter;
import seedu.constants.DateConstants;
import seedu.entities.Exercise;
import seedu.entities.Meal;
import seedu.logger.LogFileHandler;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;


public class ExerciseStorage extends Storage implements FileReadable, FileWritable{
    private static final String CSV_DELIMITER = ",";
    private ArrayList<Exercise> exercises;
    private BufferedReader br;


    public ExerciseStorage(String filePath) {
        super(filePath);
        exercises = new ArrayList<Exercise>();
        try {
            this.load();
        } catch (IOException e) {
            System.out.println("Error loading Exercise Storage");
        }
    }

    @Override
    public void load() throws IOException {
        String line = "";
        br = new BufferedReader(new FileReader(filePath));
        br.readLine();

        while ((line = br.readLine()) != null) {
            String[] exerciseLine;
            exerciseLine = line.split(CSV_DELIMITER);
            try {
                exercises.add(new Exercise(exerciseLine[0], exerciseLine[1], Float.parseFloat(exerciseLine[2])));
            } catch (Exception e) {
                LogFileHandler.logError("Invalid exercise format!");
            }
        }

        br.close();
    }

    @Override
    public void write() throws IOException {
        CSVWriter writer = new CSVWriter(new FileWriter(filePath),
                CSVWriter.DEFAULT_SEPARATOR,
                CSVWriter.NO_QUOTE_CHARACTER,
                CSVWriter.DEFAULT_ESCAPE_CHARACTER,
                CSVWriter.RFC4180_LINE_END);
        String[] header = { "Exercise Name", "Exercise Description", "Calories Burnt" };
        writer.writeNext(header);
        for (Exercise exercise : exercises) {
            writer.writeNext(exercise.toWriteFormat(CSV_DELIMITER));
        }
        writer.close();
    }
}
