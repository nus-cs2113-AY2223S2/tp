package seedu.storage;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;

import seedu.constants.DateConstants;
import seedu.entities.Exercise;

public class ExerciseStorageTest {
    private final ExerciseStorage exerciseStorage = new ExerciseStorage("./data/exerciseData.csv");
    private final DateTimeFormatter dtf = DateConstants.DATABASE_DTF;

    @Test
    void addExercise_singleExerciseAdded_expectSizeIncrease() {
        exerciseStorage.resetStorage();
        int oldSize = exerciseStorage.getExercises().size();
        LocalDate date = LocalDate.parse("1/1/2023", dtf);
        assertDoesNotThrow(() -> {
            exerciseStorage.saveExercise(
                new Exercise("Running", "5km", 400, date));
        });
        int newSize = exerciseStorage.getExercises().size();
        assertEquals(oldSize + 1, newSize);
    }
}
