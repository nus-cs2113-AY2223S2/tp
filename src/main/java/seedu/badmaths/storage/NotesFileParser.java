package seedu.badmaths.storage;

import seedu.badmaths.InvalidFormatException;
import seedu.badmaths.note.Note;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import static seedu.badmaths.storage.NoteParser.parseNoteString;

public class NotesFileParser {
    /**
     * Loads the contents of a notes file at the specified path
     * and returns a list of Note objects representing the file's contents.
     *
     * @param path the path of the notes file to be loaded
     * @return a list of Note objects representing the contents of the specified file
     */
    public static ArrayList<Note> loadFile(String path) {
        ArrayList<Note> notes = new ArrayList<>();
        try {
            Scanner scanner = new Scanner(new File(path));
            String noteScanner;
            while (scanner.hasNextLine()) {
                noteScanner = scanner.nextLine();
                notes.add(parseNoteString(noteScanner));
            }
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (InvalidFormatException e) {
            InvalidNotesFileHandler.responseHandler(path, notes);
        }
        return notes;
    }
}

