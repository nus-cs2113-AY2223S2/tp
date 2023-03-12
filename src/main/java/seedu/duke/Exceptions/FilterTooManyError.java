package seedu.duke.Exceptions;

public class FilterTooManyError extends DukeError {
    public FilterTooManyError(){
        super("You have a too specific filter, no such exercises " +
                "exists! Try generating with less filters or less number " +
                "of exercises");
    }

}
