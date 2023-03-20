package seedu.duke.exceptions;

public class InvalidDifficultyInputError extends DukeError{
    public InvalidDifficultyInputError(){
        super("Incorrect difficulty level input");
    }
}
