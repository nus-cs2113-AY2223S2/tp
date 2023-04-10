package bagpacker.commands;

import bagpacker.iohandler.Ui;
import bagpacker.packingfunc.Item;
import bagpacker.packingfunc.PackingList;
/**
 * UnpackCommand class is used to unpack an item in the packing list by reducing from the packed quantity of that item
 */
public class UnpackCommand extends Command {

    public static final String MSG_SUCCESS_UNPACK = "Item unpacked: %s";

    public static final String HELP_MSG = "unpack: Deducts from the current quantity of items packed in the packing " +
            "list.\n" +
            "\tExample: unpack 1 /of 2\n" +
            "\tMeaning: Unpacks 1 quantity of the second item in the packing list";
    private static int unpackQuantity;
    /**
     * Constructor for UnpackCommand
     * @param quantity amount to reduce from the current packed quantity of the given item
     * @param targetIndex item index in packing list of the item to unpack from
     */
    public UnpackCommand(int quantity, int targetIndex) {
        super(targetIndex);
        unpackQuantity = quantity;
        assert (targetIndex >= 1 & targetIndex <= PackingList.getItemList().size()) :
                "Unpack Command Target index is out of bounds";
    }
    /**
     * Removes from the total packed quantity of an item in the packing list and prints a successfully unpacked
     * message when done
     *
     * @param packingList list containing all items
     */
    @Override
    public void execute(PackingList packingList) {
        this.packingList = packingList;
        final Item item = getTargetItem();
        packingList.unpackItem(item, unpackQuantity);
        Ui.printToUser(String.format(MSG_SUCCESS_UNPACK, item));
    }

}
