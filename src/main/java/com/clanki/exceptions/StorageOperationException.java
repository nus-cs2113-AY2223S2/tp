package com.clanki.exceptions;

/**
 * Signals that some error has occured while trying to convert and read/write data between the application
 * and the storage file.
 * Copied from:
 * https://github.com/se-edu/addressbook-level2/blob/master/src/seedu/addressbook/storage/StorageFile.java
 */
public class StorageOperationException extends Exception {
    public StorageOperationException(String message) {
        super(message);
    }
}