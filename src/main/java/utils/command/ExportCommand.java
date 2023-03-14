package utils.command;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import java.io.FileWriter;
import java.io.IOException;
import model.Card;
import model.CardList;
import utils.UserInterface;

public class ExportCommand extends Command {

    @Override
    public void execute(CardList cardList, UserInterface ui) {

        JsonObject exportData = new JsonObject();
        exportData.addProperty("deckName", "lky deck");
        exportData.addProperty("numCards", cardList.size());
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

        // Use Gson to write the exportData object to a JSON file
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        try {
            FileWriter writer = new FileWriter("savedata.json");
            gson.toJson(exportData, writer);
            writer.close();
            ui.printExportSuccess();
        } catch (IOException e) {
            // Handle the IOException
            e.printStackTrace();
        }
    }
}
