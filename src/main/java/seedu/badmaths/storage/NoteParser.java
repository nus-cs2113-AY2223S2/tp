package seedu.badmaths.storage;

import seedu.badmaths.InvalidFormatException;
import seedu.badmaths.note.Note;
import seedu.badmaths.note.NotePriority;

import java.util.Set;

public class NoteParser {
    public static Note parseNoteString(String noteString) throws InvalidFormatException{

        //text format: Priority \\t Status \\t Count \\t Note
        String[] noteInfo = noteString.split("\\t");

        if (noteInfo.length != 4) {
            throw new InvalidFormatException();
        }

        //priority
        String priorityStr = noteInfo[0];
        Set<String> validPriorities = Set.of("LOW", "MEDIUM", "HIGH");
        if (!validPriorities.contains(priorityStr)) {
            //priorityStr is invalid, handle the error
            throw new InvalidFormatException();
        }
        NotePriority.Priority priority = NotePriority.Priority.valueOf(priorityStr);

        //note
        String noteStr = noteInfo[3];

        //count
        String reviewCountStr = noteInfo[2];
        int reviewCount;
        //reviewCountStr is invalid, handle the error
        try {
            reviewCount = Integer.parseInt(reviewCountStr);
        } catch (NumberFormatException e) {
            throw new InvalidFormatException();
        }

        //status
        String isDoneStr = noteInfo[1];
        Set<String> validIsDoneStatus = Set.of("Y", "N");
        if(!validIsDoneStatus.contains(isDoneStr)){
            //isDoneStr is invalid, handle the error
            throw new InvalidFormatException();
        }
        boolean isDone = isDoneStr.equals("Y");

        /*
         * update notes if the file is valid
         */
        //update text, priority
        Note note = new Note(noteStr, priority);
        //update status
        if(isDone) {
            note.markAsDone();
        } else {
            note.markAsNotDone();
        }
        //update count
        note.setReviewCount(reviewCount);

        //add note
        return note;
    }
}
