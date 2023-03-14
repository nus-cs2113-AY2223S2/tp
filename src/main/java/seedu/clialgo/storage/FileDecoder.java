package seedu.clialgo.storage;

import seedu.clialgo.Note;
import seedu.clialgo.Ui;

/**
 * Object that processes a <code>String</code> passed to it and returns a <code>Note</code> representing the
 * information in the <code>String</code>.
 */
public class FileDecoder {
    protected Note currentNote;
    protected String currentName;
    protected final String separator;
    private final Ui ui;

    /**
     * Constructor for the fileDecoder object
     *
     * @param separator The <code>String</code> which separates each form of data stored in each <code>Note</code>.
     */
    public FileDecoder (String separator) {
        this.separator = separator;
        this.ui = new Ui();
    }

    /**
     * Converts an encoded <code>Note</code> from a <code>String</code> and stores the name of the
     * <code>Note</code> and the <code>Note</code> itself in this object.
     *
     * @param encodedNote The encoded <code>String</code> that represents a <code>Note</code>.
     */
    public void decodeString (String encodedNote) {
        try {
            String[] splitNote = encodedNote.split(separator);
            this.currentName = splitNote[0];
            currentNote = new Note(this.currentName, splitNote[1], splitNote[2]);
        } catch (ArrayIndexOutOfBoundsException e) {
            ui.printInvalidNote();
        }
    }

    public String decodedName () {
        return currentName;
    }

    public Note processedNote () {
        return currentNote;
    }
}
