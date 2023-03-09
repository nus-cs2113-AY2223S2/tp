package commands;

import objects.FlashcardList;
import ui.Ui;

public class UnknownCommand extends Command {
    @Override
    public void execute(FlashcardList flashcardList, Ui display) {
        display.printInvalidInput();
    }
}
