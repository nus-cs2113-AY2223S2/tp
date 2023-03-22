package seedu.duke.commands;

import seedu.duke.exceptions.DukeError;
import seedu.duke.exceptions.OngoingExGenerationError;
import seedu.duke.exceptions.OngoingExHelpError;
import seedu.duke.exceptions.OngoingExProgressError;
import seedu.duke.exceptions.OngoingExWriteError;
import seedu.duke.exceptions.OngoingExHistoryError;
import seedu.duke.exercisegenerator.GenerateExercise;
import seedu.duke.storage.StorageHandler;
import seedu.duke.userdata.UserCareerData;
import seedu.duke.states.ExerciseStateHandler;
import seedu.duke.ui.Ui;

import java.util.Scanner;

public class CommandHandler {
    private static final boolean COMPLETED_EXERCISE = true;
    private static final boolean INCOMPLETE_EXERCISE = false;

    /**
     * @param rawUserCommands This refers to the
     * @param ui This allows us to output messages
     * @param exerciseGenerator This takes in filter parameters and outputs a curated exercise list
     * @param userCareerData This keeps track and allows logging of all user data
     * @param exerciseStateHandler This allows us to know when we are
     */
    public void handleUserCommands (String rawUserCommands, Ui ui, GenerateExercise exerciseGenerator,
                                    UserCareerData userCareerData, ExerciseStateHandler exerciseStateHandler,
                                    StorageHandler storageHandler){
        String[] userCommands = rawUserCommands.split(" ");
        Command command = null;
        boolean errorExists = false;
        try {
            if (exerciseStateHandler.workoutOngoing) {
                //Workout is on going,
                //There is no access to:
                //generate, filter, help, start, history
                switch (userCommands[0]) {
                case "generate":
                    throw new OngoingExGenerationError();
                case "help":
                case "filters":
                    throw new OngoingExHelpError();
                case "bye":
                case "exit":
                    boolean exit = confirmExitDuringWorkout();
                    if (exit) {
                        ui.byeUser();
                        System.exit(0);
                    } else {
                        System.out.println("You got this! Finish your exercise session!");
                    }
                    break;
                case "readSample":
                case "writeSample":
                    throw new OngoingExWriteError();
                case "start":
                    throw new OngoingExProgressError();
                case "current":
                    exerciseStateHandler.printCurrentWorkout();
                    break;
                case "finish":
                    exerciseStateHandler.endWorkout(COMPLETED_EXERCISE, userCareerData);
                    break;
                case "cancel":
                    exerciseStateHandler.endWorkout(INCOMPLETE_EXERCISE, userCareerData);
                    break;
                case "history":
                    throw new OngoingExHistoryError();
                default:
                    ui.unknownCommand();
                    errorExists = true;
                    break;
                }
            } else {
                switch (userCommands[0]) {
                case "generate":
                    command = new GenerateFilterCommand(userCommands);
                    break;
                case "filters":
                    ui.printFilters();
                    break;
                case "bye":
                case "exit":
                    ui.byeUser();
                    System.exit(0);
                    break;
                case "help":
                    command = new HelpCommand();
                    break;
                case "readSample":
                    command = new SampleReadCommand(userCareerData);
                    break;
                case "writeSample":
                    // sample data
                    command = new SampleSavingCommand(userCareerData,
                                                      exerciseGenerator.generateRandomSetFrom(
                                                              exerciseGenerator.generateSetAll(), 3), storageHandler);
                    break;
                case "start":
                    exerciseStateHandler.startWorkout();
                    break;
                case "current":
                case "finish":
                case "cancel":
                    System.out.println("No workout session active." +
                                               " Please generate a workout and use the \"start\" command!");
                    break;
                case "history":
                    userCareerData.printAllFinishedWorkoutSessions();
                    break;
                case "find":
                    command = new ExerciseSearchCommand(userCommands);
                    break;
                default:
                    ui.unknownCommand();
                    errorExists = true;
                    break;
                }
            }

        } catch (DukeError e) {
            System.out.println(e.getMessage());
            errorExists = true;
        }
        if (!errorExists) {
            try {
                if (command != null) {
                    command.executeCommand(ui, exerciseGenerator);
                    if (command instanceof GenerateFilterCommand) {
                        exerciseStateHandler
                                .storePreviousGeneratedWorkout(((GenerateFilterCommand) command).provideExerciseList());
                    }
                }
            } catch (DukeError e) {
                System.out.println(e.getMessage());
            }
        }
        ui.splitLine();
    }

    private static boolean confirmExitDuringWorkout () {
        System.out.println("Are you sure you want to exit? You have a workout session ongoing." +
                                   "\n You will lose your progress!" + "\n Type in 'y' for yes or 'n' for no");
        Scanner in = new Scanner(System.in);
        while (true) {
            String input = in.nextLine();
            switch (input.toLowerCase()) {
            case "y":
                System.out.println("I return true");
                return true;
            case "n":
                System.out.println("I return false");
                return false;
            default:
                System.out.println("Please type in a 'y' or a 'n' to state whether you wish to exit or not");
            }
        }
    }

}
