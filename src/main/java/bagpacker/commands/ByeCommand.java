package bagpacker.commands;

/**
 * ByeCommand class stops the BagPacker application by changing the run status
 */
public class ByeCommand extends Command {
    public static boolean isBagPackerRunning = true;
    public static final String HELP_MSG = "bye : Stops the BagPacker Application\n" +
            "\tExample: bye";
    public ByeCommand(){
        this.isBagPackerRunning = false;
    }

    public boolean getIsRunning() {
        return isBagPackerRunning;
    }

}
