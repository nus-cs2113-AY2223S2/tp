package seedu.badMaths;

import java.util.ArrayList;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;


class NotesTest { // this class contains the various test methods
    ArrayList<String> cache = new ArrayList<>();
    @Test
    public void handleCache() {
        String toDo = "toDo";
        String input = "Notes";
        switch (input) {
        case "Notes":
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
        assertEquals("You have stored: toDo", "You have stored: " + toDo);
    }
}
