package bagpacker.commands;

import bagpacker.iohandler.Ui;
import bagpacker.packingfunc.Item;
import bagpacker.packingfunc.PackingList;

public class EditQuantityCommand extends Command{
    public static final String MSG_SUCCESS_EDITQUANTITY = "Item total quantity edited: %s";

    public static final String HELP_MSG = "editquantity: Edit the total quantity of an item to be packed.\n" +
            "\tExample: editquantity 3 /of 2\n" +
            "\tMeaning: Change the total quantity of the second item to 3";

    private static int totalQuantity;

    public EditQuantityCommand(int newTotalQuantity, int targetIndex) {
        super(targetIndex);
        totalQuantity = newTotalQuantity;
    }

    @Override
    public void execute(PackingList packingList) {
        this.packingList = packingList;
        final Item item = getTargetItem();
        PackingList.editTotalQuantity(item, totalQuantity);
        Ui.printToUser(String.format(MSG_SUCCESS_EDITQUANTITY, item));
    }

}
