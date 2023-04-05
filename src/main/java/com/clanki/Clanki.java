package com.clanki;

import com.clanki.commands.ByeCommand;
import com.clanki.commands.Command;
import com.clanki.exceptions.InvalidStorageFilePathException;
import com.clanki.exceptions.StorageOperationException;
import com.clanki.objects.FlashcardList;
import com.clanki.parser.Parser;
import com.clanki.storage.StorageFile;
import com.clanki.ui.Ui;

public class Clanki {
    private Ui ui;
    private FlashcardList flashcardList;
    private StorageFile storageFile;

    public Clanki() {
        try {
            this.ui = new Ui();
            storageFile = new StorageFile();
            this.flashcardList = new FlashcardList(storageFile.load());
            ui.printWelcomeMessage();
            ui.printSeparationLine();
        } catch (InvalidStorageFilePathException | StorageOperationException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void main(String[] args) {
        new Clanki().run();
    }

    public void run() {
        while (true) {
            String inputText = ui.getUserCommand();
            Command command = Parser.parseCommand(inputText);
            command.execute(flashcardList, ui);
            try {
                storageFile.save(flashcardList);
            } catch (StorageOperationException e) {
                System.out.println(e.getMessage());
            }
            if (command instanceof ByeCommand) {
                return;
            }
            ui.printSeparationLine();
        }
    }
}
