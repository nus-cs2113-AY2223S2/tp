package seedu.clialgo.storage;

import seedu.clialgo.FileType;
import seedu.clialgo.file.Code;
import seedu.clialgo.file.CS2040CFile;
import seedu.clialgo.file.Note;

/**
 * Object that processes a <code>String</code> passed to it and returns a <code>CS2040CFile</code> representing the
 * information in the <code>String</code>.
 */
public class FileDecoder {
    private CS2040CFile currentCS2040CFile;
    private String currentName;
    private final String separator;

    /**
     * Constructor for the fileDecoder object
     *
     * @param separator The <code>String</code> which separates each form of data stored in each <code>File</code>.
     */
    public FileDecoder (String separator) {
        this.separator = separator;
    }

    public FileType checkFileType() {
        String currentDirectory = "./";
        String noteFileExtension = ".txt";
        String codeFileExtension = ".cpp";
        String pathInTxt =  currentDirectory + this.currentName + noteFileExtension;
        String pathInCpp = currentDirectory + this.currentName + codeFileExtension;

        if (new java.io.File(pathInTxt).isFile()) {
            return FileType.TXT;
        } else if (new java.io.File(pathInCpp).isFile()) {
            return FileType.CPP;
        } else {
            return FileType.DOESNOTEXIST;
        }
    }

    //@@author lohjooh
    /**
     * Converts an encoded <code>CS2040CFile</code> from a <code>String</code> and stores the name of the
     * <code>CS2040CFile</code> and the <code>CS2040CFile</code> itself in this object. If there are any missing or
     * corrupted fields, the <code>CS2040CFile</code> object is deemed corrupted.
     *
     * @param encodedCS2040CFile The encoded <code>String</code> that represents a <code>CS2040CFile</code>.
     * @return true if there are any wrong entries or <code>isCorrupted</code> otherwise.
     */
    public boolean decodeString (String encodedCS2040CFile, String topicName) {
        try {
            int maxFields = 4;
            String[] splitCS2040CFile = encodedCS2040CFile.split(separator, maxFields);
            String pathToCS2040CFile = splitCS2040CFile[1];
            String topicOfCS2040CFile = splitCS2040CFile[2];
            int importanceOfCS2040CFile = Integer.parseInt(splitCS2040CFile[3]);
            if (!topicOfCS2040CFile.equals(topicName)) {
                return true;
            }
            this.currentName = splitCS2040CFile[0];
            if (checkFileType() == FileType.CPP) {
                currentCS2040CFile = new Code(this.currentName, pathToCS2040CFile, topicOfCS2040CFile,
                        importanceOfCS2040CFile);
            } else if (checkFileType() == FileType.TXT) {
                currentCS2040CFile = new Note(this.currentName, pathToCS2040CFile, topicOfCS2040CFile,
                        importanceOfCS2040CFile);
            } else {
                return true;
            }
        } catch (ArrayIndexOutOfBoundsException | NumberFormatException e) {
            return true;
        }
        return false;
    }

    public String decodedName () {
        return currentName;
    }

    public CS2040CFile processedCS2040CFile() {
        return currentCS2040CFile;
    }

}
//@@author
