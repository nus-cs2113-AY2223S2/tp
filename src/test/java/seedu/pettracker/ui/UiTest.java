package seedu.pettracker.ui;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;


public class UiTest {

    @Test
    void testAddStatCommandMessage () {
        // Code for testing standard output based on: 
        // https://stackoverflow.com/questions/1119385/junit-test-for-system-out-println
        final ByteArrayOutputStream OUT_CONTENT = new ByteArrayOutputStream();
        final PrintStream ORIGINAL_OUT = System.out;

        System.setOut(new PrintStream(OUT_CONTENT));
        Ui ui = new Ui();
        ui.addStatCommandMessage("George", "age", "8");

        assertEquals("Updated age to 8 for George", OUT_CONTENT.toString());

        System.setOut(ORIGINAL_OUT);


    }
}
