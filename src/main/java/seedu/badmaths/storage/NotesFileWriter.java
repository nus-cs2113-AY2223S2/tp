package seedu.badmaths.storage;

import seedu.badmaths.note.Note;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import static seedu.badmaths.storage.NotesFileContentManager.fileContent;

public class NotesFileWriter {
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
}

