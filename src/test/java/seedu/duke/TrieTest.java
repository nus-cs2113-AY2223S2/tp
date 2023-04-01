package seedu.duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import seedu.duke.utils.trie.Trie;

import java.util.ArrayList;

public class TrieTest {
    /**
     * Tests the trie's find function
     */
    @Test
    public void findTest() {
        Trie trie = new Trie();
        trie.add("Hamburger");
        trie.add("Hamburgers");
        trie.add("Marshmallows");
        assertEquals(true, trie.isWordInTrie("Hamburger"));
        assertEquals(true, trie.isWordInTrie("hamburger"));
        assertEquals(false, trie.isWordInTrie("hamb"));
    }

    /**
     * Tests the trie's prefixFind function.
     */
    @Test
    public void findPrefixTest() {
        Trie trie = new Trie();
        trie.add("Hamburger");
        trie.add("Hamburgers");
        trie.add("Marshmallows");
        ArrayList<String> ans = new ArrayList<>();
        ans.add("hamburger");
        ans.add("hamburgers");
        assertEquals(trie.prefixFind("Hamb"), ans);
        assertEquals(trie.prefixFind("Hamburger"), ans);
        ans.remove("hamburger");
        assertEquals(trie.prefixFind("Hamburgersss"), ans);
    }
}
