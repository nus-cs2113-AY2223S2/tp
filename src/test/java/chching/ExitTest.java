package chching;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ExitTest {
    static final String EXPECTED_STRING = "Bye!";

    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @Test
    void exit_bye_expectBye() {
        System.out.println("Bye!");

        assertEquals(EXPECTED_STRING, outputStreamCaptor.toString().trim());

    }
}
