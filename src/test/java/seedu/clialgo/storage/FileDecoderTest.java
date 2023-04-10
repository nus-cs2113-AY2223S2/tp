package seedu.clialgo.storage;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.clialgo.file.Code;
import seedu.clialgo.file.Note;

import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FileDecoderTest {
    public static final String NOTE_FILE_EXTENSION = ".txt";
    public static final String CODE_FILE_EXTENSION = ".cpp";
    private static final String SEPARATOR = "@&";
    private static final String NAME = "name";
    private static final String TOPIC = "topic";
    private static final String CURRENT_DIRECTORY_PATH = "./";
    private static final int IMPORTANCE = 1;

    private FileDecoder fileDecoder;

    @BeforeEach
    public void setUp() {
        fileDecoder = new FileDecoder(SEPARATOR);
    }

    @Test
    void isCorrectNoteCreated_expectTrue() {
        String path = NAME + NOTE_FILE_EXTENSION;
        String pathToFile = CURRENT_DIRECTORY_PATH + path;
        File file = new File(pathToFile);
        try {
            if (!file.createNewFile()) {
                System.out.println("ERROR 1");
            }
            Note note = new Note(NAME, path,TOPIC, 1);
            String encodedString = NAME + SEPARATOR + path + SEPARATOR + TOPIC + SEPARATOR + IMPORTANCE;
            System.out.println(encodedString);
            fileDecoder.decodeString(encodedString, TOPIC);
            assertEquals(note, fileDecoder.processedCS2040CFile());
            if (!file.delete()) {
                System.out.println("ERROR 2");
            }
        } catch (IOException e) {
            System.out.println("ERROR 3");
        }
    }

    @Test
    void isCorrectCodeCreated_expectTrue() {
        String path = NAME + CODE_FILE_EXTENSION;
        String pathToFile = CURRENT_DIRECTORY_PATH + path;
        File file = new File(pathToFile);
        try {
            if (!file.createNewFile()) {
                System.out.println("ERROR 1");
            }
            Code code = new Code(NAME, path,TOPIC, 1);
            String encodedString = NAME + SEPARATOR + path + SEPARATOR + TOPIC + SEPARATOR + IMPORTANCE;
            System.out.println(encodedString);
            fileDecoder.decodeString(encodedString, TOPIC);
            assertEquals(code, fileDecoder.processedCS2040CFile());
            if (!file.delete()) {
                System.out.println("ERROR 2");
            }
        } catch (IOException e) {
            System.out.println("ERROR 3");
        }
    }
}
