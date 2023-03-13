package bagpacker.commands;

import bagpacker.iohandler.Ui;
import bagpacker.packingfunc.Item;
import bagpacker.packingfunc.PackingList;

public class PackCommand extends Command {

    public static final String MSG_SUCCESS_PACK = "Item packed: %s";
    public static final String MSG_USAGE_PACK = "pack : Marks an item as packed in the packing list.\n" +
            "Example: pack toothbrush";

    private final Item item;

    public PackCommand(Item item) {
        this.item = item;
    }

    @Override
    public void execute(PackingList packingList) {
        this.packingList = packingList;
        packingList.packItem(item);
        Ui.printToUser(String.format(MSG_SUCCESS_PACK, item));
    }

}
