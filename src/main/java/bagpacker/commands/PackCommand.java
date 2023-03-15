package bagpacker.commands;

import bagpacker.iohandler.Ui;
import bagpacker.packingfunc.Item;
import bagpacker.packingfunc.PackingList;

public class PackCommand extends Command {

    public static final String MSG_SUCCESS_PACK = "Item packed: %s";
    public static final String MSG_USAGE_PACK = "pack : Marks an item as packed in the packing list.\n" +
            "Example: pack toothbrush";



    public PackCommand(int targetIndex) {
        super(targetIndex);
    }

    @Override
    public void execute(PackingList packingList) {
        this.packingList = packingList;
        final Item item = getTargetItem();
        packingList.packItem(item);
        Ui.printToUser(String.format(MSG_SUCCESS_PACK, item));
    }

}
