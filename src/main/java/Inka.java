import utils.cardlist.CardList;
import utils.parser.Parser;
import utils.userinterface.UserInterface;

public class Inka {
    private final UserInterface ui;
    private final Parser parser;
    private final CardList cardList;
    public Inka() {
        ui = new UserInterface();
        parser = new Parser();
        cardList = new CardList();
    }

    public void run() {
        ui.printGreeting();

        //fill in the method here
        //while(parser.getIsExecuting()) {
        //  String fullCommand = ui.getCommand();
        //}

        ui.printBye();
    }

    public static void main(String[] args) {
        new Inka().run();
    }
}
