package utils.exceptions;

public class DeleteRangeInvalid extends InkaException {
    public DeleteRangeInvalid() {
        super("Please ensure that your date is a number and not a string!");
    }
}
