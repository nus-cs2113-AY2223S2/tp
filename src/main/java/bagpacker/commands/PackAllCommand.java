package bagpacker.commands;

import bagpacker.iohandler.Ui;
import bagpacker.packingfunc.Item;
import bagpacker.packingfunc.PackingList;

public class PackAllCommand extends Command {

    public static final String MSG_SUCCESS_PACKALL = "Item packed: %s";

    public static final String HELP_MSG = "packall: Fully pack an item of choice from the packing list.\n" +
            "\tExample: packall /of 3\n" +
            "\tMeaning: Set the third item in the packing list as fully packed";

    private static int packQuantity;

    public PackAllCommand(int targetIndex) {
        super(targetIndex);

        assert (targetIndex >= 1 & targetIndex <= PackingList.getItemList().size()) :
                "Packall Command Target index is out of bounds";
    }

    @Override
    public void execute(PackingList packingList) {
        this.packingList = packingList;
        final Item item = getTargetItem();
        packQuantity = item.getUnpackedQuantity();
        packingList.packItem(item, packQuantity);
        Ui.printToUser(String.format(MSG_SUCCESS_PACKALL, item));
    }

}
