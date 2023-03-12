package com.clanki.commands;

import com.clanki.objects.Flashcard;
import com.clanki.objects.FlashcardList;
import com.clanki.ui.Ui;

import java.util.ArrayList;

public class ByeCommand extends Command {
    @Override
    public void execute(FlashcardList flashcardList, Ui display) {
        display.printByeMessage();
    }
}
