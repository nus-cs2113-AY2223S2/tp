package seedu.duke;

import seedu.calorietracker.CalorieTracker;
import seedu.commands.Command;
import seedu.commands.ExitCommand;
import seedu.parser.Parser;
import seedu.ui.Ui;
import seedu.workouttracker.workout.WorkoutList;


public class Duke {
    private WorkoutList workoutList;
    private CalorieTracker calorieTracker;
    public static void main(String[] args) {
        new Duke().run();
    }

    private void run() {
        workoutList = new WorkoutList();
        calorieTracker = new CalorieTracker();
        Ui.showWelcomeMessage();

        executeCommandUntilExit();
        Ui.showExit();
    }

    private void executeCommandUntilExit() {
        Command command;
        do {
            String userInput = Ui.getUserInput();
            command = new Parser().processCommand(userInput);
            command.setData(workoutList, calorieTracker);
            command.execute();
        } while (!ExitCommand.isExit(command));
    }
}
