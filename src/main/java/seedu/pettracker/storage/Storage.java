package seedu.pettracker.storage;


import seedu.pettracker.data.Pet;
import seedu.pettracker.data.Task;
import seedu.pettracker.ui.Ui;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Storage {
    private final String petFilePath;
    private final String taskFilePath;

    public Storage(String petFilePath, String taskFilePath) {
        this.petFilePath = petFilePath;
        this.taskFilePath = taskFilePath;
    }

    public void createPetFile(Ui ui) {
        try {
            File file = new File(petFilePath);
            if (file.exists()) {
                return;
            }
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }
            file.createNewFile();
        } catch (IOException e){
            ui.fileIOErrorMessage();
        }
    }

    public void createTaskFile(Ui ui) {
        try {
            File file = new File(taskFilePath);
            if (file.exists()) {
                return;
            }
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }
            file.createNewFile();
        } catch (IOException e){
            ui.fileIOErrorMessage();
        }
    }

    private void writePetsToFile(ArrayList<Pet> petList) throws IOException {
        FileWriter fw = new FileWriter(petFilePath);
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

    private void writeTasksToFile(ArrayList<Task> taskList) throws IOException {
        FileWriter fw = new FileWriter(taskFilePath);
        for (Task task : taskList) {
            if (task != null) {
                fw.write(task.saveFormat() + System.lineSeparator());
            }
        }

        fw.close();
    }

    public void saveTasks(ArrayList<Task> taskList, Ui ui) {
        try {
            writeTasksToFile(taskList);
        } catch (IOException e) {
            ui.fileIOErrorMessage();
        }
    }


}
