package seedu.clialgo.file;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

class CodeTest {
    @Test
    void equals_checkEqualCode_expectTrue() {
        Code myObj = new Code("queue", "queue.cpp", "LINKED_LIST");
        Code myOtherObj = new Code("queue", "queue.cpp", "LINKED_LIST");
        assertTrue(myObj.equals(myOtherObj));
    }
    @Test
    void equals_checkNullObject_expectFalse() {
        Code myObj = new Code("queue", "queue.cpp", "LINKED_LIST");
        Code myOtherObj = null;
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
        Code myObj = new Code("queue", "queue.cpp", "LINKED_LIST", 10);
        Code myOtherObj = new Code("queue", "queue.cpp", "LINKED_LIST", 5);
        assertFalse(myObj.equals(myOtherObj));
    }

    @Test
    void equals_checkUnequalTopic_expectFalse() {
        Code myObj = new Code("queue", "queue.cpp", "LINKED_LIST", 10);
        Code myOtherObj = new Code("queue", "queue.cpp", "SORTING", 10);
        assertFalse(myObj.equals(myOtherObj));
    }

    @Test
    void equals_checkUnequalName_expectFalse() {
        Code myObj = new Code("queue1", "queue1.cpp", "LINKED_LIST", 10);
        Code myOtherObj = new Code("queue2", "queue2.cpp", "LINKED_LIST", 10);
        assertFalse(myObj.equals(myOtherObj));
    }
}
