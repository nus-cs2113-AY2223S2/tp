package seedu.commands.workoutcommands;


import seedu.commands.Command;
import seedu.ui.Ui;

public class HelpWorkoutCommand extends Command {
    public HelpWorkoutCommand(){}

    @Override
    public String execute() {
        return Ui.getWorkoutHelpMessage();
    }
}
