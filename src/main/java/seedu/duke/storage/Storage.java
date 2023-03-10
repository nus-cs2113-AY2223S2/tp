package seedu.duke.storage;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.io.FileOutputStream;
import java.io.File;
import java.util.List;
import seedu.duke.constants.StorageConstants;
import seedu.duke.entries.Category;
import seedu.duke.entries.Entry;
import seedu.duke.util.CategoryUtil;

public class Storage {

    private String filePath = StorageConstants.RELATIVE_FILE_NAME;
    private String delimiter = StorageConstants.DELIMITER;

    public Storage(){
    }

    public Storage(String filePath){
        this.filePath = filePath;
    }
    public Storage(String filePath, String delimiter){
        this.filePath = filePath;
        this.delimiter = delimiter;
    }

    private void makeFileIfNotExists() throws IOException {
        File file = new File(this.filePath);
        file.getParentFile().mkdirs();
        file.createNewFile();
        new FileOutputStream(file, true).close();
    }

    private Entry readEntryLine(String line) {
        String[] lineArray = line.split(this.delimiter);
        String description = lineArray[0];
        String amountString = lineArray[1];
        String categoryString = lineArray[2];
        double amount = Double.parseDouble(amountString);
        Category category = CategoryUtil.convertStringToCategory(
            categoryString
        );
        Entry entry = new Entry(description, amount, category);
        return entry;
    }

    private String writeEntryLine(Entry entry) {
        String description = entry.getDescription();
        String amountString = Double.toString(entry.getAmount());
        String categoryString = entry.getCategoryString();
        String returnString = String.join(
            this.delimiter,
            description,
            amountString,
            categoryString
        );
        returnString += "\n";
        return returnString;
    }

    public List<Entry> readFromDatabase() {
        List<Entry> entries = new ArrayList<Entry>();
        try {
            makeFileIfNotExists();
            BufferedReader csvReader = new BufferedReader(
                new FileReader(this.filePath)
            );
            String row;
            while ((row = csvReader.readLine()) != null) {
                entries.add(readEntryLine(row));
            }
            csvReader.close();
        } catch (IOException e) {
            System.out.println("An IOException has occured.");
        }
        return entries;
    }

    public void writeToDatabase(List<Entry> entries) {
        try {
            makeFileIfNotExists();
            FileWriter csvWriter = new FileWriter(this.filePath);
            for (Entry entry : entries) {
                String csvRow = "";
                csvRow += writeEntryLine(entry);
                csvWriter.append(csvRow);
            }
            csvWriter.flush();
            csvWriter.close();
        } catch (IOException e) {
            System.out.println("An IOException has occured.");
        }
    }

    public void reset(){
        File toBeDeleted = new File(this.filePath);
        toBeDeleted.delete();
        try{
            makeFileIfNotExists();
        } catch (IOException e) {
            System.out.println("An IOException has occured.");
        }
    }
}
