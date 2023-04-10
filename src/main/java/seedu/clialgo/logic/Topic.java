package seedu.clialgo.logic;

import seedu.clialgo.file.CS2040CFile;
import seedu.clialgo.file.Code;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;
import java.util.Map;


/**
 * The <code>Topic</code> object handles the operations of the CS2040CFiles within a specific topic.
 */
public class Topic {
    private final String topicName;
    private final HashMap<String, CS2040CFile> cs2040cFiles;

    /**
     * Constructor that initializes an empty <code>Topic</code> object.
     *
     * @param topicName The name of the topic.
     */
    public Topic(String topicName) {
        this.topicName = topicName;
        cs2040cFiles = new HashMap<>();
    }

    //@@author heejet
    /**
     * Constructor that initializes a <code>Topic</code> object with CS2040CFiles stored in it.
     *
     * @param topicName The name of the topic.
     * @param cs2040cFiles A HashMap containing the CS2040CFiles stored in the <code>Topic</code> object.
     */
    public Topic(String topicName, HashMap<String, CS2040CFile> cs2040cFiles) {
        this.topicName = topicName;
        this.cs2040cFiles = cs2040cFiles;
    }

    /**
     * Checks if the <code>Topic</code> object has any CS2040CFiles inside.
     *
     * @return Returns true if the topic has no CS2040CFiles inside, false otherwise.
     */
    public boolean isEmpty() {
        return this.cs2040cFiles.isEmpty();
    }
    //@@author

    /**
     * Checks if the <code>Topic</code> object has a specified CS2040CFile inside.
     *
     * @param cs2040CFileName Name of CS2040CFile.
     * @return Returns true if the topic contains the CS2040CFile that is specified by cs2040CFileName, false otherwise.
     */
    public boolean isInsideTopic(String cs2040CFileName) {
        return this.cs2040cFiles.containsKey(cs2040CFileName);
    }

    /**
     * Obtains all the CS2040CFiles inside the <code>Topic</code> object.
     *
     * @return A HashMap of all the cs2040CFiles inside this <code>Topic</code> object.
     */
    public HashMap<String, CS2040CFile> getC2040CFiles() {
        return cs2040cFiles;
    }

    public String getTopicName() {
        return this.topicName;
    }

    //@@author heejet
    /**
     * Gets all CS2040CFiles stored in this specific topic and stores them in an ArrayList.
     *
     * @return An ArrayList containing all the CS2040CFiles stored in this topic.
     */
    public ArrayList<String> getAllCS2040CFilesInTopicToPrint() {
        ArrayList<String> topicCS2040CFiles = new ArrayList<>();
        for (Map.Entry<String, CS2040CFile> entry : cs2040cFiles.entrySet()) {
            String cs2040cFileName = entry.getValue().getName();
            String cs2040cFileNameWithLabel;

            if (cs2040cFiles.get(cs2040cFileName) instanceof Code) {
                cs2040cFileNameWithLabel = String.format("[CODE] " + cs2040cFileName);
            } else {
                cs2040cFileNameWithLabel = String.format("[NOTE] " + cs2040cFileName);
            }

            topicCS2040CFiles.add(cs2040cFileNameWithLabel);
        }
        return topicCS2040CFiles;
    }
    //@@author

    /**
     * Adds a CS2040CFile linked to a specific topic.
     *
     * @param name Name of the CS2040CFile.
     * @param cs2040cFile A <code>CS2040CFile</code> object representing the CS2040CFile.
     */
    public void addCS2040CFile(String name, CS2040CFile cs2040cFile) {
        cs2040cFiles.put(name, cs2040cFile);
    }

    /**
     * Removes a CS2040CFile based on its name.
     *
     * @param name Name of the CS2040CFile.
     */
    public boolean removeCS2040CFile(String name) {
        this.cs2040cFiles.remove(name);
        return true;
    }

    public ArrayList<CS2040CFile> getCS2040CFilesAsArray() {
        return new ArrayList<>(cs2040cFiles.values());
    }

    /**
     * A method that checks for equality of <code>Topic</code> objects.
     *
     * @param otherTopic The other <code>Topic</code> object to be checked against.
     * @return A boolean value to determine whether the <code>Topic</code> objects are equal.
     */
    public boolean equals(Topic otherTopic) {
        boolean isSameTopicName = Objects.equals(this.topicName, otherTopic.topicName);
        boolean isSameCS2040CFile = Objects.equals(this.cs2040cFiles, otherTopic.cs2040cFiles);
        return isSameTopicName && isSameCS2040CFile;
    }
}
