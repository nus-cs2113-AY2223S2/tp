package seedu.fst.storage;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import seedu.fst.exceptions.InvalidStorageFilePathException;
import seedu.fst.exceptions.StorageOperationException;

import seedu.fst.food.FoodList;

//@@author ZhongXiangWong
/**
 * Represents the file used to store food list data.
 * The code is adapted from:
 * https://github.com/se-edu/addressbook-level2/blob/master/src/seedu/addressbook/storage/StorageFile.java
 */
public class StorageFile {

    /**
     * Default file path used.
     */
    public static final String DEFAULT_STORAGE_FILEPATH = "foodTrackerList.txt";

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
            throw new InvalidStorageFilePathException("storage file should end with '.txt'");
        }
    }

    /**
     * Returns true if the given path is acceptable as a storage file.
     * The file path is considered acceptable if it ends with '.txt'
     */
    private static boolean isValidPath(Path filePath) {
        return filePath.toString().endsWith(".txt");
    }

    /**
     * Saves the {@code foodList} data to the storage file.
     *
     * @throws StorageOperationException if there were errors converting and/or storing data to file.
     */
    public void save(FoodList foodList) throws StorageOperationException {
        try {
            List<String> encodedFoodList = FoodListEncoder.encodeFoodList(foodList);
            Files.write(path, encodedFoodList);
        } catch (IOException e) {
            throw new StorageOperationException("Error writing to file: " + path);
        }
    }

    /**
     * Loads the {@code FoodList} data from this storage file, and then returns it.
     * Returns an empty {@code FoodList} if the file does not exist, or is not a regular file.
     *
     * @throws StorageOperationException if there were errors reading and/or converting data from file.
     */
    public FoodList load() throws StorageOperationException {

        if (!Files.exists(path) || !Files.isRegularFile(path)) {
            return new FoodList();
        }

        try {
            return FoodListDecoder.decodeFoodList(Files.readAllLines(path));
        } catch (FileNotFoundException e) {
            throw new AssertionError("A non-existent file scenario is already handled earlier.");
        } catch (IOException e) {
            throw new StorageOperationException("Error writing to file: " + path);
        }
    }

}
