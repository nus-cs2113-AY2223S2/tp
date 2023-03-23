package bagpacker.commands;

import bagpacker.iohandler.Ui;
import bagpacker.packingfunc.Item;
import bagpacker.packingfunc.PackingList;

public class PackCommand extends Command {
    private static int packQuantity;
    public static final String MSG_SUCCESS_PACK = "Item packed: %s";
    public static final String HELP_MSG= "pack : Marks an item as packed in the packing list.\n" +
            "\tExample: pack 2 of 3\n" +
            "\tMeaning: packs 2 quantities of the third item in the packing list";


    public PackCommand(int quantity, int targetIndex) {
        super(targetIndex);
        packQuantity = quantity;
        assert (targetIndex >= 1 & targetIndex <= PackingList.getItemList().size()):
                "Pack Command Target index is out of bounds";
    }

    @Override
    public void execute(PackingList packingList) {
        this.packingList = packingList;
        final Item item = getTargetItem();
        packingList.packItem(item, packQuantity);
        Ui.printToUser(String.format(MSG_SUCCESS_PACK, item));
    }

}
