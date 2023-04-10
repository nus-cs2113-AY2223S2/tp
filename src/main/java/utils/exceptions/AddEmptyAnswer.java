package utils.exceptions;

public class AddEmptyAnswer extends InkaException {
    public AddEmptyAnswer() {
        super("Please ensure that you supply a valid answer to Inka!");
    }
}
