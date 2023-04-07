package seedu.badmaths.storage;

import seedu.badmaths.note.Note;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

import static seedu.badmaths.storage.NotesFileParser.loadFile;

public class InvalidNotesFileHandler {
    public static void responseHandler(String path, ArrayList<Note> notes) {
        try{
            Scanner inputScanner = new Scanner(System.in);
            System.out.println("Sorry, your notes file seems to be corrupted :(");
            System.out.println("Do you want to reset the file? (y/n)");
            System.out.println("");
            String userInput = inputScanner.nextLine().trim().toLowerCase();
            if (userInput.equals("y")) {
                // clear the file contents if the user confirms
                PrintWriter writer = new PrintWriter(path);
                writer.print("");
                writer.close();

                // clear the note list if the user confirms
                notes.clear();

                System.out.println("File contents have rest successfully.");
                System.out.println("You can continue to use the application");
                System.out.println("If you want to read Help Manual, " +
                        "please type 'Help' to learn what I can do for you.");
            } else if (userInput.equals("n")){
                System.out.println("You choose not to rest the file.");
                System.out.println("Please ensure your file status before using the application.");
                System.out.println("The program will exit in 10 seconds. See you next time.");
                Timer timer = new Timer();
                TimerTask exitTask = new TimerTask() {
                    @Override
                    public void run() {
                        System.exit(0);
                    }
                };
                timer.schedule(exitTask, 10000);
            } else {
                System.out.println("Invalid input. Please enter 'y' or 'n'.");
                loadFile(path);
            }
        } catch (FileNotFoundException eForInvalidFormatException) {
            System.out.println(eForInvalidFormatException.getMessage());
        }
    }
}
