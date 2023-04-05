package bagpacker.commands;

import bagpacker.iohandler.Ui;
import bagpacker.packingfunc.Item;
import bagpacker.packingfunc.PackingList;

public class PackAllCommand extends Command {

    public static final String MSG_SUCCESS_PACKALL = "Item packed: %s";

    public static final String HELP_MSG = "packall : Marks all quantity of the specified item as packed in the " +
            "packing list.\n" +
            "\tExample: packall /of 3\n" +
            "\tMeaning: packs all of the quantities of the third item in the packing list";

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
