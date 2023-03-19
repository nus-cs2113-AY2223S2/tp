package bagpacker.packingfunctest;

import bagpacker.commands.AddCommand;
import bagpacker.commands.Command;
import bagpacker.packingfunc.Item;
import bagpacker.packingfunc.PackingList;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * CommandsTest to test methods in Commands class
 */
public class CommandsTest {
    @Test
    public void sampleTest() {
        assertTrue(true);
    }

    @Test
    public void testAddCommand() {
        PackingList packingList = new PackingList();
        Item item = new Item("toothbrush");
        Command addCommand = new AddCommand(item);
        addCommand.execute(packingList);

        // Expect a Packinglist with a toothbrush item in first index
        Assertions.assertEquals("toothbrush",packingList.get(0).getItemName());
        Assertions.assertNotEquals("tooth", packingList.get(0).getItemName());

        // Expect second index item in the packing list to be toothpaste
        Item item_two = new Item("toothpaste");
        addCommand = new AddCommand(item_two);
        addCommand.execute(packingList);

        Assertions.assertEquals("toothpaste",packingList.get(1).getItemName());
        Assertions.assertNotEquals("toothbrush", packingList.get(1).getItemName());

    }

}
