package seedu.clialgo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

/**
 * The <code>TopicManager</code> object handles the
 */
public class TopicManager {

    /** List of valid topics */
    private static final ArrayList<String> TOPIC_NAMES = new ArrayList<>(
            Arrays.asList("SORTING", "LINKED_LIST", "GRAPH_STRUCTURES", "BINARY_HEAP", "HASH_TABLE", "GRAPH_TRAVERSAL",
                    "BINARY_SEARCH_TREE", "SS_SHORTEST_PATH", "UNION_FIND_DS", "MINIMUM_SPANNING_TREE")
    );

    /** General HashSet to check for duplicate names. */
    private HashSet<String> allNotes;

    /** Data Structure to hold all the topics */
    private HashMap<String, Topic> topics;

    private HashSet<String> allNotesOutsideTestMode;
    private HashMap<String, Topic> topicsOutsideTestMode;

    /**
     * Constructor that initializes a <code>TopicManager</code> object that contains a HashMap of all the names of the
     * topics in CLIAlgo as keys, with a corresponding <code>Topic</code> object as value. Also initializes a HashSet
     * that will be used to store the names of all notes that will be added.
     */
    public TopicManager() {
        allNotes = new HashSet<>();
        topics = new HashMap<>();
        for (String topicName : TOPIC_NAMES) {
            topics.put(topicName, new Topic(topicName));
        }
    }

    /**
     * Constructor that initialises a <code>TopicManager</code> object that contains a HashSet of the current names of
     * the notes present as well as a HashMap of all the topics.
     *
     * @param allNotes A HashSet of the names of all the notes present.
     * @param topics A HashMap of all the topics in CLIAlgo.
     */
    public TopicManager(HashSet<String> allNotes, HashMap<String, Topic> topics) {
        this.allNotes = allNotes;
        this.topics = topics;
    }


    /** Checks if there are any notes stored in CLIAlgo. */
    public boolean isEmpty() {
        return allNotes.isEmpty();
    }

    /** Checks if a specified topic has no notes stored in it. */
    public boolean isTopicEmpty(String topic) {
        return topics.get(topic).isEmpty();
    }

    /** Checks if a given note name has been used before. */
    public boolean isRepeatedNote(String noteName) {
        return this.allNotes.contains(noteName);
    }

    /**
     * Checks if the input string is a valid topic.
     *
     * @param topic The input string.
     * @return True if the input string is a valid topic, false otherwise.
     */
    public boolean isValidTopic(String topic) {
        return TOPIC_NAMES.contains(topic);
    }

    /**
     * Obtains all the topics in CLIAlgo.
     *
     * @return A HashMap containing all the topics in CLIAlgo.
     */
    public HashMap<String, Topic> getTopics() {
        return this.topics;
    }

    /**
     * Obtains all the names of the topics in CLIAlgo.
     *
     * @return An ArrayList containing all the names of the topics in CLIAlgo.
     */
    public ArrayList<String> getTopicNames() {
        return TOPIC_NAMES;
    }

    /**
     * Gets all notes stored in CLIAlgo and stores it in an ArrayList.
     *
     * @return An ArrayList containing all the names of the notes stored in CLIAlgo.
     */
    public ArrayList<String> getAllNotes() {
        ArrayList<String> toPrintNotes = new ArrayList<>();
        for (Map.Entry<String, Topic> entry : topics.entrySet()) {
            Topic currentTopic = entry.getValue();
            toPrintNotes.addAll(currentTopic.getAllNotesInTopic());
        }
        return toPrintNotes;
    }

    /**
     * Get a list of all note stored in a specified topic.
     *
     * @param topic The specified topic.
     * @return An ArrayList containing names of all the notes stored in the specified topic.
     */
    public ArrayList<String> getNotesByTopic(String topic) {
        return topics.get(topic).getAllNotesInTopic();
    }

    /**
     * Get a list of all topics stored in CLIAlgo that are grouped by topics.
     *
     * @return An HashMap containing all notes stored in CLIAlgo.
     */
    public HashMap<String, ArrayList<String>> getAllNotesByTopic() {
        HashMap<String, ArrayList<String>> toPrintNotes = new HashMap<>();
        for (Map.Entry<String, Topic> entry : topics.entrySet()) {
            Topic currentTopic = entry.getValue();
            if (currentTopic.isEmpty()) {
                continue;
            }
            toPrintNotes.put(entry.getKey(), currentTopic.getAllNotesInTopic());
            assert toPrintNotes.containsKey(currentTopic.getTopicName());
        }
        return toPrintNotes;
    }

    /**
     * Adds a new note into the specific <code>Topic</code> object
     * while keeping track of the names of all notes added.
     * Name of the note to be added cannot be the same as a previously added note.
     *
     * @param noteName Name of the note file.
     * @param topicName Name of the topic the note is added to.
     * @param note The <code>Note</code> object representing the note file.
     * @return True if note is successfully added and False otherwise.
     */
    public boolean addNote(String noteName, String topicName, Note note) {
        // Check if note name has been taken
        if (allNotes.contains(noteName)) {
            return false;
        }

        // Adds note into topic hashmap
        topics.get(topicName).addNote(noteName, note);

        assert topics.get(topicName).isInsideTopic(noteName);

        // Keep track of name of note added
        allNotes.add(noteName);

        return true;
    }

    /**
     * Removes a note from the specific <code>Topic</code> object while keeping track of the names of all notes
     * remaining.
     *
     * @param noteName Name of the note file.
     * @return Returns true if the name of the note file is inside a any topic, false otherwise
     */
    public boolean removeNote(String noteName) {
        for (String topicName : TOPIC_NAMES) {

            // Check which topic contains that particular note
            if ((topics.get(topicName).isInsideTopic(noteName))) {

                // Remove name of note from the allNotes set to keep track of names
                allNotes.remove(noteName);

                assert !allNotes.contains(noteName);

                // Remove the note from that particular topic
                return topics.get(topicName).removeNote(noteName);
            }
        }
        return false;
    }

    /**
     * Initializes the <code>topics</code> and <code>allNote</code> of this object by taking in input from the
     * <code>FileManager</code> object.
     *
     * @param topics The output obtained from the <code>FileManager</code> by calling <code>decodeAll</code>.
     */
    public void initialize(HashMap<String, Topic> topics) {
        this.topics = topics;
        for (Topic topic: topics.values()) {
            if (topic != null) {
                allNotes.addAll(topic.getAllNotesInTopic());
            }
        }
    }

    /**
     * Resets <code>topics</code> and <code>allNotes</code> when test mode starts. Stores the data outside of test mode
     * separately.
     */
    public void testModeStart() {
        this.topicsOutsideTestMode = topics;
        this.allNotesOutsideTestMode = allNotes;
        allNotes = new HashSet<>();
        topics = new HashMap<>();
        for (String topicName : TOPIC_NAMES) {
            topics.put(topicName, new Topic(topicName));
        }
    }

    /**
     * Retrieves the <code>topics</code> and <code>allNotes</code> data from before the start of test mode when test
     * mode ends such that the state before the start of test mode is restored.
     */
    public void testModeEnd() {
        this.allNotes = allNotesOutsideTestMode;
        this.topics = topicsOutsideTestMode;
    }
}
