package seedu.clialgo.storage;

import seedu.clialgo.file.CS2040CFile;

//@@author lohjooh
/**
 * Object that takes in a <code>CS2040CFile</code> object and converts it into a <code>String</code> which is then
 * written to the .txt file which the <code>CS2040CFile</code> has the <code>CS2040CFile.tag</code> of.
 */
public class FileEncoder {

    protected final String separator;

    /**
     * Constructor for the fileEncoder object.
     *
     * @param separator The <code>String</code> which separates each form of data stored in each <code>File</code>.
     */
    public FileEncoder (String separator) {
        this.separator = separator;
    }

    /**
     * Encodes a CS2040CFile into an encoded <code>String</code>.
     *
     * @param name The name of the <code>CS2040CFile</code>.
     * @param cs2040cFile The <code>CS2040CFile</code> object processed.
     * @return Returns a processed string.
     */
    public String encodeCS2040CFile(String name, CS2040CFile cs2040cFile) {
        return name + separator + cs2040cFile.getPath() + separator + cs2040cFile.getTopic()
                + separator + cs2040cFile.getImportance();
    }
}
