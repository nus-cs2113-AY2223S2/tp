package bagpacker.commands;

import bagpacker.packingfunc.PackingList;

/**
 * ByeCommand class stops the BagPacker application by changing the run status
 */
public class ByeCommand extends Command {
    public static boolean isBagPackerRunning = true;
    public static final String HELP_MSG = "bye: Stops the BagPacker Application\n" +
            "\tExample: bye";
    /**
     * Stops the BagPacker app from running when the isBagPackerRunning boolean to false
     *
     * @param packingList list of all items in packing list
     */
    @Override
    public void execute(PackingList packingList) {
        this.isBagPackerRunning = false;
    }
}
