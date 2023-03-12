package seedu.duke;

import seedu.duke.data.Record;
import seedu.duke.data.RecordList;

import java.io.File;
import java.util.ArrayList;

/**
 * Model a class to handle storage for the program.
 */
public class Storage {
    private final File file;

    /**
     * Build constructor for the Storage class.
     * @param filepath the filepath of the storage.
     */
    Storage(String filepath) {
        String dirname = filepath.substring(0, filepath.lastIndexOf("/"));
        File dir = new File(dirname);
        dir.mkdirs();
        this.file = new File(filepath);
    }

    public ArrayList<Record> load(){
    }

    public void save(RecordList records) {
    }

