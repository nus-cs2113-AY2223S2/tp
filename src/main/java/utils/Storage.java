package utils;

import exceptions.DinerDirectorException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Storage {
    public static final String FILE_DIRECTORY = "data";
    private static final Path DATA_DIRECTORY = Path.of(System.getProperty("user.dir"), FILE_DIRECTORY);

    /**
     * Creates a directory in the root project structure named "data" if directory is not found.
     *
     * @throws DinerDirectorException Program related exception.
     * @throws IOException            Unable to create a directory.
     */
    public void createDirectory() throws DinerDirectorException, IOException {
        if (Files.notExists(DATA_DIRECTORY)) {
            Files.createDirectories(DATA_DIRECTORY);
        }
    }
}
