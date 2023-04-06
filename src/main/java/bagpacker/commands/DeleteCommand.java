package bagpacker.commands;

import bagpacker.packingfunc.Item;
import bagpacker.packingfunc.PackingList;

import bagpacker.iohandler.Ui;

public class DeleteCommand extends Command {

    public static final String MSG_SUCCESS_DELETE = "%s removed from the list";

    public static final String HELP_MSG = "delete: Deletes an item from the packing list.\n" +
            "\tExample: delete 1\n" +
            "\tMeaning: Removes the first item in the packing list";


    public DeleteCommand(int targetIndex) {
        super(targetIndex);
    }

    @Override
    public void execute(PackingList packingList) {
        Item item = getTargetItem();
        this.packingList = packingList;
        packingList.deleteItem(item);
        Ui.printToUser(String.format(MSG_SUCCESS_DELETE, item));
    }
}
