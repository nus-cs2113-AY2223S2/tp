package com.clanki.commands;

import com.clanki.objects.FlashcardList;
import com.clanki.ui.Ui;

/**
 * A type of command that indicate that user want to display help menu.
 */
public class HelpCommand extends Command {
    @Override
    public void execute(FlashcardList flashcardList, Ui display) {
        display.printHelpMessage();
    }
}
