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
    public ArrayList<Item> getHistoryResults(){
        if(!inventory.getUpcCodesHistory().containsKey(input)){
            return null;
        }
        ArrayList<Item> results = inventory.getUpcCodesHistory().get(input);
        Collections.sort(results);
        return results;
    }
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
