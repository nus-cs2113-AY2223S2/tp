package seedu.duke;

import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserTest {
    @Test
    public void splitLine_testScenario_expectedBehaviour() {
        List<String> expectedOutput = new ArrayList<String>();
        expectedOutput.add("add expense");
        expectedOutput.add("de breakfast");
        expectedOutput.add("da 01/02/23");
        expectedOutput.add("v 3.50");
        
        assertEquals(expectedOutput, new Parser().splitLine("add expense /de breakfast /da 01/02/23 /v 3.50"));
    }
}
