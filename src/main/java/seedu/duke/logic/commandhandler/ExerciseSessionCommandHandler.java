package seedu.duke.logic.commandhandler;

import seedu.duke.achievements.AchievementListHandler;
import seedu.duke.commons.exceptions.DukeError;

import seedu.duke.logic.commandhandler.states.ExerciseStateHandler;
import seedu.duke.ui.ErrorMessages;
import seedu.duke.ui.Ui;
import seedu.duke.data.userdata.UserCareerData;
import java.util.Scanner;

//@@author ChubbsBunns
public class ExerciseSessionCommandHandler implements CommandList {
    private static final boolean COMPLETED_EXERCISE = true;
    private static final boolean INCOMPLETE_EXERCISE = false;

    /**
     * This checks with the user whether they wish to exit a Fitness Duke
     * session while an exercise is ongoing.
     * Prompts the user for confirmation to exit or to continue their exercise
     *
     * @return Returns true if the user wants to exit, false otherwise
     */
    private static boolean confirmExitDuringWorkout () {
        System.out.println("Are you sure you want to exit? You have a workout session ongoing." +
                               "\n You will lose your progress!" + "\n Type in 'y' for yes or 'n' for no");
        Scanner in = new Scanner(System.in);
        while (true) {
            String input = in.nextLine();
            switch (input.toLowerCase()) {
            case "y":
                return true;
            case "n":
                return false;
            default:
                System.out.println("Please type in a 'y' or a 'n' to state whether you wish to exit or not");
            }
        }
    }

    /**
     * This class takes in parsed user input and handles all user commands when an
     * exercise is ongoing
     * There will be no access to the following commands:
     * generate, filter, help, start and history.
     * This is due to the motive of pushing the user to focus and complete their
     * exercise
     *
     * @param userCommands This refers to the commands given by the user
     * @param ui This allows us to output messages
     * @param userCareerData This keeps track and allows logging of all user
     *     data
     * @param exerciseStateHandler This allows us to know whether an exercise is
     *     ongoing or not
     */

    public void handleExerciseSessionUserCommands (String[] userCommands, Ui ui,
                                                   UserCareerData userCareerData,
                                                   ExerciseStateHandler exerciseStateHandler,
                                                   AchievementListHandler achievementListHandler) {
        //additional error check for whether there's additional description behind single
        //word commands
        String additionalDescription = "";
        for (int i = 1; i < userCommands.length; i++) {
            additionalDescription = additionalDescription + " " + userCommands[i];
        }

        try {
            switch (userCommands[0]) {
            case GENERATE_COMMAND:
                throw new DukeError(ErrorMessages.ERROR_ONGOING_EXERCISE_GENERATE_COMMAND.toString());
            case HELP_COMMAND:
                if (additionalDescription.length() != 0) {
                    ui.unknownCommand();
                } else {
                    ui.printExerciseSessionHelp();
                }
                break;
            case FILTERS_COMMAND:
            case FIND_COMMAND:
                throw new DukeError(ErrorMessages.ERROR_ONGOING_EXERCISE_HELP_COMMAND.toString());
            case EXIT_COMMAND:
                if (additionalDescription.length() != 0) {
                    ui.unknownCommand();
                } else {
                    boolean exit = confirmExitDuringWorkout();
                    if (exit) {
                        exerciseStateHandler.endWorkout(INCOMPLETE_EXERCISE, userCareerData, achievementListHandler);
                        System.out.println("Back to main menu...");
                        ui.splitLine();
                        return;
                    } else {
                        System.out.println("You got this! Finish your exercise session!");
                    }
                }
                break;
            case START_COMMAND:
                throw new DukeError(ErrorMessages.ERROR_ONGOING_EXERCISE_START_COMMAND.toString());
            case CURRENT_COMMAND:
                if (additionalDescription.length() != 0) {
                    ui.unknownCommand();
                } else {
                    exerciseStateHandler.printCurrentWorkout();
                }
                break;
            case FINISH_COMMAND:
                if (additionalDescription.length() != 0) {
                    ui.unknownCommand();
                } else {
                    exerciseStateHandler.endWorkout(COMPLETED_EXERCISE, userCareerData, achievementListHandler);
                    System.out.println("Back to main menu...");
                    ui.splitLine();
                }
                return;
            case CANCEL_COMMAND:
                if (additionalDescription.length() != 0) {
                    ui.unknownCommand();
                } else {
                    exerciseStateHandler.endWorkout(INCOMPLETE_EXERCISE, userCareerData, achievementListHandler);
                    System.out.println("Back to main menu");
                    ui.splitLine();
                }
                return;
            case HISTORY_COMMAND:
                throw new DukeError(ErrorMessages.ERROR_ONGOING_EXERCISE_HISTORY_COMMAND.toString());
            case EXERCISE_DATA_COMMAND:
                throw new DukeError(ErrorMessages.ERROR_ONGOING_EXERCISE_DATA_COMMAND.toString());
            case ACHIEVEMENTS:
                throw new DukeError(ErrorMessages.ERROR_ONGOING_EXERCISE_ACHIEVEMENT_COMMAND.toString());
            case CLEAR_ACHIEVEMENTS:
                throw new DukeError(ErrorMessages.ERROR_ONGOING_EXERCISE_CLEAR_ACHIEVEMENT_COMMAND.toString());
            default:
                ui.unknownCommand();
                break;
            }
            ui.workoutMode();
            ui.splitLine();
        } catch (DukeError e) {
            System.out.println(e.getMessage());
        }
    }

}
