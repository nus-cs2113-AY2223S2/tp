package seedu.badmaths.commands;

import seedu.badmaths.IllegalIndexException;
import seedu.badmaths.note.NotesList;
import seedu.badmaths.note.NotePriority;
import seedu.badmaths.storage.NotesFileWriter;
import seedu.badmaths.ui.Ui;

import static seedu.badmaths.commands.IntegerChecker.isAnInt;
import static seedu.badmaths.commands.IndexChecker.isInvalidIndex;

public class PrioritySetter {
    private NotesList notes;
    private String filePath;

    public PrioritySetter(NotesList notes, String filePath) {
        this.notes = notes;
        this.filePath = filePath;
    }

    public void setPriority(String priorityString, String toDo) throws IllegalIndexException {
        if (!isAnInt(toDo)) {
            return;
        }

        int index = Integer.parseInt(toDo) - 1;
        if (isInvalidIndex(index, notes)) {
            throw new IllegalIndexException();
        }

        NotePriority.Priority priority = NotePriority.Priority.valueOf(priorityString.toUpperCase());

        notes.setPriority(index, priority);
        Ui.printPriority(index, notes.getAll());
        NotesFileWriter.saveFile(filePath, notes.getAll());
    }
}
