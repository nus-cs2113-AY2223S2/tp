package seedu.duke.command;

public class ExitCommand extends Command {
    @Override
    public void execute() {
        setExit(true);
        ui.printExitMessage();
    }
}
