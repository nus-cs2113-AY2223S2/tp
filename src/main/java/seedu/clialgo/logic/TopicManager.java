package seedu.clialgo.logic;

import seedu.clialgo.file.CS2040CFile;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * The <code>TopicManager</code> object handles the different
 * <code>Topic</code> objects and the CS2040CFiles within them.
 */
public class TopicManager {
    /** List of valid topics */
    private static final ArrayList<String> TOPIC_NAMES = new ArrayList<>(
            Arrays.asList("SORTING", "LINKED_LIST", "GRAPH_STRUCTURES", "BINARY_HEAP", "HASH_TABLE", "GRAPH_TRAVERSAL",
                    "BINARY_SEARCH_TREE", "SS_SHORTEST_PATH", "UNION_FIND_DS", "MINIMUM_SPANNING_TREE")
    );

    /** List of topics in topological order */
    private static final ArrayList<String> TOPO_ORDER = new ArrayList<>(
            Arrays.asList("MINIMUM_SPANNING_TREE", "SS_SHORTEST_PATH", "GRAPH_TRAVERSAL", "GRAPH_STRUCTURES",
                    "BINARY_SEARCH_TREE", "UNION_FIND_DS", "HASH_TABLE", "BINARY_HEAP", "LINKED_LIST", "SORTING")
    );

    /** General Hashmap to check for duplicate names. */
    private HashMap<String, String> allCS2040CFiles;

    /** Data Structure to hold all the topics */
    private HashMap<String, Topic> topics;

    /** A Data Structure to keep track of all the CS240CFile in CLIAlgo and the topic they are tagged to. */
    private HashMap<String, String> allCS2040CFilesOutsideTestMode;

    /** Test mode */
    private HashMap<String, Topic> topicsOutsideTestMode;
    private boolean isTestModeOn;

    /**
     * Constructor that initializes a <code>TopicManager</code> object that contains a HashMap of all the names of the
     * topics in CLIAlgo as keys, with a corresponding <code>Topic</code> object as value. Also initializes a HashSet
     * that will be used to store the names of all CS2040CFiles that will be added.
     */
    public TopicManager() {
        allCS2040CFiles = new HashMap<>();
        topics = new HashMap<>();
        for (String topicName : TOPIC_NAMES) {
            topics.put(topicName, new Topic(topicName));
        }
        isTestModeOn = false;
    }

    /** Checks if test mode is turned on. */
    public boolean getIsTestModeOn() {
        return this.isTestModeOn;
    }

    //@@ author heejet
    /** Checks if there are any CS2040CFiles stored in CLIAlgo. */
    public boolean isEmpty() {
        return allCS2040CFiles.isEmpty();
    }

    /** Checks if a specified topic has no CS2040CFiles stored in it. */
    public boolean isTopicEmpty(String topic) {
        return topics.get(topic).isEmpty();
    }

    /** Checks if a given CS2040CFile name has been used before. */
    public boolean isRepeatedCS2040CFile(String cs2040CFileName) {
        return this.allCS2040CFiles.containsKey(cs2040CFileName.toLowerCase());
    }
    //@@author

    /** Checks if the input string is a valid topic. */
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

    //@@author heejet
    /** returns the topic of the given CS2040CFile */
    public String getTopicOfCS2040CFile(String cs2040cFileName) {
        return this.allCS2040CFiles.get(cs2040cFileName.toLowerCase());
    }

    /**
     * Gets all CS2040CFiles stored in CLIAlgo and stores it in an ArrayList.
     *
     * @return An ArrayList containing all the names of the CS2040CFiles stored in CLIAlgo.
     */
    public ArrayList<String> getAllCS2040CFiles() {
        ArrayList<String> toPrintCS2040CFiles = new ArrayList<>();
        for (Map.Entry<String, Topic> entry : topics.entrySet()) {
            Topic currentTopic = entry.getValue();
            ArrayList<String> currentTopicCS2040CFiles = currentTopic.getAllCS2040CFilesInTopicToPrint();
            toPrintCS2040CFiles.addAll(currentTopicCS2040CFiles);
        }
        return toPrintCS2040CFiles;
    }

    /**
     * Get a list of all CS2040CFiles stored in a specified topic.
     *
     * @param topic The specified topic.
     * @return An ArrayList containing names of all the CS2040CFiles stored in the specified topic.
     */
    public ArrayList<String> getCS2040CFilesByTopicToPrint(String topic) {
        Topic currentTopic = topics.get(topic);
        return currentTopic.getAllCS2040CFilesInTopicToPrint();
    }

    /**
     * Get a list of all topics stored in CLIAlgo that are grouped by topics.
     *
     * @return An HashMap containing all CS2040CFiles stored in CLIAlgo.
     */
    public HashMap<String, ArrayList<String>> getAllCS2040CFilesGroupedByTopicToPrint() {
        HashMap<String, ArrayList<String>> toPrintCS2040CFiles = new HashMap<>();
        for (Map.Entry<String, Topic> entry : topics.entrySet()) {
            Topic currentTopic = entry.getValue();
            if (currentTopic.isEmpty()) {
                continue;
            }
            ArrayList<String> currentTopicFiles = currentTopic.getAllCS2040CFilesInTopicToPrint();
            toPrintCS2040CFiles.put(entry.getKey(), currentTopicFiles);
            assert toPrintCS2040CFiles.containsKey(currentTopic.getTopicName());
        }
        return toPrintCS2040CFiles;
    }

