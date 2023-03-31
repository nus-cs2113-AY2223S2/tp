package utils.storage.json;

import com.google.gson.JsonSyntaxException;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;


import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.UUID;
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

import model.TagList;
import model.CardList;
import model.TagUUID;
import model.Tag;
import model.Memory;
import model.DeckList;
import model.Card;
import model.Deck;
import model.CardUUID;
import model.DeckUUID;

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
        backupFilePath = backupFileDir + File.separator + "." + backupFilePath.substring(
                backupFilePath.lastIndexOf(File.separator) + 1);
        backupFile = new File(backupFilePath);













        gsonBuilder = new GsonBuilder();

        //Add custom adapters
        gsonBuilder.registerTypeAdapter(CardUUID.class, new CardUuidJsonAdapter());
        gsonBuilder.registerTypeAdapter(TagUUID.class, new TagUuidJsonAdapter());
    }

    @Override
    public Memory load() throws InkaException {
        CardList cardList = null;
        TagList tagList = null;
        DeckList deckList = null;
        boolean useBackup = false;
        try {
            FileReader fileReader = new FileReader(saveFile);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            gsonBuilder.setLenient();



            JsonElement jsonElement = gsonBuilder.create().fromJson(bufferedReader, JsonElement.class);
            JsonObject saveDataObject = jsonElement.getAsJsonObject();


            JsonArray jsonArray = saveDataObject.getAsJsonArray("cards");
            ArrayList<Card> cards = new ArrayList<>();




            for (JsonElement jsonCard : jsonArray) {
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

            cardList = new CardList(cards); // assign cardList object with cards

            tagList = new TagList(); // construct empty taglist to append stuff to
            JsonArray tagJsonArray = saveDataObject.getAsJsonArray("tags");
            for(JsonElement jsonTag : tagJsonArray) {
                JsonObject tagObject = jsonTag.getAsJsonObject();
                JsonObject uuidObject = tagObject.getAsJsonObject("uuid");
                String uuidString = uuidObject.get("uuid").getAsString();

                String tagName = tagObject.get("tagName").getAsString();
                JsonArray tagCardArray = tagObject.getAsJsonArray("cards");
                Tag tag = new Tag(tagName, uuidString, "pw");

                for (JsonElement cardListElement : tagCardArray) {
                    JsonObject cardUuidObject = cardListElement.getAsJsonObject();

                    String cardUuidString = cardUuidObject.get("uuid").getAsString();
                    tag.addCard(new CardUUID(UUID.fromString(cardUuidString)));
                }

                tagList.addTag(tag);





            }

            deckList = new DeckList();
            JsonArray deckJsonArray = saveDataObject.getAsJsonArray("decks");
            for(JsonElement jsonDeck : deckJsonArray) {
                JsonObject deckObject = jsonDeck.getAsJsonObject();
                JsonObject uuidObject = deckObject.getAsJsonObject("deckUUID");
                String uuidString = uuidObject.get("uuid").getAsString();

                String deckName = deckObject.get("deckName").getAsString();
                Deck deck = new Deck(deckName);
                deck.setDeckUUID(uuidString);


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

                deckList.addDeck(deck);



            }

        } catch (IOException e) {

            String absolutePath = this.saveFile.getAbsolutePath();
            logger.log(Level.WARNING, "Failed to load file from " + absolutePath, e);

            throw new StorageLoadFailure(absolutePath);
        } catch (NullPointerException | JsonSyntaxException e) {
            String absolutePath = this.saveFile.getAbsolutePath();
            logger.log(Level.WARNING, "Corrupted save file: " + absolutePath, e);
            //useBackup = true;
            throw new StorageCorrupted(absolutePath);
        }

        if (useBackup == true) {
            logger.log(Level.INFO, "Trying to load backup file");
            try {
                FileReader fileReader = new FileReader(backupFile);
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

        Memory savedMemory = new Memory();
        savedMemory.setCardList(cardList);
        savedMemory.setTagList(tagList);
        savedMemory.setDeckList(deckList);

        return savedMemory;
    }

    @Override
    public void save(CardList cardList, TagList tagList, DeckList deckList) throws StorageSaveFailure {

        JsonObject exportData = new JsonObject();
        Gson gson = new Gson();
        // Serialize cards
        JsonArray cardData = new JsonArray();
        for (int i = 0; i < cardList.size(); i++) {
            // Convert the card object to a JsonObject using Gson
            Card card = cardList.get(i);

            JsonObject cardObject = gson.toJsonTree(card).getAsJsonObject();

            // Add the card object to the cardData array
            cardData.add(cardObject);
        }
        exportData.add("cards", cardData);

        // Serialize Tags

        JsonArray tagData = new JsonArray();
        for (int i = 0; i < tagList.size(); i++) {
            // Convert the card object to a JsonObject using Gson
            Tag tag = tagList.get(i);

            JsonObject tagObject = gson.toJsonTree(tag).getAsJsonObject();

            // Add the card object to the cardData array
            tagData.add(tagObject);
        }
        exportData.add("tags", tagData);

        JsonArray deckData = new JsonArray();
        for (int i = 0; i < deckList.size(); i++) {
            // Convert the card object to a JsonObject using Gson
            Deck deck = deckList.get(i);

            JsonObject deckObject = gson.toJsonTree(deck).getAsJsonObject();

            // Add the card object to the cardData array
            deckData.add(deckObject);
        }
        exportData.add("decks", deckData);

        try {
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
