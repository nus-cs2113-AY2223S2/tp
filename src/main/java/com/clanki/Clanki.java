package com.clanki;

import com.clanki.commands.ByeCommand;
import com.clanki.commands.Command;
import com.clanki.objects.FlashcardList;
import com.clanki.parser.Parser;
import com.clanki.ui.Ui;

public class Clanki {
    private final Ui ui;
    private final FlashcardList flashcardList;
    private final Parser parser;

    public Clanki() {
        this.ui = new Ui();
        this.flashcardList = new FlashcardList();
        this.parser = new Parser();
    }

    public static void main(String[] args) {
        new Clanki().run();
    }

    public void run() {
        ui.printWelcomeMessage();
        while (true) {
            String inputText = ui.getUserCommand();
            Command command = parser.parseCommand(inputText);
            command.execute(flashcardList, ui);

            if (command instanceof ByeCommand) {
                return;
            }
        }
    }
}
