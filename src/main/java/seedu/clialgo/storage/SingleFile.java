package seedu.clialgo.storage;

import seedu.clialgo.logic.Topic;
import seedu.clialgo.Ui;
import seedu.clialgo.file.CS2040CFile;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;

public class SingleFile {

    private File file;
    private final String name;
    private final Ui ui;
    private final HashMap<String, String> storedRawData;
    private final HashMap<String, CS2040CFile> cs2040cFiles;
    private final FileDecoder decoder;


    public SingleFile (File file, String name, FileDecoder decoder) {
        this.file = file;
        this.name = name;
        this.ui = new Ui();
        this.decoder = decoder;
        this.storedRawData  = new HashMap<>();
        this.cs2040cFiles = new HashMap<>();
    }
    //@@author lohjooh
    /**
     * Reads data from the .txt file and stores it in this object. If the file is corrupted, after reading in the
     * non-corrupted data, overwrites the data file with the non-corrupted data while purging the corrupted data.
     *
     * @throws FileNotFoundException Thrown when the .txt file does not exist.
     */
    public void readFile() throws FileNotFoundException {
        Scanner scanner = new Scanner(file);
        boolean isFileCorrupted = false;
        int corruptCount = 0;
        while (scanner.hasNext()) {
            String rawData = scanner.nextLine();
            boolean isCorrupted = decoder.decodeString(rawData, name);
            if (isCorrupted) {
                isFileCorrupted = true;
                corruptCount += 1;
                break;
            }
            this.storedRawData.put(decoder.decodedName(), rawData);
            this.cs2040cFiles.put(decoder.decodedName(), decoder.processedCS2040CFile());
        }
        scanner.close();
        if (isFileCorrupted) {
            try {
                overwriteFile();
                ui.printCorruptedFileDiscarded(corruptCount, name);
            } catch (IOException e) {
                ui.printFileWriteError();
            } catch (SecurityException e) {
                ui.printSecurityDenied();
            }
        }
    }

    /**
     * Writes a single <code>CS2040CFile</code> encoded as a <code>String</code> to the .txt file.
     * If the file does not exist
     * during method call, recreate the file with <code>recreateFile</code>.
     *
     * @param encodedCS2040CFile The <code>CS2040CFile</code> encoded as a <code>String</code>.
     * @throws IOException Throws an exception if the file write fails.
     * @throws SecurityException Throws an exception if the security manager denies permission.
     */
    public void writeCS2040CFileToFile(String fileName, String encodedCS2040CFile, CS2040CFile cs2040cFile)
            throws IOException, SecurityException {
        assert encodedCS2040CFile != null : "Empty string";
        try {
            if (!file.exists()) {
                recreateFile();
            }
            FileWriter fileWriter = new FileWriter(file, true);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write(encodedCS2040CFile);
            bufferedWriter.newLine();
            bufferedWriter.close();
            fileWriter.close();
            this.storedRawData.put(fileName, encodedCS2040CFile);
            this.cs2040cFiles.put(fileName, cs2040cFile);
        } catch (IOException e) {
            throw new IOException();
        } catch (SecurityException e) {
            throw new SecurityException();
        }
    }

    /**
     * Writes all the stored raw data into the .txt file, overwriting all the existing data stored in the .txt file.
     *
     * @throws IOException Throws an exception if the file write fails.
     * @throws SecurityException Throws an exception if the security manager denies permission.
     */
    public void overwriteFile() throws IOException, SecurityException {
        try {
            FileWriter fileWriter = new FileWriter(file, false);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            for (String string : storedRawData.values()) {
                bufferedWriter.write(string);
                bufferedWriter.newLine();
            }
            bufferedWriter.close();
            fileWriter.close();
        } catch (IOException e) {
            throw new IOException();
        } catch (SecurityException e) {
            throw new SecurityException();
        }
    }

    /**
     * Deletes a single <code>CS2040CFile</code> and updates the .txt file. If the file does not exist
     * during method call, recreate the file with <code>recreateFile</code>.
     *
     * @param name The name of the <code>CS2040CFile</code> being deleted.
     * @throws IOException Throws an exception if the file write fails.
     * @throws SecurityException Throws an exception if the security manager denies permission.
     */
    public void deleteEntry(String name) throws IOException,SecurityException {
        if (!this.storedRawData.containsKey(name)) {
            return;
        }
        storedRawData.remove(name);
        try {
            if (!file.exists()) {
                recreateFile();
            }
            overwriteFile();
        } catch (IOException e) {
            throw new IOException();
        } catch (SecurityException e) {
            throw new SecurityException();
        }
        cs2040cFiles.remove(name);
    }

    /**
     * Recreates the data file with all the entries reset based on the current data stored in this object.
     */
    public void recreateFile() {
        try {
            if (file.createNewFile()) {
                overwriteFile();
            }
        } catch (IOException e) {
            ui.printFileWriteError();
        } catch (SecurityException e) {
            ui.printSecurityDenied();
        }
    }

    public Topic convertFileToTopic () {
        return new Topic(name, cs2040cFiles);
    }

    public void clearFile() {
        storedRawData.clear();
    }

    public void setFile(File file) {
        this.file = file;
    }
}
