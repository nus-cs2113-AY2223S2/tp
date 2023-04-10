package utils.exceptions;

public class InvalidUUIDException extends InkaException {
    public InvalidUUIDException() {
        super("UUID needs to be in 03658854-e5d4-468f-8c41-74917e5d4515 format");
    }
}
