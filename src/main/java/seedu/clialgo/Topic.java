package seedu.clialgo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;
import java.util.Map;

/**
 * The <code>Topic</code> object handles the operations of the notes within a specific topic.
 */
public class Topic {
    private final String topicName;
    private final HashMap<String, Note> notes;

    /**
     * Constructor that initializes an empty <code>Topic</code> object.
     *
     * @param topicName The name of the topic.
     */
    public Topic(String topicName) {
        this.topicName = topicName;
        notes = new HashMap<>();
    }

    /**
     * Constructor that initializes a <code>Topic</code> object with notes stored in it.
     *
     * @param topicName The name of the topic.
     * @param notes A HashMap containing the notes stored in the <code>Topic</code> object.
     */
    public Topic(String topicName, HashMap<String, Note> notes) {
        this.topicName = topicName;
        this.notes = notes;
    }

    /**
     * Checks if the <code>Topic</code> object has any notes inside.
     *
     * @return Returns true if the topic has no notes inside, false otherwise.
     */
    public boolean isEmpty() {
        return this.notes.isEmpty();
    }

    /**
     * Checks if the <code>Topic</code> object has a specified note inside.
     *
     * @param noteName Name of note.
     * @return Returns true if the topic contains the note that is specified by noteName, false otherwise.
     */
    public boolean isInsideTopic(String noteName) {
        return this.notes.containsKey(noteName);
    }

    /**
     * Obtains all the notes inside the <code>Topic</code> object.
     *
     * @return A HashMap of all the notes inside this <code>Topic</code> object.
     */
    public HashMap<String, Note> getNotes() {
        return notes;
    }

    public String getTopicName() {
        return this.topicName;
    }

    /**
     * Gets all notes stored in this specific topic and stores them in an ArrayList.
     *
     * @return An ArrayList containing all the notes stored in this topic.
     */
    public ArrayList<String> getAllNotesInTopic() {
        ArrayList<String> topicNotes = new ArrayList<>();
        for (Map.Entry<String, Note> entry : notes.entrySet()) {
            String noteName = entry.getValue().getName();
            topicNotes.add(noteName);
        }
        return topicNotes;
    }

    /**
     * Adds a note linked to a specific topic.
     *
     * @param name Name of the note file.
     * @param note A <code>Note</code> object representing the note file.
     */
    public void addNote(String name, Note note) {
        notes.put(name, note);
    }

    /**
     * Removes a note based on its name.
     *
     * @param name Name of the note file.
     */
    public boolean removeNote(String name) {
        notes.remove(name);

        return true;
    }

    /**
     * A method that checks for equality of <code>Topic</code> objects.
     *
     * @param otherTopic The other <code>Topic</code> object to be checked against.
     * @return A boolean value to determine whether the <code>Topic</code> objects are equal.
     */
    public boolean equals(Topic otherTopic) {
        return Objects.equals(this.topicName, otherTopic.topicName) &&
                Objects.equals(this.notes, otherTopic.notes);
    }
}
