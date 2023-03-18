package seedu.rainyDay.command;

public class HelpCommand extends Command {

    @Override
    protected void setupLogger() {

    }

    @Override
    public void execute() {
        System.out.println("Have you tried reading the UG?");
    }
}
