package seedu.pettracker.storage;

import seedu.pettracker.data.Pet;
import seedu.pettracker.data.PetList;
import seedu.pettracker.data.Task;
import seedu.pettracker.ui.Ui;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
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
        } catch (IOException e) {
            ui.fileIOErrorMessage();
        }
    }

    public void loadPetFile(Ui ui)  {
        try {
            ArrayList<String> data = readFile(petFilePath);
            parsePetFile(data);
        } catch (IOException e) {
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
        } catch (IOException e) {
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

    private ArrayList<String> readFile(String filePath) throws IOException {
        File file = new File(filePath);

        if (!file.exists()) {
            throw new FileNotFoundException();
        }

        ArrayList<String> data = (ArrayList) Files.readAllLines(file.toPath(), Charset.defaultCharset());
        return data;
    }

    private void parsePetFile(ArrayList<String> data) {
        for (String line : data) {
            String petName = getPetName(line);
            PetList.addPet(petName);

            String petType = getPetType(line);
            if (!petType.equals("")) {
                PetList.addStat(petName, "type", petType);
            }

            String age = getAge(line);
            if (!age.equals("")) {
                PetList.addStat(petName, "age", age);
            }

            String weight = getWeight(line);
            if (!weight.equals("")) {
                PetList.addStat(petName, "weight", weight);
            }
        }
    }

    private String getPetName(String line) {
        String[] words = line.split("\\|", 2);
        String petName = words[0];
        return petName;
    }

    private String getPetType(String line) {
        String[] words = line.split("\\|", 3);
        String petType = words[1];
        return petType;
    }

    private String getAge(String line) {
        String[] words = line.split("\\|", 4);
        String age = words[2];
        return age;
    }

    private String getWeight(String line) {
        String[] words = line.split("\\|", 5);
        String weight = words[3];
        return weight;
    }
}
