package com.clanki.commands;

import com.clanki.objects.FlashcardList;
import com.clanki.ui.Ui;

public class
ByeCommand extends Command {
    @Override
    public void execute(FlashcardList flashcardList, Ui display) {
        display.printByeMessage();
    }
}
