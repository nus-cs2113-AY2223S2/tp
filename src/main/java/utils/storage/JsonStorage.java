package utils.storage;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import model.Card;
import model.CardList;
import utils.exceptions.StorageLoadFailure;
import utils.exceptions.StorageSaveFailure;

public class JsonStorage implements IStorage {
    private GsonBuilder gsonBuilder;

    private File saveFile;

    public JsonStorage(String filePath) {
        saveFile = new File(filePath);
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
        } catch (IOException E) {
            throw new StorageLoadFailure();
        }

        return cardList;
    }

    @Override
    public void save(CardList cardList) throws StorageSaveFailure {

        try (FileWriter fileWriter = new FileWriter(saveFile);
                BufferedWriter bufferedWriter = new BufferedWriter(fileWriter)) {

            String serialized = gsonBuilder.create().toJson(cardList);
            bufferedWriter.write(serialized);
        } catch (IOException e) {
            throw new StorageSaveFailure();
        }
    }
}
