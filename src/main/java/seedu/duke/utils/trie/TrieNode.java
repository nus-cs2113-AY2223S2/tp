package seedu.duke.utils.trie;

import java.util.HashMap;
import java.util.ArrayList;

public class TrieNode {
    private HashMap<Character, TrieNode> children;
    private String content;
    private boolean isWord;


    public TrieNode(String content) {
        children = new HashMap<>();
        isWord = false;
        this.content = content;
    }

    /**
     * Returns an ArrayList of children for the node.
     *
     * @return An ArrayList of children for the node.
     */

    public ArrayList<TrieNode> getChildren() {
        ArrayList<TrieNode> results = new ArrayList<>();
        for (TrieNode nodes : children.values()) {
            results.add(nodes);
        }
        return results;
    }

    /**
     * Returns the content of the node.
     *
     * @return The content of the node in a string.
     */
    public String getContent() {
        return content;
    }

    /**
     * Returns whether the node is a word.
     *
     * @return A boolean that is true if the node is a word, false otherwise.
     */
    public boolean isWord() {
        return isWord;
    }

    /**
     * Sets the node to a word or not a word.
     *
     * @param isWord Boolean value to set isWord to.
     */
    public void setWord(boolean isWord) {
        this.isWord = isWord;
    }

    /**
     * Checks if a child exists under the node.
     *
     * @param key Child to check if it exists under the node.
     * @return True if the child exists, false otherwise.
     */
    public boolean doesChildExist(char key) {
        return children.containsKey(key);
    }

    /**
     * Adds a child to the node.
     *
     * @param key Child to be added to the node.
     */
    public void addChild(char key) {
        children.put(key, new TrieNode(this.getContent() + key));
    }

    /**
     * Returns a child of the node. Used in conjunction with getChildren() so only valid keys are called.
     *
     * @param key Child to be returned.
     * @return Child to be returned in TrieNode form.
     */
    public TrieNode getChild(char key) {
        return children.get(key);
    }


}
