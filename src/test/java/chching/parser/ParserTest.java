/* @@author avielcx */
package chching;

import chching.parser.Parser;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class ParserTest {
    static final String ADD_EXPENSE ="add expense";
    static final String CA = "ca meal";
    static final String DE ="de breakfast";
    static final String DA = "da 01/02/23";
    static final String V = "v 3.50";
    static final String INVALID_INPUT = "invalid";
    static final String CATEGORY_FIELD = "c";
    static final String CATEGORY_VALUE = "meal";
    static final String DESCRIPTION_FIELD = "de";
    static final String DATE_FIELD = "da";
    static final String VALUE_FIELD = "v";
    static final String VALUE_VALUE = "3.50";
    static final String DATE_VALUE = "01/02/23";
    static final String DESCRIPTION_VALUE = "breakfast";
    static final String KEYWORD_FIELD = "k";
    static final String KEYWORD_VALUE = "hello";
    static final String CA_WITH_EMPTY_DETAIL = "ca  ";
    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
    
    /**
     * Sets up the output stream to capture the output of the system.
     */
    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }
    
    /**
     * JUnit test for splitLine method.
     * Checks if the method splits the input line into the correct format.
     */
    @Test
    public void splitLine_testScenario_expectedBehaviour() {
        List<String> expectedOutput = new ArrayList<String>();
        expectedOutputsForExpectedBehaviour(expectedOutput);

        assertEquals(expectedOutput, new Parser().splitLine("add expense /ca meal /de breakfast /da 01/02/23 /v 3.50"));
    }

    private static void expectedOutputsForExpectedBehaviour(List<String> expectedOutput) {
        expectedOutput.add(ADD_EXPENSE);
        expectedOutput.add(CA);
        expectedOutput.add(DE);
        expectedOutput.add(DA);
        expectedOutput.add(V);
    }

    /**
     * JUnit test for sortArguments method.
     * Checks if the arguments are sorted in the expected behaviour.
     */
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

    /**
     * JUnit test for sortArguments method.
     * Checks if the method returns an empty HashMap if the input is empty.
     */
    @Test
    public void sortArguments_emptyInput_returnsEmptyHashMap() throws ChChingException {
        List<String> input = new ArrayList<String>();
        
        HashMap<String, String> expectedOutput = new HashMap<String, String>();
        assertEquals(expectedOutput, new Parser().sortArguments(input));
    }
    
    /**
     * JUnit test for sortArguments method.
     * Checks if the method throws an exception if the input is invalid.
     */
    @Test
    public void sortArguments_invalidInput_throwsException() {
        List<String> input = new ArrayList<String>();
        input.add(INVALID_INPUT);

        try {
            new Parser().sortArguments(input);
            fail(); // the test should not reach this line
        } catch (ChChingException e) {
            assertEquals("Arguments not inputted correctly / Missing details", e.getMessage());
        }
    }
    
    /**
     * JUnit test for sortArguments method.
     * Checks if the method throws an exception if the input contains duplicate fields.
     */
    @Test
    public void sortArguments_duplicateField_throwsException() {
        List<String> input = new ArrayList<String>();
        input.add(CA);
        input.add(CA);
        
        try {
            new Parser().sortArguments(input);
            fail(); // the test should not reach this line
        } catch (ChChingException e) {
            assertEquals("Duplicate fields detected", e.getMessage());
        }
    }
    
    /**
     * JUnit test for sortArguments method.
     * Checks if the method throws an exception if the input contains empty detail.
     */
    @Test
    public void sortArguments_emptyDetail_throwsException() {
        List<String> input = new ArrayList<String>();
        input.add(CA_WITH_EMPTY_DETAIL);
        
        try {
            new Parser().sortArguments(input);
            fail(); // the test should not reach this line
        } catch (ChChingException e) {
            assertEquals("Empty detail detected or improper use of \" / \"", e.getMessage());
        }
    }

    /**
     * JUnit test for getCategory method.
     * Checks if the method returns the category properly.
     */
    @Test
    public void getCategory_testScenario_expectedBehaviour() throws ChChingException {
        HashMap<String, String> input = new HashMap<String, String>();
        input.put(CATEGORY_FIELD, CATEGORY_VALUE);
        input.put(DESCRIPTION_FIELD, DESCRIPTION_VALUE);
        input.put(DATE_FIELD, DATE_VALUE);
        input.put(VALUE_FIELD, VALUE_VALUE);
        
        assertEquals(CATEGORY_VALUE, new Parser().getCategory(input));
    }
    
    /**
     * JUnit test for getKeyword method.
     * Checks if the method returns the keyword properly.
     */
    @Test
    public void getKeyword_normalScenario_expectedBehaviour() throws ChChingException {
        HashMap<String, String> input = new HashMap<String, String>();
        input.put(KEYWORD_FIELD, KEYWORD_VALUE);
        
        assertEquals("hello", new Parser().getKeyword(input));
    }

    @AfterEach
    public void tearDown() {
        System.setOut(standardOut);
    }

}
