package seedu.pettracker.exceptions;

public class DuplicatePetException extends Exception{
    public DuplicatePetException(){
        super("ERROR: Pet already exists");
    }
}
