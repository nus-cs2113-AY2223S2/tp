package chching.record;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Junit Test for Target
 */

public class TargetTest {
    
        static final double TARGET_VALUE = 100;
        /**
        * Junit Test to get target value
        */
        @Test
        void getTargetValue_expected() {
            Target target = new Target(TARGET_VALUE);
            assertEquals(100, target.getValue());
        }
    
}
