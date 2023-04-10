package seedu.duke.logic.commandhandler;

import java.util.Scanner;
import seedu.duke.achievements.AchievementListHandler;
import seedu.duke.commons.exceptions.DukeError;
import seedu.duke.data.exercisegenerator.GenerateExercise;
import seedu.duke.data.userdata.UserCareerData;
import seedu.duke.logic.commandhandler.states.ExerciseStateHandler;
import seedu.duke.storage.Storage;
import seedu.duke.ui.ErrorMessages;
import seedu.duke.ui.Ui;
import seedu.duke.data.userdata.userplan.UserPlan;
import seedu.duke.commons.util.StringSplitter;

public class CommandHandler {

    /**
     * @param rawUserCommands This refers to the raw inputs given by the user
     * @param ui This allows us to output messages
     * @param exerciseGenerator This takes in filter parameters and outputs a curated exercise list
     * @param userCareerData This keeps track and allows logging of all user data
     * @param exerciseStateHandler This allows us to know whether an exercise is ongoing or not
     * @param planner This keeps track of the workout plans
     */
    public void handleUserCommands (String rawUserCommands, Ui ui, GenerateExercise exerciseGenerator,
                                    UserCareerData userCareerData, ExerciseStateHandler exerciseStateHandler,
                                    Storage storage, UserPlan planner, AchievementListHandler achievementListHandler,
                                    Scanner scanner) throws DukeError {
        StringSplitter stringSplitter = new StringSplitter();
        String[] userCommands = stringSplitter.splitString(rawUserCommands);
        if (userCommands.length == 0) {
            throw new DukeError(ErrorMessages.ERROR_EMPTY_INPUT.toString());
        }
        if (exerciseStateHandler.workoutOngoing) {
            ExerciseSessionCommandHandler exerciseSessionCommandHandler = new ExerciseSessionCommandHandler();
            exerciseSessionCommandHandler.handleExerciseSessionUserCommands(userCommands, ui,
                                                                            userCareerData, exerciseStateHandler,
                                                                            achievementListHandler);
        } else {
            GeneralCommandHandler generalCommandHandler = new GeneralCommandHandler();
            generalCommandHandler.handleGeneralUserCommands(userCommands, ui, exerciseGenerator,
                                                            userCareerData, exerciseStateHandler,
                                                            storage,
                                                            planner,
                                                            achievementListHandler,
                                                            scanner);
        }
    }

}
