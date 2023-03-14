import utils.Storage;
import model.CardList;
import utils.command.Command;
import utils.exceptions.ExceptionHandler;
import utils.exceptions.ImportBad;
import utils.Parser;
import utils.UserInterface;


public class Inka {

    private final UserInterface ui;
    private final Parser parser;
    private CardList cardList;

    private Storage storage;
    private ExceptionHandler exceptionHandler;

    public Inka(String filePath) {
        storage = new Storage(filePath);
        ui = new UserInterface();
        parser = new Parser();
        cardList = new CardList();
        exceptionHandler = new ExceptionHandler();

        try {
            storage.load(filePath, cardList);
        } catch (ImportBad e) {
            ui.printImportBad();
        }
    }

    public void run() {
        ui.printGreeting();

        while (parser.getIsExecuting()) {
            String userCommand = ui.getCommand();
            Command command = exceptionHandler.mainExceptionHandler(parser, userCommand, ui, cardList);
            command.execute(cardList, ui);
        }
    }

    public static void main(String[] args) {
        new Inka("savedata.json").run();
    }
}
