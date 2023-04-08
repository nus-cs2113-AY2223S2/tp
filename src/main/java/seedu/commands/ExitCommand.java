package seedu.commands;


public class ExitCommand extends seedu.commands.Command {
    private static final String EXIT_MESSAGE = "Thank you, hope you had a great workout!!!";

    public static boolean isExit(seedu.commands.Command command) {
        return command instanceof ExitCommand;
    }

    @Override
    public String execute() {
        return EXIT_MESSAGE + InfoMessage.showLinesAfterExecution();
    }
}
