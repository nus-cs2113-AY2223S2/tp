package seedu.duke.parsers;

import org.junit.jupiter.api.Test;
import seedu.duke.commands.AddAlertCommand;
import seedu.duke.commands.AddCommand;
import seedu.duke.commands.Command;
import seedu.duke.objects.Alert;
import seedu.duke.objects.Inventory;
import seedu.duke.objects.Item;
import seedu.duke.utils.parsers.AlertParser;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AlertParserTest {

    Inventory inventory;
    @Test
    void addAlertAllParameters() {
        inventory = new Inventory();
        Item newItem = new Item("apples", "1234", 6, 15.0);
        Command command = new AddCommand(inventory, newItem);
        command.run();

        String minAlert = "add upc/1234 min/2";
        String maxAlert = "add upc/1234 max/10";

        AlertParser alertParserMin = new AlertParser(minAlert, inventory);
        alertParserMin.run();
        AlertParser alertParserMax = new AlertParser(maxAlert, inventory);
        alertParserMax.run();

        assertEquals(1, inventory.getAlertList().getMinAlertUpcs().size());
        assertEquals(1, inventory.getAlertList().getMaxAlertUpcs().size());
    }

    @Test
    void addAlertInvalidFormat() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        String rawInput = "add upc/1234 min 2";
        AlertParser alertParser = new AlertParser(rawInput, inventory);
        alertParser.run();

        String expectedOutput = "Wrong/Incomplete Entry For Add Alert!";
        assertTrue(outContent.toString().contains(expectedOutput));
        
    }

   @Test
    void removeAlertAllParameters() {
       inventory = new Inventory();
       Item newItem = new Item("apples", "1234", 6, 15.0);
       Command command = new AddCommand(inventory, newItem);
       command.run();

       String inventoryUpc = inventory.getItemInventory().get(0).getUpc();
       Alert maxAlert = new Alert(inventoryUpc, "max", "10");
       Command addAlertCommandMax = new AddAlertCommand(inventory, maxAlert);
       addAlertCommandMax.run();
       assertEquals(1, inventory.getAlertList().getMaxAlertUpcs().size());

       Alert minAlert = new Alert(inventoryUpc, "min", "2");
       Command addAlertCommandMin = new AddAlertCommand(inventory, minAlert);
       addAlertCommandMin.run();
       assertEquals(1, inventory.getAlertList().getMinAlertUpcs().size());

       String removeMaxAlert = "remove upc/1234 level/max";
       AlertParser alertParserMax = new AlertParser(removeMaxAlert, inventory);
       alertParserMax.run();
       assertEquals(0, inventory.getAlertList().getMaxAlertUpcs().size());

       String removeMinAlert = "remove upc/1234 level/min";
       AlertParser alertParserMin = new AlertParser(removeMinAlert, inventory);
       alertParserMin.run();
       assertEquals(0, inventory.getAlertList().getMinAlertUpcs().size());

   }

   @Test
    void removeAlertInvalidFormat() {
       ByteArrayOutputStream outContent = new ByteArrayOutputStream();
       System.setOut(new PrintStream(outContent));

       String rawInput = "remove upc/1234 level/abcd";
       AlertParser alertParser = new AlertParser(rawInput, inventory);
       alertParser.run();

       String expectedOutput = "Wrong/Incomplete Entry For Remove Alert!";
       assertTrue(outContent.toString().contains(expectedOutput));


   }

}
