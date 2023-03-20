package com.clanki.exceptions;

public class InvalidStorageFilePathException extends Exception {
    /**
     * Signals that the given file path does not fulfill the storage filepath constraints.
     * Copied from:
     * https://github.com/se-edu/addressbook-level2/blob/master/src/seedu/addressbook/storage/StorageFile.java
     */
    public InvalidStorageFilePathException(String message) {
        super(message);
    }
}
