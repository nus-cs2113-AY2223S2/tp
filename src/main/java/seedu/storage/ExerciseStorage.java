package seedu.storage;

import com.opencsv.CSVWriter;

import seedu.constants.DateConstants;
import seedu.entities.Exercise;
import seedu.exceptions.LifeTrackerException;
import seedu.exceptions.UnableToSaveDatabaseException;
import seedu.logger.LogFileHandler;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class ExerciseStorage extends Storage implements FileReadable, FileWritable{
    private static final String CSV_DELIMITER = ",";
    private static final DateTimeFormatter DTF = DateConstants.DATABASE_DTF;
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
                String exerciseName = exerciseLine[0];
                String exerciseDescription = exerciseLine[1];
                float calorieBurnt = Float.parseFloat(exerciseLine[2]);
                LocalDate date = LocalDate.parse(exerciseLine[3], DTF);
                exercises.add(new Exercise(exerciseName, exerciseDescription, calorieBurnt, date));
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
        Collections.sort(exercises);
        for (Exercise exercise : exercises) {
            writer.writeNext(exercise.toWriteFormat(CSV_DELIMITER, DTF));
        }
        writer.close();
    }

    public void saveExercise(Exercise exercise) throws LifeTrackerException {
        exercises.add(exercise);
        try {
            this.write();
        } catch (IOException e) {
            throw new UnableToSaveDatabaseException("Exercise");
        }
    }

    public void resetStorage() {
        exercises = new ArrayList<Exercise>();
        try {
            this.write();
            System.out.println("ExerciseStorage was successfully resetted!");
        } catch (IOException e) {
            System.out.println("Could not reset ExerciseStorage!");
        }
    }

    public ArrayList<Exercise> getExercises() {
        return this.exercises;
    }

    public List<Exercise> getExercisesByDate(LocalDate date) {
        List<Exercise> filteredExercises = exercises.stream()
                .filter(e -> e.getDate().equals(date))
                .collect(Collectors.toList());
        return filteredExercises;
    }
}
