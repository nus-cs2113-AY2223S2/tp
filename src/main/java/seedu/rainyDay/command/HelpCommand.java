package seedu.rainyDay.command;

public class HelpCommand extends Command {

    @Override
    protected void setupLogger() {

    }

    @Override
    public CommandResult execute() {
        CommandResult result = new CommandResult("Have you tried reading the UG?");
        result.printResult();
        return result;
    }
}
