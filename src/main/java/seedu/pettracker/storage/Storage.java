package seedu.pettracker.storage;

import seedu.pettracker.data.Pet;
import seedu.pettracker.data.PetList;
import seedu.pettracker.data.Task;
import seedu.pettracker.data.TaskList;
import seedu.pettracker.exceptions.DuplicatePetException;
import seedu.pettracker.exceptions.EmptyPetNameException;
import seedu.pettracker.exceptions.EmptyTaskNameException;
import seedu.pettracker.exceptions.InvalidMarkTaskSymbolException;
import seedu.pettracker.exceptions.InvalidPetNameException;
import seedu.pettracker.exceptions.InvalidSeparatorException;
import seedu.pettracker.exceptions.InvalidStatException;
import seedu.pettracker.exceptions.InvalidTaskNameException;
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
        } catch (NumberFormatException e) {
            ui.printFileNonIntegerMessage();
        } catch (NonPositiveIntegerException e) {
            ui.printFileIntegerNotPositiveMessage();
        } catch (InvalidStatException e) {
            ui.printFileInvalidStatMessage();
        } catch (PetNotFoundException e) {
            ui.printFilePetNotFoundMessage();
        } catch (EmptyPetNameException e) {
            ui.printFilePetNameEmptyMessage();
        } catch (DuplicatePetException e) {
            ui.printFileDuplicatePetMessage();
        } catch (InvalidSeparatorException e) {
            ui.printPetFileInvalidSeparatorMessage();
        } catch (InvalidPetNameException e) {
            ui.printFileInvalidPetNameMessage();
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
        } catch (InvalidSeparatorException e) {
            ui.printTaskFileInvalidSeparatorMessage();
        } catch (DateTimeParseException e) {
            ui.printFileInvalidDateMessage();
        } catch (EmptyTaskNameException e) {
            ui.printFileEmptyTaskNameMessage();
        } catch (InvalidMarkTaskSymbolException e) {
            ui.printFileInvalidMarkTaskSymbolMessage();
        } catch (InvalidTaskNameException e) {
            ui.printFileInvalidTaskNameMessage();
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

    private void parsePetFile(ArrayList<String> data) throws NumberFormatException, NonPositiveIntegerException,
            InvalidStatException, PetNotFoundException, EmptyPetNameException, DuplicatePetException,
            InvalidSeparatorException, InvalidPetNameException {
        for (String line : data) {
            validatePetDataSep(line);
            String petName = getPetName(line);
            String petType = getPetType(line);
            String age = getAge(line);
            String weight = getWeight(line);

            PetList.addPet(petName);
            if (!petType.equals("")) {
                PetList.addStat(petName, "type", petType);
            }

            if (!age.equals("")) {
                PetList.addStat(petName, "age", age);
            }

            if (!weight.equals("")) {
                PetList.addStat(petName, "weight", weight);
            }
        }
    }

    private void parseTaskFile(ArrayList<String> data) throws InvalidSeparatorException,
            DateTimeParseException, EmptyTaskNameException, InvalidMarkTaskSymbolException,
            InvalidTaskNameException {
        for (String line : data) {
            try {
                validateTaskDataSep(line);
                LocalDate deadline = getDeadline(line);
                String taskName = getTaskName(line);
                String taskStatus = getTaskStatus(line);

                TaskList.addTask(taskName, deadline);
                if (taskStatus.equals("1")) {
                    int taskNumber = TaskList.getNumberOfTasks();
                    TaskList.markTask(taskNumber, true);
                }
            } catch (ArrayIndexOutOfBoundsException e) {
                parseTaskWithoutDeadline(line);
            }
        }
    }

    private void parseTaskWithoutDeadline(String line) throws EmptyTaskNameException,
            InvalidMarkTaskSymbolException, InvalidTaskNameException {
        String taskName = getTaskName(line);
        String taskStatus = getTaskStatus(line);

        TaskList.addTask(taskName);
        if (taskStatus.equals("1")) {
            int taskNumber = TaskList.getNumberOfTasks();
            TaskList.markTask(taskNumber, true);
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

    private String getTaskName(String line) throws EmptyTaskNameException {
        String[] words = line.split("\\|", 3);
        String taskName = words[1];

        if (taskName.trim().isEmpty()) {
            throw new EmptyTaskNameException();
        }

        return taskName;
    }

    private String getTaskStatus(String line) throws InvalidMarkTaskSymbolException {
        String[] words = line.split("\\|", 2);
        String taskStatus = words[0];

        if (!taskStatus.equals("1") && !taskStatus.equals("0")) {
            throw new InvalidMarkTaskSymbolException();
        }

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
