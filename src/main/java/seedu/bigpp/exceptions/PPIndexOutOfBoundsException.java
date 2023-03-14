package seedu.bigpp.exceptions;

public class PPIndexOutOfBoundsException extends PPException{
    @Override
    public String getMessage() {
        return "Please enter a valid index";
    }
}
