package seedu.moneymind.commands;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class HelpCommandTest extends CommandTest {

    public HelpCommandTest() {
        super();
    }

    @Test
    void helpCommand_typeHelp_expectHelpMessage() {
        setup();
        String terminalOutput = executeInput("help").toString();
        String expected = "Here are the commands you can use:" + System.lineSeparator()
                + "1. help - show detailed instructions on how to use the app\n" +
                "Format: help\n" + "Example: help\n" + System.lineSeparator()
                + "2. summary - show a summary of the commands that you can use\n" +
                "Format: summary\n" + "Example: summary\n" + System.lineSeparator()
                + "3. category - add a category to your list\n" +
                "Format: category <name> [(optional) b/<budget number>]\n" +
                "Example: category food b/2000\n" + System.lineSeparator()
                + "4. event - add an event to a category\n" +
                "Format: event <name> e/<expense number> [(optional) t/<time>]\n" +
                "Example: event lunch e/10 t/01/01/2020 12:00\n" +
                "(time is optional and the format is dd/mm/yyyy hh:mm)\n"
                + System.lineSeparator()
                + "5. view - view all the events in a category or all the categories\n" +
                "You can view all the events in a category by specifying the category name\n" +
                "Format: view <category name>\n" +
                "Example: view food\n" +
                "(category name is optional and if you do not enter a category name, " +
                "all the categories will be shown)\n"
                + System.lineSeparator()
                + "6. edit - edit the expense for an event or budget for a category\n" +
                "Format: edit c/<category name> [(optional) e/<event index>]\n" +
                "Example: edit c/food e/1\n" + System.lineSeparator()
                + "7. delete - delete an event or a category\n" +
                "Format: delete c/<category name> [(optional) e/<event index>]\n" +
                "Example: delete c/food e/1\n" +
                "Example: delete c/food\n" + System.lineSeparator() +

                "8. search - search for matching events and categories\n" +
                "Format: search <keyword>\n" +
                "Example: search bill\n" + System.lineSeparator() +

                "9. bye - exit the app\n" + "Format: bye\n" + "Example: bye\n" + System.lineSeparator();
        assertEquals(expected, terminalOutput);
        clear();
    }

}
