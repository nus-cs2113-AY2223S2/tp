package seedu.pettracker.exceptions;

public class EmptyPetNameException extends IllegalArgException{
    public EmptyPetNameException() {
        super("Pet name cannot be empty!");
    }
}
