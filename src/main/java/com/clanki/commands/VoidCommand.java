package com.clanki.commands;

import com.clanki.objects.FlashcardList;
import com.clanki.ui.Ui;

/**
 * The type of command that indicate an invalid input, with the display
 * of why the input is invalid already handled elsewhere.
 */
public class VoidCommand extends Command {
    @Override
    public void execute(FlashcardList flashcardList, Ui display) {

    }
}
