package utils.storage.json;

import com.google.gson.JsonSyntaxException;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Logger;
import java.util.logging.Level;

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

import utils.exceptions.InkaException;
import utils.exceptions.StorageCorrupted;
import utils.exceptions.StorageLoadFailure;
import utils.exceptions.StorageSaveFailure;
import utils.storage.Storage;

public class JsonStorage extends Storage {

    public static Logger logger = Logger.getLogger("storage.JsonStorage");
    private GsonBuilder gsonBuilder;
    private File backupFile;

    public JsonStorage(String filePath) {
        super(filePath);

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
    }

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
            logger.log(Level.WARNING, "Failed to load file from " + absolutePath, e);
            throw new StorageLoadFailure(absolutePath);
        } catch (NullPointerException | JsonSyntaxException e) {
            String absolutePath = this.saveFile.getAbsolutePath();
            logger.log(Level.WARNING, "Corrupted save file: " + absolutePath, e);
            return loadBackup();
        }
    }

    private Memory loadBackup() throws InkaException {
        try {
            FileReader fileReader = new FileReader(backupFile);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            JsonElement jsonElement = gsonBuilder.create().fromJson(bufferedReader, JsonElement.class);
            JsonObject saveDataObject = jsonElement.getAsJsonObject();
            Memory savedMemory = JsonToMemory.convert(saveDataObject);
            logger.log(Level.INFO, "Loaded backup file successfully");
            return savedMemory;
        } catch (IOException | NullPointerException | JsonSyntaxException ex) {
            String absolutePath = this.backupFile.getAbsolutePath();
            logger.log(Level.WARNING, "Corrupted backup file: " + absolutePath, ex);
            throw new StorageCorrupted(absolutePath);
        }
    }



    public static Memory makeMemory(CardList cardList, TagList tagList, DeckList deckList) {
        Memory memory = new Memory();
        memory.setCardList(cardList);
        memory.setTagList(tagList);
        memory.setDeckList(deckList);

        return memory;
    }


    @Override
    public void save(CardList cardList, TagList tagList, DeckList deckList) throws StorageSaveFailure {

        Memory memory = new Memory(cardList, tagList, deckList);
        JsonObject exportData = MemoryToJson.convert(memory);

        try {
            saveDataToFile(saveFile, exportData);
            saveDataToFile(backupFile, exportData);
        } catch (IOException e) {
            String absolutePath = this.saveFile.getAbsolutePath();
            logger.log(Level.WARNING, "Failed to save data to savedata.json" + absolutePath, e);
            throw new StorageSaveFailure(absolutePath);
        }
    }

    private void saveDataToFile(File file, JsonObject data) throws IOException {
        try (FileWriter fileWriter = new FileWriter(file);
             BufferedWriter bufferedWriter = new BufferedWriter(fileWriter)) {

            Gson gson = gsonBuilder.setPrettyPrinting().create();
            String serialized = gson.toJson(data);

            bufferedWriter.write(serialized);
        }
    }
}
