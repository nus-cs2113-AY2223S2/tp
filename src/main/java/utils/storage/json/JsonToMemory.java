package utils.storage.json;



import static utils.storage.json.JsonStorage.logger;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.UUID;
import java.util.logging.Level;
import model.CardUUID;
import model.DeckUUID;
import model.TagList;
import model.CardList;
import model.Tag;
import model.Memory;
import model.DeckList;
import model.Card;
import model.Deck;
import model.TagUUID;
import utils.exceptions.InkaException;


public class JsonToMemory {
    public static Memory convert(JsonObject saveDataObject) throws InkaException {

        CardList cardList = getJsonCardList(saveDataObject);
        TagList tagList = getJsonTagList(saveDataObject);
        DeckList deckList = getJsonDeckList(saveDataObject);

        Memory memory = new Memory(cardList, tagList, deckList);


        return memory;
    }


    public static CardList getJsonCardList(JsonObject saveDataObject) throws InkaException {
        JsonArray jsonCardArray = saveDataObject.getAsJsonArray("cards");
        ArrayList<Card> cards = new ArrayList<>();

        for (JsonElement jsonCard : jsonCardArray) {
            JsonObject cardObject = jsonCard.getAsJsonObject();

            JsonObject uuidObject = cardObject.getAsJsonObject("uuid");
            String uuidString = uuidObject.get("uuid").getAsString();
            String question = cardObject.get("question").getAsString();
            String answer = cardObject.get("answer").getAsString();
            Card card = Card.createCardWithUUID(question, answer, uuidString);
            JsonArray tagsArray = cardObject.getAsJsonArray("tags");
            JsonArray decksArray = cardObject.getAsJsonArray("decks");

            for (JsonElement tagListElement : tagsArray) {
                JsonObject tagUuidObject = tagListElement.getAsJsonObject();
                String tagUuidString = tagUuidObject.get("uuid").getAsString();
                card.addTag(new TagUUID(UUID.fromString(tagUuidString)));
            }

            for (JsonElement deckListElement : decksArray) {
                JsonObject deckUuidObject = deckListElement.getAsJsonObject();
                String deckUuidString = deckUuidObject.get("uuid").getAsString();
                card.addDeck(new DeckUUID(UUID.fromString(deckUuidString)));
            }

            cards.add(card);
        }
        CardList cardList = new CardList(cards); // assign cardList object with cards
        return cardList;
    }

    public static TagList getJsonTagList(JsonObject saveDataObject){
        TagList tagList = new TagList(); // construct empty taglist to append stuff to
        JsonArray tagJsonArray = saveDataObject.getAsJsonArray("tags");
        for (JsonElement jsonTag : tagJsonArray) {
            JsonObject tagObject = jsonTag.getAsJsonObject();
            JsonObject uuidObject = tagObject.getAsJsonObject("uuid");
            String uuidString = uuidObject.get("uuid").getAsString();

            String tagName = tagObject.get("tagName").getAsString();
            JsonArray tagCardArray = tagObject.getAsJsonArray("cards");
            Tag tag = new Tag(tagName, uuidString);

            for (JsonElement cardListElement : tagCardArray) {
                JsonObject cardUuidObject = cardListElement.getAsJsonObject();

                String cardUuidString = cardUuidObject.get("uuid").getAsString();
                tag.addCard(new CardUUID(UUID.fromString(cardUuidString)));
            }

            tagList.addTag(tag);
        }

        return tagList;

    }

    public static DeckList getJsonDeckList(JsonObject saveDataObject){
        DeckList deckList = new DeckList();
        JsonArray deckJsonArray = saveDataObject.getAsJsonArray("decks");
        for (JsonElement jsonDeck : deckJsonArray) {
            JsonObject deckObject = jsonDeck.getAsJsonObject();
            Deck deck = getJsonDeck(deckObject);
            deckList.addDeck(deck);
        }
        return deckList;

    }

    public static Deck getJsonDeck(JsonObject deckObject){

        String deckName = deckObject.get("deckName").getAsString();
        Deck deck = new Deck(deckName);

        JsonObject uuidObject = deckObject.getAsJsonObject("deckUUID");
        String uuidString = uuidObject.get("uuid").getAsString();
        deck.setDeckUUID(uuidString);

        // obtain cardsSet
        JsonArray deckCardSetArray = deckObject.getAsJsonArray("cardsSet");
        HashSet<CardUUID> cardsSet = new HashSet<>();
        for (JsonElement deckListElement : deckCardSetArray) {
            JsonObject cardUuidObject = deckListElement.getAsJsonObject();
            String cardUuidString = cardUuidObject.get("uuid").getAsString();
            cardsSet.add(new CardUUID(UUID.fromString(cardUuidString)));
        }
        deck.setCardsSet(cardsSet);

        //obtain cardUUIDIntegerHashMap
        JsonObject cardUuidIntegerHashMapObject = deckObject.getAsJsonObject("cardUuidIntegerHashMap");
        HashMap<CardUUID, Integer> cardUuidIntegerHashMap = new HashMap<>();

        try{
            for (HashMap.Entry<String, JsonElement> entry : cardUuidIntegerHashMapObject.entrySet()) {
                String cardUuidString = entry.getKey();
                int value = entry.getValue().getAsInt();
                cardUuidIntegerHashMap.put(new CardUUID(UUID.fromString(cardUuidString)), value);
            }
        } catch (NullPointerException e){
            logger.log(Level.WARNING, "Invalid: hashmap", e);
        }
        deck.setcardUUIDIntegerHashMap(cardUuidIntegerHashMap);

        //obtain cards from a jsonDeck
        JsonArray deckCardArray = deckObject.getAsJsonArray("cards");

        for (JsonElement deckListElement : deckCardArray) {
            JsonObject cardUuidObject = deckListElement.getAsJsonObject();
            String cardUuidString = cardUuidObject.get("uuid").getAsString();
            deck.addCard(new CardUUID(UUID.fromString(cardUuidString)));
        }


        //obtain tags from a jsonDeck
        JsonArray deckTagArray = deckObject.getAsJsonArray("tags");
        for (JsonElement deckListElement : deckTagArray) {
            JsonObject tagUuidObject = deckListElement.getAsJsonObject();
            String tagUuidString = tagUuidObject.get("uuid").getAsString();
            deck.addTag(new TagUUID(UUID.fromString(tagUuidString)));
        }

        return deck;
    }
}
