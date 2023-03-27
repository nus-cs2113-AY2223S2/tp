package seedu.entities;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Exercise {
    protected String exerciseName;
    protected float caloriesBurnt;
    protected String exerciseDescription;
    protected LocalDate date;

    public Exercise(String exerciseName, String exerciseDescription, float caloriesBurnt, LocalDate date){
        this.exerciseName = exerciseName;
        this.exerciseDescription = exerciseDescription;
        this.caloriesBurnt = caloriesBurnt;
        this.date = date;
    }

    public String[] toWriteFormat(String csvDelimiter, DateTimeFormatter dtf) {
        String[] output = new String[4];
        output[0] = exerciseName;
        output[1] = exerciseDescription;
        output[2] = Float.toString(caloriesBurnt);
        output[3] = date.format(dtf);
        return output;
    }

    @Override
    public String toString() {
        return "Exercise: " + exerciseName + System.lineSeparator() +
                "Description: " + exerciseDescription + System.lineSeparator() +
                "Calories Burnt: " + caloriesBurnt + System.lineSeparator() +
                "Date of Exercise: " + date + System.lineSeparator();
    }


    public String getExerciseName() {
        return this.exerciseName;
    }

    public void setExerciseName(String exerciseName) {
        this.exerciseName = exerciseName;
    }

    public float getCaloriesBurnt() {
        return this.caloriesBurnt;
    }

    public void setCaloriesBurnt(float caloriesBurnt) {
        this.caloriesBurnt = caloriesBurnt;
    }

    public String getExerciseDescription() {
        return this.exerciseDescription;
    }

    public void setExerciseDescription(String exerciseDescription) {
        this.exerciseDescription = exerciseDescription;
    }

    public LocalDate getDate() {
        return this.date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

}
