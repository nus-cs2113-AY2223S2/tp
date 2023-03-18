package seedu.duke.exception;

public class FailedSaveException extends ToDoListException {
    private static final String ERROR_MESSAGE = "Error encountered while saving your data. Please check that you have" +
            " provided a filepath that exists. For now, a new save file 'data.txt' has been saved for you.";
    public FailedSaveException() {
        super(ERROR_MESSAGE);
    }
}
