package seedu.duke.logic.commandhandler;

import java.util.Arrays;
import java.util.Scanner;
import seedu.duke.achievements.AchievementListHandler;
import seedu.duke.logic.commands.Command;
import seedu.duke.logic.commands.ExerciseSearchCommand;
import seedu.duke.logic.commands.GenerateFilterCommand;
import seedu.duke.logic.commands.HelpCommand;
import seedu.duke.logic.commands.IPPTCmd;
import seedu.duke.logic.commands.QuickStartCommand;
import seedu.duke.commons.exceptions.DukeError;
import seedu.duke.data.exercisegenerator.GenerateExercise;
import seedu.duke.logic.commandhandler.states.ExerciseStateHandler;
import seedu.duke.logic.commands.CompletedExerciseSearchCommand;

import seedu.duke.storage.Storage;
import seedu.duke.ui.ErrorMessages;
import seedu.duke.ui.Ui;
import seedu.duke.data.userdata.UserCareerData;
import seedu.duke.data.userdata.UserExerciseData;
import seedu.duke.data.userdata.userplan.UserPlan;
import java.util.HashMap;

public class GeneralCommandHandler implements CommandList {

    /**
     * This class handles all user commands when not in an exercise
     *
     * @param userCommands This refers to the commands given by the user
     * @param ui This allows us to output messages
     * @param exerciseGenerator This takes in filter parameters and outputs a
     *         curated exercise list
     * @param userCareerData This keeps track and allows logging of all user
     *         data
     * @param exerciseStateHandler This allows us to start workouts
     */
    // addition of user exercise history
    public void handleGeneralUserCommands (String[] userCommands, Ui ui, GenerateExercise exerciseGenerator,
                                           UserCareerData userCareerData, ExerciseStateHandler exerciseStateHandler,
                                           Storage storage, UserPlan planner,
                                           AchievementListHandler achievementListHandler,
                                           Scanner scanner) {
        Command command = null;
        boolean errorExists = false;
        //additional error check for whether there's additional description behind single
        //word commands
        String additionalDescription = "";
        for (int i = 1; i < userCommands.length; i++) {
            additionalDescription = additionalDescription + " " + userCommands[i];
        }

        try {
            switch (userCommands[0]) {
            case DELETE_COMMAND:
                //additional description becomes a number. Delete the session
                try {
                    if (additionalDescription.length() == 0) {
                        throw new DukeError(ErrorMessages.ERROR_EMPTY_DESCRIPTION_NUMBER.toString());
                    }
                    int sessionNumber = Integer.parseInt(userCommands[1]);
                    if ((sessionNumber > userCareerData.getTotalUserCareerSessions().size()) || sessionNumber <= 0) {
                        throw new DukeError(ErrorMessages.ERROR_INVALID_DELETE_SESSION.toString());
                    }
                    exerciseStateHandler.deleteWorkoutSession(userCareerData, sessionNumber);
                } catch (NumberFormatException e) {
                    System.out.println("You did not key in a session number. " +
                                               "Please key in a valid session number and try again!");
                }
                break;
            case GENERATE_COMMAND:
                command = new GenerateFilterCommand(userCommands);
                break;
            case FILTERS_COMMAND:
                if (additionalDescription.length() != 0) {
                    ui.unknownCommand();
                    errorExists = true;
                } else {
                    ui.printFilters();
                }
                break;
            case EXIT_COMMAND:
                if (additionalDescription.length() != 0) {
                    ui.unknownCommand();
                    errorExists = true;
                } else {
                    ui.byeUser();
                    System.exit(0);
                }
                break;
            case HELP_COMMAND:
                if (additionalDescription.length() != 0) {
                    ui.unknownCommand();
                    errorExists = true;
                } else {
                    command = new HelpCommand();
                }
                break;
            case PLANNER_EDITOR_COMMAND:
                if (additionalDescription.length() != 0) {
                    ui.unknownCommand();
                    errorExists = true;
                } else {
                    PlannerCommandHandler.plannerCommandHandler(ui, planner, storage, scanner);
                }
                break;
            case VIEW_PLAN_COMMAND:
                if (additionalDescription.length() != 0) {
                    ui.unknownCommand();
                    errorExists = true;
                } else {
                    ui.showPlan(planner);
                }
                break;
            case IPPT_COMMAND:
                IPPTCmd generateIPPT = new IPPTCmd(Arrays.copyOfRange(userCommands,1,5));
                generateIPPT.addIPPTSession(exerciseGenerator,userCareerData,storage);
                break;
            case QUICK_START_COMMAND:
                if (additionalDescription.length() == 0) {
                    ui.unknownCommand();
                    errorExists = true;
                } else {
                    command = new QuickStartCommand(userCommands, ui, exerciseGenerator, exerciseStateHandler);
                }
                break;
            case START_COMMAND:
                if (additionalDescription.length() != 0) {
                    ui.unknownCommand();
                    errorExists = true;
                } else {
                    exerciseStateHandler.startWorkout();
                    ui.workoutMode();
                }
                break;
            case CURRENT_COMMAND:
            case FINISH_COMMAND:
            case CANCEL_COMMAND:
                System.out.println("No workout session active." +
                                           " Please generate a workout and use the \"start\" command!");
                break;
            case HISTORY_COMMAND:
                if (additionalDescription.length() != 0) {
                    ui.unknownCommand();
                    errorExists = true;
                } else {
                    userCareerData.printAllFinishedWorkoutSessions();
                }
                break;
            case FIND_COMMAND:
                command = new ExerciseSearchCommand(userCommands);
                break;
            case EXERCISE_DATA_COMMAND:
                if (additionalDescription.length() != 0) {
                    ui.unknownCommand();
                    errorExists = true;
                } else {
                    HashMap<String, Integer> userExerciseDataMap = UserExerciseData
                            .addUserExerciseHistory(userCareerData);
                    ui.printUserExerciseHistory(userExerciseDataMap);
                }
                break;
            case ACHIEVEMENTS:
                achievementListHandler.printAchievements();
                break;
            case QUICK_FIND_COMMAND:
                command = new CompletedExerciseSearchCommand(userCommands, userCareerData);
                break;
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
                    //test
                    command.executeCommand(ui, exerciseGenerator);
                    //command.executeCommand(ui, exerciseGenerator);
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

}
