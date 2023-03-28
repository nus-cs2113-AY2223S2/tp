package seedu.duke;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import seedu.duke.utils.trie.Trie;

import java.util.ArrayList;

public class TrieTest {
    /**
     * Tests the trie's find function
     */
    @Test
    public void findTest(){
        Trie trie = new Trie();
        trie.add("Hamburger");
        trie.add("Hamburgers");
        trie.add("Marshmallows");
        assertTrue(trie.isWordInTrie("Hamburger")==true);
        assertTrue(trie.isWordInTrie("hamburger")==true);
        assertTrue(trie.isWordInTrie("hamb")==false);
    }

    /**
     * Tests the trie's prefixFind function.
     */
    @Test
    public void findPrefixTest(){
        Trie trie = new Trie();
        trie.add("Hamburger");
        trie.add("Hamburgers");
        trie.add("Marshmallows");
        ArrayList<String> ans = new ArrayList<>();
        ans.add("hamburger");
        ans.add("hamburgers");
        assertTrue(trie.prefixFind("Hamb").equals(ans));
        assertTrue(trie.prefixFind("Hamburger").equals(ans));
        ans.remove("hamburger");
        assertTrue(trie.prefixFind("Hamburgersss").equals(ans));
    }
}
