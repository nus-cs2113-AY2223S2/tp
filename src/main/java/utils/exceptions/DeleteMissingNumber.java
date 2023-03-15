package utils.exceptions;

public class DeleteMissingNumber extends InkaException {
    public DeleteMissingNumber() {
        super("Whoops, ensure that your delete command is valid!");
    }
}
