package bagpacker.commands;

import bagpacker.iohandler.Ui;
import bagpacker.packingfunc.Item;
import bagpacker.packingfunc.PackingList;

import java.util.ArrayList;

public class ListUnpackedCommand extends Command{
    public static final String HELP_MSG =
            "listunpacked: List all items and their total quantities if they are not fully packed yet.\n"
                    + "\tExample: listunpacked";

    @Override
    public void execute(PackingList packingList) {
        ArrayList<Item> unpackedList = getUnpackedList();
        if (packingList.size() == 0) {
            Ui.printLine();
            System.out.println("There are no items in your list. What would you like to add?");
            Ui.printLine();
            return;
        }
        if (unpackedList.size() == 0) {
            Ui.printLine();
            System.out.println("All items in your list are fully packed!");
            Ui.printLine();
            return;
        }
        Ui.printLine();
        System.out.println("Here are the unpacked items in your list");
        for (int i = 0; i < unpackedList.size(); i++) {
            System.out.print((i + 1) + ". ");
            System.out.println(unpackedList.get(i));
        }
        Ui.printLine();
    }

    /**
     * Returns ArrayList of Item {@link Item} that are not yet packed by using
     *      checkFullyPacked: {@link Item#checkFullyPacked()}
     * @return list of items that are unpacked
     */
    private ArrayList<Item> getUnpackedList(){
        Item currItem;
        ArrayList<Item> unpackedList = new ArrayList<>();
        for (int i = 0; i < PackingList.getItemList().size(); i++) {
            currItem = PackingList.get(i);
            if (!currItem.checkFullyPacked()){
                unpackedList.add(currItem);
            }
        }
        return unpackedList;
    }
}
