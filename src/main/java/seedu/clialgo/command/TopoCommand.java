package seedu.clialgo.command;

import seedu.clialgo.TopicManager;
import seedu.clialgo.Ui;
import seedu.clialgo.storage.FileManager;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Objects;

/**
 * The <code>TopoCommand</code> object represents the user command to
 * print the topologically sorted notes after a specific target note.
 */
public class TopoCommand extends Command {

    private LinkedHashMap<String, ArrayList<String>> topoSortedNotes;
    private final String name;

    /**
     * Constructor for command to print notes in a topological manner.
     *
     * @param name Name of the note file.
     */
    public TopoCommand(String name) {
        this.topoSortedNotes = new LinkedHashMap<>();
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    /**
     * Prints all notes of a single specific topic.
     *
     * @param topicName The topic by which the notes are to be printed from.
     */
    private void printSingleTopic(String topicName) {
        ArrayList<String> notes = topoSortedNotes.get(topicName);
        int serialNumber = 1;
        System.out.println("[" + topicName + "]");
        for (String note : notes) {
            System.out.println(serialNumber + ". " + note);
            serialNumber++;
        }
    }

    /**
     * Prints all notes after a specific target note in a topological manner.
     *
     * @param topicManager The <code>TopicManager</code> object which handles all notes stored in CLIAlgo.
     * @param ui The <code>Ui</code> object which handles outputs to the user.
     */
    public void printTopoSortedNotes(TopicManager topicManager, Ui ui) {
        topoSortedNotes = topicManager.getAllNotesBeforeTopic(name);
        ui.printTopoSortSuccess();
        for (String topicName : topoSortedNotes.keySet()) {
            if (topoSortedNotes.get(topicName).isEmpty()) {
                continue;
            }
            printSingleTopic(topicName);
        }
        ui.printDivider();
    }

    /**
     * An overridden method to execute the user command to print the
     * topologically sorted notes after a specific target note.
     *
     * @param topicManager The <code>TopicManager</code> object which handles all notes stored in CLIAlgo.
     * @param ui The <code>Ui</code> object which handles outputs to the user.
     * @param fileManager The <code>FileManager</code> object responsible for saving information in CLIAlgo.
     */
    @Override
    public void execute(TopicManager topicManager, Ui ui, FileManager fileManager) {
        if (topicManager.isEmpty()) {
            ui.printNoNotesSaved();
            return;
        }

        // Check if noteName is valid
        if (!topicManager.isRepeatedNote(name)) {
            ui.printDivider();
            System.out.println("You do not have this note!");
            ui.printDivider();
            return;
        }

        printTopoSortedNotes(topicManager, ui);
    }

    /**
     * An overridden method that checks for equality of <code>TopoCommand</code> objects.
     *
     * @param otherCommand The other <code>TopoCommand</code> object to be checked against.
     * @return A boolean value to determine whether the <code>TopoCommand</code> objects are equal.
     */
    @Override
    public boolean equals(Command otherCommand) {
        TopoCommand otherTopoCommand = (TopoCommand) otherCommand;
        LinkedHashMap<String, ArrayList<String>> otherTopoSortedNotes = otherTopoCommand.topoSortedNotes;

        // Check name attribute is equal
        if (!Objects.equals(otherTopoCommand.name, this.name)) {
            return false;
        }

        // Check that all topics in otherTopoCommand are also present
        for (String topic : otherTopoSortedNotes.keySet()) {
            if (!this.topoSortedNotes.containsKey(topic)) {
                return false;
            }

            // Check that all notes in specific topic of otherTopoCommand are also present
            for (String note : otherTopoSortedNotes.get(topic)) {
                if (!this.topoSortedNotes.get(topic).contains(note)) {
                    return false;
                }

            }

            // Check that number of notes in topic is equal -> topic is equal
            if (otherTopoSortedNotes.get(topic).size() == this.topoSortedNotes.get(topic).size()) {
                return false;
            }
        }

        // Check that number of topics are equal -> topoSortedNotes is equal
        if (otherTopoSortedNotes.size() == this.topoSortedNotes.size()) {
            return false;
        }

        return true;
    }
}
