package seedu.clialgo.storage;

import seedu.clialgo.logic.Topic;
import seedu.clialgo.file.CS2040CFile;
import seedu.clialgo.file.Code;
import seedu.clialgo.file.Note;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

//@@author heejet
public class FileManagerStub {
    private final HashMap<String, Topic> topics;
    private final HashSet<String> allLabelledCS2040CFileNames;

    private final HashSet<String> allCS2040CFileNames;

    private final HashMap<String, HashSet<String>> topoOrderCS2040CFiles;

    public FileManagerStub() {
        // Add CS2040CFiles to some topics
        HashMap<String, CS2040CFile> sortingCS2040CFiles = new HashMap<>(
                Map.of(
                        "Bubble Sort Note",
                        new Note("Bubble Sort Note", "Bubble Sort Note.txt", "SORTING", 5),
                        "Merge Sort Code",
                        new Code("Merge Sort Code", "Merge Sort Code.cpp", "SORTING", 8),
                        "Quick Sort Note",
                        new Note("Quick Sort Note", "Quick Sort Note.txt", "SORTING", 3)
                )
        );
        Topic sortingTopic = new Topic("SORTING", sortingCS2040CFiles);

        HashMap<String, CS2040CFile> binaryTreeCS2040CFiles = new HashMap<>(
                Map.of(
                        "BST Note",
                        new Note("BST Note", "BST Note.txt", "BINARY_SEARCH_TREE", 5),
                        "AVL Code",
                        new Code("AVL Code", "AVL Code.cpp", "BINARY_SEARCH_TREE", 8)
                )
        );
        Topic binaryTreeTopic = new Topic("BINARY_SEARCH_TREE", binaryTreeCS2040CFiles);

        HashMap<String, CS2040CFile> shortestPathCS2040CFiles = new HashMap<>(
                Map.of(
                        "Bellman Ford Note",
                        new Note("Bellman Ford Note", "Bellman Ford Note.txt", "SS_SHORTEST_PATH",
                                5),
                        "Dijkstra Code",
                        new Code("Dijkstra Code", "Dijkstra Code.cpp", "SS_SHORTEST_PATH", 8)
                )
        );
        Topic shortestPathTopic = new Topic("SS_SHORTEST_PATH", shortestPathCS2040CFiles);

        this.topics = new HashMap<>();
        this.topics.put("SORTING", sortingTopic);
        this.topics.put("BINARY_SEARCH_TREE", binaryTreeTopic);
        this.topics.put("SS_SHORTEST_PATH", shortestPathTopic);

        // Initialize empty Topics
        ArrayList<String> emptyTopicNames = new ArrayList<>(
                Arrays.asList("LINKED_LIST", "GRAPH_STRUCTURES", "HASH_TABLE", "GRAPH_TRAVERSAL", "BINARY_HEAP",
                        "UNION_FIND_DS", "MINIMUM_SPANNING_TREE")
        );
        for (String emptyTopicName: emptyTopicNames) {
            this.topics.put(emptyTopicName, new Topic(emptyTopicName));
        }

        this.allLabelledCS2040CFileNames = new HashSet<>(
                Arrays.asList("[NOTE] Bubble Sort Note", "[CODE] Merge Sort Code", "[NOTE] Quick Sort Note",
                        "[NOTE] BST Note", "[CODE] AVL Code", "[NOTE] Bellman Ford Note", "[CODE] Dijkstra Code")
        );

        this.allCS2040CFileNames = new HashSet<>(
                Arrays.asList("Bubble Sort Note", "Merge Sort Code", "Quick Sort Note", "BST Note", "AVL Code",
                        "Bellman Ford Note", "Dijkstra Code")
        );

        topoOrderCS2040CFiles = new HashMap<>();
        topoOrderCS2040CFiles.put("SORTING", new HashSet<>(
                Arrays.asList("[NOTE] Bubble Sort Note", "[CODE] Merge Sort Code", "[NOTE] Quick Sort Note")
        ));

        topoOrderCS2040CFiles.put("BINARY_SEARCH_TREE", new HashSet<>(
                Arrays.asList("[NOTE] Bubble Sort Note", "[CODE] Merge Sort Code", "[NOTE] Quick Sort Note",
                        "[NOTE] BST Note", "[CODE] AVL Code")
        ));

        topoOrderCS2040CFiles.put("SS_SHORTEST_PATH", new HashSet<>(
                Arrays.asList("[NOTE] Bubble Sort Note", "[CODE] Merge Sort Code", "[NOTE] Quick Sort Note",
                        "[NOTE] BST Note", "[CODE] AVL Code", "[NOTE] Bellman Ford Note", "[CODE] Dijkstra Code")
        ));
    }

    public HashMap<String, Topic> decodeAll() {
        return this.topics;
    }

    public boolean isLabelledFileNamePresent(String labelledCS2040CFileName) {
        return allLabelledCS2040CFileNames.contains(labelledCS2040CFileName);
    }

    public boolean isFileNamePresent(String cs2040cFileName) {
        return allCS2040CFileNames.contains(cs2040cFileName);
    }

    public boolean isPartOfTopoOrder(String topicName, String cs2040cFileName) {
        return topoOrderCS2040CFiles.get(topicName).contains(cs2040cFileName);
    }
}
