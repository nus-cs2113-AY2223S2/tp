package seedu.duke.commands;

import org.junit.jupiter.api.Test;
import seedu.duke.exceptions.DukeException;
import seedu.duke.food.FoodList;
import seedu.duke.general.Parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.duke.commands.ExitCommand.MESSAGE_EXIT_ACKNOWLEDGEMENT;

class ExitCommandTest {

    private final Parser p = new Parser();
    private final FoodList foodList = new FoodList();
    @Test
    void execute() throws DukeException {
        assertEquals(MESSAGE_EXIT_ACKNOWLEDGEMENT, p.parse("exit").execute(foodList).feedbackToUser);
    }

    @Test
    void isExit() throws DukeException {
        assertEquals(true, p.parse("exit").isExit());
    }
}
