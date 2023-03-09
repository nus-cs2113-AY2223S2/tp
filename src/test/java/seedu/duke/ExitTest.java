package seedu.duke;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class ExitTest {

    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @Test
    void ExitUnderTest_bye_expectBye() {
        System.out.println("Bye!");

        assertEquals("Bye!", outputStreamCaptor.toString().trim());

    }

    @BeforeEach
    public void tearDown() {
        System.setOut(standardOut);
    }
}