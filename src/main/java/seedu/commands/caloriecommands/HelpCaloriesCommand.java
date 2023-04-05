package seedu.commands.caloriecommands;

import seedu.ui.Ui;

public class HelpCaloriesCommand extends CaloriesCommand{


        @Override
        public String execute() {
            return Ui.getCaloriesHelpMessage();
        }


}
