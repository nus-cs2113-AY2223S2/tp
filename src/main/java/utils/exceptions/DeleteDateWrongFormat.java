package utils.exceptions;

public class DeleteDateWrongFormat extends InkaException {
    public DeleteDateWrongFormat() {
        super("Please ensure that your date is a number and not a string!");
    }
}
