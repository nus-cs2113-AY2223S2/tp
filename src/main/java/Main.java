import commands.Command;
import objects.FlashcardList;
import parser.Parser;
import ui.Ui;

public class Main {
    private Ui ui;
    private FlashcardList flashcardList;
    private Parser parser;

    public Main() {
        this.ui = new Ui();
        this.flashcardList = new FlashcardList();
        this.parser = new Parser();
    }

    public void run() {
        String inputText = ui.getUserCommand();
        Command command = parser.parseCommand(inputText);
        command.execute(flashcardList, ui);
    }

    public static void main() {
        new Main().run();
    }
}
