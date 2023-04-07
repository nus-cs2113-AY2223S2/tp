package seedu.badmaths.storage;

import org.junit.jupiter.api.Test;
import seedu.badmaths.note.Note;
import seedu.badmaths.note.NotePriority;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class InvalidNotesFileHandlerTest {
    @Test
    public void testResponseHandlerReset() throws IOException {
        // create a temporary file
        Path tempFile = Files.createTempFile("temp", ".txt");
        String fileContents = "HIGH\tN\t0\tThis is a test note\nMEDIUM\tY\t2\tThi is another test note\n";
        Files.writeString(tempFile, fileContents);

        // create a temporary ArrayList
        ArrayList<Note> notes = new ArrayList<>();
        Note note1 = new Note("This is a test note", NotePriority.Priority.HIGH);
        Note note2 = new Note("This is another test note", NotePriority.Priority.MEDIUM);
        note2.markAsDone();
        note2.incrementReviewCount();
        note2.incrementReviewCount();
        notes.add(note1);
        notes.add(note2);

        // redirect System.in and System.out for testing
        ByteArrayInputStream in = new ByteArrayInputStream("y\n".getBytes());
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setIn(in);
        System.setOut(new PrintStream(out));

        InvalidNotesFileHandler.responseHandler(tempFile.toString(), notes);
        String expectedOutput = "Sorry, your notes file seems to be corrupted :(\n" +
                "Do you want to reset the file? (y/n)\n\n" +
                "File contents have rest successfully.\n" +
                "You can continue to use the application\n" +
                "If you want to read Help Manual, please type 'Help' to learn what I can do for you.\n";
        expectedOutput = expectedOutput.replace("\r\n", "\n").replace("\r", "\n");
        assertEquals(expectedOutput, out.toString());

        // verify that the file contents were cleared
        assertEquals("", Files.readString(tempFile));

        //verify that the ArrayList contents were cleared
        assertEquals(0, notes.size());

        // clean up
        Files.delete(tempFile);
        System.setIn(System.in);
        System.setOut(System.out);
    }

    @Test
    public void testResponseHandlerExit() throws IOException {

        Path tempFile = Files.createTempFile("temp", ".txt");
        String fileContents = "HIGH\tN\t0\tThis is a test note\nMEDIUM\tY\t2\tThis is another test note\n";
        Files.writeString(tempFile, fileContents);

        // redirect System.in and System.out for testing
        ByteArrayInputStream in = new ByteArrayInputStream("n\n".getBytes());
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setIn(in);
        System.setOut(new PrintStream(out));

        // create a temporary ArrayList
        ArrayList<Note> notes = new ArrayList<>();
        Note note1 = new Note("This is a test note", NotePriority.Priority.HIGH);
        Note note2 = new Note("This is another test note", NotePriority.Priority.MEDIUM);
        note2.markAsDone();
        note2.incrementReviewCount();
        note2.incrementReviewCount();
        notes.add(note1);
        notes.add(note2);

        // call the responseHandler method and verify the output
        InvalidNotesFileHandler.responseHandler(tempFile.toString(), notes);
        String expectedOutput = "Sorry, your notes file seems to be corrupted :(\n" +
                "Do you want to reset the file? (y/n)\n\n" +
                "You choose not to rest the file.\n" +
                "Please ensure your file status before using the application.\n" +
                "The program will exit in 10 seconds. See you next time.\n";
        expectedOutput = expectedOutput.replace("\r\n", "\n").replace("\r", "\n");
        assertEquals(expectedOutput, out.toString());

        // verify that the file contents were not cleared
        assertEquals(fileContents, Files.readString(tempFile));

        // clean up
        Files.delete(tempFile);
        System.setIn(System.in);
        System.setOut(System.out);
    }
}
