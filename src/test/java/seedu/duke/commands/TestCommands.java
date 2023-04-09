package seedu.duke.commands;

import org.junit.jupiter.api.Test;
import seedu.duke.commons.exceptions.DukeError;
import seedu.duke.data.exercisegenerator.exersisedata.ExerciseData;
import seedu.duke.data.userdata.Session;
import seedu.duke.data.userdata.UserCareerData;
import seedu.duke.logic.commands.CompletedExerciseSearchCommand;
import seedu.duke.logic.commands.GenerateFilterCommand;
import seedu.duke.logic.commands.ExerciseSearchCommand;
import seedu.duke.data.exercisegenerator.GenerateExercise;
import seedu.duke.data.userdata.userplan.UserPlan;
import seedu.duke.ui.Ui;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

/**
 * This class test the handling of commands and checks if commands throw appropriate errors.
 */
public class TestCommands {
    /**
     * Tests the GenerateFilterCommand and checks if command have a valid argument to number of exercises in the user
     * input. If number of exercise is not present in user input, an exception should be thrown, none otherwise.
     */
    @Test
    public void testUserFilterCommand () {

        String[] invalidCommands = {"this is an invalid command", "this is another invalid command"};
        assertThrows(DukeError.class, () -> {
            new GenerateFilterCommand(invalidCommands);
        });
        String[] validCommands = {"body", "3"};
        assertDoesNotThrow(() -> {
            new GenerateFilterCommand(validCommands);
        });
    }

    /**
     * Tests the executeCommand of GenerateFilterCommand and checks if command can execute and throw the appropriate
     * error such as when an event that the user inputs an invalid filter argument.
     */
    @Test
    public void testExecuteFilterCommand () {
        String[] invalidCommands = {"An", "invalid", "command", "3"};
        Ui ui = new Ui();
        GenerateExercise generateExercise = new GenerateExercise();
        assertThrows(DukeError.class, () -> {
            GenerateFilterCommand generateFilterCommand = new GenerateFilterCommand(invalidCommands);
            generateFilterCommand.executeCommand(ui, generateExercise);
        });
        String[] validCommands = {"easy", "upper", "3"};
        assertDoesNotThrow(() -> {
            GenerateFilterCommand generateFilterCommand = new GenerateFilterCommand(validCommands);
            generateFilterCommand.executeCommand(ui, generateExercise);
        });
    }

    //@@author Khulon
    @Test
    public void testAddPlanCommand () {
        String[] invalidCommands = {"add", "invalid", "command",};
        new UserPlan();
        assertThrows(DukeError.class, () -> {
            UserPlan.addPlan(invalidCommands);
        });
        String[] validCommands = {"add", "monday", "home", "static"};
        assertDoesNotThrow(() -> {
            UserPlan.addPlan(validCommands);
        });
    }

    //@@author Khulon
    @Test
    public void testDeletePlanCommand () throws DukeError {
        new UserPlan();
        String[] dummyCommand = {"add", "monday", "home", "static"};
        UserPlan.addPlan(dummyCommand);

        String[] invalidCommands = {"delete", "invalid", "command",};
        assertThrows(DukeError.class, () -> {
            UserPlan.deletePlan(invalidCommands);
        });
        String[] validCommands = {"delete", "monday", "home"};
        assertDoesNotThrow(() -> {
            UserPlan.deletePlan(validCommands);
        });
    }

    //@@author ghzr0
    @Test
    public void testFindCommand () {
        String[] invalidCmd = {"Please key in a keyword for Fitness Duke to search!"};
        String[] testInputs1 = {"find"};
        String[] testInputs2 = {"find", "legs"};
        GenerateExercise generateExercise = new GenerateExercise();
        ArrayList<ExerciseData> exerciseData = generateExercise.generateSetAll();
        assertThrows(DukeError.class, () -> {
            new ExerciseSearchCommand(testInputs1);
        });
        assertDoesNotThrow(() -> {
            new ExerciseSearchCommand(testInputs2);
        });

    }

    //@@author L-K-Chng
    @Test
    public void testFindCompletedExerciseCommand() {
        String[] testInputs1 = {"quickfind"};
        String[] testInputs2 = {"quickfind", "3/4"};

        ArrayList<ExerciseData> exerciseList = new ArrayList<>();
        GenerateExercise generateExercise = new GenerateExercise();
        ArrayList<ExerciseData> exerciseData = generateExercise.generateSetAll();
        exerciseList.add(exerciseData.get(0));
        UserCareerData userCareerData = new UserCareerData();
        Session session = new Session(exerciseList);
        userCareerData.addWorkoutSession(session);

        assertThrows(DukeError.class, () -> {
            new CompletedExerciseSearchCommand(testInputs1, userCareerData);
        });
        assertDoesNotThrow(() -> {
            new CompletedExerciseSearchCommand(testInputs2, userCareerData);
        });

    }

}
