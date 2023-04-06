package com.clanki.commands;

import com.clanki.objects.FlashcardList;
import com.clanki.ui.Ui;

public class ClearCommand extends Command {

    @Override
    public void execute(FlashcardList flashcardList, Ui display) {
        flashcardList.deleteAllFlashcards();
        System.out.println("All flashcards have been deleted.");
        System.out.println("Your list of flashcards is now empty.");
    }
}
