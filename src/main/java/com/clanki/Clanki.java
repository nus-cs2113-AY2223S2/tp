package com.clanki;

import com.clanki.commands.Command;
import com.clanki.objects.FlashcardList;
import com.clanki.parser.Parser;
import com.clanki.ui.Ui;

public class Clanki {
    private Ui ui;
    private FlashcardList flashcardList;
    private Parser parser;

    public Clanki() {
        this.ui = new Ui();
        this.flashcardList = new FlashcardList();
        this.parser = new Parser();
    }

    public void run() {
        String inputText = ui.getUserCommand();
        Command command = parser.parseCommand(inputText);
        command.execute(flashcardList, ui);
    }

    public static void main(String[] args) {
        new Clanki().run();
    }
}
