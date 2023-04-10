package utils.exceptions;

public class AddEmptyQuestion extends InkaException {
    public AddEmptyQuestion() {
        super("Please ensure that you supply a valid question to Inka!");
    }
}
