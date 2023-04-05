package seedu.badmaths.commands;

import seedu.badmaths.IllegalTodoException;
import seedu.badmaths.Storage;
import seedu.badmaths.note.Note;
import seedu.badmaths.note.NotesList;
import seedu.badmaths.ui.Ui;

import java.util.ArrayList;

import static seedu.badmaths.Store.isInvalidTodo;

public class NotesFinder {
    private NotesList notes;
    private String filePath;

    public NotesFinder(NotesList notes, String filePath) {
        this.notes = notes;
        this.filePath = filePath;
    }

    public void find(String keyword) throws IllegalTodoException {
        if (isInvalidTodo(keyword)) {
            throw new IllegalTodoException();
        }

        ArrayList<Note> relevantNotes = notes.relevantInfo(keyword);
        Ui.printFindNotes(relevantNotes);
    }
}
