package seedu.commands;

public class ExitCommand extends Command {

    public static boolean isExit(Command command) {
        return command instanceof ExitCommand;
    }

    @Override
    public void execute() {
    }
}
