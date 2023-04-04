package seedu.pettracker.exceptions;

public class PetNotFoundException extends Exception {
    public PetNotFoundException() {
        super("ERROR: Pet not Found");
    }
}
