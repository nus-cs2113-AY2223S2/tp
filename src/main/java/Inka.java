import utils.cardlist.CardList;
import utils.parser.Parser;
import utils.userinterface.UserInterface;
import utils.command.*;

public class Inka {
    private final UserInterface ui;
    private final Parser parser;
    private CardList cardList;

    public Inka() {
        ui = new UserInterface();
        parser = new Parser();
        cardList = new CardList();
    }

    public void run() {
        ui.printGreeting();
        while (parser.getIsExecuting()) {
            String userCommand = ui.getCommand();
            Command command = parser.parseCommand(userCommand);
            command.execute(cardList, ui);
        }
    }

    public static void main(String[] args) {
        new Inka().run();
    }
}
