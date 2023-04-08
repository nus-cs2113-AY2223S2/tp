package seedu;

import seedu.calorietracker.CalorieTracker;
import seedu.calorietracker.FoodList;
import seedu.commands.Command;
import seedu.commands.ExitCommand;
import seedu.exceptions.InvalidArgumentException;
import seedu.exceptions.InvalidSyntaxException;
import seedu.parser.Parser;
import seedu.storage.Storage;
import seedu.ui.Ui;
import seedu.workout.WorkoutList;

//@@author calebcjl
/**
 * Main entry to the program
 */
public class Duke {
    private WorkoutList workoutList;
    private CalorieTracker calorieTracker;
    private FoodList foodList;
    private Storage storage;


    public Duke(){
    }

    public static void main(String[] args) {
        new Duke().run();
    }

    /**
     * Runs the program.
     */
    private void run() {
        storage = new Storage();
        workoutList = new WorkoutList(storage);
        foodList = new FoodList(storage);
        calorieTracker = new CalorieTracker(storage, foodList);
        Ui.showWelcomeMessage();
        executeCommandUntilExit();
    }

    //@@author calebcjl
    /**
     * Continuously receives and execute commands entered by user.
     * Stops when user enters exit command.
     */
    private void executeCommandUntilExit() {
        Command command;
        do {
            String userInput = Ui.getUserInput();
            try {
                command = Parser.processCommand(userInput);
                command.setData(workoutList, calorieTracker, foodList);
                Ui.showCommandResult(command);
            } catch (InvalidSyntaxException | InvalidArgumentException e) {
                Ui.showErrorMessage(e.toString());
                command = new Command();
            }
            storage.saveUserData(workoutList, foodList, calorieTracker);
        } while (!ExitCommand.isExit(command));
    }
}

