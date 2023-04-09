package seedu.duke.logic.commands;

import seedu.duke.commons.exceptions.DukeError;
import seedu.duke.data.exercisegenerator.GenerateExercise;
import seedu.duke.data.userdata.UserCareerData;
import seedu.duke.ui.ErrorMessages;
import seedu.duke.ui.Ui;
import java.util.ArrayList;
import java.util.StringJoiner;

//@@author L-K-Chng
public class CompletedExerciseSearchCommand extends Command{
    private static final String OPEN_BRACE = "[";
    private static final String CLOSE_BRACE = "]";
    private String keyword = "";
    private ArrayList<String> foundList = new ArrayList<>();

    /**
     * Parses the user input to recognise the keyword provided by the user. Finds matching
     * exercises within the user's completed exercises and passes it into a new arraylist.
     *
     * @param userCommands The user's input into the program.
     * @param userCareerData Data which contains the user's completed workout sessions.
     * @throws DukeError if user does not enter keyword or if there are no exercises that match keyword provided.
     */

    public CompletedExerciseSearchCommand (String[] userCommands, UserCareerData userCareerData) throws DukeError {
        assert userCommands != null : "User Commands should not be null!";
        assert userCareerData != null : "User Career Data should not be null!";

        if (userCommands.length > 1) {
            StringJoiner joiner = new StringJoiner(" ");
            for (int i = 1; i < userCommands.length; i++) {
                joiner.add(userCommands[i]);
            }
            keyword = joiner.toString();
        } else {
            throw new DukeError(ErrorMessages.ERROR_EMPTY_KEYWORD.toString());
        }

        int totalSessionsArraySize = userCareerData.getTotalUserCareerSessions().size();
        int index = 1;
        for (int i = 0; i < totalSessionsArraySize; i++) {
            int indivSessionsArraySize = userCareerData.getTotalUserCareerSessions().
                    get(i).getSessionExercises().size();
            for (int j = 0; j < indivSessionsArraySize; j++) {
                String exerciseName = userCareerData.getTotalUserCareerSessions().
                        get(i).getSessionExercises().get(j).getName();
                String exerciseInstructions = userCareerData.getTotalUserCareerSessions().
                        get(i).getSessionExercises().get(j).getInstructions().toString();
                String exerciseDifficulty = parseDifficultyLevel(userCareerData.getTotalUserCareerSessions().
                        get(i).getSessionExercises().get(j).getLevel());
                String exerciseWorkoutType = userCareerData.getTotalUserCareerSessions().
                        get(i).getSessionExercises().get(j).getWorkoutType().toString();
                String exerciseId = userCareerData.getTotalUserCareerSessions().
                        get(i).getSessionExercises().get(j).getId();
                int startInstructions = exerciseInstructions.indexOf(OPEN_BRACE);
                int endInstructions = exerciseInstructions.indexOf(CLOSE_BRACE);
                int startWorkoutType = exerciseWorkoutType.indexOf(OPEN_BRACE);
                int endWorkoutType = exerciseWorkoutType.indexOf(CLOSE_BRACE);
                exerciseInstructions = exerciseInstructions.substring(startInstructions+1,endInstructions);
                exerciseWorkoutType = exerciseWorkoutType.substring(startWorkoutType+1,endWorkoutType);

                if (exerciseName.toLowerCase().contains(keyword)) {
                    String output = index + ". " + exerciseName +
                            System.lineSeparator() + "Exercise ID: " + exerciseId +
                            System.lineSeparator() + "Difficulty Level: " + exerciseDifficulty +
                            System.lineSeparator() + "Workout type: " + exerciseWorkoutType +
                            System.lineSeparator() + exerciseInstructions;
                    foundList.add(output);
                    index++;
                }
            }
        }
        if (foundList.size() == 0) {
            throw new DukeError(ErrorMessages.ERROR_NO_MATCHING_KEYWORD.toString());
        }
    }

    /**
     * Parses the difficulty level to change its value from beginner, intermediate and expert to
     * easy, medium and hard.
     *
     * @param difficulty The difficulty of the exercise.
     * @return renames the difficulties to easy, medium and hard.
     */

    public static String parseDifficultyLevel(String difficulty) {
        if (difficulty.equals("beginner")) {
            return "easy";
        } else if (difficulty.equals("intermediate")) {
            return "medium";
        } else {
            return "hard";
        }
    }

    /**
     * Prints out the arraylist which contain the user's matching
     * exercises.
     *
     * @param ui This allows us to output messages
     * @param exerciseGenerator This takes in filter parameters and outputs a curated exercise list.
     */
    public void executeCommand (Ui ui, GenerateExercise exerciseGenerator) {
        System.out.println("Here are the exercises you have " +
                "completed which match the keyword that you have provided!" + System.lineSeparator());
        for (int i = 0; i < foundList.size(); i++) {
            System.out.println(foundList.get(i) + System.lineSeparator());
        }
    }
}
