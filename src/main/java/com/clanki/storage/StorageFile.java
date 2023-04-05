package com.clanki.storage;

import com.clanki.exceptions.InvalidStorageFilePathException;
import com.clanki.exceptions.StorageOperationException;
import com.clanki.objects.Flashcard;
import com.clanki.objects.FlashcardList;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * This class manages the storing of FlashcardList and the recovering of stored
 * data each time the application first start up.
 * Many portions of the code in this class are modified from the link below: (addressbook-level2)
 * https://github.com/se-edu/addressbook-level2/blob/master/src/seedu/addressbook/storage/StorageFile.java
 */
public class StorageFile {
    /**
     * Default file path used if the user doesn't provide the file name.
     */
    public static final String DEFAULT_STORAGE_FILEPATH = "flashcardList.txt";
    public static final String FILE_END_STRING = ".txt";


    public final Path path;

    /**
     * @throws InvalidStorageFilePathException if the default path is invalid
     */
    public StorageFile() throws InvalidStorageFilePathException {
        this(DEFAULT_STORAGE_FILEPATH);
    }

    /**
     * @throws InvalidStorageFilePathException if the given file path is invalid
     */
    public StorageFile(String filePath) throws InvalidStorageFilePathException {
        path = Paths.get(filePath);
        if (!isValidPath(path)) {
            throw new InvalidStorageFilePathException("Storage file should end with '.txt'");
        }
    }

    /**
     * Returns true if the given path is acceptable as a storage file.
     * The file path is considered acceptable if it ends with '.txt'
     */
    private static boolean isValidPath(Path filePath) {
        return filePath.toString().endsWith(FILE_END_STRING);
    }

    /**
     * Saves the {@code addressBook} data to the storage file.
     *
     * @throws StorageOperationException if there were errors converting and/or storing data to file.
     */
    public void save(FlashcardList flashcardList) throws StorageOperationException {
        try {
            List<String> encodedAddressBook = FlashcardListEncoder.encodeAddressBook(flashcardList);
            Files.write(path, encodedAddressBook);
        } catch (IOException ioe) {
            throw new StorageOperationException("Error writing to file: " + path);
        }
    }

    /**
     * Loads the {@code AddressBook} data from this storage file, and then returns it.
     * Returns an empty {@code AddressBook} if the file does not exist, or is not a regular file.
     *
     * @throws StorageOperationException if there were errors reading and/or converting data from file.
     */
    public ArrayList<Flashcard> load() throws StorageOperationException {

        if (!Files.exists(path) || !Files.isRegularFile(path)) {
            return new ArrayList<>();
        }
        try {
            return FlashcardListDecoder.decodeFlashcardList(Files.readAllLines(path));
        } catch (FileNotFoundException fnfe) {
            throw new AssertionError("A non-existent file scenario is already handled earlier.");
        } catch (IOException ioe) {
            throw new StorageOperationException("Error writing to file: " + path);
        }
    }

    public String getPath() {
        return path.toString();
    }


}
