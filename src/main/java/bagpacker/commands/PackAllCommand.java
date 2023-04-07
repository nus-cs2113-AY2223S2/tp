package bagpacker.commands;

import bagpacker.iohandler.Ui;
import bagpacker.packingfunc.Item;
import bagpacker.packingfunc.PackingList;
/**
 * PackAllCommand class is used to set an item as fully packed by setting the packed quantity of that item to equal the
 *      total quantity of the item
 */
public class PackAllCommand extends Command {

    public static final String MSG_SUCCESS_PACKALL = "Item packed: %s";

    public static final String HELP_MSG = "packall: Fully pack an item of choice from the packing list.\n" +
            "\tExample: packall /of 3\n" +
            "\tMeaning: Set the third item in the packing list as fully packed";

    /**
     * Constructor for PackAllCommand
     * @param targetIndex item index in packing list of the item to set as fully packed
     */
    public PackAllCommand(int targetIndex) {
        super(targetIndex);

        assert (targetIndex >= 1 & targetIndex <= PackingList.getItemList().size()) :
                "Packall Command Target index is out of bounds";
    }
    /**
     * Fully packs a given item in the packing list and prints a successfully PackAll message when
     *      done
     *
     * @param packingList list containing all items
     */
    @Override
    public void execute(PackingList packingList) {
        this.packingList = packingList;
        final Item item = getTargetItem();
        int packQuantity = item.getUnpackedQuantity();
        packingList.packItem(item, packQuantity);
        Ui.printToUser(String.format(MSG_SUCCESS_PACKALL, item));
    }

}
