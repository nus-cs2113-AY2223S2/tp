package seedu.dukeofbooks.data.book;
import seedu.dukeofbooks.common.IVerifiable;
import seedu.dukeofbooks.common.Name;
import seedu.dukeofbooks.data.exception.IllegalValueException;

public class Title extends Name implements IVerifiable{

    public Title(String name) throws IllegalValueException {
        super(name);
    }
    public boolean equals(String otherTitle) {
        return fullName.equals(otherTitle);
    }
}
