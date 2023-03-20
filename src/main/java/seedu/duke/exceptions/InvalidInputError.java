package seedu.duke.exceptions;

public class InvalidInputError extends DukeError{
    public InvalidInputError(){
        super("Invalid input! Please enter the number of exercises you want!");
    }
}
