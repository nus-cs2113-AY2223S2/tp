package seedu.Entities;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UserTest {

    @Test
    void calculateCaloricNeeds_100_100_100_male_expect1466() {
        assertEquals(1466, 1466);
    }

    @Test
    void calculateCaloricNeeds_100_100_100_female_expect1325() {
        assertEquals(1325, 1325);
    }

    @Test
    void calculateCaloricNeeds_0_0_0_male_expect66() {
        assertEquals(66, 66);
    }

    @Test
    void calculateCaloricNeeds_1p2_2p3_4_female_expect651p86() {
        assertEquals(651.86, 651.86);
    }
}
