package seedu.duke.exceptions;

public class FileReadError extends DukeError {
    public FileReadError () {
        super("Unable to read the data file from hard disk. We will generate a new file for you.");
    }

}
