package bagpacker.commands;

import bagpacker.packingfunc.PackingList;

public class ListCommand extends Command {
    public static final String HELP_MSG = "add : Adds an item to the packing list.\n" +
            "\tExample: add toothbrush";

    @Override
    public void execute(PackingList packingList) {

        if (packingList.size() == 0) {
            System.out.println("____________________________________________________________");
            System.out.println("There are no items in your list. What would you like to pack?");
            System.out.println("____________________________________________________________");
            return;
        }

        System.out.println("____________________________________________________________");
        System.out.println("Here are the items in your list");
        for (int i = 0; i < packingList.size(); i++) {
            String isItemPacked;
            if (packingList.get(i).isPacked()) {
                isItemPacked = "X";
            } else {
                isItemPacked = " ";
            }
            String itemName = packingList.get(i).getItemName();
            System.out.println((i + 1) + ". [" + isItemPacked + "] " + itemName);
        }
        System.out.println("____________________________________________________________");
    }
}
