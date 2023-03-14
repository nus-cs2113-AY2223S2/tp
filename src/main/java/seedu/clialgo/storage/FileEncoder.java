package seedu.clialgo.storage;

import seedu.clialgo.Note;

/**
 * Object that takes in a <code>Note</code> object and converts it into a <code>String</code> which is then written
 * to the .txt file which the <code>Note</code> has the <code>Note.tag</code> of.
 */
public class FileEncoder {

    protected final String separator;

    /**
     * Constructor for the fileEncoder object.
     *
     * @param separator The <code>String</code> which separates each form of data stored in each <code>Note</code>.
     */
    public FileEncoder (String separator) {
        this.separator = separator;
    }

    /**
     * Encodes a note into an encoded <code>String</code>.
     *
     * @param name The name of the <code>Note</code>.
     * @param note The <code>Note</code> object processed.
     * @return Returns a processed string.
     */
    public String encodeNote (String name, Note note) {
        return name + separator + note.getPath() + separator + note.getTopic();
    }

}
