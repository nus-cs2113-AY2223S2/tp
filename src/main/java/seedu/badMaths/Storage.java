package seedu.badMaths;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    public static ArrayList<Note> loadFile(String path) {
        ArrayList<Note> notes = new ArrayList<>();
        try {
            Scanner scanner = new Scanner(new File(path));
            String noteScanner;
            //text format: Priority | Note | Status | Count
            while (scanner.hasNextLine()) {
                noteScanner = scanner.nextLine();
                String[] noteInfo = noteScanner.split("[|]");
                //priority
                String priorityStr = noteInfo[0];
                NotePriority.Priority priority = NotePriority.Priority.valueOf(priorityStr);
                //note
                String noteStr = noteInfo[1];
                //count
                String reviewCountStr = noteInfo[2];
                int reviewCount = Integer.parseInt(reviewCountStr);
                //status
                String isDoneStr = noteInfo[3];
                boolean isDone = isDoneStr.equals("X");
                //update note
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
        }
        return notes;
    }
    private static String fileContent(ArrayList<Note> notes) {
        StringBuilder content = new StringBuilder();
        for(Note note : notes) {
            NotePriority.Priority priority = note.getPriority();
            String priorityStr = priority.name();
            content.append(priorityStr).append("|");
            String noteText = note.getText();
            content.append(noteText).append("|");
            int reviewCount = note.getReviewCount();
            String reviewCountStr = String.valueOf(reviewCount);
            content.append(reviewCountStr).append("|");
            boolean isDone = note.getIsDone();
            String isDoneStatus = "N";
            if(isDone) {
                isDoneStatus = "Y";
            }
            content.append(isDoneStatus);
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
