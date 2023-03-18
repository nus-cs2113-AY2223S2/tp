package utils.storage.json;

import com.google.gson.JsonSyntaxException;
import java.util.logging.Logger;
import java.util.logging.Level;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import model.Card;
import model.CardList;
import model.CardUUID;
import model.TagUUID;
import utils.exceptions.StorageLoadFailure;
import utils.exceptions.StorageSaveFailure;
import utils.storage.Storage;

public class JsonStorage extends Storage {
    private static Logger logger = Logger.getLogger("storage.JsonStorage");
    private GsonBuilder gsonBuilder = new GsonBuilder();

    public JsonStorage(String filePath) {
        super(filePath);
        gsonBuilder = new GsonBuilder();

        //Add custom adapters
        gsonBuilder.registerTypeAdapter(CardUUID.class, new CardUuidJsonAdapter());
        gsonBuilder.registerTypeAdapter(TagUUID.class, new TagUuidJsonAdapter());
    }

    @Override
    public CardList load() throws StorageLoadFailure {

        CardList cardList;
        try (FileReader fileReader = new FileReader(saveFile);
                BufferedReader bufferedReader = new BufferedReader(fileReader)) {
            Gson gson = new Gson();
            JsonElement jsonElement = gsonBuilder.create().fromJson(bufferedReader, JsonElement.class);
            JsonObject jsonObject = jsonElement.getAsJsonObject();
            String deckName = jsonObject.get("deckName").getAsString();

            JsonArray jsonArray = jsonObject.getAsJsonArray("cards");
            Type cardListType = new TypeToken<ArrayList<Card>>() {
            }.getType();

            ArrayList<Card> cards = gsonBuilder.create().fromJson(jsonArray, cardListType);
            cardList = new CardList(cards);
        } catch (IOException e) {
            logger.log(Level.WARNING, "Failed to load file from " + this.saveFile.getAbsolutePath(), e);
            throw new StorageLoadFailure();
        } catch (NullPointerException | JsonSyntaxException e) {
            logger.log(Level.WARNING, "Corrupted save file: " + this.saveFile.getAbsolutePath(), e);
            // TODO: New exception type?
            throw new StorageLoadFailure();
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

        try (FileWriter fileWriter = new FileWriter(saveFile);
                BufferedWriter bufferedWriter = new BufferedWriter(fileWriter)) {

            Gson gson = gsonBuilder.setPrettyPrinting().create();
            String serialized = gson.toJson(exportData);

            bufferedWriter.write(serialized);
        } catch (IOException e) {
            logger.log(Level.WARNING, "Failed to save data to savedata.json", e);
            throw new StorageSaveFailure();
        }
    }
}
