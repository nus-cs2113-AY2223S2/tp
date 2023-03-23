package seedu.badMaths;

import seedu.badMaths.ui.Ui;

import java.util.ArrayList;

public class Notes {
    private static final String filePath = "data/notes.txt";
    private String toDo;
    private ArrayList<String> cache;
    public Notes(String toDo) {
        this.toDo = toDo;
    }
    public void setToDo(String toDo) {
        this.toDo = toDo;
    }

    public void handleCache(String input){
        assert (input.equals("Store") || input.equals("List")): "input should either be Store or List";
        cache = new ArrayList<>(Storage.loadFile(filePath));
        switch(input) {
        case "Store":
            cache.add(toDo);
            System.out.println("You have stored: " + toDo);
            Storage.saveFile(filePath, cache);
            break;
        case "List":
            Ui.printNotes(cache);
            break;
        default:
        }
    }
}
