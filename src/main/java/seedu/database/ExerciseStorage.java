package seedu.database;

import seedu.entities.Exercise;

import java.io.IOException;
import java.util.ArrayList;

public class ExerciseStorage extends Storage implements FileReadable, FileWritable{
    private ArrayList<Exercise> exercises;
    private Exercise exercise;

    public ExerciseStorage(String filePath) {
        super(filePath);
        try {
            this.load();
        } catch (IOException e) {
            System.out.println("Error loading Exercise Storage");
        }
    }

    @Override
    public void load() throws IOException {
        
        String exerciseName;
        float caloriesBurnt;
        exercise = new Exercise(exerciseName, caloriesBurnt);
    }

    @Override
    public void write() throws IOException {

    }
}
