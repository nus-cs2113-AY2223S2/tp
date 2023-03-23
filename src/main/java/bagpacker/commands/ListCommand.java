package bagpacker.commands;

import bagpacker.packingfunc.PackingList;

public class ListCommand extends Command {
    public static final String HELP_MSG = "list : List all items in packing list.\n" +
            "\tExample: list";

    @Override
    public void execute(PackingList packingList) {

        if (packingList.size() == 0) {
            System.out.println("____________________________________________________________");
            System.out.println("There are no items in your list. What would you like to add?");
            System.out.println("____________________________________________________________");
            return;
        }

        System.out.println("____________________________________________________________");
        System.out.println("Here are the items in your list");
        for (int i = 0; i < packingList.size(); i++) {
            System.out.print((i + 1) + ". ");
            System.out.println(PackingList.get(i));
        }
        System.out.println("____________________________________________________________");
    }
}
