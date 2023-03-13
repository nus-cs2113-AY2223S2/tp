package com.clanki.commands;

import com.clanki.objects.FlashcardList;
import com.clanki.ui.Ui;

/**
 * Represents a command that is instructed for the program to conduct. A Command
 * Object specifies what the type of command is with its subclasses.
 */
public abstract class Command {
    public abstract void execute(FlashcardList flashcardList, Ui display);
}
