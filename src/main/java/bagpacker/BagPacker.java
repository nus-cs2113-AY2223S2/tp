package bagpacker;

import bagpacker.commands.ByeCommand;
import bagpacker.commands.Command;
import bagpacker.iohandler.Storage;
import bagpacker.iohandler.Ui;
import bagpacker.iohandler.Parser;
import bagpacker.packingfunc.PackingList;

import java.io.FileNotFoundException;

public class BagPacker {
    private static PackingList packingList;

    /**
     * Main entry-point for the java.BagPacker application.
     */
    public static void main(String[] args) {
        // Initialise variables
        BagPacker.packingList = new PackingList();
        Ui.initialMessage();
        Storage storage = new Storage("./packing_list.txt");

        // load save file if possible
        try {
            Storage.load();
            Ui.showUserReturn();
        } catch (FileNotFoundException | RuntimeException e) {
            Ui.showNewUser();
        }

        // run main program
        runBagPacker();

        // save packingList
        Storage.save(packingList);

        // Close program
        Ui.goodbyeMessage();
    }

    public static void runBagPacker() {
        while (ByeCommand.isBagPackerRunning) {
            Command c = Parser.parse();
            c.execute(packingList);
        }
    }
}
