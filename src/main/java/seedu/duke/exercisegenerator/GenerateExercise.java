package seedu.duke.exercisegenerator;

import seedu.duke.exersisedata.ExerciseData;

import java.util.ArrayList;
import java.util.Random;

import seedu.duke.Utility.DifficultyLevel;

public class GenerateExercise {
    public ArrayList<ExerciseData> exerciseData = new ArrayList<>();

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

    public ArrayList<ExerciseData> generateSpecificDifficultySet(int count, String difficultyLevel) {
        ArrayList<ExerciseData> specificDifficultyExerciseList = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < count; i++) {
            while (true) {
                ExerciseData randomExercise = exerciseData.get(random.nextInt(exerciseData.size()));
                if (randomExercise.getLevel().equals(difficultyLevel)) {
                    specificDifficultyExerciseList.add(randomExercise);
                    break;
                }
            }

        }
        return specificDifficultyExerciseList;
    }
}
