import model.CardList;
import model.TagList;
import utils.UserInterface;
import utils.command.Command;
import utils.exceptions.InkaException;
import utils.parser.Parser;
import utils.storage.Storage;
import utils.storage.json.JsonStorage;

public class Inka {

    private final UserInterface ui;
    private final Parser parser;
    private Storage storage;

    private CardList cardList;
    private TagList tagList;

    public Inka(String filePath) {
        storage = new JsonStorage(filePath);
        ui = new UserInterface();
        parser = new Parser();

        cardList = loadSaveFile();
        tagList = new TagList();
    }

    public static void main(String[] args) {
        new Inka("savedata.json").run();
    }

    /**
     * Attempts to load from save file
     *
     * @return CardList containing all saved cards
     */
    private CardList loadSaveFile() {
        // Notify user of no previously saved file
        if (!storage.saveFileExists()) {
            ui.printNoSaveFile();
            return new CardList();
        }

        // File exists; try to load from it
        CardList cardList = new CardList();
        try {
            cardList = storage.load();
            ui.printLoadSuccess();
        } catch (InkaException e) {
            ui.printException(e);
        }

        return cardList;
    }

    public void run() {
        ui.printGreeting();

        while (parser.getIsExecuting()) {
            String userInput = ui.getUserInput();
            try {
                Command command = parser.parseCommand(userInput);
                command.execute(cardList, tagList, ui, storage);
            } catch (InkaException e) {
                ui.printException(e);
            }
        }
    }
}
