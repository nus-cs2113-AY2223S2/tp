package utils.exceptions;

public class AddGoneWrong extends InkaException {
    public AddGoneWrong() {
        super("Please ensure use '-' to separate the question and answer to the add command!");
    }
}
