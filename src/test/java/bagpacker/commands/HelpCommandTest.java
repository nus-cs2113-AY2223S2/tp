package bagpacker.commands;

import bagpacker.packingfunc.PackingList;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * HelpCommandTest to test HelpCommand class
 */
public class HelpCommandTest {
    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }
    public void tearDown() {
        System.setOut(standardOut);
    }
    @Test
    public void testHelpCommand() {
        setUp();
        String userInput = "  help";
        HelpCommand testHelpCmd = new HelpCommand();
        InputStream inStream = new ByteArrayInputStream(userInput.getBytes());
        System.setIn(inStream);
        testHelpCmd.execute(new PackingList());
        String helpMessage = "____________________________________________________________\r\n" +
                "All Commands:\r\n" +
                "1. add : Adds quantity and name of item to the packing list.\r\n" +
                "\tExample: add 3 /of toothbrush\r\n" +
                "\tMeaning: add quantity of 3 toothbrushes to the packing list\r\n" +
                "2. delete : Deletes an item from the packing list.\r\n" +
                "\tExample: delete 1\r\n" +
                "\tMeaning: removes the first item in the packing list\r\n" +
                "3. list : List all items and their total quantities packed in packing list.\r\n" +
                "\tExample: list\r\n" +
                "4. pack : Adds to the current quantity of items packed in the packing list.\r\n" +
                "\tExample: pack 2 /of 3\r\n" +
                "\tMeaning: packs 2 more quantities of the third item in the packing list\r\n" +
                "5. unpack : Deducts from the current quantity of items packed in the packing list.\r\n" +
                "\tExample: unpack 1 /of 2\r\n" +
                "\tMeaning: unpacks 1 quantity of the second item in the packing list\r\n" +
                "6. deletelist : Deletes all items in the packing list.\r\n" +
                "\tExample: deletelist\r\n" +
                "7. packall : Marks all quantity of the specified item as packed in the packing list.\r\n" +
                "\tExample: packall /of 3\r\n" +
                "\tMeaning: packs all of the quantities of the third item in the packing list\r\n" +
                "8. bye : Stops the BagPacker Application\r\n" +
                "\tExample: bye\r\n" +
                "____________________________________________________________";
        assertNotNull(outputStreamCaptor.toString()
                .trim());
        tearDown();
    }
}
