package seedu.bigpp.command.commoncommand;

import seedu.bigpp.command.Command;
import seedu.bigpp.datastorage.DataStorage;
import seedu.bigpp.ui.UI;

public class ByeCommand extends Command {

    /*
     * bye command to exit the program
     * @return the message displaying the exit message
     */
    @Override
    public String executeCommand(DataStorage dataStorage) {
        UI.setPCViewerMode();
        dataStorage.saveUserPcs();
        return "Bye. Hope to see you again soon!";
    }
}
