package seedu.exceptions;

public class InvalidSortInputException extends Exception {
    @Override
    public String getMessage() {
        return "Invalid sort message!\n " +
                "Please input either <sort ascend> to sort list in ascending amount order or " +
                "<sort descend> to sort list in descending amount order";
    }
}
