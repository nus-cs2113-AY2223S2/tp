package seedu.fst.commands;

import org.junit.jupiter.api.Test;
import seedu.fst.exceptions.FSTException;
import seedu.fst.food.FoodList;
import seedu.fst.general.Parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.fst.commands.ExitCommand.MESSAGE_EXIT_ACKNOWLEDGEMENT;

class ExitCommandTest {

    private final Parser p = new Parser();
    private final FoodList foodList = new FoodList();
    @Test
    void execute() throws FSTException {
        assertEquals(MESSAGE_EXIT_ACKNOWLEDGEMENT, p.parse("exit").execute(foodList).feedbackToUser);
    }

    @Test
    void isExit() throws FSTException {
        assertEquals(true, p.parse("exit").isExit());
    }
}
