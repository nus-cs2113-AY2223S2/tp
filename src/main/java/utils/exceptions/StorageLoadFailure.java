package utils.exceptions;

public class StorageLoadFailure extends InkaException {
    public StorageLoadFailure(String filePath) {
        super("Failed to load deck from " + filePath + " :(");
    }
}
