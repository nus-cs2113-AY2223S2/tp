package seedu.moneymind;

import seedu.moneymind.Command.Command;
import seedu.moneymind.Command.InvalidCommandException;
import seedu.moneymind.Command.Parser;

import java.util.Scanner;

public class Moneymind {
    private Parser parser;
    private Storage storage;
    private Ui ui;
    private Scanner in;

    public Moneymind() {
        this.parser = new Parser();
        // this line crashes the app currently, so it is commented out
        // this.storage = new Storage();
        this.ui = new Ui();
        this.in = new Scanner(System.in);
    }

    public void run() {
        ui.greet();
        boolean isExit = false;
        while (!isExit) {
            try {
                Command command = parser.parseNextCommand(in.nextLine());
                if (command.isExit()) {
                    ui.goodbye();
                    isExit = true;
                } else {
                    command.execute(ui); // should also accept storage object as parameter
                }
            } catch (InvalidCommandException e) {
                e.showErrorMessage();
            } catch (Exception e) {
                ui.error(e);
            }
        }
    }

    public static void main(String[] args) {
        new Moneymind().run();
    }
}
