package chching;

import chching.parser.Parser;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class ParserTest {
    static final String ADD_EXPENSE ="add expense";
    static final String CA = "ca meal";
    static final String DE ="de breakfast";
    static final String DA = "da 01/02/23";
    static final String V = "v 3.50";
    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
    
    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }
    
    @Test
    public void splitLine_testScenario_expectedBehaviour() {
        List<String> expectedOutput = new ArrayList<String>();
        expectedOutput.add(ADD_EXPENSE);
        expectedOutput.add(CA);
        expectedOutput.add(DE);
        expectedOutput.add(DA);
        expectedOutput.add(V);
        
        assertEquals(expectedOutput, new Parser().splitLine("add expense /ca meal /de breakfast /da 01/02/23 /v 3.50"));
    }
    
    @Test
    public void sortArguments_testScenario_expectedBehaviour() throws ChChingException {
        List<String> input = new ArrayList<String>();
        input.add(CA);
        input.add(DE);
        input.add(DA);
        input.add(V);
        
        HashMap<String, String> expectedOutput = new HashMap<String, String>();
        expectedOutput.put("ca", "meal");
        expectedOutput.put("de", "breakfast");
        expectedOutput.put("da", "01/02/23");
        expectedOutput.put("v", "3.50");
        
        assertEquals(expectedOutput, new Parser().sortArguments(input));
    }
    
    @Test
    public void sortArguments_emptyInput_returnsEmptyHashMap() throws ChChingException {
        List<String> input = new ArrayList<String>();
        
        HashMap<String, String> expectedOutput = new HashMap<String, String>();
        assertEquals(expectedOutput, new Parser().sortArguments(input));
    }


    @AfterEach
    public void tearDown() {
        System.setOut(standardOut);
    }

}
