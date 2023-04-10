package seedu.clialgo.file;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

class NoteTest {
    @Test
    void equals_checkEqualNote_expectTrue() {
        Note myObj = new Note("queue", "queue.txt", "LINKED_LIST");
        Note myOtherObj = new Note("queue", "queue.txt", "LINKED_LIST");
        assertTrue(myObj.equals(myOtherObj));
    }
    @Test
    void equals_checkNullObject_expectFalse() {
        Note myObj = new Note("queue", "queue.txt", "LINKED_LIST");
        Note myOtherObj = null;
        assertFalse(myObj.equals(myOtherObj));
    }
    @Test
    void equals_checkUnequalClass_expectFalse() {
        Note myObj = new Note("queue", "queue.txt", "LINKED_LIST");
        Code myOtherObj = new Code("queue", "queue.cpp", "LINKED_LIST");
        assertFalse(myObj.equals(myOtherObj));
    }

    @Test
    void equals_checkUnequalImportance_expectFalse() {
        Note myObj = new Note("queue", "queue.txt", "LINKED_LIST", 10);
        Note myOtherObj = new Note("queue", "queue.txt", "LINKED_LIST", 5);
        assertFalse(myObj.equals(myOtherObj));
    }

    @Test
    void equals_checkUnequalTopic_expectFalse() {
        Note myObj = new Note("queue", "queue.txt", "LINKED_LIST", 10);
        Note myOtherObj = new Note("queue", "queue.txt", "SORTING", 10);
        assertFalse(myObj.equals(myOtherObj));
    }

    @Test
    void equals_checkUnequalName_expectFalse() {
        Note myObj = new Note("queue1", "queue1.txt", "LINKED_LIST", 10);
        Note myOtherObj = new Note("queue2", "queue2.txt", "LINKED_LIST", 10);
        assertFalse(myObj.equals(myOtherObj));
    }
}
