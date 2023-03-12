package seedu.duke.exercisegenerator;

import seedu.duke.errors.DukeError;
import seedu.duke.exersisedata.ExerciseData;

import java.util.ArrayList;
import java.util.Random;

public class GenerateExercise {

    private static final String EASY = "easy";
    private static final String MEDIUM = "medium";
    private static final String HARD = "hard";

    private static final String BEGINNER = "beginner";
    private static final String INTERMEDIATE = "intermediate";
    private static final String EXPERT = "expert";
    public ArrayList<ExerciseData> exerciseData;

    public GenerateExercise() {
        ParseData parseData = new ParseData();
        exerciseData = parseData.getExercises();
    }

    public ArrayList<ExerciseData> generateRandomSet(int count) {
        Random random = new Random();
        ArrayList<ExerciseData> filteredExerciseList = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            ExerciseData randomExercise = exerciseData.get(random.nextInt(exerciseData.size()));
            filteredExerciseList.add(randomExercise);
        }
        return filteredExerciseList;
    }

    public ArrayList<ExerciseData> generateRandomSetFrom(ArrayList<ExerciseData> exerciseList, int count) {
        Random random = new Random();
        ArrayList<ExerciseData> filteredExerciseList = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            ExerciseData randomExercise = exerciseList.get(random.nextInt(exerciseList.size()));
            filteredExerciseList.add(randomExercise);
        }
        return filteredExerciseList;
    }

    public ArrayList<ExerciseData> generateFilteredGymSetFrom(ArrayList<ExerciseData> exerciseList) {
        ArrayList<ExerciseData> filteredExerciseList = new ArrayList<>();
        for (int i = 0; i < exerciseList.size(); i++) {
            ExerciseData exercise = exerciseList.get(i);
            if (exercise.getEquipment() != null && !exercise.getEquipment().equals("body only")) {
                filteredExerciseList.add(exercise);
            }
        }
        return filteredExerciseList;
    }

    public ArrayList<ExerciseData> generateFilteredBodySetFrom(ArrayList<ExerciseData> exerciseList) {
        ArrayList<ExerciseData> filteredExerciseList = new ArrayList<>();
        for (int i = 0; i < exerciseList.size(); i++) {
            ExerciseData exercise = exerciseList.get(i);
            if (exercise.getEquipment() != null && exercise.getEquipment().equals("body only")) {
                filteredExerciseList.add(exercise);
            }
        }
        return filteredExerciseList;
    }


    public ArrayList<ExerciseData> generateFilteredDifficultySetFrom(ArrayList<ExerciseData> exerciseList,
            String difficulty) throws DukeError {
        String exerciseDataDifficultyLevel;
        exerciseDataDifficultyLevel = parseDifficultyLevel(difficulty);
        ArrayList<ExerciseData> filteredExerciseList = new ArrayList<>();
        for (int i = 0; i < exerciseList.size(); i++) {
            ExerciseData exercise = exerciseList.get(i);
            if (exercise.getLevel().equals(exerciseDataDifficultyLevel)) {
                filteredExerciseList.add(exercise);
            }
        }
        return filteredExerciseList;
    }

    public ArrayList<ExerciseData> generateSpecificDifficultySet(String difficultyLevel, int count) {
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

    public ArrayList<ExerciseData> generateSetAll() {
        ArrayList<ExerciseData> allExerciseList = exerciseData;
        return allExerciseList;
    }

    private static String parseDifficultyLevel(String difficulty) throws DukeError {
        if (difficulty.equals(EASY)) {
            return BEGINNER;
        } else if (difficulty.equals(MEDIUM)) {
            return INTERMEDIATE;
        } else if (difficulty.equals(HARD)) {
            return EXPERT;
        } else {
            throw new DukeError("Incorrect difficulty level input");
        }
    }

}
