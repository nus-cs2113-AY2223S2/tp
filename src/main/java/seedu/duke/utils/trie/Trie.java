package seedu.duke.utils.trie;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Queue;
import java.util.LinkedList;
import java.util.Collections;

public class Trie {
    private TrieNode root = new TrieNode("");

    /**
     * Adds a word to the trie object.
     *
     * @param word The word to be added.
     */
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

    /**
     * Removes a word from the trie object.
     *
     * @param word The word to be removed.
     */
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

    /**
     * Finds out whether a word is in the trie.
     *
     * @param word The word to be checked
     * @return Returns false if word is not in trie, true otherwise.
     */

    public boolean isWordInTrie(String word) {
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

    /**
     * Internal function to find all words that can be reached from a node.
     *
     * @param node The origin node to be traversed from.
     * @param visited Set of all visited TrieNodes
     * @return An ArrayList String of all words that can be reached.
     */

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

    /**
     * Finds all words that are registered in the trie and can be reached from any prefix of the provided word.
     * If an earlier prefix returns a word or words, the word or words will be returned first.
     * For example, the word supercalifragilisticexpialidocious can be reached by the word simple as they both share
     * the same prefix "s", but if the word simulation is in the trie it will be returned instead.
     *
     * @param word The search term.
     * @return Returns an ArrayList String of found words.
     */

    public ArrayList<String> prefixFind(String word) {
        word = word.toLowerCase().trim();
        TrieNode currentNode = root;
        HashSet<TrieNode> visited = new HashSet<>();
        ArrayList<String> results = new ArrayList<>();
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
