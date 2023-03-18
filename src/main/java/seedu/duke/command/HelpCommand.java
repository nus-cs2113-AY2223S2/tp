package seedu.duke.command;

public class HelpCommand extends Command {

    public void execute() {
        ui.printHelpCommandMessage();
    }
}
