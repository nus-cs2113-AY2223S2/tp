package seedu.badmaths;

import seedu.badmaths.note.Note;
import seedu.badmaths.note.NotePriority;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.IOException;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;
import java.util.Scanner;

public class Storage {
    public static ArrayList<Note> loadFile(String path) {
        ArrayList<Note> notes = new ArrayList<>();
        try {
            Scanner scanner = new Scanner(new File(path));
            String noteScanner;
            //text format: Priority | Status | Count | Note
            while (scanner.hasNextLine()) {
                noteScanner = scanner.nextLine();
                String[] noteInfo = noteScanner.split("\\t");

                if (noteInfo.length != 4) {
                    throw new InvalidFormatException();
                }

                //priority
                String priorityStr = noteInfo[0];
                Set<String> validPriorities = Set.of("LOW", "MEDIUM", "HIGH");
                if (!validPriorities.contains(priorityStr)) {
                    //priorityStr is invalid, handle the error
                    throw new InvalidFormatException();
                }
                NotePriority.Priority priority = NotePriority.Priority.valueOf(priorityStr);

                //note
                String noteStr = noteInfo[3];

                //count
                String reviewCountStr = noteInfo[2];
                int reviewCount;
                //reviewCountStr is invalid, handle the error
                try {
                    reviewCount = Integer.parseInt(reviewCountStr);
                } catch (NumberFormatException e) {
                    throw new InvalidFormatException();
                }

                //status
                String isDoneStr = noteInfo[1];
                Set<String> validIsDoneStatus = Set.of("Y", "N");
                if(!validIsDoneStatus.contains(isDoneStr)){
                    //isDoneStr is invalid, handle the error
                    throw new InvalidFormatException();
                }
                boolean isDone = isDoneStr.equals("Y");

                /*
                 * update notes if the file is valid
                 */
                //update text, priority
                Note note = new Note(noteStr, priority);
                //update status
                if(isDone) {
                    note.markAsDone();
                } else {
                    note.markAsNotDone();
                }
                //update count
                note.setReviewCount(reviewCount);
                //add note
                notes.add(note);
            }
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (InvalidFormatException e) {
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
                    return loadFile(path);
                }
            } catch (FileNotFoundException eForInvalidFormatException) {
                System.out.println(eForInvalidFormatException.getMessage());
            }
        }

        return notes;
    }
    private static String fileContent(ArrayList<Note> notes) {
        StringBuilder content = new StringBuilder();
        for(Note note : notes) {
            //priority
            NotePriority.Priority priority = note.getPriority();
            String priorityStr = priority.name();
            content.append(priorityStr).append("\t");
            //status
            boolean isDone = note.getIsDone();
            String isDoneStatus = "N";
            if(isDone) {
                isDoneStatus = "Y";
            }
            content.append(isDoneStatus).append("\t");
            //count
            int reviewCount = note.getReviewCount();
            String reviewCountStr = String.valueOf(reviewCount);
            content.append(reviewCountStr).append("\t");
            //text
            String noteText = note.getText();
            content.append(noteText);
            //line separator
            content.append(System.lineSeparator());
        }
        return content.toString();
    }
    public static void saveFile(String path, ArrayList<Note> notes) {
        File file = new File(path);
        if (!file.exists()) {
            System.out.println("File not exists, create it ...");
            if (!file.getParentFile().exists()) {
                System.out.println("Directory not exists, create it ...");
                file.getParentFile().mkdirs();
            }
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            FileWriter fl = new FileWriter(path);
            fl.write(fileContent(notes));
            fl.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void clearFile(String path) {
        try {
            FileWriter fileWriter = new FileWriter(path);
            fileWriter.write("");
            fileWriter.close();
            System.out.println("File content cleared successfully!");
        } catch (IOException e) {
            System.out.println("An error occurred while clearing the file content.");
            e.printStackTrace();
        }
    }
}



