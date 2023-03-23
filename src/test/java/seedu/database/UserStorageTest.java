package seedu.database;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import seedu.entities.User;

public class UserStorageTest {
    private static final String FILE_PATH = "./data/userData.csv";

    @Nested
    @DisplayName("Test User Storage Functionalities")
    class ReadWriteTest {
        private final UserStorage userStorage = new UserStorage(FILE_PATH);

        @Test
        @DisplayName("Test retrieve user")
        public User retrieveUser_emptyInput_expectNoException() {
            User user = assertDoesNotThrow(() -> userStorage.getUser());
            return user;
        }

        @Test
        @DisplayName("Test Update User")
        public void updateUser_newUserDetails_expectUserHasNewUserDetails() {
            String name = "test";
            float weight = (float) 65.0;
            float height = (float) 175.0;
            int age = 21;
            String gender = "male";

            User newUser = new User(name, weight, height, age, gender);

            assertDoesNotThrow(() -> userStorage.updateUser(newUser));
            User retrievedUser = retrieveUser_emptyInput_expectNoException();

            assertEquals(name, retrievedUser.getName());
            assertEquals(weight, retrievedUser.getWeight());
            assertEquals(height, retrievedUser.getHeight());
            assertEquals(age, retrievedUser.getAge());
            assertEquals(gender, retrievedUser.getGender());
        }
    }

}
