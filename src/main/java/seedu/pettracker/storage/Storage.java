package seedu.pettracker.storage;


import seedu.pettracker.data.Pet;
import seedu.pettracker.ui.Ui;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Storage {
    private final String filePath;

    public Storage(String filePath, Ui ui) {
        this.filePath = filePath;
        try {
            createFile();
        } catch (IOException e) {
            ui.fileIOErrorMessage();
        }
    }

    private void createFile() throws IOException {
        File file = new File(filePath);
        if (file.exists()) {
            return;
        }
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }
        file.createNewFile();
    }

    private void writePetsToFile(ArrayList<Pet> petList) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        for (Pet pet : petList) {
            if (pet != null) {
                fw.write(pet.saveFormat() + System.lineSeparator());
            }
        }

        fw.close();
    }

    public void savePets(ArrayList<Pet> petList, Ui ui) {
        try {
            writePetsToFile(petList);
        } catch (IOException e) {
            ui.fileIOErrorMessage();
        }
    }


}
