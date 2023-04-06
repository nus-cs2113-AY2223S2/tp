package seedu.duke.data.exercisegenerator;

import seedu.duke.commons.exceptions.DukeError;
import seedu.duke.data.exercisegenerator.exersisedata.ExerciseData;
import seedu.duke.ui.ErrorMessages;

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
    private static ArrayList<ExerciseData> ipptExercises;
    private final ArrayList<ExerciseData> exerciseDataList;
    private final Random random;

    /**
     * Constructs the class to generate exercises randomly for each instance without a seed that will be used in
     * official release so that users can get a truly random exercise set each time.
     */
    public GenerateExercise () {
        LoadExerciseJson loadExerciseJson = new LoadExerciseJson();
        exerciseDataList = loadExerciseJson.getExercises();
        this.random = new Random();
    }

    /**
     * Overloaded constructor for the class to generate exercises with a random seed that will be used for testing
     * purposes only so that outputs can be checked accordingly.
     *
     * @param randomSeed A number that sets the seed for the random class.
     */
    public GenerateExercise (long randomSeed) {
        LoadExerciseJson loadExerciseJson = new LoadExerciseJson();
        exerciseDataList = loadExerciseJson.getExercises();
        this.random = new Random(randomSeed);
    }

    private static String parseDifficultyLevel (String difficulty) throws DukeError {
        switch (difficulty) {
        case EASY:
            return BEGINNER;
        case MEDIUM:
            return INTERMEDIATE;
        case HARD:
            return EXPERT;
        default:
            throw new DukeError(ErrorMessages.ERROR_DIFFICULTY_INPUT.toString());
        }
    }

    //@@author L-K-Chng

    /**
     * Parse the user input to return the corresponding workout type within the data file.
     *
     * @param workoutBodyType The workout type as input by the user.
     * @return The corresponding workout type within the data file.
     * @throws DukeError if the workout type input provided by the user is invalid
     */
    private static String parseWorkoutType (String workoutBodyType) throws DukeError {
        assert workoutBodyType != null : "Workout Type should not be null.";
        switch (workoutBodyType) {
        case UPPER:
            return UPPER_BODY;
        case CORE:
            return CORE;
        case LEGS:
            return LEGS;
        default:
            throw new DukeError(ErrorMessages.ERROR_BODY_WORKOUT_TYPE_INPUT.toString());
        }
    }

    //@@author Khulon

    public ArrayList<ExerciseData> generateRandomSetFrom (ArrayList<ExerciseData> exerciseList, int count) {
        filteredExerciseList = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            int indexToRemove = this.random.nextInt(exerciseList.size());
            ExerciseData randomExercise = exerciseList.get(indexToRemove);
            exerciseList.remove(indexToRemove);
            filteredExerciseList.add(randomExercise);
        }
        return filteredExerciseList;
    }

    public ArrayList<ExerciseData> generateFirstThree () {
        ArrayList<ExerciseData> tripletExerciseList = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            ExerciseData exerciseToAdd = this.exerciseDataList.get(i);
            tripletExerciseList.add(exerciseToAdd);
        }
        return tripletExerciseList;
    }

    /**
     * Returns an exercise list which is filtered according to the workout type: Gym
     * chosen by the user.
     *
     * @param exerciseList Arraylist containing a set of workout exercises.
     * @return returns list of exercises filtered according to work out type: Gym
     */
    public ArrayList<ExerciseData> generateFilteredGymSetFrom (ArrayList<ExerciseData> exerciseList) {
        ArrayList<ExerciseData> filteredExerciseList = new ArrayList<>();
        for (ExerciseData exercise : exerciseList) {
            if (!exercise.getEquipment().equals(NULL) && !exercise.getEquipment().equals(OUTPUT_BODY)) {
                filteredExerciseList.add(exercise);
            }
        }
        return filteredExerciseList;
    }

    /**
     * Returns an exercise list which is filtered according to the workout type: Static
     * chosen by the user.
     *
     * @param exerciseList Arraylist containing a set of workout exercises.
     * @return returns list of exercises filtered according to work out type: Static
     */
    public ArrayList<ExerciseData> generateFilteredStaticSetFrom (ArrayList<ExerciseData> exerciseList) {
        ArrayList<ExerciseData> filteredExerciseList = new ArrayList<>();
        for (ExerciseData exercise : exerciseList) {
            if (!exercise.getEquipment().equals(NULL) && exercise.getEquipment().equals(OUTPUT_BODY)) {
                filteredExerciseList.add(exercise);
            }
        }
        return filteredExerciseList;
    }

    //Dylan's set - filter by difficulty
    public ArrayList<ExerciseData> generateFilteredDifficultySetFrom (ArrayList<ExerciseData> exerciseList,
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

    //@@author L-K-Chng

    /**
     * Returns an exercise list which is filtered according to the workout type
     * chosen by the user.
     *
     * @param exerciseList Arraylist containing the entire set of workout exercises.
     * @param workoutType The workout type as input by the user.
     * @return returns list of exercises filtered according to work out type input by the user.
     * @throws DukeError Occurs if user inputs invalid workout type.
     */
    public ArrayList<ExerciseData> generateFilteredWorkoutTypeFrom (ArrayList<ExerciseData> exerciseList,
                                                                    String workoutType) throws DukeError {
        assert exerciseList != null : "Exercise List should not be null.";
        assert workoutType != null : "Workout Type should not be null.";
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

    //@@author ghzr0
    public ArrayList<ExerciseData> generateIPPTExercises (ArrayList<ExerciseData> exerciseList) {
        assert exerciseList != null : "Exercise List should not be null.";
        ipptExercises = new ArrayList<>();
        for (ExerciseData exercise : exerciseList) {
            // 563 -> push-up wide , 686 -> sit-up , 873 -> timed 2.4km run
            if (exercise.getId().equals("563") || exercise.getId().equals("686") || exercise.getId().equals("873")) {
                ipptExercises.add(exercise);
            }
        }
        return ipptExercises;
    }

    public ArrayList<ExerciseData> generateSetAll () {
        return this.exerciseDataList;
    }

}
