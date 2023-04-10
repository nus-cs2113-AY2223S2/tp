package bagpacker.commands;

import bagpacker.iohandler.Ui;
import bagpacker.packingfunc.Item;
import bagpacker.packingfunc.PackingList;

/**
 * AddCommand class adds an item to the packing list, however if the item already exists in the packing list, the
 *      new input quantity will be added to the total quantity of that item
 */
public class AddCommand extends Command {
    public static final String MSG_SUCCESS_ADD = "New item added: %s";
    public static final String MSG_SUCCESS_ADD_EXISTING = "Add to quantity of existing item: %s";

    public static final String HELP_MSG = "add: Adds quantity and name of item to the packing list.\n" +
            "\tExample: add 3 /of toothbrush\n" +
            "\tMeaning: Add quantity of 3 toothbrushes to the packing list";

    private final Item item;
    /**
     * Constructor for AddCommand
     * @param item item to be added to list
     */
    public AddCommand(Item item) {
        this.item = item;
    }
    /**
     * Either adds a new item or if the item already exists, changes the total quantity of an existing item
     * - item is an AddCommand object level variable
     * - once successfully completed, Ui message will be printed
     *
     * @param packingList item list to add new item or to change current quantity of existing item
     */
    @Override
    public void execute(PackingList packingList) {
        if (PackingList.itemFinder(item.getItemName())) {
            PackingList.addToItemQuantity(item.getItemName(), item.getTotalQuantity());
            Ui.printToUser(String.format(MSG_SUCCESS_ADD_EXISTING, PackingList.getExistingItem(item.getItemName())));
            return;
        }
        this.packingList = packingList;
        packingList.addItem(item);
        Ui.printToUser(String.format(MSG_SUCCESS_ADD, item));
    }
}
