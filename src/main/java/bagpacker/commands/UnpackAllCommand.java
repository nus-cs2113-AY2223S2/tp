package bagpacker.commands;

import bagpacker.iohandler.Ui;
import bagpacker.packingfunc.Item;
import bagpacker.packingfunc.PackingList;

public class UnpackAllCommand extends Command {

    public static final String MSG_SUCCESS_UNPACKALL = "Item unpacked: %s";

    public static final String HELP_MSG = "unpackall : Marks all quantity of the specified item as unpacked in the " +
            "packing list.\n" +
            "\tExample: unpackall /of 3\n" +
            "\tMeaning: unpacks all of the quantities of the third item in the packing list";

    private static int unpackQuantity;

    public UnpackAllCommand(int targetIndex) {
        super(targetIndex);

        assert (targetIndex >= 1 & targetIndex <= PackingList.getItemList().size()) :
                "Unpackall Command Target index is out of bounds";
    }

    @Override
    public void execute(PackingList packingList) {
        this.packingList = packingList;
        final Item item = getTargetItem();
        unpackQuantity = item.getPackedQuantity();
        packingList.unpackItem(item, unpackQuantity);
        Ui.printToUser(String.format(MSG_SUCCESS_UNPACKALL, item));
    }

}

