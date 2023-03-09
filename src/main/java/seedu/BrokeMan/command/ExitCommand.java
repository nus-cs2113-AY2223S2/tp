package seedu.BrokeMan.command;

public class ExitCommand extends Command {
    public static final String COMMAND_WORD = "exit";
    public void execute() {
        System.out.println("Exiting program...");
    }
    public static boolean isExit(Command command) {
        return command instanceof ExitCommand;
    }
}
