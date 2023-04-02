package seedu.apollo.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ToDoTest {

    ToDo toDo = new ToDo("test");

    @Test
    void getType_normalToDo_expectToDoString() {
        assertEquals("todo", toDo.getType());
    }

    @Test
    void testToString_unmarkedToDo_expectString() {
        assertEquals("[T][ ] test", toDo.toString());
    }

    @Test
    void testToString_markedToDo_expectString() {
        toDo.setDone(true);
        assertEquals("[T][X] test", toDo.toString());
    }
}
