package seedu.clialgo.logic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

import org.junit.jupiter.api.Test;
import seedu.clialgo.file.CS2040CFile;
import seedu.clialgo.file.Note;
import seedu.clialgo.storage.FileManagerStub;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class TopicManagerTest {
    //@@author nicholas132000
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
        topicManager.addCS2040CFile("bubble sort notes", "SORTING",
                new Note("bubble sort notes", "dummy", "SORTING"));
        assertTrue(topicManager.removeCS2040CFile("bubble sort notes", "SORTING"));
    }

    /**
     * Checks the <code>removeNote</code> method of the <code>TopicManager</code> class.
     * Instantiates a topicManager object.
     * Expects the method to return false since the note does note exist.
     */
    @Test
    void removeNote_checkForNoteThatDoesNotExist_expectFalse() {
        TopicManager topicManager = new TopicManager();
        String noteNameThatDoesNotExist = "dummy";
        assertFalse(topicManager.removeCS2040CFile(noteNameThatDoesNotExist, "SORTING"));
    }

    /**
     * Checks the <code>isRepeatedNote</code> method of the <code>TopicManager</code> class.
     * Inputs a non-existent note name.
     * Expects the method to return false.
     */
    @Test
    void isRepeatedNote_checkForNoteThatDoesNotExist_expectFalse() {
        TopicManager topicManager = new TopicManager();
        String noteName = "thisNoteNameDoesNotExist";
        assertFalse(topicManager.isRepeatedCS2040CFile(noteName));
    }

    /**
     * Checks the <code>isRepeatedNote</code> method of the <code>TopicManager</code> class.
     * Adds a note, and in the next instance, check if the note was used before.
     * Expects the method to return true.
     */
    @Test
    void isRepeatedNote_checkForNoteThatDoesExist_expectTrue() {
        TopicManager topicManager = new TopicManager();
        String noteName = "queue";
        String topicName = "LINKED_LIST";
        Note note = new Note(noteName, "dummy path", topicName);
        topicManager.addCS2040CFile(noteName, topicName, note);
        assertTrue(topicManager.isRepeatedCS2040CFile(noteName));
    }
    //@@author

    /**
     * Checks the <code>addFile</code> method of the <code>TopicManager</code> class.
     * Inputs proper inputs for <code>addFile</code> method and expects the method
     * to return True.
     */
    @Test
    void addFile_checkProperInput_expectTrue() {
        String noteName = "dummyName";
        String topicName = "SORTING";
        Note note = new Note(noteName, noteName + ".txt", topicName);
        assertTrue(new TopicManager().addCS2040CFile(noteName, topicName, note));
    }

    /**
     * Checks the <code>addFile</code> method of the <code>TopicManager</code> class.
     * Inputs repeated name for <code>TopicManager</code> object and expects the method
     * to return False.
     */
    @Test
    void addFile_checkRepeatedNoteName_expectFalse() {
        String noteName = "dummyName";
        String topicName = "SORTING";
        Note note = new Note(noteName, noteName + ".txt", topicName);
        TopicManager topicManager = new TopicManager();
        topicManager.addCS2040CFile(noteName, topicName, note);
        assertFalse(topicManager.addCS2040CFile(noteName, topicName, note));
    }

    //@@author heejet
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
    //@@author

    //@@author nicholas132000
    @Test
    void isEmpty_allNotesIsNotEmpty_expectFalse() {
        TopicManager topicManager = new TopicManager();
        String noteName = "queue";
        String topicName = "LINKED_LIST";
        Note note = new Note(noteName, "dummy path", topicName);
        topicManager.addCS2040CFile(noteName, topicName, note);
        assertFalse(topicManager.isEmpty());
    }

    @Test
    void isEmpty_allNotesIsEmpty_expectTrue() {
        TopicManager topicManager = new TopicManager();
        assertTrue(topicManager.isEmpty());
    }

    @Test
    void isTopicEmpty_givenTopicIsNotEmpty_expectFalse() {
        TopicManager topicManager = new TopicManager();
        String noteName = "queue";
        String topicName = "LINKED_LIST";
        Note note = new Note(noteName, "dummy path", topicName);
        topicManager.addCS2040CFile(noteName, topicName, note);
        assertFalse(topicManager.isTopicEmpty(topicName));
    }

    @Test
    void isTopicEmpty_givenTopicIsEmpty_expectTrue() {
        TopicManager topicManager = new TopicManager();
        String topicName = "LINKED_LIST";
        assertTrue(topicManager.isTopicEmpty(topicName));
    }

    @Test
    void getTopics_useGetTopicsMethodOnTopicManager_expectCLIAlgoTopics() {
        TopicManager topicManager = new TopicManager();
        HashMap<String, Topic> actualTopics = topicManager.getTopics();

        HashMap<String, Topic> expectedTopics = new HashMap<>();
        ArrayList<String> topicNames = new ArrayList<>(
                Arrays.asList("SORTING", "LINKED_LIST", "GRAPH_STRUCTURES", "BINARY_HEAP", "HASH_TABLE",
                        "GRAPH_TRAVERSAL", "BINARY_SEARCH_TREE", "SS_SHORTEST_PATH", "UNION_FIND_DS",
                        "MINIMUM_SPANNING_TREE")
        );
        for (String topicName : topicNames) {
            expectedTopics.put(topicName, new Topic(topicName));
        }

        Set<String> actualTopicsNames = actualTopics.keySet();
        Set<String> expectedTopicsNames = expectedTopics.keySet();

        assertEquals(expectedTopicsNames, actualTopicsNames);
    }

    @Test
    void getTopicNames_useGetTopicNamesOnTopicManager_expectCLIAlgoTopicNames() {
        TopicManager topicManager = new TopicManager();
        ArrayList<String> actualTopicsNames = topicManager.getTopicNames();

        ArrayList<String> expectedTopicsNames = new ArrayList<>(
                Arrays.asList("SORTING", "LINKED_LIST", "GRAPH_STRUCTURES", "BINARY_HEAP", "HASH_TABLE",
                        "GRAPH_TRAVERSAL", "BINARY_SEARCH_TREE", "SS_SHORTEST_PATH", "UNION_FIND_DS",
                        "MINIMUM_SPANNING_TREE")
        );
        assertEquals(expectedTopicsNames, actualTopicsNames);
    }

    @Test
    void getAllNotes_inputNotes_expectedInputNotesToBeReturn() {
        TopicManager topicManager = new TopicManager();

        ArrayList<String> expectedResult = new ArrayList<>();
        for (int index = 0; index < 10; index++) {
            Note note = new Note("dummy" + index, "dummy", "dummy");
            topicManager.addCS2040CFile("dummy" + index, "LINKED_LIST", note);
            expectedResult.add("[NOTE] dummy" + index);
        }
        assertEquals(expectedResult, topicManager.getAllCS2040CFiles());
    }

    @Test
    void getFilesByTopic_inputNotesByTopic_expectCorrectNotesByTopic() {
        TopicManager topicManager = new TopicManager();
        String noteName1 = "queue";
        String topicName1 = "LINKED_LIST";
        Note note1 = new Note(noteName1, "dummy1", topicName1);
        topicManager.addCS2040CFile(noteName1, topicName1, note1);

        String noteName2 = "deque";
        Note note2 = new Note(noteName2, "dummy2", topicName1);
        topicManager.addCS2040CFile(noteName2, topicName1, note2);

        String noteName3 = "bubble sort";
        String topicName2 = "SORTING";
        Note note3 = new Note(noteName3, "dummy3", topicName2);
        topicManager.addCS2040CFile(noteName3, topicName2, note3);

        String noteName4 = "merge sort";
        Note note4 = new Note(noteName4, "dummy4", topicName2);
        topicManager.addCS2040CFile(noteName4, topicName2, note4);

        ArrayList<String> expectedOutcomeForLinkedList = new ArrayList<>();
        expectedOutcomeForLinkedList.add("[NOTE] deque");
        expectedOutcomeForLinkedList.add("[NOTE] queue");
        assertEquals(expectedOutcomeForLinkedList, topicManager.getCS2040CFilesByTopicToPrint("LINKED_LIST"));

        ArrayList<String> expectedOutcomeForSorting = new ArrayList<>();
        expectedOutcomeForSorting.add("[NOTE] bubble sort");
        expectedOutcomeForSorting.add("[NOTE] merge sort");
        assertEquals(expectedOutcomeForSorting, topicManager.getCS2040CFilesByTopicToPrint("SORTING"));
    }

    @Test
    void initialize_initializeTopicManagerTestObjectWithTopics_expectNotesToBeInside() {
        TopicManager topicManager = new TopicManager();

        ArrayList<String> expectedAllNotes = new ArrayList<>();

        String topicName = "LINKED_LIST";

        String noteName1 = "queue";
        Note note1 = new Note(noteName1, "dummy1", topicName);
        topicManager.addCS2040CFile(noteName1, topicName, note1);

        String noteName2 = "stack";
        Note note2 = new Note(noteName2, "dummy2", topicName);
        topicManager.addCS2040CFile(noteName2, topicName, note2);

        expectedAllNotes.add("[NOTE] " + noteName2);
        expectedAllNotes.add("[NOTE] " + noteName1);

        TopicManager topicManagerTest = new TopicManager();
        topicManagerTest.initialize(topicManager.getTopics());

        assertEquals(expectedAllNotes, topicManagerTest.getAllCS2040CFiles());
    }
    //@@author

    //@@author heejet
    @Test
    void getAllCS2040CFilesGroupedByTopicToPrint() {
        TopicManager topicManager = new TopicManager();
        FileManagerStub fileManagerStub = new FileManagerStub();
        topicManager.initialize(fileManagerStub.decodeAll());
        HashMap<String, ArrayList<String>> actualOutput = topicManager.getAllCS2040CFilesGroupedByTopicToPrint();

        for (Map.Entry<String, ArrayList<String>> entry : actualOutput.entrySet()) {
            ArrayList<String> currentCS2040CFiles = entry.getValue();
            for (String cs2040cFileName: currentCS2040CFiles) {
                assertTrue(fileManagerStub.isLabelledFileNamePresent(cs2040cFileName));
            }
        }
    }

    @Test
    void getAllCS2040CFilesBeforeTopic() {
        ArrayList<String> cs2040cFileNames = new ArrayList<>(
                Arrays.asList(
                        "Bubble Sort Note", "BST Note", "Dijkstra Code"
                )
        );
        TopicManager topicManager = new TopicManager();
        FileManagerStub fileManagerStub = new FileManagerStub();
        topicManager.initialize(fileManagerStub.decodeAll());

        for (String cs2040cFileName: cs2040cFileNames) {
            LinkedHashMap<String, ArrayList<String>> actualTopoOrder = new LinkedHashMap<>();
            actualTopoOrder = topicManager.getAllCS2040CFilesBeforeTopic(cs2040cFileName);

            // Check each CS2040CFile in actualTopoOrder against the expected topo order in FileManagerStub
            for (Map.Entry<String, ArrayList<String>> entry : actualTopoOrder.entrySet()) {
                String topicName = entry.getKey();
                ArrayList<String> currentTopicCS2040CFiles = entry.getValue();

                for (String fileName: currentTopicCS2040CFiles) {
                    assertTrue(fileManagerStub.isPartOfTopoOrder(topicName, fileName));
                }
            }
        }
    }

    @Test
    void getAllFilesAsFiles() {
        TopicManager topicManager = new TopicManager();
        FileManagerStub fileManagerStub = new FileManagerStub();
        topicManager.initialize(fileManagerStub.decodeAll());

        ArrayList<CS2040CFile> actualOutput = topicManager.getAllFilesAsFiles();

        for (CS2040CFile file: actualOutput) {
            assertTrue(fileManagerStub.isFileNamePresent(file.getName()));
        }
    }
}
