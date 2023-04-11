package seedu.badmaths.commands;

import seedu.badmaths.note.NotesList;

public class IndexChecker {
    public static boolean isInvalidIndex(int index, NotesList notes) {
        return index < 0 || index >= notes.getSize();
    }
}
