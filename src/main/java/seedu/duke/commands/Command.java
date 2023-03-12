package seedu.duke.commands;

import seedu.duke.Exception.DukeError;
import seedu.duke.exercisegenerator.GenerateExercise;
import seedu.duke.ui.Ui;

/**
 * Handles the various user commands.
 */
public abstract class Command {

    public abstract void executeCommand(Ui ui, GenerateExercise exerciseGenerator) throws DukeError;

}
