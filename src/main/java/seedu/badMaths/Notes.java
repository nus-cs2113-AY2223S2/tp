package seedu.badMaths;

import seedu.badMaths.ui.Ui;
import java.util.ArrayList;
import java.util.logging.Logger;
import java.util.logging.LogManager;
import java.util.logging.Level;
import java.io.IOException;
import java.io.File;

public class Notes {
    protected static String logFilePath = "Notes";
    private static final String filePath = "data/notes.txt";
    private static Logger logr = Logger.getLogger("Notes");
    private String toDo;
    private ArrayList<String> cache;

    public Notes(String toDo) {
        this.toDo = toDo;
    }

    public static void implementLogger() {
        LogManager.getLogManager().reset();
        logr.setLevel(Level.INFO);
        try {
            if (!new File(logFilePath).exists()) {
                new File(logFilePath).createNewFile();
            }
            throw new IOException();
        } catch (IOException ioException) {
            logr.log(Level.SEVERE, "File logger is not working", ioException);
        }
    }

    public void setToDo(String toDo) { // 1
        this.toDo = toDo;
    }

    public void handleCache(String input){ // List as input
        implementLogger();
        try {
            logr.log(Level.INFO, "Reading in inputs by users");
            cache = new ArrayList<>(Storage.loadFile(filePath));
            assert (input.equals("Store") || input.equals("List")) : "input should either be Store or List";
            switch (input) {
            case "Store":
                cache.add(toDo);
                System.out.println("You have stored: " + toDo);
                Storage.saveFile(filePath, cache);
                break;

            case "List":
                // List. 1
                // toDo is "1"
                // List.
                // toDo is "Invalid todo"
                if (toDo.equals("Invalid todo")) {
                    Ui.printNotes(cache);
                    break;
                }
                int indexList;
                indexList = Integer.parseInt(toDo);
                System.out.println("cache is" + cache);
                System.out.println(cache.get(indexList - 1));
                break;

            case "Delete":
                // input is Delete, toDo is "2" String
                int index = Integer.parseInt(toDo);
                System.out.println("Ok, I have deleted the following list item: " + cache.get(index - 1));
                cache.remove(index - 1);
                Storage.saveFile(filePath, cache);
                break;

            default:
            }

        } catch (Exception e) {
            System.out.println("Please key in a valid input command");
            logr.severe("Invalid input command");
        }
    }
}
