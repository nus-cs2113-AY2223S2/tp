package com.clanki;

import com.clanki.commands.ByeCommand;
import com.clanki.commands.Command;
import com.clanki.objects.Flashcard;
import com.clanki.objects.FlashcardList;
import com.clanki.parser.Parser;
import com.clanki.ui.Ui;

import java.util.ArrayList;

public class Clanki {
    private Ui ui;
    private FlashcardList flashcardList;
    private Parser parser;
    private ArrayList<Flashcard> flashcardsForToday;
    private int currentFlashcardIndex;

    public Clanki() {
        this.ui = new Ui();
        this.flashcardList = new FlashcardList();
        this.parser = new Parser();
    }

    public void run() {
        while (true) {
            String inputText = ui.getUserCommand();
            Command command = parser.parseCommand(inputText);
            command.execute(flashcardList, ui);

            if (command instanceof ByeCommand) {
                return;
            }
        }
    }

    public static void main(String[] args) {
        new Clanki().run();
    }
}
