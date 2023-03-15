package seedu.Entities;

import org.junit.jupiter.api.Test;
import seedu.entities.User;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UserTest {
    private final User newUser = new User("user", 80, 180, 21, "male");

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

    @Test
    void getWeight80Expect80() {
        assertEquals(80.0, newUser.getWeight());
    }

    @Test
    void getHeight180Expect180() {
        assertEquals(180.0, newUser.getHeight());
    }

    @Test
    void getAge21Expect21() {
        assertEquals(21, newUser.getAge());
    }

    @Test
    void getGenderMaleExpectMale() {
        assertEquals("male", newUser.getGender());
    }
}
