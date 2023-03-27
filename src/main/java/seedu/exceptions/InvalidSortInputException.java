package seedu.exceptions;

public class InvalidSortInputException extends Exception {
    @Override
    public String getMessage() {
        return "Invalid sort message!\n " +
                "<sort ascend> to sort amount in ascending order. " +
                "<sort descend> to sort amount in descending order.\n" +
                "<sort earliest> to sort amount from earliest date added. " +
                "<sort latest> to sort amount from latest date added.";
    }
}
