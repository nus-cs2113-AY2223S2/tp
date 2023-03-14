package seedu.clialgo;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class TopicManagerTest {
    /**
     * Checks the <code>removeNote</code> method of the <code>TopicManager</code> class.
     * Instantiates a topicManager object which contains a topic object which contains a test note.
     * Expects the method to return true if the test note is removed successfully.
     */
    @Test
    void removeNote_checkForNoteThatExist_expectTrue() {
        TopicManager topicManager = new TopicManager();
        Topic topic = new Topic("SORTING");
        topicManager.getTopics().put("SORTING", topic);
        topic.getNotes().put("bubble sort notes", new Note("dummy", "dummy", "SORTING"));
        assertTrue(topicManager.removeNote("bubble sort notes"));
    }

    /**
     * Checks the <code>noteExist</code> method of the <code>TopicManager</code> class.
     * Inputs a non-existent note name.
     * Expects the method to return false.
     */
    @Test
    void noteExist_checkForNoteThatDoesNotExist_expectFalse() {
        String noteName = "thisNoteNameDoesNotExist";
        assertFalse(new TopicManager().isRepeatedNote(noteName));
    }

    /**
     * Checks the <code>addNote</code> method of the <code>TopicManager</code> class.
     * Inputs proper inputs for <code>addNote</code> method and expects the method
     * to return True.
     */
    @Test
    void addNote_checkProperInput_expectTrue() {
        String noteName = "dummyName";
        String topicName = "SORTING";
        Note note = new Note(noteName, noteName + ".txt", topicName);
        assertTrue(new TopicManager().addNote(noteName, topicName, note));
    }

    /**
     * Checks the <code>addNote</code> method of the <code>TopicManager</code> class.
     * Inputs repeated name for <code>TopicManager</code> object and expects the method
     * to return False.
     */
    @Test
    void addNote_checkRepeatedNoteName_expectFalse() {
        String noteName = "dummyName";
        String topicName = "SORTING";
        Note note = new Note(noteName, noteName + ".txt", topicName);
        TopicManager topicManager = new TopicManager();
        topicManager.addNote(noteName, topicName, note);
        assertFalse(topicManager.addNote(noteName, topicName, note));
    }

    @Test
    void isValidTopic_validTopicInput_expectTrue() {
        ArrayList<String> validTopics = new ArrayList<>(
                Arrays.asList("SORTING", "LINKED_LIST", "GRAPH_STRUCTURES", "BINARY_HEAP", "HASH_TABLE",
                        "GRAPH_TRAVERSAL", "BINARY_SEARCH_TREE", "SS_SHORTEST_PATH", "UNION_FIND_DS",
                        "MINIMUM_SPANNING_TREE")
        );
        TopicManager topicManager = new TopicManager();
        for (String topic : validTopics) {
            assertTrue(topicManager.isValidTopic(topic));
        }
    }

    @Test
    void isValidTopic_invalidTopicInput_expectFalse() {
        TopicManager topicManager = new TopicManager();
        ArrayList<String> lowerCaseTopics = new ArrayList<>(
                Arrays.asList("sorting", "linked_list", "graph_structures", "binary_heap", "hash_table",
                        "graph_traversal", "binary_search_tree", "ss_shortest_path", "union_find_ds",
                        "minimum_spanning_tree")
        );
        for (String topic : lowerCaseTopics) {
            assertFalse(topicManager.isValidTopic(topic));
        }

        ArrayList<String> capitalizedCaseTopics = new ArrayList<>(
                Arrays.asList("Sorting", "Linked_list", "Graph_structures", "Binary_heap", "Hash_table",
                        "Graph_traversal", "Binary_search_tree", "Ss_shortest_path", "Union_find_ds",
                        "Minimum_spanning_tree")
        );
        for (String topic : capitalizedCaseTopics) {
            assertFalse(topicManager.isValidTopic(topic));
        }

        ArrayList<String> noUnderScoreTopics = new ArrayList<>(
                Arrays.asList("LINKED LIST", "GRAPH STRUCTURES", "BINARY HEAP", "HASH TABLE", "GRAPH TRAVERSAL",
                        "BINARY SEARCH TREE", "SS SHORTEST PATH", "UNION FIND DS", "MINIMUM SPANNING TREE")
        );
        for (String topic : noUnderScoreTopics) {
            assertFalse(topicManager.isValidTopic(topic));
        }
    }
}
