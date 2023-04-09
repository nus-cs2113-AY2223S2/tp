package utils.storage.json;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.UUID;
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
    /**
     * Converts a JsonObject representation of a memory into a Memory object. This method is called by the
     * {@link JsonStorage#load()} method.
     *
     * @param saveDataObject the JsonObject representation of the memory
     * @return the Memory object converted from the JsonObject
     * @throws InkaException if there is an error in the conversion process
     */
    public static Memory convert(JsonObject saveDataObject) throws InkaException {

        CardList cardList = getJsonCardList(saveDataObject);
        TagList tagList = getJsonTagList(saveDataObject);
        DeckList deckList = getJsonDeckList(saveDataObject);

        Memory memory = new Memory(cardList, tagList, deckList);

        return memory;
    }

    /**
     * Converts a JSON object to a CardList object by parsing the "cards" JsonArray.
     *
     * @param saveDataObject the JSON object containing the data to be converted
     * @return a CardList object generated from the JSON data
     * @throws InkaException if there is an error while parsing the data
     */
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

    /**
     * Converts a JSON object to a TagList object by parsing the "tags" member of saveDataObject
     *
     * @param saveDataObject the JsonObject representing the savedata with the "tags" member
     * @return the TagList object containing the taglist
     */
    public static TagList getJsonTagList(JsonObject saveDataObject) {
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

    /**
     * Extracts and converts a JSON object representing savedata into a DeckList object.
     *
     * @param saveDataObject the JSON object containing the data
     * @return the DeckList object
     */
    public static DeckList getJsonDeckList(JsonObject saveDataObject) {
        DeckList deckList = new DeckList();
        JsonArray deckJsonArray = saveDataObject.getAsJsonArray("decks");
        for (JsonElement jsonDeck : deckJsonArray) {
            JsonObject deckObject = jsonDeck.getAsJsonObject();
            Deck deck = getJsonDeck(deckObject);
            deckList.addDeck(deck);
        }
        return deckList;
    }

    /**
     * Converts a JSON object into a Deck object. Used by the {@link JsonToMemory#getJsonDeckList(JsonObject)} method.
     *
     * @param deckObject the JSON object representing a deck
     * @return a Deck object with properties parsed from the JSON object
     */
    public static Deck getJsonDeck(JsonObject deckObject) {

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
        boolean mapEmpty = false;

        try {
            for (HashMap.Entry<String, JsonElement> entry : cardUuidIntegerHashMapObject.entrySet()) {
                String cardUuidString = entry.getKey();
                int value = entry.getValue().getAsInt();
                cardUuidIntegerHashMap.put(new CardUUID(UUID.fromString(cardUuidString)), value);
            }
        } catch (NullPointerException e) {
            mapEmpty = true;
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
