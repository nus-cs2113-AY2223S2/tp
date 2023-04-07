package utils.exceptions;

public class WrongTagCreation extends InkaException{
    public WrongTagCreation() {
        super("New tags can only be created with tag names, not with an UUID!");
    }
}
