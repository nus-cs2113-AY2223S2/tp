package seedu.pettracker.exceptions;

public class EmptyPetNameException extends IllegalArgException{
    public EmptyPetNameException() {
        super("ERROR: Pet Name is empty");
    }
}
