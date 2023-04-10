package seedu.duke.logic.commands;

import seedu.duke.commons.exceptions.DukeError;
import seedu.duke.data.exercisegenerator.GenerateExercise;
import seedu.duke.logic.commandhandler.states.ExerciseStateHandler;
import seedu.duke.ui.ErrorMessages;
import seedu.duke.ui.Ui;
import seedu.duke.data.userdata.userplan.UserPlan;

import java.util.ArrayList;

//@@author Khulon
public class QuickStartCommand extends Command {
    public QuickStartCommand (String[] userCommands, Ui ui, GenerateExercise exerciseGenerator,
                              ExerciseStateHandler exerciseStateHandler) throws DukeError {
        if (userCommands.length != 3) {
            throw new DukeError(ErrorMessages.ERROR_INVALID_QUICK_START.toString());
        }
        String planName = userCommands[1];
        UserPlan.getPlan();
        ArrayList<String> exercisePlans = UserPlan.getExercisePlan(planName);
        if (exercisePlans == null) {
            throw new DukeError(ErrorMessages.ERROR_INVALID_PLAN.toString());
        } else {
            System.out.println("Generating workout for: \n" + planName);
            System.out.println();
        }

        String[] generateExerciseCommand = new String[2 + exercisePlans.size()];
        generateExerciseCommand[0] = "generate";
        for (int filterNo = 0; filterNo < exercisePlans.size(); filterNo++) {
            generateExerciseCommand[filterNo + 1] = exercisePlans.get(filterNo);
        }
        generateExerciseCommand[generateExerciseCommand.length - 1] = userCommands[2];

        Command command = new GenerateFilterCommand(generateExerciseCommand);
        command.executeCommand(ui, exerciseGenerator);

        exerciseStateHandler
            .storePreviousGeneratedWorkout(((GenerateFilterCommand) command).provideExerciseList());

    }

    @Override
    //test

    public void executeCommand (Ui ui, GenerateExercise exerciseGenerator) throws DukeError {

    }

}
