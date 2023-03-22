package seedu.duke.exercisegenerator;

import seedu.duke.exceptions.DukeError;
import seedu.duke.exceptions.InvalidDifficultyInputError;
import seedu.duke.exceptions.InvalidBodyWorkoutTypeError;
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
    private static final String NULL = "null";
    private static final String OUTPUT_BODY = "body only";

    private static final String BEGINNER = "beginner";
    private static final String INTERMEDIATE = "intermediate";
    private static final String EXPERT = "expert";
    private static final String OPEN_BRACE = "[";
    private static final String CLOSE_BRACE = "]";
    private static ArrayList<ExerciseData> filteredExerciseList;
    private final ArrayList<ExerciseData> exerciseDataList;


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

    //@@author Khulon
    /**
     * Returns an exercise list which is filtered according to the workout type: Gym
     * chosen by the user.
     * @param exerciseList Arraylist containing a set of workout exercises.
     * @return returns list of exercises filtered according to work out type: Gym
     */
    public ArrayList<ExerciseData> generateFilteredGymSetFrom(ArrayList<ExerciseData> exerciseList) {
        ArrayList<ExerciseData> filteredExerciseList = new ArrayList<>();
        for (ExerciseData exercise : exerciseList) {
            if (!exercise.getEquipment().equals(NULL) && !exercise.getEquipment().equals(OUTPUT_BODY)) {
                filteredExerciseList.add(exercise);
            }
        }
        return filteredExerciseList;
    }

    //@@author Khulon
    /**
     * Returns an exercise list which is filtered according to the workout type: Static
     * chosen by the user.
     * @param exerciseList Arraylist containing a set of workout exercises.
     * @return returns list of exercises filtered according to work out type: Static
     */
    public ArrayList<ExerciseData> generateFilteredStaticSetFrom(ArrayList<ExerciseData> exerciseList) {
        ArrayList<ExerciseData> filteredExerciseList = new ArrayList<>();
        for (ExerciseData exercise : exerciseList) {
            if (!exercise.getEquipment().equals(NULL) && exercise.getEquipment().equals(OUTPUT_BODY)) {
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

    /**
     * Returns an exercise list which is filtered according to the workout type
     * chosen by the user.
     * @param exerciseList Arraylist containing the entire set of workout exercises.
     * @param workoutType The workout type as input by the user.
     * @return returns list of exercises filtered according to work out type input by the user.
     * @throws DukeError Occurs if user inputs invalid workout type.
     */
    public ArrayList<ExerciseData> generateFilteredWorkoutTypeFrom(ArrayList<ExerciseData> exerciseList,
                                                                   String workoutType) throws DukeError {
        assert exerciseList != null : "exerciseList should not be null.";
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
            throw new InvalidDifficultyInputError();
        }
    }

    /**
     * Parse the user input to return the corresponding workout type within the data file.
     * @param workoutBodyType The workout type as input by the user.
     * @return The corresponding workout type within the data file.
     * @throws DukeError if the workout type input provided by the user is invalid
     */
    private static String parseWorkoutType(String workoutBodyType) throws DukeError {
        assert workoutBodyType != null : "workout type should not be null.";
        switch (workoutBodyType) {
        case UPPER:
            return UPPER_BODY;
        case CORE:
            return CORE;
        case LEGS:
            return LEGS;
        default:
            throw new InvalidBodyWorkoutTypeError();
        }
    }

}
