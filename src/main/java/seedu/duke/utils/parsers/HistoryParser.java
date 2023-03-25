package seedu.duke.utils.parsers;

import seedu.duke.exceptions.MissingParametersException;
import seedu.duke.objects.Inventory;
import seedu.duke.objects.Item;
import seedu.duke.utils.Ui;

import java.util.ArrayList;
import java.util.Collections;

public class HistoryParser extends Parser{
    public HistoryParser(String rawInput, Inventory inventory){
        super(rawInput, inventory);
    }
    private ArrayList<Item> getHistoryResults(){
        if(!inventory.getUpcCodesHistory().containsKey(rawInput)){
            return null;
        }
        ArrayList<Item> results = inventory.getUpcCodesHistory().get(rawInput);
        Collections.sort(results);
        return results;
    }
    @Override
    public void run() {
        rawInput = rawInput.trim();
        try{
            if(rawInput.split(" ").length!=1){
                throw new MissingParametersException();
            }
        }catch(MissingParametersException e){
            e.missingHistoryItemParameters();
            return;
        }
        ArrayList<Item> results = getHistoryResults();
        if(results!=null) {
            Ui.printHistory(results);
        }else{
            Ui.printItemNotFound();
        }
    }
}
