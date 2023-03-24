package seedu.duke.logic.commands;

import seedu.duke.model.exercisegenerator.GenerateExercise;
import seedu.duke.ui.Ui;

public class HelpCommand extends Command {
    public void executeCommand (Ui ui, GenerateExercise generateExercise) {
        ui.printHelp();
    }

}
