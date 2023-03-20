package seedu.duke.commandhandler;

import seedu.duke.exercisegenerator.GenerateExercise;
import seedu.duke.storage.StorageHandler;
import seedu.duke.userdata.UserCareerData;
import seedu.duke.states.ExerciseStateHandler;
import seedu.duke.ui.Ui;
import seedu.duke.util.StringSplitter;

public class CommandHandler {
    private static final String BLANK = " ";

    /**
     * @param rawUserCommands      This refers to the raw inputs given by the user
     * @param ui                   This allows us to output messages
     * @param exerciseGenerator    This takes in filter parameters and outputs a curated exercise list
     * @param userCareerData       This keeps track and allows logging of all user data
     * @param exerciseStateHandler This allows us to know when we are
     */
    public void handleUserCommands(String rawUserCommands, Ui ui, GenerateExercise exerciseGenerator,
                                   UserCareerData userCareerData, ExerciseStateHandler exerciseStateHandler,
                                   StorageHandler storageHandler) {
        StringSplitter stringSplitter = new StringSplitter();
        String[] userCommands = stringSplitter.splitString(rawUserCommands);
        if (exerciseStateHandler.workoutOngoing) {
            ExerciseSessionCommandHandler exerciseSessionCommandHandler = new ExerciseSessionCommandHandler();
            exerciseSessionCommandHandler.handleExerciseSessionUserCommands(userCommands, ui, exerciseGenerator,
                    userCareerData, exerciseStateHandler);
        } else {
            GeneralCommandHandler generalCommandHandler = new GeneralCommandHandler();
            generalCommandHandler.handleGeneralUserCommands(userCommands, ui, exerciseGenerator,
                    userCareerData, exerciseStateHandler, storageHandler);
        }
        ui.splitLine();
    }


}
