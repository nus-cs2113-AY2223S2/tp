package chching.record;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TargetStorageTest {
    
        static final double TARGET_VALUE = 100;
        /**
        * Junit Test to get target value
        */
        @Test
        void getTargetValue_expected() {
            Target target = new Target(TARGET_VALUE);
            TargetStorage targetStorage = new TargetStorage();
            targetStorage.addTarget(target);
            assertEquals(100, targetStorage.getTarget());
        }
    
}
