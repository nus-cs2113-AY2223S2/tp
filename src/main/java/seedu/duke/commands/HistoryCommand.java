package seedu.duke.commands;

import seedu.duke.objects.Inventory;
import seedu.duke.objects.Item;
import seedu.duke.utils.Ui;

import java.util.ArrayList;
import java.util.Collections;

public class HistoryCommand extends Command{
    String input;
    public HistoryCommand(Inventory inventory, String input){
        super(inventory);
        this.input = input;
    }

    /**
     * Gets a list of history results for an item.
     *
     * @return ArrayList Item of history results. If no results are found, returns null instead.
     */
    public ArrayList<Item> getHistoryResults(){
        if(!inventory.getUpcCodesHistory().containsKey(input)){
            return null;
        }
        ArrayList<Item> results = inventory.getUpcCodesHistory().get(input);
        Collections.sort(results);
        return results;
    }

    /**
     * Executes function to find results. If there are results, prints them. Else it prints that there are no items.
     *
     */
    @Override
    public void run() {
        ArrayList<Item> results = getHistoryResults();
        if(results!=null) {
            Ui.printHistory(results);
        }else{
            Ui.printItemNotFound();
        }
    }
}
