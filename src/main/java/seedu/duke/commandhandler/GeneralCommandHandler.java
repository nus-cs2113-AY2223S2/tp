package seedu.duke.commandhandler;




import seedu.duke.commands.ExerciseSearchCommand;
import seedu.duke.commands.SampleSavingCommand;
import seedu.duke.commands.Command;
import seedu.duke.commands.GenerateFilterCommand;
import seedu.duke.commands.HelpCommand;
import seedu.duke.commands.SampleReadCommand;
import seedu.duke.commands.QuickStartCommand;

import seedu.duke.exceptions.DukeError;
import seedu.duke.exercisegenerator.GenerateExercise;
import seedu.duke.states.ExerciseStateHandler;
import seedu.duke.storage.StorageHandler;
import seedu.duke.ui.Ui;
import seedu.duke.userdata.UserCareerData;
import seedu.duke.userplan.UserPlan;

public class GeneralCommandHandler implements CommandList{

    /**
     * This class handles all user commands when not in an exercise
     *
     * @param userCommands         This refers to the commands given by the user
     * @param ui                   This allows us to output messages
     * @param exerciseGenerator    This takes in filter parameters and outputs a curated exercise list
     * @param userCareerData       This keeps track and allows logging of all user data
     * @param exerciseStateHandler This allows us to start workouts
     */
    public void handleGeneralUserCommands(String[] userCommands, Ui ui, GenerateExercise exerciseGenerator,
                                          UserCareerData userCareerData, ExerciseStateHandler exerciseStateHandler,
                                          StorageHandler storageHandler, UserPlan planner) {
        Command command = null;
        boolean errorExists = false;
        try {

            switch (userCommands[0]) {
            case GENERATE_COMMAND:
                command = new GenerateFilterCommand(userCommands);
                break;
            case FILTERS_COMMAND:
                ui.printFilters();
                break;
            case BYE_COMMAND:
            case EXIT_COMMAND:
                ui.byeUser();
                System.exit(0);
                break;
            case HELP_COMMAND:
                command = new HelpCommand();
                break;
            case READ_SAMPLE_COMMAND:
                command = new SampleReadCommand(userCareerData);
                break;
            case WRITE_SAMPLE_COMMAND:
                // sample data
                command = new SampleSavingCommand(userCareerData,
                        exerciseGenerator.generateRandomSetFrom(
                                exerciseGenerator.generateSetAll(), 3), storageHandler);
                break;
            case "planner":
                PlannerCommandHandler.plannerCommandHandler(ui, planner);
                break;
            case "plan":
                ui.showPlan(planner);
                break;
            case "quick":
                command = new QuickStartCommand(userCommands, ui, exerciseGenerator);
                break;
            case START_COMMAND:
                exerciseStateHandler.startWorkout();
                break;
            case CURRENT_COMMAND:
            case FINISH_COMMAND:
            case CANCEL_COMMAND:
                System.out.println("No workout session active." +
                        " Please generate a workout and use the \"start\" command!");
                break;
            case HISTORY_COMMAND:
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
}
