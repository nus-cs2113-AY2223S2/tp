package seedu.duke.commandhandler;

import seedu.duke.commands.Command;

import seedu.duke.exceptions.DukeError;
import seedu.duke.exceptions.OngoingExGenerationError;
import seedu.duke.exceptions.OngoingExHelpError;
import seedu.duke.exceptions.OngoingExHistoryError;
import seedu.duke.exceptions.OngoingExProgressError;
import seedu.duke.exceptions.OngoingExWriteError;

import seedu.duke.exercisegenerator.GenerateExercise;
import seedu.duke.states.ExerciseStateHandler;
import seedu.duke.ui.Ui;
import seedu.duke.userdata.UserCareerData;

import java.util.Scanner;

public class ExerciseSessionCommandHandler implements CommandList{
    private static final boolean COMPLETED_EXERCISE = true;
    private static final boolean INCOMPLETE_EXERCISE = false;

    //Workout is on going,
    //There is no access to:
    //generate, filter, help, start, history

    public void handleExerciseSessionUserCommands(String[] userCommands, Ui ui, GenerateExercise exerciseGenerator,
                                                  UserCareerData userCareerData
                                                  , ExerciseStateHandler exerciseStateHandler
                                                  ) {
        Command command = null;
        boolean errorExists = false;
        try {
            switch (userCommands[0]) {
            case GENERATE_COMMAND:
                throw new OngoingExGenerationError();
            case HELP_COMMAND:
            case FILTERS_COMMAND:
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
                errorExists = true;
                break;
            }
        } catch (DukeError e) {
            System.out.println(e.getMessage());
            errorExists = true;
        }
        if (!errorExists) {
            try {
                if (command != null) {
                    command.executeCommand(ui, exerciseGenerator);
                }
            } catch (DukeError e) {
                System.out.println(e.getMessage());
            }
        }
        ui.splitLine();
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
