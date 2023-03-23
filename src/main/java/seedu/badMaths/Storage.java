package seedu.badMaths;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    public static ArrayList<String> loadFile(String path) {
        ArrayList<String> notes = new ArrayList<>();
        try {
            Scanner scanner = new Scanner(new File(path));
            String noteInfo;
            while (scanner.hasNextLine()) {
                noteInfo = scanner.nextLine();
                notes.add(noteInfo);
            }
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
        return notes;
    }

    private static String fileContent(ArrayList<String> notes) {
        String content = "";
        for(String note : notes) {
            content += note + System.lineSeparator();
        }
        return content;
    }

    public static void saveFile(String path, ArrayList<String> notes) {
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

}
