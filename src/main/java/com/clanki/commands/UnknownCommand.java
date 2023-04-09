package com.clanki.commands;

import com.clanki.objects.FlashcardList;
import com.clanki.ui.Ui;

/**
 * The type of command that indicates user have keyed in an invalid input.
 */
public class UnknownCommand extends Command {
    @Override
    public void execute(FlashcardList flashcardList, Ui display) {
        display.printInvalidInput();
    }
}

