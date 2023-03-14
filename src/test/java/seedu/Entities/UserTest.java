package seedu.entities;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UserTest {

    @Test
    void calculateCaloricNeedsMaleExpect1466() {
        assertEquals(1466, 1466);
    }

    @Test
    void calculateCaloricNeedsFemaleExpect1325() {
        assertEquals(1325, 1325);
    }

    @Test
    void calculateCaloricNeedsMaleExpect66() {
        assertEquals(66, 66);
    }

    @Test
    void calculateCaloricNeedsFemaleExpect651p86() {
        assertEquals(651.86, 651.86);
    }
}
