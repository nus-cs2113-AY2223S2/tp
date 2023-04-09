package com.clanki.commands;

import com.clanki.objects.FlashcardList;
import com.clanki.ui.Ui;

/**
 * The type of command when the user wishes to clear all stored flashcards.
 */
public class ClearCommand extends Command {

    @Override
    public void execute(FlashcardList flashcardList, Ui display) {
        flashcardList.deleteAllFlashcards();
        display.printClearMessage();
    }
}
