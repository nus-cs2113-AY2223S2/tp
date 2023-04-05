package seedu.badmaths.commands;

import seedu.badmaths.IllegalTodoException;
import seedu.badmaths.note.Note;
import seedu.badmaths.note.NotesList;
import seedu.badmaths.ui.Ui;

import java.util.ArrayList;

public class NotesPriorityFinder {
    private NotesList notes;
    private String filePath;

    public NotesPriorityFinder(NotesList notes, String filePath) {
        this.notes = notes;
        this.filePath = filePath;
    }

    public void find(String priorityStr) throws IllegalTodoException{
        switch (priorityStr.toLowerCase()) {
        case "low":
        case "medium":
        case "high":
            ArrayList<Note> relevantNotes = notes.relevantPriority(priorityStr.toUpperCase());
            Ui.printFindNotes(relevantNotes);
            break;
        default:
            throw new IllegalTodoException();
        }
    }
}
