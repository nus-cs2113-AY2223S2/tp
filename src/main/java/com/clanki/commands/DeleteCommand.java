package com.clanki.commands;

import com.clanki.objects.FlashcardList;
import com.clanki.ui.Ui;
public class DeleteCommand extends Command{

    int index;
    @Override
    public void execute(FlashcardList flashcardList, Ui display) {
        flashcardList.deleteFlashcard(index);
    }
}
