package seedu.badMaths;

import java.util.ArrayList;

public class Notes {
    private String toDo;
    private ArrayList<String> cache = new ArrayList<>();
    public Notes(String toDo) {
        this.toDo = toDo;
    }

    public void setToDo(String toDo) {
        this.toDo = toDo;
    }

    public void handleCache(String input){

        switch(input) {
        case "Store":
            cache.add(toDo);
            System.out.println("You have stored: " + toDo);
            break;
        case "List":
            System.out.println("Here are the notes that you have stored:");
            for (int i = 0; i < cache.size(); i++) {
                String notesItem = cache.get(i);
                System.out.println((i + 1) + ". " + notesItem);
            }
            break;
        default:
        }
    }
}
