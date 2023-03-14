package seedu.clialgo.command;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RemoveCommandTest {
    @Test
    public void testRemoveCommandConstructor() {
        RemoveCommand myObj = new RemoveCommand("queue notes");
        assertEquals("queue notes", myObj.getName());
    }

}
