package seedu.duke;

import seedu.commands.Command;
import seedu.commands.ExitCommand;
import seedu.parser.Parser;
import seedu.ui.Ui;
import seedu.workout.WorkoutList;


public class Duke {

    private Ui ui;
    private WorkoutList workoutList;
    public static void main(String[] args) {
        new Duke().run();
    }

    private void run() {
        ui = new Ui();
        workoutList = new WorkoutList();
        ui.showGreeting();

        executeCommandUntilExit();
        Ui.showExit();
    }

    private void executeCommandUntilExit() {
        Command command;
        do {
            String userInput = ui.getUserInput();
            command = new Parser().processCommand(userInput);
            command.setData(workoutList);
            command.execute();
        } while (!ExitCommand.isExit(command));
    }
}
