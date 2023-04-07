package utils.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.UUID;
import model.Card;
import model.CardUUID;
import model.DeckList;
import model.DeckUUID;
import model.Memory;
import model.TagUUID;
import org.junit.jupiter.api.Test;
import utils.exceptions.InkaException;
import utils.exceptions.StorageCorrupted;
import utils.storage.json.JsonStorage;
import model.CardList;

public class JsonStorageTest {

    private static final Path TEST_DATA_FOLDER = Path.of("src/test/data/storage");

    /* Test cases */
    private static final Path EMPTY_FILE = TEST_DATA_FOLDER.resolve("empty.json");
    private static final Path MALFORMED_FILE = TEST_DATA_FOLDER.resolve("malformed.json");
    private static final Path VALID_FILE = TEST_DATA_FOLDER.resolve("valid.json");

    //valid file properties

    private static final String card1Uuid = "1ddd9a67-f56c-4914-99c0-2f90c580f0e9";
    private static final String card1Q = "fdfds";
    private static final String card1A = "ffffffghgg";
    private static final String fileCard1DeckUuid = "c83e08ad-e5b7-4812-9dd1-4b44504386ad";
    private static final int fileCard1DeckSize = 1;
    private static final int fileCard1TagSize = 1;
    private static final String fileCard1TagUuid = "03658854-e5d4-468f-8c41-74917e5d4515";

    private static final int fileDeckSize = 2;

    private static final String card2Uuid = "619c689d-395a-4bb8-ab00-6ae9972bb929";
    private static final String card2Q = "question2";



    @Test
    public void load_emptyFile() {
        Storage storage = new JsonStorage(EMPTY_FILE.toString());
        assertThrows(StorageCorrupted.class, storage::load, "Expected a StorageCorrupted exception");
    }

    @Test
    public void load_malformedFile() {
        Storage storage = new JsonStorage(MALFORMED_FILE.toString());
        assertThrows(StorageCorrupted.class, storage::load, "Expected a StorageCorrupted exception");
    }

    @Test
    public void load_validFile() throws InkaException {
        Storage storage = new JsonStorage(VALID_FILE.toString());
        Memory memory = storage.load();


    }



    @Test
    public void load_validFile_cardList() throws InkaException {
        //check if it is loading 2 cards
        Storage storage = new JsonStorage(VALID_FILE.toString());
        Memory memory = storage.load();
        CardList cardList = memory.getCardList();
        int cardListSize = cardList.size();
        int validFileCardListSize = 2;
        int expectedSize = validFileCardListSize;
        assertEquals(cardListSize, expectedSize);

    }

    @Test
    public void load_validFile_cardUuid() throws InkaException {
        Storage storage = new JsonStorage(VALID_FILE.toString());
        Memory memory = storage.load();
        CardList cardList = memory.getCardList();

        CardUUID card1UuidObj = new CardUUID(UUID.fromString(card1Uuid));
        String card1UuidStr = card1UuidObj.toString();

        Card card1 = cardList.findCardFromUUID(card1UuidObj);
        CardUUID card1TestUuidobj =  card1.getUuid();
        String card1TestUuidStr = card1TestUuidobj.toString();

        assertEquals(card1UuidStr, card1TestUuidStr);
        //todo need to add case for checking for malformed UUID

    }

    @Test
    public void load_validFile_cardTags() throws InkaException {
        //check if it is loading 2 cards
        Storage storage = new JsonStorage(VALID_FILE.toString());
        Memory memory = storage.load();
        CardList cardList = memory.getCardList();

        CardUUID card1UuidObj = new CardUUID(UUID.fromString(card1Uuid));
        Card card1 = cardList.findCardFromUUID(card1UuidObj);
        ArrayList<TagUUID> card1Tags =  card1.getTagsUUID();
        int  expectedSize = fileCard1TagSize;


        assertEquals(expectedSize, card1Tags.size());
        //todo need to add case for checking for malformed UUID

    }

    @Test
    public void load_validFile_cardTagUuid() throws InkaException {
        //check if it is loading tag's Uuids
        Storage storage = new JsonStorage(VALID_FILE.toString());
        Memory memory = storage.load();
        CardList cardList = memory.getCardList();

        CardUUID card1UuidObj = new CardUUID(UUID.fromString(card1Uuid));
        Card card1 = cardList.findCardFromUUID(card1UuidObj);
        ArrayList<TagUUID> card1Tags =  card1.getTagsUUID();
        TagUUID card1Tag1UuidObj = card1Tags.get(0);
        String card1Tag1UuidObjStr = card1Tag1UuidObj.toString();


        assertEquals(fileCard1TagUuid, card1Tag1UuidObjStr);
        //todo need to add case for checking for malformed UUID

    }


    @Test
    public void load_validFile_cardDecks() throws InkaException {
        //check if it is loading 2 cards
        Storage storage = new JsonStorage(VALID_FILE.toString());
        Memory memory = storage.load();
        CardList cardList = memory.getCardList();

        CardUUID card1UuidObj = new CardUUID(UUID.fromString(card1Uuid));
        Card card1 = cardList.findCardFromUUID(card1UuidObj);
        ArrayList<DeckUUID> card1Decks =  card1.getDecksUUID();
        int  expectedSize = fileCard1DeckSize;


        assertEquals(expectedSize, card1Decks.size());
        //todo need to add case for checking for malformed UUID

    }
    @Test
    public void load_validFile_cardDeckUuid() throws InkaException {
        //check if it is loading deck's Uuids
        Storage storage = new JsonStorage(VALID_FILE.toString());
        Memory memory = storage.load();
        CardList cardList = memory.getCardList();

        CardUUID card1UuidObj = new CardUUID(UUID.fromString(card1Uuid));
        Card card1 = cardList.findCardFromUUID(card1UuidObj);
        ArrayList<DeckUUID> card1Decks =  card1.getDecksUUID();
        DeckUUID card1Deck1UuidObj = card1Decks.get(0);
        String card1Deck1UuidObjStr = card1Deck1UuidObj.toString();


        assertEquals(fileCard1DeckUuid, card1Deck1UuidObjStr);
        //todo need to add case for checking for malformed UUID

    }

    @Test
    public void load_validFile_deckList() throws InkaException {
        //check if it is loading deckList
        Storage storage = new JsonStorage(VALID_FILE.toString());
        Memory memory = storage.load();
        DeckList deckList = memory.getDeckList();
        int testDeckSize = deckList.getDecks().size();



        assertEquals(fileDeckSize, testDeckSize);
        //todo need to add case for checking for malformed UUID

    }


}
