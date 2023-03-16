package seedu.moneymind;

import java.util.Scanner;

import seedu.moneymind.command.Command;
import seedu.moneymind.exceptions.InvalidCommandException;
import seedu.moneymind.command.Parser;
import seedu.moneymind.storage.Storage;
import seedu.moneymind.string.Strings;
import seedu.moneymind.ui.Ui;

public class Moneymind {
    public static Scanner in;
    private Parser parser;
    private Storage storage;
    private Ui ui;
    private String userInput;

    public Moneymind() {
        this.parser = new Parser();
        this.storage = new Storage();
        this.ui = new Ui();
        this.in = new Scanner(System.in);
    }

    public void run() {
        ui.greet();
        boolean isExit = false;
        storage.load();
        while (!isExit) {
            try {
                getInput();
                String refinedUserInput = userInput.trim().replaceAll(Strings.EXTRA_SPACE_REGEX_FORMAT, " ");
                Command command = parser.parseNextCommand(refinedUserInput);
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
        storage.save();
    }

    private void getInput() {
        System.out.println(Strings.HORIZONTAL_LINE);
        System.out.println();
        userInput = in.nextLine();
        System.out.println(Strings.HORIZONTAL_LINE);
    }

    public static void main(String[] args) {
        new Moneymind().run();
    }
}
