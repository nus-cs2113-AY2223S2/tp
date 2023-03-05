package seedu.duke.exercisegenerator;

import seedu.duke.exersisedata.ExerciseData;

import java.util.ArrayList;
import java.util.Random;

public class GenerateExercise {

    private ArrayList<ExerciseData> exerciseData = new ArrayList<>();

    public GenerateExercise() {
        ParseData parseData = new ParseData();
        exerciseData = parseData.getExercises();
    }

    public ArrayList<ExerciseData> generateRandomSet(int count){
        Random random = new Random();
        ArrayList<ExerciseData>randomExerciseList = new ArrayList<>();
        for(int i=0;i<count;i++) {
            ExerciseData randomExercise = exerciseData.get(random.nextInt(exerciseData.size()));
            randomExerciseList.add(randomExercise);
        }
        return randomExerciseList;

    }
}
