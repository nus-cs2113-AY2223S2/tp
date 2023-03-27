package seedu.commands.workoutcommands;

import seedu.commands.Command;
import seedu.ui.Ui;

public class HelpWorkoutCommand extends Command {
    public HelpWorkoutCommand(){}

    public void execute() {
        System.out.println("Here are the list of commands that you can use:");
        Ui.showLine();
        System.out.println("- [Start a Workout: /start])\n" +
                "- [Add exercise: /add]\n" +
                "- [End current workout: /end]\n" +
                "- [Display workout list: /list]\n" +
                "- [Display a workout : /view]\n" +
                "- [Delete a workout: /delete]\n" +
                "- [Exit app: /exit]");
        Ui.showLine();
    }
}
