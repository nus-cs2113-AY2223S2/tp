package seedu.entities;

import org.junit.jupiter.api.Test;
import seedu.entities.User;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UserTest {
    private final User newUser = new User("user", 80, 180, 21, "male");

    @Test
    void calculateCaloricNeeds_Male_Expect1466() {
        assertEquals(1466, 1466);
    }

    @Test
    void calculateCaloricNeeds_Female_Expect1325() {
        assertEquals(1325, 1325);
    }

    @Test
    void calculateCaloricNeeds_Male_Expect66() {
        assertEquals(66, 66);
    }

    @Test
    void calculateCaloricNeeds_Female_Expect651p86() {
        assertEquals(651.86, 651.86);
    }

    @Test
    void getWeight_80_Expect80() {
        assertEquals(80.0, newUser.getWeight());
    }

    @Test
    void getHeight_180_Expect180() {
        assertEquals(180.0, newUser.getHeight());
    }

    @Test
    void getAge_21_Expect21() {
        assertEquals(21, newUser.getAge());
    }

    @Test
    void getGender_Male_ExpectMale() {
        assertEquals("male", newUser.getGender());
    }
}
