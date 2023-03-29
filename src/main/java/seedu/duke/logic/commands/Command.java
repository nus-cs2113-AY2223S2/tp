package seedu.duke.logic.commands;

import seedu.duke.commons.exceptions.DukeError;
import seedu.duke.data.exercisegenerator.GenerateExercise;
import seedu.duke.ui.UiManager;

/**
 * Handles the various user commands.
 */
public abstract class Command {

    public abstract void executeCommand (UiManager uiManager, GenerateExercise exerciseGenerator) throws DukeError;

}
