package seedu.duke;


import seedu.calorietracker.CalorieTracker;
import seedu.commands.Command;
import seedu.commands.ExitCommand;
import seedu.commands.IncorrectSyntaxCommand;
import seedu.exceptions.InvalidSyntaxException;
import seedu.parser.Parser;
import seedu.ui.Ui;
import seedu.workout.WorkoutList;

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
    }

    private void executeCommandUntilExit() {
        Command command;
        do {
            String userInput = Ui.getUserInput();
            try {
                command = new Parser().processCommand(userInput);
            } catch (InvalidSyntaxException ise) {
                command = new IncorrectSyntaxCommand(ise.toString());
            }
            command.setData(workoutList, calorieTracker);
            System.out.println(command.execute());
        } while (!ExitCommand.isExit(command));
    }
}
