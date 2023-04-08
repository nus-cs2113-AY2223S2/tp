package seedu.badmaths.storage;

import seedu.badmaths.InvalidFormatException;
import seedu.badmaths.note.Note;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import static seedu.badmaths.storage.NoteParser.parseNoteString;

public class NotesFileParser {
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

