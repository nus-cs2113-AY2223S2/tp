package bagpacker.packingfunctest;

import bagpacker.commands.AddCommand;
import bagpacker.commands.Command;
import bagpacker.packingfunc.Item;
import bagpacker.packingfunc.PackingList;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * CommandsTest to test methods in Commands class
 */
public class CommandsTest {
    @Test
    public void sampleTest() {
        assertTrue(true);
    }

    /*@Test
    public void testAddCommand() {
        PackingList packingList = new PackingList();
        Item itemOne = new Item("toothbrush");
        Command addObj = new AddCommand(itemOne);
        addObj.execute(packingList);

        // Expect a Packinglist with a toothbrush item in first index
        Assertions.assertEquals("toothbrush",packingList.get(0).getItemName());
        Assertions.assertNotEquals("tooth", packingList.get(0).getItemName());

        // Expect second index item in the packing list to be toothpaste
        Item itemTwo = new Item("toothpaste");
        addObj = new AddCommand(itemTwo);
        addObj.execute(packingList);

        Assertions.assertEquals("toothpaste",packingList.get(1).getItemName());
        Assertions.assertNotEquals("toothbrush", packingList.get(1).getItemName());

    }*/

}
