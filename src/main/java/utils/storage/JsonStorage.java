package utils.storage;

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
import utils.exceptions.StorageLoadFailure;
import utils.exceptions.StorageSaveFailure;

public class JsonStorage extends Storage {
    private static Logger logger = Logger.getLogger("storage.JsonStorage");
    private GsonBuilder gsonBuilder;

    public JsonStorage(String filePath) {
        super(filePath);
        gsonBuilder = new GsonBuilder();
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

            ArrayList<Card> cards = gson.fromJson(jsonArray, cardListType);
            cardList = new CardList(cards);
        } catch (IOException e) {
            logger.log(Level.WARNING, "Failed to load file from savedata.json", e);
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
            cardObject.addProperty("uuid", card.getUuid());
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
