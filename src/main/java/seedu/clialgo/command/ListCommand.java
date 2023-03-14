package seedu.clialgo.command;

import seedu.clialgo.TopicManager;
import seedu.clialgo.Ui;
import seedu.clialgo.storage.FileManager;

import java.util.ArrayList;

public class ListCommand extends Command {
    /**
     * This method prints all the notes stored in CLIAlgo.
     * If there are no notes stored in CLIAlgo, this method informs the user that the topicManager is empty.
     *
     * @param topicManager The <code>TopicManager</code> object which handles all notes stored in CLIAlgo.
     * @param ui The <code>Ui</code> object which handles outputs to the user.
     * @param fileManager The <code>FileManager</code> object responsible for saving information in CLIAlgo.
     */
    @Override
    public void execute (TopicManager topicManager, Ui ui, FileManager fileManager) {
        if (topicManager.isEmpty()) {
            ui.printListFail();
            return;
        }
        ui.printListSuccess();
        ArrayList<String> printedNotes = topicManager.getAllNotes();
        int serialNumber = 1;
        for (String note : printedNotes) {
            System.out.println(serialNumber + ". " + note);
            serialNumber++;
        }
        ui.printDivider();
    }

    @Override
    public boolean equals(Command otherCommand) {
        return otherCommand instanceof ListCommand;
    }
}
