package bagpacker.commands;

import bagpacker.packingfunc.Item;
import bagpacker.packingfunc.PackingList;

import bagpacker.iohandler.Ui;
/**
 * DeleteCommand class deletes an item in the packing list
 */
public class DeleteCommand extends Command {

    public static final String MSG_SUCCESS_DELETE = "%s removed from the list";

    public static final String HELP_MSG = "delete: Deletes an item from the packing list.\n" +
            "\tExample: delete 1\n" +
            "\tMeaning: Removes the first item in the packing list";

    /**
     * Constructor for DeleteCommand
     * @param targetIndex item index in packing list of the item to be deleted
     */
    public DeleteCommand(int targetIndex) {
        super(targetIndex);
    }
    /**
     * Deletes a given item in the packing list by its packing list index, and prints a successfully deleted message
     *      when done
     *
     * @param packingList list containing the item to be deleted
     */
    @Override
    public void execute(PackingList packingList) {
        Item item = getTargetItem();
        this.packingList = packingList;
        packingList.deleteItem(item);
        Ui.printToUser(String.format(MSG_SUCCESS_DELETE, item));
    }
}
