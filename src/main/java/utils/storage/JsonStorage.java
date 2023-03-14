package utils.storage;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import model.Card;
import model.CardList;
import utils.exceptions.StorageLoadFailure;
import utils.exceptions.StorageSaveFailure;

public class JsonStorage implements IStorage {
    private File f;

    public JsonStorage(String filePath) {
        f = new File(filePath);
    }

    @Override
    public CardList load(String fileName) throws StorageLoadFailure {

        CardList cardList;
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            Gson gson = new Gson();
            JsonElement jsonElement = gson.fromJson(reader, JsonElement.class);
            JsonObject jsonObject = jsonElement.getAsJsonObject();
            String deckName = jsonObject.get("deckName").getAsString();

            JsonArray jsonArray = jsonObject.getAsJsonArray("cards");
            Type cardListType = new TypeToken<ArrayList<Card>>() {
            }.getType();

            ArrayList<Card> cards = gson.fromJson(jsonArray, cardListType);
            cardList = new CardList(cards);
        } catch (IOException E) {
            throw new StorageLoadFailure();
        }

        return cardList;
    }

    @Override
    public void save(String fileName, CardList cardList) throws StorageSaveFailure {
        // TODO: Not implemented
        throw new StorageSaveFailure();
    }
}
