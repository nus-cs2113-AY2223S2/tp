package seedu.pettracker.storage;

import seedu.pettracker.data.Pet;
import seedu.pettracker.data.PetList;
import seedu.pettracker.data.Task;
import seedu.pettracker.data.TaskList;
import seedu.pettracker.exceptions.DuplicatePetException;
import seedu.pettracker.exceptions.EmptyPetNameException;
import seedu.pettracker.exceptions.InvalidSeparatorException;
import seedu.pettracker.exceptions.InvalidStatException;
import seedu.pettracker.exceptions.NonPositiveIntegerException;
import seedu.pettracker.exceptions.PetNotFoundException;
import seedu.pettracker.ui.Ui;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

public class Storage {
    private static final int EXPECTED_PET_SEP_COUNT = 3;
    private static final int EXPECTED_TASK_SEP_MAX_COUNT = 2;
    private static final int EXPECTED_TASK_SEP_MIN_COUNT = 1;
    private final String petFilePath;
    private final String taskFilePath;

    public Storage(String petFilePath, String taskFilePath) {
        this.petFilePath = petFilePath;
        this.taskFilePath = taskFilePath;
    }

    /**
     * Checks if pet output file exists, else creates it
     *
     * @param ui Ui to print error if needed
     */
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
            ui.printFileIOErrorMessage();
        }
    }

    public void loadPetFile(Ui ui) {
        try {
            ArrayList<String> data = readFile(petFilePath);
            parsePetFile(data);
        } catch (IOException e) {
            ui.printFileIOErrorMessage();
        }
    }

    /**
     * Checks if task output file exists, else creates it
     *
     * @param ui Ui to print error if needed
     */
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
            ui.printFileIOErrorMessage();
        }
    }

    public void loadTaskFile(Ui ui) {
        try {
            ArrayList<String> data = readFile(taskFilePath);
            parseTaskFile(data);
        } catch (IOException e) {
            ui.printFileIOErrorMessage();
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

    /**
     * Saves the current pet list into the pet output file
     *
     * @param petList ArrayList of all pets
     * @param ui      Ui to print error if needed
     */
    public void savePets(ArrayList<Pet> petList, Ui ui) {
        try {
            writePetsToFile(petList);
        } catch (IOException e) {
            ui.printFileIOErrorMessage();
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

    /**
     * Saves the current task list into the task output file
     *
     * @param taskList ArrayList of all tasks
     * @param ui       Ui to print error if needed
     */
    public void saveTasks(ArrayList<Task> taskList, Ui ui) {
        try {
            writeTasksToFile(taskList);
        } catch (IOException e) {
            ui.printFileIOErrorMessage();
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
            try {
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
            } catch (NumberFormatException e) {
                System.out.println("Output File has non-integer values for age/weight");
            } catch (NonPositiveIntegerException e) {
                System.out.println("Output File has non-positive values for age/weight");
            } catch (InvalidStatException e) {
                System.out.println("Output File has invalid Stats");
            } catch (PetNotFoundException e) {
                System.out.println("Stat belongs to a pet that does not exist");
            } catch (EmptyPetNameException e) {
                System.out.println("Pet name in file is empty");
            } catch (DuplicatePetException e) {
                System.out.println("File contains duplicate pet names");
            }
        }
    }

    private void parseTaskFile(ArrayList<String> data) {
        for (String line : data) {
            try {
                String taskName = getTaskName(line);
                LocalDate deadline = getDeadline(line);
                TaskList.addTask(taskName, deadline);

                String taskStatus = getTaskStatus(line);
                if (taskStatus.startsWith("1")) {
                    int taskNumber = TaskList.getNumberOfTasks();
                    TaskList.markTask(taskNumber, true);
                }
            } catch (ArrayIndexOutOfBoundsException e) {
                String taskName = getTaskName(line);
                TaskList.addTask(taskName);

                String taskStatus = getTaskStatus(line);
                if (taskStatus.startsWith("1")) {
                    int taskNumber = TaskList.getNumberOfTasks();
                    TaskList.markTask(taskNumber, true);
                }
            } catch (DateTimeParseException e) {
                System.out.println("A task in output file has invalid date format");
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

    private String getTaskName(String line) {
        String[] words = line.split("\\|", 3);
        String taskName = words[1];
        return taskName;
    }

    private String getTaskStatus(String line) {
        String[] words = line.split("\\|", 2);
        String taskStatus = words[0];
        return taskStatus;
    }

    private LocalDate getDeadline(String line) throws ArrayIndexOutOfBoundsException, DateTimeParseException {
        String[] words = line.split("\\|", 3);
        LocalDate deadline = LocalDate.parse(words[2]);
        return deadline;
    }

    private void validatePetDataSep(String line) throws InvalidSeparatorException {
        int sepCount = (int) line.chars().filter(ch -> ch == '|').count();

        if (sepCount != EXPECTED_PET_SEP_COUNT) {
            throw new InvalidSeparatorException();
        }
    }

    private void validateTaskDataSep(String line) throws InvalidSeparatorException {
        int sepCount = (int) line.chars().filter(ch -> ch == '|').count();

        if (sepCount != EXPECTED_TASK_SEP_MIN_COUNT && sepCount != EXPECTED_TASK_SEP_MAX_COUNT) {
            throw new InvalidSeparatorException();
        }
    }
}
