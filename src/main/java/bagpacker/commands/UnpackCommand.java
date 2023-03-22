package bagpacker.commands;

import bagpacker.iohandler.Ui;
import bagpacker.packingfunc.Item;
import bagpacker.packingfunc.PackingList;

public class UnpackCommand extends Command {

    public static final String MSG_SUCCESS_UNPACK = "Item unpacked: %s";
    public static final String HELP_MSG = "unpack : Marks an item as unpacked in the packing list.\n" +
            "\tExample: unpack toothbrush";


    public UnpackCommand(int targetIndex) {
        super(targetIndex);
        assert (targetIndex >= 1 & targetIndex <= PackingList.getItemList().size()):
                "Unpack Command Target index is out of bounds";
    }

    @Override
    public void execute(PackingList packingList) {
        this.packingList = packingList;
        final Item item = getTargetItem();
        packingList.unpackItem(item);
        Ui.printToUser(String.format(MSG_SUCCESS_UNPACK, item));
    }

}
