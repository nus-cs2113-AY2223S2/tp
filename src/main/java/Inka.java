import model.CardList;
import utils.Parser;
import utils.UserInterface;
import utils.command.Command;
import utils.exceptions.ExceptionHandler;
import utils.exceptions.StorageLoadFailure;
import utils.storage.IStorage;
import utils.storage.JsonStorage;

public class Inka {

    private final UserInterface ui;
    private final Parser parser;
    private ExceptionHandler exceptionHandler;
    private IStorage storage;

    private CardList cardList;

    public Inka(String filePath) {
        storage = new JsonStorage(filePath);
        ui = new UserInterface();
        parser = new Parser();
        cardList = new CardList();
        exceptionHandler = new ExceptionHandler();

        // TODO: Separate no file into another print
        try {
            cardList = storage.load();
            cardList = storage.load();
        } catch (StorageLoadFailure e) {
            ui.printCreateNewSaveFile();
        }
    }

    public static void main(String[] args) {
        new Inka("savedata.json").run();
    }

    public void run() {
        ui.printGreeting();

        while (parser.getIsExecuting()) {
            String userInput = ui.getUserInput();
            Command command = exceptionHandler.mainExceptionHandler(parser, userInput, ui, cardList);
            command.execute(cardList, ui, storage);
        }
    }
}
