package bagpacker.commands;

import bagpacker.iohandler.Ui;
import bagpacker.packingfunc.Item;
import bagpacker.packingfunc.PackingList;

public class UnpackCommand extends Command {

    public static final String MSG_SUCCESS_UNPACK = "Item unpacked: %s";
    public static final String MSG_USAGE_UNPACK = "pack : Marks an item as unpacked in the packing list.\n" +
            "Example: unpack toothbrush";


    public UnpackCommand(int targetIndex) {
        super(targetIndex);
    }

    @Override
    public void execute(PackingList packingList) {
        this.packingList = packingList;
        final Item item = getTargetItem();
        packingList.unpackItem(item);
        Ui.printToUser(String.format(MSG_SUCCESS_UNPACK, item));
    }

}
