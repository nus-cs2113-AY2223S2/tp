import model.CardList;
import utils.Parser;
import utils.exceptions.StorageLoadFailure;
import utils.storage.IStorage;
import utils.storage.JsonStorage;
import utils.UserInterface;
import utils.command.Command;
import utils.exceptions.ExceptionHandler;
import utils.exceptions.StorageSaveFailure;

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

        try {
            cardList = storage.load(filePath);
        } catch (StorageLoadFailure e) {
            ui.printImportBad();
        }
    }

    public static void main(String[] args) {
        new Inka("savedata.json").run();
    }

    public void run() {
        ui.printGreeting();

        while (parser.getIsExecuting()) {
            // TODO: Confusing naming
            String userCommand = ui.getCommand();
            Command command = exceptionHandler.mainExceptionHandler(parser, userCommand, ui, cardList);
            command.execute(cardList, ui);
        }
    }
}
