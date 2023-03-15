package bagpacker;

import bagpacker.iohandler.Storage;
import bagpacker.iohandler.Ui;
import bagpacker.iohandler.Parser;
import bagpacker.packingfunc.PackingList;

public class BagPacker {
    private static PackingList packingList;
    /**
     * Main entry-point for the java.BagPacker application.
     */
    public static void main(String[] args) {
        // intialise variables
        PackingList packingList = new PackingList();
        BagPacker.packingList = packingList;
        //initialise BagPacker program
        Ui.initialMessage();

        //run main program
        runBagPacker();

        //Close program
        Ui.goodbyeMessage();
    }

    public static void runBagPacker() {

        while (true) {

        }
        Storage.save();
    }
}
