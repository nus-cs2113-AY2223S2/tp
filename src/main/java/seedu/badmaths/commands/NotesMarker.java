package seedu.badmaths.commands;

import seedu.badmaths.IllegalIndexException;
import seedu.badmaths.note.NotesList;
import seedu.badmaths.storage.NotesFileWriter;
import seedu.badmaths.ui.Ui;

import static seedu.badmaths.commands.IntegerChecker.isAnInt;
import static seedu.badmaths.commands.IndexChecker.isInvalidIndex;

public class NotesMarker {
    private NotesList notes;
    private String filePath;

    public NotesMarker(NotesList notes, String filePath) {
        this.notes = notes;
        this.filePath = filePath;
    }

    public void mark(String toDo) throws IllegalIndexException {
        if (!isAnInt(toDo)) {
            return;
        }

        int markIndex = Integer.parseInt(toDo) - 1;
        if (isInvalidIndex(markIndex, notes)) {
            throw new IllegalIndexException();
        }

        notes.markAsDone(markIndex);
        Ui.printMark(notes.getText(markIndex));
        NotesFileWriter.saveFile(filePath, notes.getAll());
    }

    public void unmark(String toDo) throws IllegalIndexException {
        if (!isAnInt(toDo)) {
            return;
        }

        int unmarkIndex = Integer.parseInt(toDo) - 1;
        if (isInvalidIndex(unmarkIndex, notes)) {
            throw new IllegalIndexException();
        }

        notes.markAsUndone(unmarkIndex);
        Ui.printUnmark(notes.getText(unmarkIndex));
        NotesFileWriter.saveFile(filePath, notes.getAll());
    }

}
