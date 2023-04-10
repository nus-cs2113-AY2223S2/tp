package seedu.clialgo.storage;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.clialgo.Ui;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SingleFileTest {
    private static final String PATH = "./test";
    private FileDecoder fileDecoder;
    private final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

    /**
     * Deletes folder at <code>pathToFolder</code> and all the files within.
     * @param pathToFolder The <code>File</code> representing the folder to delete.
     */
    public void deleteAll(File pathToFolder) {
        File[] files = pathToFolder.listFiles();
        if (files != null) {
            for (File f : files) {
                if (!f.delete()) {
                    Ui.printDeleteFail();
                }
            }
        }
        if (!pathToFolder.delete()) {
            Ui.printDeleteFail();
        } else {
            Ui.printDeleteSuccess();
        }
    }

    /**
     * Runs before each test, initializes  <code>Ui</code>, <code>TopicManager</code>, <code>Parser</code> and
     * <code>FileManager</code>.
     */
    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStream));
        String separator = "@&";
        fileDecoder = new FileDecoder(separator);
        deleteAll(new File(PATH));
        outputStream.reset();
    }

    @Test
    void isCorruptedEntryDiscarded_expectTrue() {
        if (!new File(PATH).mkdir()) {
            System.out.println("ERROR 1");
        }
        String fileName = "/test.txt";
        String pathToTest = PATH + fileName;
        File file = new File(pathToTest);
        try {
            if (!file.createNewFile()) {
                System.out.println("ERROR 2");
            }
            SingleFile singleFile = new SingleFile(file, "test", fileDecoder);
            FileWriter fileWriter = new FileWriter(file);
            String corruptedString = "corrupted";
            fileWriter.write(corruptedString);
            fileWriter.close();
            singleFile.readFile();
        } catch (IOException e) {
            System.out.println("ERROR 3");
        }

        String os = System.getProperty("os.name");
        String expectedOutput;

        if (os.contains("Windows")) {
            expectedOutput = "======================================================\r\n" +
                    "1 corrupted save entry detected in test.txt.\r\n" +
                    "Note that the corrupted entries are discarded!\r\n" +
                    "======================================================\r\n";
        } else {
            expectedOutput = "======================================================\n" +
                    "1 corrupted save entry detected in test.txt.\n" +
                    "Note that the corrupted entries are discarded!\n" +
                    "======================================================\n";
        }
        assertEquals(expectedOutput, outputStream.toString());
        deleteAll(new File(PATH));
    }
}
