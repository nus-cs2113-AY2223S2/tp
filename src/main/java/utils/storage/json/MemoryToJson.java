package utils.storage.json;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import model.*;

public class MemoryToJson {

    public static JsonObject convert(Memory memory) {
        JsonObject jsonMemory = new JsonObject();
        Gson gson = new Gson();

        CardList cardList = memory.getCardList();
        TagList tagList = memory.getTagList();
        DeckList deckList = memory.getDeckList();

        JsonArray cardData = cardListToJson(cardList);
        JsonArray tagData = tagListToJson(tagList);
        JsonArray deckData = deckListToJson(deckList);

        jsonMemory.add("decks", deckData);
        jsonMemory.add("tags", tagData);
        jsonMemory.add("cards", cardData);


        return jsonMemory;
    }

    private static JsonArray cardListToJson(CardList cardList) {
        Gson gson = new Gson();
        JsonArray cardData = new JsonArray();
        for (int i = 0; i < cardList.size(); i++) {
            Card card = cardList.get(i);
            JsonObject cardObject = gson.toJsonTree(card).getAsJsonObject();
            cardData.add(cardObject);
        }

        return cardData;
    }

    private static JsonArray tagListToJson(TagList tagList) {
        Gson gson = new Gson();
        JsonArray tagData = new JsonArray();
        for (int i = 0; i < tagList.size(); i++) {
            Tag tag = tagList.get(i);
            JsonObject tagObject = gson.toJsonTree(tag).getAsJsonObject();
            tagData.add(tagObject);
        }

        return tagData;
    }

    private static JsonArray deckListToJson(DeckList deckList) {
        Gson gson = new Gson();
        JsonArray deckData = new JsonArray();
        for (int i = 0; i < deckList.size(); i++) {
            Deck deck = deckList.get(i);
            JsonObject deckObject = gson.toJsonTree(deck).getAsJsonObject();
            deckData.add(deckObject);
        }

        return deckData;
    }
}
