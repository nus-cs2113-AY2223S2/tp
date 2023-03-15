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
        //initialise BagPacker program
        Ui.initialMessage();

        //run main program
        runBagPacker();

        //Close program
        Ui.goodbyeMessage();
    }

    public static void runBagPacker() {
        Parser.receiveInput();
        while (!Parser.getCommand().equals("bye")) {
            switch (Parser.getCommand()) {
            case "add":
                //Add add function
                break;
            case "remove":
                //Add remove function
                break;
            case "pack":
                //Add pack function
                break;
            case "unpack":
                //Add unpack function
                break;
            case "list":
                //Add list function
                break;
            case "help":
                Ui.helpMessage();
                break;
            default:
                Ui.errorMessage("'" + Parser.getCommand() + "' is an invalid User Command",
                        "input 'help' to receive all valid commands");
                break;
            }

            Parser.receiveInput();
        }
        Storage.save();
    }
}
