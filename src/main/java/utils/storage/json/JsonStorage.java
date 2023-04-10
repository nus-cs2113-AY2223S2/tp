package utils.storage.json;

import com.google.gson.JsonSyntaxException;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.attribute.PosixFilePermission;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Logger;

import com.google.gson.Gson;

import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import model.TagList;
import model.CardList;
import model.TagUUID;
import model.Memory;
import model.DeckList;
import model.CardUUID;

import utils.UserInterface;
import utils.exceptions.InkaException;
import utils.exceptions.StorageCorrupted;
import utils.exceptions.StorageLoadFailure;
import utils.exceptions.StorageSaveFailure;
import utils.storage.Storage;

public class JsonStorage extends Storage {

    public static Logger logger = Logger.getLogger("storage.JsonStorage");
    private GsonBuilder gsonBuilder;
    private File backupFile;

    /**
     * This class represents a JSON storage object that extends the functionality of the {@link Storage} class. It
     * creates a backup file, adds custom adapters and allows for the serialization and deserialization of objects to
     * and from JSON.
     *
     * @param filePath The file path of the JSON storage file.
     */
    public JsonStorage(String filePath,  UserInterface ui) {
        super(filePath, ui);

        // Create the backup file
        String backupFilePath = filePath.replace(".json", "_backup.json");
        String backupFileDir = saveFile.getParent();
        if (backupFileDir == null) {
            backupFileDir = ".";
        }
        backupFilePath = backupFileDir + File.separator + "." +
                backupFilePath.substring(backupFilePath.lastIndexOf(File.separator) + 1);
        backupFile = new File(backupFilePath);
        gsonBuilder = new GsonBuilder();

        //Add custom adapters
        gsonBuilder.registerTypeAdapter(CardUUID.class, new CardUuidJsonAdapter());
        gsonBuilder.registerTypeAdapter(TagUUID.class, new TagUuidJsonAdapter());

        try {
            if (System.getProperty("os.name").startsWith("Windows")) {
                backupFile.setReadOnly();
                saveFile.setReadOnly();
            } else {
                Set<PosixFilePermission> permissions = new HashSet<>();
                permissions.add(PosixFilePermission.OWNER_READ);
                permissions.add(PosixFilePermission.GROUP_READ);
                permissions.add(PosixFilePermission.OTHERS_READ);
                Files.setPosixFilePermissions(backupFile.toPath(), permissions);
                Files.setPosixFilePermissions(saveFile.toPath(), permissions);
            }
        } catch (IOException e) {
            JsonStorage.ui.printPermFail();

        }
    }

    /**
     * Loads the memory data from the JSON file.
     *
     * @return A Memory object containing the loaded data.
     * @throws InkaException If there is an issue loading or converting the data from the file.
     */
    @Override
    public Memory load() throws InkaException {
        try {
            FileReader fileReader = new FileReader(saveFile);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            gsonBuilder.setLenient();
            JsonElement jsonElement = gsonBuilder.create().fromJson(bufferedReader, JsonElement.class);
            JsonObject saveDataObject = jsonElement.getAsJsonObject();
            Memory savedMemory = JsonToMemory.convert(saveDataObject);
            return savedMemory;
        } catch (IOException e) {
            String absolutePath = this.saveFile.getAbsolutePath();
            throw new StorageLoadFailure(absolutePath);
        } catch (NullPointerException | JsonSyntaxException | InkaException e) {
            JsonStorage.ui.printLoadBackup();
            return loadBackup();
        }
    }

    /**
     * Loads the backup save to a Memory object.
     *
     * @return Memory object representing the savedata in backup save file.
     * @throws InkaException if an I/O error occurs while reading the backup file or if the backup file is corrupted.
     */
    private Memory loadBackup() throws InkaException {
        try {
            FileReader fileReader = new FileReader(backupFile);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            JsonElement jsonElement = gsonBuilder.create().fromJson(bufferedReader, JsonElement.class);
            JsonObject saveDataObject = jsonElement.getAsJsonObject();
            Memory savedMemory = JsonToMemory.convert(saveDataObject);
            JsonStorage.ui.printLoadBackupSuccess();
            return savedMemory;
        } catch (IOException | NullPointerException | JsonSyntaxException | InkaException ex) {
            String absolutePath = this.backupFile.getAbsolutePath();
            JsonStorage.ui.CorruptedBackup();
            throw new StorageCorrupted(absolutePath);
        }
    }

    /**
     * Saves cardList, tagList, and deckList data to a JSON file
     *
     * @param cardList the CardList object containing all cards to be saved
     * @param tagList  the TagList object containing all tags to be saved
     * @param deckList the DeckList object containing all decks to be saved
     * @throws StorageSaveFailure if the file cannot be saved
     */
    @Override
    public void save(CardList cardList, TagList tagList, DeckList deckList) throws StorageSaveFailure {

        Memory memory = new Memory(cardList, tagList, deckList);
        JsonObject exportData = MemoryToJson.convert(memory);

        try {
            saveDataToFile(saveFile, exportData);
            saveDataToFile(backupFile, exportData);
        } catch (IOException e) {
            String absolutePath = this.saveFile.getAbsolutePath();
            JsonStorage.ui.failedSave(absolutePath);
            throw new StorageSaveFailure(absolutePath);
        }
    }

    /**
     * Saves the given JSON data to a file.
     *
     * @param file the file to save the data to
     * @param data the JSON data to save to the file
     * @throws IOException if an I/O error occurs while writing to the file
     */
    private void saveDataToFile(File file, JsonObject data) throws IOException {
        boolean wasReadOnly = file.canWrite();

        try {
            if (!wasReadOnly) {
                file.setWritable(true);
            }
            try (FileWriter fileWriter = new FileWriter(file);
                    BufferedWriter bufferedWriter = new BufferedWriter(fileWriter)) {

                Gson gson = gsonBuilder.setPrettyPrinting().create();
                String serialized = gson.toJson(data);

                bufferedWriter.write(serialized);
            }

            if (!wasReadOnly) {
                file.setWritable(false);
            }
        } catch (IOException e) {
            if (!wasReadOnly) {
                file.setWritable(false);
            }
            throw e;
        }
    }
}
