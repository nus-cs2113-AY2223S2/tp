
package utils;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import utils.cardlist.CardList;
import utils.exceptions.ImportBad;

import java.io.*;

import java.io.FileReader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private File f;

    public Storage(String filePath) {
        f = new File(filePath);


    }

    public void load(String fileName, CardList cardList) throws ImportBad {
        try {
            Gson gson = new Gson();
            BufferedReader reader = new BufferedReader(new FileReader(fileName));
            JsonElement jsonElement = gson.fromJson(reader, JsonElement.class);
            JsonObject jsonObject = jsonElement.getAsJsonObject();
            String deckName = jsonObject.get("deckName").getAsString();

            JsonArray jsonArray = jsonObject.getAsJsonArray("cards");
            Type cardListType = new TypeToken<ArrayList<Card>>(){}.getType();
            ArrayList<Card> cards = gson.fromJson(jsonArray, cardListType); // creates an arraylist of cards
            CardList currentCardList = new CardList(); // creates a new cardlist
            currentCardList.cards = cards; // appends the arraylist to the cardlist class
            cardList.cards = currentCardList.cards; // set the cards field in the CardList object
            reader.close();

        } catch (IOException E) {
            //too bad!
            throw new ImportBad();


        }


    }
}
