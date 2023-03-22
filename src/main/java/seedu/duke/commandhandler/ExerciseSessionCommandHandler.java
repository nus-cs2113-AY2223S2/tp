package seedu.duke.commandhandler;

import seedu.duke.exceptions.DukeError;
import seedu.duke.exceptions.OngoingExGenerationError;
import seedu.duke.exceptions.OngoingExHelpError;
import seedu.duke.exceptions.OngoingExHistoryError;
import seedu.duke.exceptions.OngoingExProgressError;
import seedu.duke.exceptions.OngoingExWriteError;

import seedu.duke.states.ExerciseStateHandler;
import seedu.duke.ui.Ui;
import seedu.duke.userdata.UserCareerData;

import java.util.Scanner;

public class ExerciseSessionCommandHandler implements CommandList{
    private static final boolean COMPLETED_EXERCISE = true;
    private static final boolean INCOMPLETE_EXERCISE = false;

    /**
     * This class takes in parsed user input and handles all user commands when an exercise is ongoing
     * There will be no access to the following commands:
     * generate, filter, help, start and history.
     * This is due to the motive of pushing the user to focus and complete their exercise
     *
     * @param userCommands         This refers to the commands given by the user
     * @param ui                   This allows us to output messages
     * @param userCareerData       This keeps track and allows logging of all user data
     * @param exerciseStateHandler This allows us to know whether an exercise is ongoing or not
     */

    public void handleExerciseSessionUserCommands(String[] userCommands, Ui ui,
                                                  UserCareerData userCareerData
                                                  , ExerciseStateHandler exerciseStateHandler
                                                  ) {
        try {
            switch (userCommands[0]) {
            case GENERATE_COMMAND:
                throw new OngoingExGenerationError();
            case HELP_COMMAND:
            case FILTERS_COMMAND:
            case FIND_COMMAND:
                throw new OngoingExHelpError();
            case BYE_COMMAND:
            case EXIT_COMMAND:
                boolean exit = confirmExitDuringWorkout();
                if (exit) {
                    ui.byeUser();
                    System.exit(0);
                } else {
                    System.out.println("You got this! Finish your exercise session!");
                }
                break;
            case READ_SAMPLE_COMMAND:
            case WRITE_SAMPLE_COMMAND:
                throw new OngoingExWriteError();
            case START_COMMAND:
                throw new OngoingExProgressError();
            case CURRENT_COMMAND:
                exerciseStateHandler.printCurrentWorkout();
                break;
            case FINISH_COMMAND:
                exerciseStateHandler.endWorkout(COMPLETED_EXERCISE, userCareerData);
                break;
            case CANCEL_COMMAND:
                exerciseStateHandler.endWorkout(INCOMPLETE_EXERCISE, userCareerData);
                break;
            case HISTORY_COMMAND:
                throw new OngoingExHistoryError();
            default:
                ui.unknownCommand();
                break;
            }
        } catch (DukeError e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * This checks with the user whether they wish to exit a Fitness Duke
     * session while an exercise is ongoing.
     * Prompts the user for confirmation to exit or to continue their exercise
     * @return Returns true if the user wishes to exit, false if they do not want exit
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
}
