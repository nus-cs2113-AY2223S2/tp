//package seedu.duke;
//
//import static org.junit.jupiter.api.Assertions.assertTrue;
//
//import org.junit.jupiter.api.Test;
//import seedu.duke.trie.Trie;
//
//import java.util.ArrayList;
//
//public class TrieTest {
//    @Test
//    public void findTest(){
//        Trie trie = new Trie();
//        trie.add("Hamburger");
//        trie.add("Hamburgers");
//        trie.add("Marshmallows");
//        assertTrue(trie.find("Hamburger")==true);
//        assertTrue(trie.find("hamburger")==true);
//        assertTrue(trie.find("hamb")==false);
//    }
//    @Test
//    public void findPrefixTest(){
//        Trie trie = new Trie();
//        trie.add("Hamburger");
//        trie.add("Hamburgers");
//        trie.add("Marshmallows");
//        ArrayList<String> ans = new ArrayList<>();
//        ans.add("hamburger");
//        ans.add("hamburgers");
//        assertTrue(trie.prefixFind("Hamb").equals(ans));
//        assertTrue(trie.prefixFind("Hamburger").equals(ans));
//        ans.remove("hamburger");
//        assertTrue(trie.prefixFind("Hamburgersss").equals(ans));
//    }
//}
