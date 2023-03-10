package seedu.badMaths;

import java.util.ArrayList;

public class Notes {
    private String toDo;
    private ArrayList<String> cache = new ArrayList<>();
    public Notes(String toDo) {
        this.toDo = toDo;
    }

    public void handleCache(String input){

        switch(input) {
        case "store":
            cache.add(toDo);
            System.out.println("You have stored " + toDo + " to the list");
            break;
        case "list":
            System.out.println("Here are the notes that you have stored:");
            for (int i = 0; i < cache.size(); i++) {
                String notesItem = cache.get(i);
                System.out.println((i + 1) + ". " + notesItem);
            }
            break;
        }

    }
}
