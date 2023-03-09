package seedu.duke;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

class DukeTest {
    @Test
    public void sampleTest() {
        assertTrue(true);
    }
    
    @Test
    public void splitLine_testScenario_expectedBehaviour() {
        List<String> expectedOutput = new ArrayList<String>();
        expectedOutput.add("add expense");
        expectedOutput.add("de breakfast");
        expectedOutput.add("da 01/02/23");
        expectedOutput.add("v 3.50");
        
        assertEquals(expectedOutput, new Duke().splitLine("add expense /de breakfast /da 01/02/23 /v 3.50"));
    }
}
