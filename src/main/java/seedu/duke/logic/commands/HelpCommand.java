package seedu.duke.logic.commands;

import seedu.duke.data.exercisegenerator.GenerateExercise;
import seedu.duke.ui.UiManager;

public class HelpCommand extends Command {
    public void executeCommand (UiManager uiManager, GenerateExercise generateExercise) {
        uiManager.printHelp();
    }

}
