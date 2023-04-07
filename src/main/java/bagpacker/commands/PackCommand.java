package bagpacker.commands;

import bagpacker.iohandler.Ui;
import bagpacker.packingfunc.Item;
import bagpacker.packingfunc.PackingList;
/**
 * PackCommand class is used to pack an item in the packing list by adding to the packed quantity of that item
 */
public class PackCommand extends Command {

    public static final String MSG_SUCCESS_PACK = "Item packed: %s";
    public static final String HELP_MSG = "pack: Adds to the current quantity of items packed in the packing list.\n" +
            "\tExample: pack 2 /of 3\n" +
            "\tMeaning: Packs 2 more quantities of the third item in the packing list";

    private static int packQuantity;
    /***
     * Constructor for PackCommand
     * @param quantity amount to add to the current packed quantity of the given item
     * @param targetIndex item index in packing list of the item to pack to
     */
    public PackCommand(int quantity, int targetIndex) {
        super(targetIndex);
        packQuantity = quantity;
        assert (targetIndex >= 1 & targetIndex <= PackingList.getItemList().size()) :
                "Pack Command Target index is out of bounds";
    }
    /**
     * Adds to the total packed quantity of an item in the packing list and prints a successfully packed message when
     *      done
     *
     * @param packingList list containing all items
     */
    @Override
    public void execute(PackingList packingList) {
        this.packingList = packingList;
        final Item item = getTargetItem();
        packingList.packItem(item, packQuantity);
        Ui.printToUser(String.format(MSG_SUCCESS_PACK, item));
    }

}
