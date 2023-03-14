package seedu.clialgo.command;

import java.util.Objects;

import seedu.clialgo.TopicManager;
import seedu.clialgo.storage.FileManager;
import seedu.clialgo.Ui;


/**
 * Represents an executable command from the user. A <code>RemoveCommand</code> object contains the name of
 * the note to be deleted. This object removes the note corresponding to the name of the note from the list when the
 * execute method is called.
 */
public class RemoveCommand extends Command {

    private final String name;

    /**
     * Constructor for command to remove note from topic list.
     *
     * @param name Name of the note file.
     */
    public RemoveCommand(String name) {
        this.name = name;
    }

    /**
     * Overridden method that removes a note corresponding to <code>name</code> from the list. It then saves the
     * updated list.
     *
     * @param topicManager A Topic object containing all the different topics available.
     * @param ui A Ui object which handles outputs to the user.
     * @param fileManager An object responsible for saving information.
     */
    @Override
    public void execute(TopicManager topicManager, Ui ui, FileManager fileManager) {
        if (topicManager.isEmpty()) {
            ui.printRemoveFail();
            return;
        }

        boolean isSuccessfullyRemoved = topicManager.removeNote(name);

        if (!isSuccessfullyRemoved) {
            ui.printRemoveFail();
            return;
        }

        fileManager.deleteEntry(name);
        ui.printRemoveSuccess(name);
    }

    /**
     * Gets the name of the note to be removed.
     *
     * @return Name of the note.
     */
    public String getName() {
        return name;
    }

    /**
     * An overridden method that checks for equality of <code>RemoveCommand</code> objects.
     *
     * @param otherCommand The other <code>RemoveCommand</code> object to be checked against.
     * @return A boolean value to determine whether the <code>RemoveCommand</code> objects are equal.
     */
    @Override
    public boolean equals(Command otherCommand) {
        RemoveCommand otherRemoveCommand = (RemoveCommand) otherCommand;
        return Objects.equals(this.name, otherRemoveCommand.name);
    }
}
