package seedu.duke.logic.commands;

import seedu.duke.commons.exceptions.DukeError;
import seedu.duke.data.exercisegenerator.GenerateExercise;
import seedu.duke.data.userdata.UserCareerData;
import seedu.duke.ui.Ui;

/**
 * Handles the various user commands.
 */
public abstract class Command {

    //might screw up, please dont screw up
    //public abstract void executeCommand (Ui ui, GenerateExercise exerciseGenerator) throws DukeError;
    public abstract void executeCommand (Ui ui, GenerateExercise exerciseGenerator) throws DukeError;

}
