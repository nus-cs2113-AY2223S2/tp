package seedu.duke.logic.commands;

import seedu.duke.data.exercisegenerator.GenerateExercise;
import seedu.duke.ui.Ui;

public class HelpCommand extends Command {
    //edited
    public void executeCommand (Ui ui, GenerateExercise generateExercise) {
        ui.printHelp();
    }

}
