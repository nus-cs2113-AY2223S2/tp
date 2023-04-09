package seedu.duke.ipptc;

import org.junit.jupiter.api.Test;
import seedu.duke.commons.exceptions.DukeError;
import seedu.duke.data.ipptcalculator.IPPTCalc;
import seedu.duke.data.ipptcalculator.Scores;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class TestIPPT {
    private IPPTCalc ipptCalculator;
    private int ageGroup;
    private int runReps;
    private int pushupReps;
    private int situpReps;
    private Scores scores;
    /**
     * Test for invalid age input by user: either under the age limit or over the age limit (valid range is 16-60)
     **/
    @Test
    public void testUnderageIPPTCmd(){
        String[] inputs1 = {"9", "10:10" , "10", "10"};
        String testInputAge = inputs1[0];
        String testInputRunTime = inputs1[1];
        String testInputPushUps = inputs1[2];
        String testInputSitups = inputs1[3];
        int testAgeGroup = Integer.parseInt(testInputAge);
        int testPushupReps = Integer.parseInt(testInputPushUps);
        int testSitupReps = Integer.parseInt(testInputSitups);
        assertThrows(DukeError.class, () -> {
            ipptCalculator = new IPPTCalc(testAgeGroup, testInputRunTime, testPushupReps, testSitupReps);;
        });
    }
    @Test
    public void testOverageIPPTCmd(){
        String[] inputs2 = {"70", "10:10" , "10", "10"};
        String testInputAge = inputs2[0];
        String testInputRunTime = inputs2[1];
        String testInputPushUps = inputs2[2];
        String testInputSitups = inputs2[3];
        int testAgeGroup = Integer.parseInt(testInputAge);
        int testPushupReps = Integer.parseInt(testInputPushUps);
        int testSitupReps = Integer.parseInt(testInputSitups);
        assertThrows(DukeError.class, () -> {
            ipptCalculator = new IPPTCalc(testAgeGroup, testInputRunTime, testPushupReps, testSitupReps);;
        });
    }

}
