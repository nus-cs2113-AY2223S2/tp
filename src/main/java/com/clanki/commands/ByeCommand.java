package com.clanki.commands;

import com.clanki.objects.FlashcardList;
import com.clanki.ui.Ui;

/**
 * A type of command that will indicate that the program is ready to close.
 */
public class ByeCommand extends Command {
    @Override
    public void execute(FlashcardList flashcardList, Ui display) {
        display.printByeMessage();
    }
}
