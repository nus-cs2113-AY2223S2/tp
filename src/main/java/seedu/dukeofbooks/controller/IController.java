package seedu.dukeofbooks.controller;

import seedu.dukeofbooks.data.exception.IllegalOperationException;

public interface IController {
    <T> void setData(T dataPoint) throws IllegalOperationException;
    boolean checkDataExists() throws IllegalOperationException;
}
