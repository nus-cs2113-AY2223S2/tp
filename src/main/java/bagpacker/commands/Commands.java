package bagpacker.commands;

import bagpacker.iohandler.Ui;
import bagpacker.packingfunc.Item;
import bagpacker.packingfunc.PackingList;

/**
 * Commands class used to execute given commands from user
 */
public abstract class Commands {
    protected PackingList packingList;
    private int targetIndex = -1;

    /**
     * @param targetIndex listing index of the target item
     */
    public Commands(int targetIndex) {
        this.setTargetIndex(targetIndex);
    }

    protected Commands() {
    }

    /**
     * Executes the command.
     */
    public void execute(Item item, Ui ui) {
    };

    public void setTargetIndex(int targetIndex) {
        this.targetIndex = targetIndex;
    }
    public int getTargetIndex() {
        return targetIndex;
    }

    /**
     * Extracts the target item in the list.
     *
     * @throws IndexOutOfBoundsException if the target index is out of bounds
     */
    protected Item getTargetItem() throws IndexOutOfBoundsException {
        return packingList.get(getTargetIndex() - 1);
    }

    public abstract void execute(PackingList packingList);
}