package seedu.duke.utils.trie;

import java.util.HashMap;
import java.util.ArrayList;

public class TrieNode {
    private HashMap<Character, TrieNode> children;
    private String content;
    private boolean isWord;

    public TrieNode() {
        children = new HashMap<>();
        isWord = false;
    }

    public TrieNode(String content) {
        children = new HashMap<>();
        isWord = false;
        this.content = content;
    }

    public ArrayList<TrieNode> getChildren() {
        ArrayList<TrieNode> results = new ArrayList<>();
        for (TrieNode nodes : children.values()) {
            results.add(nodes);
        }
        return results;
    }

    public void setChildren(HashMap<Character, TrieNode> children) {
        this.children = children;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public boolean isWord() {
        return isWord;
    }

    public void setWord(boolean word) {
        isWord = word;
    }

    public boolean doesChildExist(char key) {
        return children.containsKey(key);
    }

    public void addChild(char key) {
        children.put(key, new TrieNode(this.getContent() + key));
    }

    public TrieNode getChild(char key) {
        return children.get(key);
    }


}
