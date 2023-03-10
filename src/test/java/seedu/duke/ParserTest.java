package seedu.duke;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class ParserTest {
    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @Test
    public void splitLine_testScenario_expectedBehaviour() {
        List<String> expectedOutput = new ArrayList<String>();
        expectedOutput.add("add expense");
        expectedOutput.add("de breakfast");
        expectedOutput.add("da 01/02/23");
        expectedOutput.add("v 3.50");
        
        assertEquals(expectedOutput, new Parser().splitLine("add expense /de breakfast /da 01/02/23 /v 3.50"));
    }


    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @Test
    public void parseCommand_badInput_exceptionThrown() throws Exception{
        String badInput = "add expenses /de breakfast /da 01/02/23 /v 3.50";
        new Parser().parseCommand(badInput);
        assertEquals("Command not recognized", outputStreamCaptor.toString().trim());

    }

    @AfterEach
    public void tearDown() {
        System.setOut(standardOut);
    }

}
