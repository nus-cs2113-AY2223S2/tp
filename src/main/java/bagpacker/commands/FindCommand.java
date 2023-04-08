package bagpacker.commands;

import bagpacker.packingfunc.Item;
import bagpacker.packingfunc.PackingList;

import java.util.ArrayList;

import static bagpacker.iohandler.Ui.printToUser;

public class FindCommand extends Command {
    public static final String HELP_MSG =
            "find: Displays all items in the packingList as a list that contains the keyword used in the command.\n"
                    + "\tExample: find jacket";
    private static final String MSG_FIND_HEADER = "There are %d items(s) in your list containing %s: ";
    private static final String MSG_NO_FOUND_ITEM =
            "An item containing the description %s does not exist. Please try again.";
    private static String keyword;

    public FindCommand(String keyword) {
        FindCommand.keyword = keyword;
    }
    @Override
    public void execute(PackingList packingList) {
        if (PackingList.keywordFinder(keyword)) {
            ArrayList<String> foundList = new ArrayList<>();
            for (int i = 0; i < packingList.size(); i++) {
                Item item = PackingList.get(i);
                if (item.getItemName().contains(keyword)) {
                    foundList.add((i + 1) + ". " + item);
                }
            }

            foundList.add(0, String.format(MSG_FIND_HEADER, foundList.size(), keyword));
            printToUser(foundList.toArray(new String[0]));
        } else {
            printToUser(String.format(MSG_NO_FOUND_ITEM, keyword));
        }
    }
}
