package seedu.duke;

import java.io.IOException;

public interface DatabaseInterface {

    void initialiseDatabase() throws IOException;

    default String writeTaskPreparation(String saveString) {
        return saveString + System.lineSeparator();
    }

    boolean checkDatabaseCorrupted();
}
