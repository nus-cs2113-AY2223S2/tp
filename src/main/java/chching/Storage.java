package chching;

import chching.record.ExpenseList;
import chching.record.IncomeList;
import chching.record.Record;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

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

    public ArrayList<Record> load() throws ChChingException {
        ArrayList<Record> records = new ArrayList<>();

        try {
            Scanner reader = new Scanner(file);
            reader.close();

        } catch (FileNotFoundException e) {
            throw new ChChingException("Unfortunately, file can't be found. I'll make a new one!");
        }

        return records;
    }

    public void save(IncomeList incomes, ExpenseList expenses) {
    }
}

