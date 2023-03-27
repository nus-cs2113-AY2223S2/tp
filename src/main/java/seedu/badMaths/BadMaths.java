package seedu.badMaths;

import seedu.badMaths.ui.Ui;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import java.io.File;
import java.io.IOException;

public class BadMaths {
    private static final String path = "data/notes.txt";

    private static final Set<String> VALID_COMMANDS = new HashSet<>(Arrays.asList(
            "Graph", "Bye", "List", "Store", "Matrix", "Help", "FindInfo", "FindPrior", "FindMark", "FindUnmark", "Low",
            "Medium", "High", "Delete", "Mark", "Unmark", "Clear", "Rank"
    ));
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
        System.out.println("This is BadMaths. You can type 'Help.' to learn what I can do for you :)");
        Command inputCommand = null;

        notesCreator(path);
        NotesList notes = new NotesList(Storage.loadFile(path));

        while (true) {
            Scanner scanner = new Scanner(System.in);
            String userInput = scanner.nextLine();

            Parser parser = new Parser(userInput);
            String command = parser.getCommand();
            String toDo = parser.getToDo();
            commandChecker(command);

            if (inputCommand == null) {
                inputCommand = new Command(command, toDo);
            } else {
                inputCommand.setCommand(command);
                assert inputCommand.getCommand().equals(command) : "inputCommand != command";
                inputCommand.setToDo(toDo);
            }

            inputCommand.executeCommand(notes);
            if (userInput.equals("Bye.")) {
                break;
            }
        }
    }
}

