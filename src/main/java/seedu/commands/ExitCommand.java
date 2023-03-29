package seedu.commands;

import seedu.storage.WriteFile;

public class ExitCommand extends Command {
    private static final String EXIT_MESSAGE = "Thank you, hope you had a great workout!!!";
    private static final String FILE_PATH = "data/workoutRecording.txt";

    public static boolean isExit(Command command) {
        return command instanceof ExitCommand;
    }

    @Override
    public String execute() {
        WriteFile.writeWorkoutToFile(FILE_PATH);
        return EXIT_MESSAGE;
    }
}
