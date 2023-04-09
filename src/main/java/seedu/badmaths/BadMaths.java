package seedu.badmaths;

import seedu.badmaths.note.NotesList;
import seedu.badmaths.storage.NotesFileParser;
import seedu.badmaths.ui.Ui;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.Arrays;
import java.util.Scanner;
import java.io.File;
import java.io.IOException;

public class BadMaths {
    private static final String path = "data/notes.txt";

    private static final Set<String> VALID_COMMANDS = new HashSet<>(Arrays.asList(
            "Graph", "Bye", "List", "Store", "Matrix", "Help", "FindInfo", "FindPrior", "FindMark", "FindUnmark", "Low",
            "Medium", "High", "Delete", "Mark", "Unmark", "Clear", "Rank", "Quadratic", "History"
    ));

    /**
     * Checks whether command is one of the valid commands
     * Throws exception if not
     * @param command which is the user inputted command
     */
    public static void commandChecker(String command) {
        try {
            if (!VALID_COMMANDS.contains(command)) {
                throw new IllegalArgumentException();
            }
        } catch (IllegalArgumentException e) {
            Ui.printIncorrectFormatEntered();
        }
    }

    public static void notesCreator(String path) {
        File notesFile = new File(path);
        if (!notesFile.exists()) {
            if (!notesFile.getParentFile().exists()) {
                notesFile.getParentFile().mkdirs();
            }
            try {
                notesFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        Command inputCommand = null;
        Ui.printWelcomeMessage();
        notesCreator(path);
        NotesList notes = new NotesList(NotesFileParser.loadFile(path));
        ArrayList<String> historyCommand = new ArrayList<>();
        CommandHistory commandHist = new CommandHistory(historyCommand);

        while (true) {
            Scanner scanner = new Scanner(System.in);
            String userInput = scanner.nextLine();

            Parser parser = new Parser(userInput);
            String command = parser.getCommand();
            String toDo = parser.getToDo();
            commandHist.storeCommand(userInput);

            commandChecker(command);

            if (inputCommand == null) {
                inputCommand = new Command(command, toDo);
            } else {
                inputCommand.setCommand(command);
                assert inputCommand.getCommand().equals(command) : "inputCommand != command";
                inputCommand.setToDo(toDo);
            }

            inputCommand.executeCommand(notes, historyCommand);
            if (userInput.equals("Bye")) {
                break;
            }
        }
    }
}

