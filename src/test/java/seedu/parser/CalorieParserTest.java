package seedu.parser;

import org.junit.jupiter.api.Test;
import seedu.exceptions.InvalidArgumentException;
import seedu.exceptions.InvalidSyntaxException;

import static org.junit.Assert.assertThrows;
import static seedu.parser.CalorieParser.parseAddCalorieCommand;
import static seedu.parser.CalorieParser.parseDeleteCalorieCommand;
import static seedu.parser.CalorieParser.parseListCalorieCommand;

class CalorieParserTest {

    //@@author calebcjl
    @Test
    void parseAddCalorieCommand_negativeCalorieCount_expectInvalidArgumentException() {
        String argument = "11/12/22 Food -10";
        assertThrows(InvalidArgumentException.class, () -> parseAddCalorieCommand(argument));
    }

    @Test
    void parseAddCalorieCommand_noFoodName_expectInvalidArgumentException() {
        String argument1 = "11/12/22 100";
        assertThrows(InvalidArgumentException.class, () -> parseAddCalorieCommand(argument1));
        String argument2 = "11/12/22   100";
        assertThrows(InvalidArgumentException.class, () -> parseAddCalorieCommand(argument2));
    }

    @Test
    void parseListCalorieCommand_notBlankArgument_expectInvalidSyntaxException() {
        String argument1 = "@";
        assertThrows(InvalidSyntaxException.class, () -> parseListCalorieCommand(argument1));
        String argument2 = "2";
        assertThrows(InvalidSyntaxException.class, () -> parseListCalorieCommand(argument2));
    }

    @Test
    void parseDeleteCalorieCommand_multipleIntegers_expectInvalidSyntaxException() {
        String argument = "11/12/22 1 2";
        assertThrows(InvalidSyntaxException.class, () -> parseDeleteCalorieCommand(argument));
    }
}
