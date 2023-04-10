package seedu.duke;

import org.junit.jupiter.api.Test;
import seedu.duke.commands.Command;
import seedu.duke.objects.Item;
import seedu.duke.objects.Inventory;
import seedu.duke.commands.AddCommand;
import seedu.duke.commands.RemoveCommand;
import seedu.duke.utils.SessionManager;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RemoveTest {

    @Test
    void removeItemAtIndex() {
        Inventory inventory = new Inventory();
        Item item1 = new Item("appleA", "123", 000, 0.0);
        Item item2 = new Item("apples", "012345678", 5000, 12.0);
        Item item3 = new Item("oranges", "876543210", 3000, 0.32);
        Command addCommand = new AddCommand(inventory, item1);
        addCommand.run();
        Command addCommand1 = new AddCommand(inventory, item2);
        addCommand1.run();
        Command addCommand2 = new AddCommand(inventory, item3);
        addCommand2.run();

        Command removeCommand = new RemoveCommand(inventory, 2);
        removeCommand.run();

        assertEquals(2, inventory.getItemInventory().size());
    }

    @Test
    void removeByUpc() {
        Inventory inventory = new Inventory();
        Item item1 = new Item("appleA", "123", 100, 0.0);
        Item item2 = new Item("apples", "012345678", 5000, 12.0);
        Item item3 = new Item("oranges", "876543210", 3000, 0.32);

        Command addCommand = new AddCommand(inventory, item1);
        addCommand.run();
        Command addCommand1 = new AddCommand(inventory, item2);
        addCommand1.run();
        Command addCommand2 = new AddCommand(inventory, item3);
        addCommand2.run();

        Command removeCommand = new RemoveCommand(inventory,"876543210");
        removeCommand.run();
        assertEquals(2, inventory.getItemInventory().size());
        Command removeCommand1 = new RemoveCommand(inventory,"012345678");
        removeCommand1.run();
        assertEquals(1, inventory.getItemInventory().size());
        Command removeCommand2 = new RemoveCommand(inventory,"012345678");
        removeCommand2.run();
        assertEquals(1, inventory.getItemInventory().size());
        Command removeCommand3 = new RemoveCommand(inventory,"123");
        removeCommand3.run();
        assertEquals(0, inventory.getItemInventory().size());
    }

    @Test
    void removeByUpcMultipleTimes(){
        Inventory inventory = new Inventory();
        SessionManager.setAutoSave(false);
        for(int i=0;i<1000;i++){
            Command addCommand = new AddCommand(inventory, new Item("item1", "01",15,10.0, "normal"));
            addCommand.run();
            addCommand = new AddCommand(inventory, new Item("item2", "02",15,10.0, "normal"));
            addCommand.run();
            addCommand = new AddCommand(inventory, new Item("item3", "03",15,10.0, "normal"));
            addCommand.run();
            addCommand = new AddCommand(inventory, new Item("item4", "04",15,10.0, "normal"));
            addCommand.run();
            addCommand = new AddCommand(inventory, new Item("item5", "05",15,10.0, "normal"));
            addCommand.run();
            Command removeCommand = new RemoveCommand(inventory, "01");
            removeCommand.run();
            removeCommand = new RemoveCommand(inventory, "02");
            removeCommand.run();
            removeCommand = new RemoveCommand(inventory, "03");
            removeCommand.run();
            removeCommand = new RemoveCommand(inventory, "04");
            removeCommand.run();
            removeCommand = new RemoveCommand(inventory, "05");
            removeCommand.run();
            assertEquals(inventory.getItemInventory().size(),0);
        }
    }
}
