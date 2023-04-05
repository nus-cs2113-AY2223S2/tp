package bagpacker.commands;

import bagpacker.iohandler.Ui;
import bagpacker.packingfunc.Item;
import bagpacker.packingfunc.PackingList;

public class AddCommand extends Command {
    public static final String MSG_SUCCESS_ADD = "New item added: %s";
    public static final String MSG_SUCCESS_ADD_EXISTING = "Add to quantity of existing item: %s";

    public static final String HELP_MSG = "add: Adds quantity and name of item to the packing list.\n" +
            "\tExample: add 3 /of toothbrush\n" +
            "\tMeaning: Add quantity of 3 toothbrushes to the packing list";

    private final Item item;

    public AddCommand(Item item) {
        this.item = item;
    }

    @Override
    public void execute(PackingList packingList) {
        if(PackingList.itemFinder(item.getItemName())){
            PackingList.addToItemQuantity(item.getItemName(), item.getTotalQuantity());
            Ui.printToUser(String.format(MSG_SUCCESS_ADD_EXISTING, PackingList.getExistingItem(item.getItemName())));
            return;
        }
        this.packingList = packingList;
        packingList.addItem(item);
        Ui.printToUser(String.format(MSG_SUCCESS_ADD, item));
    }
}
