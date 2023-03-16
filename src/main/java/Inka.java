import java.io.IOException;
import model.CardList;
import utils.Parser;
import utils.UserInterface;
import utils.command.Command;
import utils.exceptions.InkaException;
import utils.exceptions.StorageLoadFailure;
import utils.storage.JsonStorage;
import utils.storage.Storage;

public class Inka {

    private final UserInterface ui;
    private final Parser parser;
    private Storage storage;

    private CardList cardList;

    public Inka(String filePath) {
        storage = new JsonStorage(filePath);
        ui = new UserInterface();
        parser = new Parser();

        cardList = loadSaveFile();
    }

    public static void main(String[] args) {
        new Inka("savedata.json").run();
    }

    /**
     * Attempts to load from save file
     *
     * @return CardList containing all saved cards
     * @note Will create a new file if no file exists
     */
    private CardList loadSaveFile() {
        // No previously saved file
        if (!storage.saveFileExists()) {
            try {
                storage.createSaveFile();
                ui.printNoSaveFile();
            } catch (IOException e) {
                ui.printSaveFailure();
            }

            return new CardList();
        }

        // File exists; try to load from it
        CardList cardList = new CardList();
        try {
            cardList = storage.load();
            ui.printLoadSuccess();
        } catch (StorageLoadFailure e) {
            ui.printLoadFailure();
        }

        return cardList;
    }

    public void run() {
        ui.printGreeting();

        while (parser.getIsExecuting()) {
            String userInput = ui.getUserInput();
            //Command command = exceptionHandler.mainExceptionHandler(parser, userInput, ui, cardList);
            try {
                Command command = parser.parseCommand(userInput, cardList);
                command.execute(cardList, ui, storage);
            } catch (InkaException e) {
                ui.printException(e);
            }
        }
    }
}
