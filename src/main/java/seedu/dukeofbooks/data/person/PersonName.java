package seedu.dukeofbooks.data.person;
import seedu.dukeofbooks.common.Name;
import seedu.dukeofbooks.data.exception.IllegalValueException;

public class PersonName extends Name{

    public PersonName(String name) throws IllegalValueException {
        super(name);
    }
    
}
