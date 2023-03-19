package seedu.entities;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UserTest {
    private final User newUser = new User("user", 80, 180, 21, "male");

    @Test
    void calculateCaloricNeeds_male_expect1466() {
        assertEquals(1466, 1466);
    }

    @Test
    void calculateCaloricNeeds_female_expect1325() {
        assertEquals(1325, 1325);
    }

    @Test
    void calculateCaloricNeeds_male_expect66() {
        assertEquals(66, 66);
    }

    @Test
    void calculateCaloricNeeds_female_expect651p86() {
        assertEquals(651.86, 651.86);
    }

    @Test
    void getWeight_input80_expect80() {
        assertEquals(80.0, newUser.getWeight());
    }

    @Test
    void getHeight_input180_expect180() {
        assertEquals(180.0, newUser.getHeight());
    }

    @Test
    void getAge_input21_expect21() {
        assertEquals(21, newUser.getAge());
    }

    @Test
    void getGender_male_expectMale() {
        assertEquals("male", newUser.getGender());
    }
}
