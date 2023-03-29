package seedu.moneymind.commands;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ByeCommandTest extends CommandTest {

    public ByeCommandTest() {
        super();
    }

    @Test
    void byeCommand_typeBye_expectByeMessage() {
        setup();
        String terminalOutput = executeInput("bye").toString();
        assertEquals("Bye. Hope to see you again soon!" + System.lineSeparator()
                + "____________________________________________________________"
                + System.lineSeparator(), terminalOutput);
        clear();
    }

}
