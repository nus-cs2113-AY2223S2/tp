package seedu.commands;


import seedu.commands.Command;
import seedu.ui.Ui;

public class HelpCommand extends Command {
    public HelpCommand(){}

    @Override
    public String execute() {
        return Ui.getHelpMessage();
    }
}
