package bagpacker.commands;

import bagpacker.iohandler.Ui;
import bagpacker.packingfunc.Item;
import bagpacker.packingfunc.PackingList;

public class PackCommand extends Command {

    public static final String MSG_SUCCESS_PACK = "Item packed: %s";
    public static final String HELP_MSG= "pack : Marks an item as packed in the packing list.\n" +
            "\tExample: pack toothbrush";


    public PackCommand(int targetIndex) {
        super(targetIndex);
        assert (targetIndex >= 1 & targetIndex <= PackingList.getItemList().size()):
                "Pack Command Target index is out of bounds";
    }

    @Override
    public void execute(PackingList packingList) {
        this.packingList = packingList;
        final Item item = getTargetItem();
        packingList.packItem(item);
        Ui.printToUser(String.format(MSG_SUCCESS_PACK, item));
    }

}
