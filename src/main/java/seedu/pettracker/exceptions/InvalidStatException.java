package seedu.pettracker.exceptions;

public class InvalidStatException extends Exception {
    public InvalidStatException() {
        super("ERROR: The only valid stats are type, age, or weight.");
    }

}
