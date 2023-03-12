package bagpacker.commands;

import bagpacker.packingfunc.Item;
import bagpacker.packingfunc.PackingList;

import bagpacker.iohandler.Ui;

public class DeleteCommand extends Command {

    public static final String MSG_SUCCESS_DELETE = "%s removed from the list";

    public static final String MSG_USAGE_DELETE = "delete : Deletes an item from the packing list.\n" +
            "Example: delete toothbrush";

    private final Item item;

    public DeleteCommand(Item item) {
        this.item = item;
    }

    @Override
    public void execute(PackingList packingList) {
        this.packingList = packingList;
        packingList.deleteItem(item);
        Ui.printToUser(String.format(MSG_SUCCESS_DELETE, item));

    }
}
