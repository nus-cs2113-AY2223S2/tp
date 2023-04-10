package seedu.rainyDay.modules;

import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UiTest {

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
        Ui ui = new Ui();
        setUpStreams();
        String expectedGreeting = "Welcome pekopekopeko" + System.lineSeparator();
        ui.greetUser("pekopekopeko");
        assertEquals(expectedGreeting, outContent.toString());
        restoreStreams();
    }
}
