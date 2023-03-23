package seedu.entities;

public class Exercise {
    protected String exerciseName;
    protected float caloriesBurnt;
    protected String exerciseDescription;
    public Exercise(String exerciseName, String exerciseDescription, float caloriesBurnt){
        this.exerciseName = exerciseName;
        this.exerciseDescription = exerciseDescription;
        this.caloriesBurnt = caloriesBurnt;
    }

    public String[] toWriteFormat(String csvDelimiter) {
        String[] output = new String[3];
        output[0] = exerciseName;
        output[1] = exerciseDescription;
        output[2] = Float.toString(caloriesBurnt);
        return output;
    }
}
