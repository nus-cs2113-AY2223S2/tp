package seedu.badmaths.storage;

import seedu.badmaths.note.Note;
import seedu.badmaths.note.NotePriority;

import java.util.ArrayList;

public class NotesFileContentManager {
    /**
     * Returns a string representing the contents of a list of notes.
     *
     * @param notes the list of notes to be represented by a string
     * @return a string representing the contents of the list of notes
     */
    public static String fileContent(ArrayList<Note> notes) {
        StringBuilder content = new StringBuilder();
        for(Note note : notes) {
            //priority
            NotePriority.Priority priority = note.getPriority();
            String priorityStr = priority.name();
            content.append(priorityStr).append("\t");
            //status
            boolean isDone = note.getIsDone();
            String isDoneStatus = "N";
            if(isDone) {
                isDoneStatus = "Y";
            }
            content.append(isDoneStatus).append("\t");
            //count
            int reviewCount = note.getReviewCount();
            String reviewCountStr = String.valueOf(reviewCount);
            content.append(reviewCountStr).append("\t");
            //text
            String noteText = note.getText();
            content.append(noteText);
            //line separator
            content.append(System.getProperty("line.separator"));
        }
        return content.toString();
    }
}


