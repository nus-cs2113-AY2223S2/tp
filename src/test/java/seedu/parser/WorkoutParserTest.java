package seedu.parser;

import org.junit.jupiter.api.Test;

import seedu.exceptions.InvalidArgumentException;
import seedu.exceptions.InvalidSyntaxException;

import static org.junit.Assert.assertThrows;
import static seedu.parser.WorkoutParser.parseAddExerciseCommand;
import static seedu.parser.WorkoutParser.parseEndWorkoutCommand;


class WorkoutParserTest {
    //@@author calebcjl
    @Test
    void parseAddExerciseCommand_emptyExerciseName_expectInvalidArgumentException() {
        String addExerciseArgument1 = "  100kg 5 5 5 5";
        assertThrows(InvalidArgumentException.class, () -> parseAddExerciseCommand(addExerciseArgument1));
        String addExerciseArgument2 = "100kg 5 5 5 5";
        assertThrows(InvalidArgumentException.class, () -> parseAddExerciseCommand(addExerciseArgument2));
    }

    @Test
    void parseAddExerciseCommand_invalidWeight_expectException() {
        String addExerciseArgument1 = "ExerciseName 100 kg 5 5 5 5";
        assertThrows(InvalidArgumentException.class, () -> parseAddExerciseCommand(addExerciseArgument1));
        String addExerciseArgument2 = "ExerciseName 100kh 5 5 5 5";
        assertThrows(InvalidSyntaxException.class, () -> parseAddExerciseCommand(addExerciseArgument2));
    }

    @Test
    void parseAddExerciseCommand_multiWhiteSpacesInRps_expectInvalidArgumentException() {
        String addExerciseArgument1 = "ExerciseName 100kg 5   5  5 5";
        assertThrows(InvalidArgumentException.class, () -> parseAddExerciseCommand(addExerciseArgument1));
    }

    @Test
    void parseEndWorkoutCommand_argumentNotEmpty_expectInvalidSyntaxException() {
        String endWorkoutArguments1 = "argument";
        assertThrows(InvalidSyntaxException.class, () -> parseEndWorkoutCommand(endWorkoutArguments1));
        String endWorkoutArguments2 = "1";
        assertThrows(InvalidSyntaxException.class, () -> parseEndWorkoutCommand(endWorkoutArguments2));
    }
}
