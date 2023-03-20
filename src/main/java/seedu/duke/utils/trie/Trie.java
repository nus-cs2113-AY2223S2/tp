package seedu.duke.utils.trie;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Queue;
import java.util.LinkedList;
import java.util.Collections;

public class Trie {
    private TrieNode root = new TrieNode("");

    public void add(String word) {
        word = word.toLowerCase().trim();
        TrieNode currentNode = root;
        for (char character : word.toCharArray()) {
            if (!currentNode.doesChildExist(character)) {
                currentNode.addChild(character);
            }
            currentNode = currentNode.getChild(character);
        }
        currentNode.setWord(true);
    }

    public void remove(String word) {
        word = word.toLowerCase().trim();
        TrieNode currentNode = root;
        for (char character : word.toCharArray()) {
            if (!currentNode.doesChildExist(character)) {
                return;
            }
            currentNode = currentNode.getChild(character);
        }
        currentNode.setWord(false);
    }

    public boolean find(String word) {
        word = word.toLowerCase().trim();
        TrieNode currentNode = root;
        for (int i = 0; i < word.length(); i++) {
            char character = word.charAt(i);
            if (!currentNode.doesChildExist(character)) {
                return false;
            }
            currentNode = currentNode.getChild(character);
        }
        return currentNode.isWord();
    }

    private ArrayList<String> prefixBFS(TrieNode node, HashSet<TrieNode> visited) {
        ArrayList<String> results = new ArrayList<>();
        Queue<TrieNode> queue = new LinkedList<>();
        queue.add(node);
        while (!queue.isEmpty()) {
            TrieNode u = queue.peek();
            for (TrieNode v : u.getChildren()) {
                if (visited.contains(v)) {
                    continue;
                }
                queue.add(v);
            }
            visited.add(u);
            if (u.isWord()) {
                results.add(u.getContent());
            }
            queue.remove();
        }
        return results;
    }

    public ArrayList<String> prefixFind(String word) {
        word = word.toLowerCase().trim();
        TrieNode currentNode = root;
        HashSet<TrieNode> visited = new HashSet<>();
        ArrayList<String> results = new ArrayList<>();
        Queue<TrieNode> queue = new LinkedList<>();
        ArrayList<TrieNode> nodes = new ArrayList<>();
        for (int i = 0; i < word.length(); i++) {
            char character = word.charAt(i);
            if (!currentNode.doesChildExist(character)) {
                currentNode.addChild(character);
            }
            currentNode = currentNode.getChild(character);
            nodes.add(currentNode);
        }
        Collections.reverse(nodes);
        visited.add(currentNode);
        for (int i = 0; i < nodes.size(); i++) {
            results = prefixBFS(nodes.get(i), visited);
            if (!results.isEmpty()) {
                return results;
            }
        }
        return results;
    }
}
