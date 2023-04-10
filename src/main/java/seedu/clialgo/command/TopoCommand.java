package seedu.clialgo.command;

import seedu.clialgo.Buffer;
import seedu.clialgo.logic.Topic;
import seedu.clialgo.logic.TopicManager;
import seedu.clialgo.Ui;
import seedu.clialgo.file.CS2040CFile;
import seedu.clialgo.storage.FileManager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

//@@author ong-ck
/**
 * The <code>TopoCommand</code> object represents the user command to
 * print the topologically sorted CS2040CFiles after a specific target CS2040CFile.
 */
public class TopoCommand extends Command {
    /** Store the names of CS2040CFiles in topological order. */
    private LinkedHashMap<String, ArrayList<String>> topoSortedCS2040CFiles;

    /** Name of the input CS2040CFile. */
    private final String name;

    /**
     * Constructor for command to print CS2040CFiles in a topological manner.
     *
     * @param name Name of the CS2040CFile.
     */
    public TopoCommand(String name) {
        this.topoSortedCS2040CFiles = new LinkedHashMap<>();
        this.name = name;
    }

    /**
     * Constructor that initializes a <code>TopoCommand</code> object with topoSortedCS2040CFiles stored in it.
     *
     * @param name Name of the CS2040CFile.
     * @param topoSortedCS2040CFiles A LinkedHashMap of the topologically sorted CS2040CFiles.
     */
    public TopoCommand(String name, LinkedHashMap<String, ArrayList<String>> topoSortedCS2040CFiles) {
        this.topoSortedCS2040CFiles = topoSortedCS2040CFiles;
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    /**
     * Prints all CS2040CFiles after a specific target CS2040CFile in a topological manner.
     *
     * @param topicManager The <code>TopicManager</code> object which handles all CS2040CFiles stored in CLIAlgo.
     * @param ui The <code>Ui</code> object which handles outputs to the user.
     */
    private ArrayList<CS2040CFile> printTopoSortedCS2040CFiles(TopicManager topicManager, Ui ui) {
        topoSortedCS2040CFiles = topicManager.getAllCS2040CFilesBeforeTopic(name);

        if (topoSortedCS2040CFiles.isEmpty()) {
            ui.printFileDoesNotExist();
            return new ArrayList<>();
        }

        ui.printTopoSortSuccess();
        ArrayList<CS2040CFile> files = new ArrayList<>();
        int currentSerialNumber = 1;
        for (Map.Entry<String, ArrayList<String>> entry : topoSortedCS2040CFiles.entrySet()) {
            String topicName = entry.getKey();
            ArrayList<String> listOfFiles = entry.getValue();

            if (listOfFiles.isEmpty()) {
                continue;
            }

            ui.printWithBox(topicName);

            currentSerialNumber = ui.printListOfCS2040CFilesWithGivenIndex(listOfFiles, currentSerialNumber);

            HashMap<String, Topic> topics = topicManager.getTopics();
            Topic currentTopic = topics.get(topicName);
            ArrayList<CS2040CFile> cs2040cFiles = currentTopic.getCS2040CFilesAsArray();
            files.addAll(cs2040cFiles);
        }
        ui.printDivider();
        return files;
    }

    /**
     * An overridden method to execute the user command to print the
     * topologically sorted CS2040CFiles after a specific target CS2040CFile.
     *
     * @param topicManager The <code>TopicManager</code> object which handles all CS2040CFiles stored in CLIAlgo.
     * @param ui The <code>Ui</code> object which handles outputs to the user.
     * @param fileManager The <code>FileManager</code> object responsible for saving information in CLIAlgo.
     * @param buffer The <code>Buffer</code> object responsible for exporting filtered files.
     */
    @Override
    public void execute(TopicManager topicManager, Ui ui, FileManager fileManager, Buffer buffer) {
        if (topicManager.isEmpty()) {
            ArrayList<CS2040CFile> emptyBuffer = new ArrayList<>();
            buffer.updateBuffer(emptyBuffer);
            ui.printNoCS2040CFilesSaved();
            return;
        }

        // Check if cs2040cFileName is valid
        if (!topicManager.isRepeatedCS2040CFile(name)) {
            ui.printFileDoesNotExist();
            return;
        }

        ArrayList<CS2040CFile> files = printTopoSortedCS2040CFiles(topicManager, ui);
        buffer.updateBuffer(files);
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
        LinkedHashMap<String, ArrayList<String>> otherTopoSortedCS2040CFiles = otherTopoCommand.topoSortedCS2040CFiles;

        // Check name attribute is equal
        if (!Objects.equals(otherTopoCommand.name, this.name)) {
            return false;
        }

        // Check that all topics in otherTopoCommand are also present
        for (String topic : otherTopoSortedCS2040CFiles.keySet()) {
            if (!this.topoSortedCS2040CFiles.containsKey(topic)) {
                return false;
            }

            // Check that all CS2040CFiles in specific topic of otherTopoCommand are also present
            for (String cs2040cFile : otherTopoSortedCS2040CFiles.get(topic)) {
                if (!this.topoSortedCS2040CFiles.get(topic).contains(cs2040cFile)) {
                    return false;
                }

            }

            // Check that number of CS2040CFiles in topic is equal -> topic is equal
            if (otherTopoSortedCS2040CFiles.get(topic).size() != this.topoSortedCS2040CFiles.get(topic).size()) {
                return false;
            }
        }

        // Check that number of topics are equal -> topoSortedCS2040CFiles is equal
        return otherTopoSortedCS2040CFiles.size() == this.topoSortedCS2040CFiles.size();
    }
}
