package seedu.duke;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import app.Command;
import app.MoneyGoWhere;
import exception.InvalidFlagException;
import org.junit.jupiter.api.Test;
import utility.Ui;
import exception.InvalidArgumentException;

class ItemTest {
    MoneyGoWhere moneyGoWhere = new MoneyGoWhere();
    public void sampleTest() {
        assertTrue(true);
    }

    public void runTest(String input, MoneyGoWhere moneyGoWhere) {

        Ui ui = new Ui();


        ui.promptUserInput();
        String userInput = input;


        Command command = new Command(userInput);

        try {
            moneyGoWhere.handleCommand(command);
        } catch (InvalidArgumentException | InvalidFlagException e) {
            ui.println(e.getMessage());
        }

    }

    @Test
    public void itemTest() {

        runTest("additem -p 20 -n chicken rice1", moneyGoWhere);
        assertEquals("chicken rice1", moneyGoWhere.items.getItems().
                get(moneyGoWhere.items.getItems().size() - 1).getName());
        assertEquals(20.00, moneyGoWhere.items.getItems().
                get(moneyGoWhere.items.getItems().size() - 1).getPrice());

        runTest("listitem", moneyGoWhere);

        runTest("deleteitem -i " + (moneyGoWhere.items.getItems().size()-1), moneyGoWhere);
    }

    @Test
    public void itemTest2() {
        runTest("additem -p 2kuku0.01 -n chicken rice2", moneyGoWhere);
    }

    @Test
    public void itemTest3() {
        // max 2dp error
        runTest("additem -p 20.001 -n chicken rice3", moneyGoWhere);
    }

}
