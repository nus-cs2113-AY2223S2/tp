package bagpacker;

import bagpacker.commands.ByeCommand;
import bagpacker.commands.Command;
import bagpacker.iohandler.Ui;
import bagpacker.iohandler.Parser;
import bagpacker.packingfunc.PackingList;

public class BagPacker {
    private static PackingList packingList;
    /**
     * Main entry-point for the java.BagPacker application.
     */
    public static void main(String[] args) {
        // initialise variables
        BagPacker.packingList = new PackingList();
        //initialise BagPacker program
        Ui.initialMessage();

        //run main program
        runBagPacker();

        //Close program
        Ui.goodbyeMessage();
    }

    public static void runBagPacker() {
        while (ByeCommand.isBagPackerRunning) {
            Command c = Parser.parse();
            c.execute(packingList);
        }
    }
}
