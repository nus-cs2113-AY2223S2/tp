package utils.exceptions;

public class StorageCorrupted extends InkaException {
    public StorageCorrupted(String filePath) {
        super("Save file " + filePath + " seems corrupted... Will pretend it doesn't exist...");
    }
}
