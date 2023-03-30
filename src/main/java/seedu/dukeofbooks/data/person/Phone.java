package seedu.dukeofbooks.data.person;
import seedu.dukeofbooks.data.exception.IllegalValueException;

public class Phone {
    public static final String INVALID_PHONE_NUMBER = "The phone number supplied is not valid in Singapore";
    public static final int DEFAULT_PHONE_NUMBER = 10000000;
    private int number;

    public Phone(int number) throws IllegalValueException {
        setNumber(number);
    }
    public int getNumber() {
        return number;
    }
    public String toString() {
        return Integer.toString(number);
    }
    public void setNumber(int newNumber) throws IllegalValueException {
        // Perform checks if number is 8 number long
        if (newNumber<10000000) {
            throw new IllegalValueException(INVALID_PHONE_NUMBER);
        }
        number = newNumber;
    }
    public boolean isDefault() {
        return number == DEFAULT_PHONE_NUMBER;
    }
}
