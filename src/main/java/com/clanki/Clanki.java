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
            this.storageFile = new StorageFile();
            this.flashcardList = new FlashcardList(storageFile.load());
        } catch (InvalidStorageFilePathException | StorageOperationException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void main(String[] args) {
        new Clanki().run();
    }

    /**
     * Function that loop the process of getting an user input, processing the input
     * and execute the command, until a ByeCommand is inputted.
     */
    public void run() {
        ui.printWelcomeMessage();
        ui.printSeparationLine();
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
