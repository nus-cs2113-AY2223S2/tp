package bagpacker.commands;

import bagpacker.iohandler.Ui;
import bagpacker.packingfunc.Item;
import bagpacker.packingfunc.PackingList;

public class UnpackCommand extends Command {

    public static final String MSG_SUCCESS_UNPACK = "Item unpacked: %s";

    public static final String HELP_MSG = "unpack: Deducts from the current quantity of items packed in the packing " +
            "list.\n" +
            "\tExample: unpack 1 /of 2\n" +
            "\tMeaning: Unpacks 1 quantity of the second item in the packing list";
    private static int unpackQuantity;

    public UnpackCommand(int quantity, int targetIndex) {
        super(targetIndex);
        unpackQuantity = quantity;
        assert (targetIndex >= 1 & targetIndex <= PackingList.getItemList().size()) :
                "Unpack Command Target index is out of bounds";
    }

    @Override
    public void execute(PackingList packingList) {
        this.packingList = packingList;
        final Item item = getTargetItem();
        packingList.unpackItem(item, unpackQuantity);
        Ui.printToUser(String.format(MSG_SUCCESS_UNPACK, item));
    }

}
