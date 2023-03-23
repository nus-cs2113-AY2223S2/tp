package seedu.duke.exceptions;

public class EmptyKeywordError extends DukeError{
    public EmptyKeywordError(){
        super("Please key in a keyword for Fitness Duke to search!");
    }

}