    //@@author ong-ck
    /**
     * Get a list of all topics stored in CLIAlgo that are before a specific target topic.
     *
     * @param cs2040cFileName The name of the CS2040CFile that is part of the target topic.
     * @return A HashMap containing all cs2040cFiles before a specific target topic.
     */
    public LinkedHashMap<String, ArrayList<String>> getAllCS2040CFilesBeforeTopic(String cs2040cFileName) {
        LinkedHashMap<String, ArrayList<String>> toPrintCS2040CFiles = new LinkedHashMap<>();
        boolean isPartOfTopoOrder = false;

        for (String topicName : TOPO_ORDER) {
            // Check which topic contains that particular CS2040CFile
            if ((topics.get(topicName).isInsideTopic(cs2040cFileName))) {
                isPartOfTopoOrder = true;
            }

            // Start tracking subsequent CS2040CFiles when topic of target CS2040CFile is found
            if (isPartOfTopoOrder) {
                ArrayList<String> topicCS2040CFiles = getCS2040CFilesByTopicToPrint(topicName);
                toPrintCS2040CFiles.put(topicName, topicCS2040CFiles);
            }
        }
        return toPrintCS2040CFiles;
    }
    //@@author

    //@@author nicholas132000
    public ArrayList<CS2040CFile> getAllFilesAsFiles() {
        ArrayList<CS2040CFile> files = new ArrayList<>();
        for (Topic topic: topics.values()) {
            files.addAll(topic.getC2040CFiles().values());
        }
        return files;
    }

    public Topic getOneTopic(String topicName) {
        return topics.get(topicName);
    }

    //@@author lohjooh
    /**
     * Initializes the <code>topics</code> and <code>allCS2040CFile</code> of this object by taking in input from the
     * <code>FileManager</code> object.
     *
     * @param topics The output obtained from the <code>FileManager</code> by calling <code>decodeAll</code>.
     */
    public void initialize(HashMap<String, Topic> topics) {
        this.topics = topics;
        for (Map.Entry<String, Topic> entry : topics.entrySet()) {
            Topic topic = entry.getValue();
            if (topic.isEmpty()) {
                continue;
            }
            for (CS2040CFile fileName : topic.getC2040CFiles().values()) {
                allCS2040CFiles.put(fileName.getName().toLowerCase(), topic.getTopicName());
            }
        }
    }

    //@@author ong-ck
    /**
     * Adds a new CS2040CFile into the specific <code>Topic</code> object
     * while keeping track of the names of all CS2040CFiles added.
     * Name of the CS2040CFile to be added cannot be the same as a previously added CS2040CFile.
     *
     * @param cs2040cFileName Name of the CS2040CFile.
     * @param topicName Name of the topic the CS2040CFile is added to.
     * @param cs2040cFile The <code>CS2040CFile</code> object representing the CS2040CFile.
     * @return True if file is successfully added and False otherwise.
     */
    public boolean addCS2040CFile(String cs2040cFileName, String topicName, CS2040CFile cs2040cFile) {
        // Check if CS2040CFile name has been taken
        if (isRepeatedCS2040CFile(cs2040cFileName)) {
            return false;
        }

        // Adds CS2040CFile into topic hashmap
        topics.get(topicName).addCS2040CFile(cs2040cFileName, cs2040cFile);

        assert topics.get(topicName).isInsideTopic(cs2040cFileName);

        // Keep track of name of CS2040CFile added
        String cs2040cFileNameLowerCase = cs2040cFileName.toLowerCase();
        allCS2040CFiles.put(cs2040cFileNameLowerCase, topicName);

        return true;
    }

    //@@author nicholas132000
    /**
     * Removes a CS2040CFile from the specific <code>Topic</code> object while keeping track of the names of all
     * CS2040CFiles remaining.
     *
     * @param cs2040cFileName Name of the CS2040CFile.
     * @return Returns true if the name of the CS2040CFile is inside any topic, false otherwise
     */
    public boolean removeCS2040CFile(String cs2040cFileName, String topicName) {
        if (!isRepeatedCS2040CFile(cs2040cFileName)) {
            return false;
        }

        Topic topic = topics.get(topicName);
        boolean isInsideTopic = topic.isInsideTopic(cs2040cFileName);

        if (!isInsideTopic) {
            return false;
        }

        // Removes CS2040CFile into topic hashmap
        topics.get(topicName).removeCS2040CFile(cs2040cFileName);

        assert !topics.get(topicName).isInsideTopic(cs2040cFileName);

        // Updates hashmap of CS2040CFiles present
        String cs2040cFileNameLowerCase = cs2040cFileName.toLowerCase();
        allCS2040CFiles.remove(cs2040cFileNameLowerCase);
        return true;
    }

    //@@author lohjooh
    /**
     * Resets <code>topics</code> and <code>allCS2040CFiles</code> when test mode starts. Stores the data outside of
     * test mode separately.
     */
    public void testModeStart() {
        this.topicsOutsideTestMode = topics;
        this.allCS2040CFilesOutsideTestMode = allCS2040CFiles;
        allCS2040CFiles = new HashMap<>();
        topics = new HashMap<>();
        for (String topicName : TOPIC_NAMES) {
            topics.put(topicName, new Topic(topicName));
        }
        this.isTestModeOn = true;
    }

    /**
     * Retrieves the <code>topics</code> and <code>allCS2040CFiles</code> data from before the start of test mode when
     * test mode ends such that the state before the start of test mode is restored.
     */
    public void testModeEnd() {
        this.allCS2040CFiles = allCS2040CFilesOutsideTestMode;
        this.topics = topicsOutsideTestMode;
        this.isTestModeOn = false;
    }

}
