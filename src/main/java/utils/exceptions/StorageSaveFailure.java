package utils.exceptions;

public class StorageSaveFailure extends InkaException {
    public StorageSaveFailure(String filePath) {
        super("Failed to save deck to " + filePath + " :(");
    }
}
