package seedu.rainyDay;

import modules.*;
import org.junit.*;
import org.junit.jupiter.api.Test;

import java.io.*;

import static org.junit.jupiter.api.Assertions.*;

class UITest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final PrintStream originalErr = System.err;

    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
    }

    @After
    public void restoreStreams() {
        System.setOut(originalOut);
        System.setErr(originalErr);
    }

    @Test
    void checkGreetingPrinted() {
        setUpStreams();
        String expectedGreeting = "Welcome pekopekopeko" + System.lineSeparator();
        UI.greetUser("pekopekopeko");
        assertEquals(expectedGreeting, outContent.toString());
        restoreStreams();
    }
}
