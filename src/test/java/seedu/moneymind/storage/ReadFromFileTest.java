package seedu.moneymind.storage;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static seedu.moneymind.storage.ReadFromFile.readFromFile;

import java.io.File;

import org.junit.jupiter.api.Test;

public class ReadFromFileTest {
    @Test
    void readFromFile_testFile_noExceptionThrown() {
        new Storage("testFile.txt");
        File testFile = new File("testFile.txt");
        assertDoesNotThrow(() -> readFromFile(testFile));
        testFile.delete();
    }

    @Test
    void readFromFile_nonExistentFile_exceptionThrown() {
        File testFile = new File("nonExistentFile.txt");
        assertThrows(Exception.class, () -> readFromFile(testFile));
    }
}
