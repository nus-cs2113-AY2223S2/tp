package bagpacker.commands;

import bagpacker.iohandler.Ui;
import bagpacker.packingfunc.Item;
import bagpacker.packingfunc.PackingList;
/**
 * UnpackAllCommand class is used to set an item as fully unpacked by setting the packed quantity of that item to equal
 *      to 0
 */
public class UnpackAllCommand extends Command {

    public static final String MSG_SUCCESS_UNPACKALL = "Item unpacked: %s";

    public static final String HELP_MSG = "unpackall: Fully unpacks an item of choice from the packing list.\n" +
            "\tExample: unpackall /of 3\n" +
            "\tMeaning: Set the third item in the packing list as totally not packed yet";

    /**
     * Constructor for UnpackAllCommand
     * @param targetIndex item index in packing list of the item to set as totally unpacked
     */
    public UnpackAllCommand(int targetIndex) {
        super(targetIndex);

        assert (targetIndex >= 1 & targetIndex <= PackingList.getItemList().size()) :
                "Unpackall Command Target index is out of bounds";
    }
    /**
     * Fully unpacks a given item in the packing list and prints a successfully UnpackAll message when
     *      done
     *
     * @param packingList list containing all items
     */
    @Override
    public void execute(PackingList packingList) {
        this.packingList = packingList;
        final Item item = getTargetItem();
        int unpackQuantity = item.getPackedQuantity();
        packingList.unpackItem(item, unpackQuantity);
        Ui.printToUser(String.format(MSG_SUCCESS_UNPACKALL, item));
    }

}

