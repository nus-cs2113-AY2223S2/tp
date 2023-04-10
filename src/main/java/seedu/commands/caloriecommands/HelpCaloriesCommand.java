package seedu.commands.caloriecommands;

import seedu.commands.Command;
import seedu.ui.Ui;

public class HelpCaloriesCommand extends Command {

    @Override
    public String execute() {
        return Ui.getCaloriesHelpMessage();
    }

}
