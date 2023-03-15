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
        Parser.receiveInput();
        while (!Parser.getCommand().equals("bye")) {
            switch (Parser.getCommand()) {
            case "add":
                String itemDescrip;
                //Add add function
                itemDescrip = Parser.getItemDescrip();
                Parser.addItem(itemDescrip, packingList);
                break;
            case "remove":
                //Add remove function
                itemDescrip = Parser.getItemDescrip();
                Parser.removeItem(itemDescrip, packingList);
                break;
            case "pack":
                //Add pack function
                itemDescrip = Parser.getItemDescrip();
                Parser.packItem(itemDescrip, packingList);
                break;
            case "unpack":
                //Add unpack function
                itemDescrip = Parser.getItemDescrip();
                Parser.unpackItem(itemDescrip, packingList);
                break;
            case "list":
                //Add list function
                Parser.displayList(packingList);
                break;
            case "help":
                Ui.helpMessage();
                break;
            case "deleteall":
                Parser.deleteList(packingList);
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
