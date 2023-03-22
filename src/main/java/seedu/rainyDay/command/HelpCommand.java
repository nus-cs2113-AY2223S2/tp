package seedu.rainyDay.command;

public class HelpCommand extends Command {

    @Override
    protected void setupLogger() {

    }

    @Override
    public CommandResult execute() {
        return new CommandResult("Have you tried reading the UG?");
    }
}
