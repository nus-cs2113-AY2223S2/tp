package seedu.clialgo;

import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class TopicTest {

    /**
     * Checks the <code>equals</code> method of the <code>Topic</code> class.
     * Inputs two equal <code>Topic</code> objects and expects the method to return true.
     */
    @Test
    void equals_checkEqualTopic_expectTrue() {
        String firstTopicName = "LINKED_LIST";
        String secondTopicName = "LINKED_LIST";
        Topic firstTopic = new Topic(firstTopicName);
        Topic secondTopic = new Topic(secondTopicName);
        assertTrue(firstTopic.equals(secondTopic));
    }

    /**
     * Checks the <code>equals</code> method of the <code>Topic</code> class.
     * Inputs two unequal <code>Topic</code> objects and expects the method to return false.
     */
    @Test
    void equals_checkUnequalTopic_expectFalse() {
        String firstTopicName = "LINKED_LIST";
        String secondTopicName = "SORTING";
        Topic firstTopic = new Topic(firstTopicName);
        Topic secondTopic = new Topic(secondTopicName);
        assertFalse(firstTopic.equals(secondTopic));
    }

    /**
     * Checks the <code>addNote</code> method correctly
     * adds a <code>Note</code> object into its Hashmap.
     */
    @Test
    void addNote_checkNoteIsAdded_expectCorrectNotesHashmapAttribute() {
        String testTopicName = "SORTING";
        String testNoteName = "dummyName";

        Note testNote = new Note(testNoteName, testNoteName + ".txt", testTopicName);

        HashMap<String, Note> testNotesHashmap = new HashMap<>();
        testNotesHashmap.put(testNoteName, testNote);

        Topic testTopic = new Topic(testTopicName);
        testTopic.addNote(testNoteName, testNote);

        assertEquals(testTopic.getNotes(), testNotesHashmap);
    }

    @Test
    void isEmpty_topicIsEmpty_expectTrue() {
        Topic topic = new Topic("LINKED_LIST");
        assertTrue(topic.isEmpty());
    }
}
