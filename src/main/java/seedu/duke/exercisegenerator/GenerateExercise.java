package seedu.duke.exercisegenerator;

import seedu.duke.exceptions.DukeError;
import seedu.duke.exersisedata.ExerciseData;

import java.util.ArrayList;
import java.util.Random;

public class GenerateExercise {

    private static final String EASY = "easy";
    private static final String MEDIUM = "medium";
    private static final String HARD = "hard";
    private static final String UPPER = "upper";
    private static final String UPPER_BODY = "upper body";
    private static final String CORE = "core";
    private static final String LEGS = "legs";

    private static final String BEGINNER = "beginner";
    private static final String INTERMEDIATE = "intermediate";
    private static final String EXPERT = "expert";
    private static final String OPEN_BRACE = "[";
    private static final String CLOSE_BRACE = "]";
    private final ArrayList<ExerciseData> exerciseDataList;

    private ArrayList<ExerciseData> filteredExerciseList;

    public GenerateExercise() {
        ParseData parseData = new ParseData();
        exerciseDataList = parseData.getExercises();
    }

    public ArrayList<ExerciseData> generateRandomSetFrom(ArrayList<ExerciseData> exerciseList, int count) {
        Random random = new Random();
        filteredExerciseList = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            int indexToRemove = random.nextInt(exerciseList.size());
            ExerciseData randomExercise = exerciseList.get(indexToRemove);
            exerciseList.remove(indexToRemove);
            filteredExerciseList.add(randomExercise);
        }
        return filteredExerciseList;
    }

    //Cleon's gym set - filter by body and gym
    public ArrayList<ExerciseData> generateFilteredGymSetFrom(ArrayList<ExerciseData> exerciseList) {
        ArrayList<ExerciseData> filteredExerciseList = new ArrayList<>();
        for (ExerciseData exercise : exerciseList) {
            if (exercise.getEquipment() != "null" && !exercise.getEquipment().equals("body only")) {
                filteredExerciseList.add(exercise);
            }
        }
        return filteredExerciseList;
    }

    public ArrayList<ExerciseData> generateFilteredBodySetFrom(ArrayList<ExerciseData> exerciseList) {
        ArrayList<ExerciseData> filteredExerciseList = new ArrayList<>();
        for (ExerciseData exercise : exerciseList) {
            if (exercise.getEquipment() != "null" && exercise.getEquipment().equals("body only")) {
                filteredExerciseList.add(exercise);
            }
        }
        return filteredExerciseList;
    }


    //Dylan's set - filter by difficulty
    public ArrayList<ExerciseData> generateFilteredDifficultySetFrom(ArrayList<ExerciseData> exerciseList,
                                                                     String difficulty) throws DukeError {
        String exerciseDataDifficultyLevel;
        exerciseDataDifficultyLevel = parseDifficultyLevel(difficulty);
        ArrayList<ExerciseData> filteredExerciseList = new ArrayList<>();
        for (ExerciseData exercise : exerciseList) {
            if (exercise.getLevel().equals(exerciseDataDifficultyLevel)) {
                filteredExerciseList.add(exercise);
            }
        }
        return filteredExerciseList;
    }

    //lipkuang - Added new filter for workout type.
    public ArrayList<ExerciseData> generateFilteredWorkoutTypeFrom(ArrayList<ExerciseData> exerciseList,
                                                                   String workoutType) throws DukeError {
        String exerciseDataWorkoutType;
        String getWorkoutType;
        String getWorkoutTypeFinal;
        exerciseDataWorkoutType = parseWorkoutType(workoutType);
        ArrayList<ExerciseData> filteredExerciseList = new ArrayList<>();
        for (ExerciseData exercise : exerciseList) {
            //toString still includes opening and closing brackets
            getWorkoutType = exercise.getWorkoutType().toString();
            int start = getWorkoutType.indexOf(OPEN_BRACE);
            int end = getWorkoutType.indexOf(CLOSE_BRACE);
            //remove the open and close brackets.
            getWorkoutTypeFinal = getWorkoutType.substring(start + 1, end);
            if (getWorkoutTypeFinal.equals(exerciseDataWorkoutType)) {
                filteredExerciseList.add(exercise);
            }
        }
        return filteredExerciseList;
    }



    public ArrayList<ExerciseData> generateSetAll() {
        return this.exerciseDataList;
    }

    private static String parseDifficultyLevel(String difficulty) throws DukeError {
        switch (difficulty) {
        case EASY:
            return BEGINNER;
        case MEDIUM:
            return INTERMEDIATE;
        case HARD:
            return EXPERT;
        default:
            throw new DukeError("Incorrect difficulty level input");
        }
    }

    private static String parseWorkoutType(String workoutType) throws DukeError {
        switch (workoutType) {
        case UPPER:
            return UPPER_BODY;
        case CORE:
            return CORE;
        case LEGS:
            return LEGS;
        default:
            throw new DukeError("Incorrect workout type input");
        }
    }

}
