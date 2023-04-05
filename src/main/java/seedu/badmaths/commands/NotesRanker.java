package seedu.badmaths.commands;

import seedu.badmaths.IllegalTodoException;
import seedu.badmaths.note.NotesList;
import seedu.badmaths.storage.NotesFileWriter;
import seedu.badmaths.ui.Ui;

public class NotesRanker {
    private NotesList notes;
    private String filePath;

    public NotesRanker(NotesList notes, String filePath) {
        this.notes = notes;
        this.filePath = filePath;
    }

    public void rank(String toDo) throws IllegalTodoException {
        switch (toDo) {
        case "Review Count":
            notes.rankByReviewCount();
            Ui.printNotesByReviewCount(notes.getAll());
            NotesFileWriter.saveFile(filePath, notes.getAll());
            break;
        case "Priority":
            notes.rankByPriority();
            Ui.printNotesByPriority(notes.getAll());
            NotesFileWriter.saveFile(filePath, notes.getAll());
            break;
        default:
            throw new IllegalTodoException();
        }
    }

}
