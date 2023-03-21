package utils.storage.json;

import com.google.gson.JsonSyntaxException;

import java.io.*;
import java.util.logging.Logger;
import java.util.logging.Level;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import model.Card;
import model.CardList;
import model.CardUUID;
import model.TagUUID;
import utils.exceptions.InkaException;
import utils.exceptions.StorageCorrupted;
import utils.exceptions.StorageLoadFailure;
import utils.exceptions.StorageSaveFailure;
import utils.storage.Storage;

public class JsonStorage extends Storage {
    private static Logger logger = Logger.getLogger("storage.JsonStorage");
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
        backupFilePath = backupFileDir + File.separator + "." + backupFilePath.substring(backupFilePath.lastIndexOf(File.separator) + 1);
        backupFile = new File(backupFilePath);

        gsonBuilder = new GsonBuilder();

        //Add custom adapters
        gsonBuilder.registerTypeAdapter(CardUUID.class, new CardUuidJsonAdapter());
        gsonBuilder.registerTypeAdapter(TagUUID.class, new TagUuidJsonAdapter());
    }

    @Override
    public CardList load() throws InkaException {
        CardList cardList = null;
        boolean useBackup = false;
        try {
                FileReader fileReader = new FileReader(saveFile);

             BufferedReader bufferedReader = new BufferedReader(fileReader);
            JsonElement jsonElement = gsonBuilder.create().fromJson(bufferedReader, JsonElement.class);
            JsonObject jsonObject = jsonElement.getAsJsonObject();
            String deckName = jsonObject.get("deckName").getAsString();
            JsonArray jsonArray = jsonObject.getAsJsonArray("cards");
            Type cardListType = new TypeToken<ArrayList<Card>>() {
            }.getType();
            ArrayList<Card> cards = gsonBuilder.create().fromJson(jsonArray, cardListType);
            cardList = new CardList(cards);

        } catch (IOException e) {
            String absolutePath = this.saveFile.getAbsolutePath();
            logger.log(Level.WARNING, "Failed to load file from " + absolutePath, e);

            throw new StorageLoadFailure(absolutePath);
        } catch (NullPointerException | JsonSyntaxException e) {
            String absolutePath = this.saveFile.getAbsolutePath();
            logger.log(Level.WARNING, "Corrupted save file: " + absolutePath, e);
            useBackup = true;
        }

        if (useBackup == true) {
            logger.log(Level.INFO, "Trying to load backup file");
            try{  FileReader fileReader = new FileReader(backupFile);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
                JsonElement jsonElement = gsonBuilder.create().fromJson(bufferedReader, JsonElement.class);
                JsonObject jsonObject = jsonElement.getAsJsonObject();
                String deckName = jsonObject.get("deckName").getAsString();

                JsonArray jsonArray = jsonObject.getAsJsonArray("cards");
                Type cardListType = new TypeToken<ArrayList<Card>>() {
                }.getType();

                ArrayList<Card> cards = gsonBuilder.create().fromJson(jsonArray, cardListType);
                cardList = new CardList(cards);
            } catch (IOException | NullPointerException | JsonSyntaxException ex) {
                String absolutePath = this.backupFile.getAbsolutePath();
                logger.log(Level.WARNING, "Corrupted backup file: " + absolutePath, ex);
                throw new StorageCorrupted(absolutePath);
            }
        }

        return cardList;
    }


    @Override
    public void save(CardList cardList) throws StorageSaveFailure {

        JsonObject exportData = new JsonObject();
        exportData.addProperty("deckName", "lky deck");
        exportData.addProperty("numCards", cardList.size());

        // Serialize cards
        JsonArray cardData = new JsonArray();
        for (int i = 0; i < cardList.size(); i++) {
            Card card = cardList.get(i);
            JsonObject cardObject = new JsonObject();
            cardObject.addProperty("uuid", card.getUuid().toString());
            cardObject.addProperty("question", card.getQuestion());
            cardObject.addProperty("answer", card.getAnswer());
            cardData.add(cardObject);
        }
        exportData.add("cards", cardData);

        try {
            // Save data to the main file
            saveDataToFile(saveFile, exportData);

            // Save data to the backup file
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
