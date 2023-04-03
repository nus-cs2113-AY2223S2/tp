package seedu.duke;


import seedu.calorietracker.CalorieTracker;
import seedu.commands.Command;
import seedu.commands.ExitCommand;
import seedu.commands.IncorrectSyntaxCommand;
import seedu.exceptions.InvalidSyntaxException;
import seedu.parser.Parser;
import seedu.storage.ReadFile;
import seedu.storage.WriteFile;
import seedu.ui.Ui;
import seedu.workout.Day;
import seedu.workout.WorkoutList;


public class Duke {
    public static final String FILE_PATH = "data/workoutRecording.txt";
    private WorkoutList workoutList = new WorkoutList();
    private Day day;
    private CalorieTracker calorieTracker;


    public Duke() {
        //workoutList = ReadFile.readWorkoutFromFile(FILE_PATH);
    }

    public static void main(String[] args) {
        new Duke().run();
    }

    private void run() {
        //  workoutList = ReadFile.readWorkoutFromFile(FILE_PATH);
        //day = new Day();
        workoutList = ReadFile.readWorkoutFromFile(FILE_PATH);
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
        WriteFile.writeWorkoutToFile(Duke.FILE_PATH, workoutList);
    }
}

