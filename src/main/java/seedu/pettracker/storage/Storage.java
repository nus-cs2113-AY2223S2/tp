package seedu.pettracker.storage;

import seedu.pettracker.data.Pet;
import seedu.pettracker.data.PetList;
import seedu.pettracker.data.Task;
import seedu.pettracker.data.TaskList;
import seedu.pettracker.exceptions.EmptyTaskNameException;
import seedu.pettracker.exceptions.InvalidMarkTaskSymbolException;
import seedu.pettracker.exceptions.InvalidPetNameException;
import seedu.pettracker.exceptions.InvalidSeparatorException;
import seedu.pettracker.exceptions.InvalidStatException;
import seedu.pettracker.exceptions.InvalidStatValueException;
import seedu.pettracker.exceptions.InvalidTaskNameException;
import seedu.pettracker.exceptions.NonPositiveIntegerException;
import seedu.pettracker.exceptions.PetNotFoundException;
import seedu.pettracker.exceptions.EmptyPetNameException;
import seedu.pettracker.exceptions.DuplicatePetException;
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

    /**
     * Loads pet list from the pet output file
     *
     * @param ui Ui to print error if needed
     */
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
        } catch (InvalidStatValueException e) {
            ui.printFileInvalidStatValueMessage();
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

    /**
     * Loads task list from the task output file
     *
     * @param ui Ui to print error if needed
     */
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


    /**
     * Parses data read from the pet output file and loads the parsed data into the pet list
     *
     * @param data ArrayList of data read from the pet output file
     * @throws NumberFormatException       Line of data contains non-integer values for age/weight
     * @throws NonPositiveIntegerException Line of data contains non-positive values for age/weight
     * @throws InvalidStatException        Line of data contains invalid stats
     * @throws PetNotFoundException        Line of data contains a stat that belongs to a pet that does not exist
     * @throws EmptyPetNameException       Line of data contains empty pet name
     * @throws DuplicatePetException       Line of data contains pet name that already existed
     * @throws InvalidSeparatorException   Line of data contains invalid separator/invalid number of separator
     * @throws InvalidPetNameException     Line of data contains invalid pet name with pipes
     * @throws InvalidStatValueException   Line of data contains invalid stat value with pipes
     */
    private void parsePetFile(ArrayList<String> data) throws NumberFormatException, NonPositiveIntegerException,
            InvalidStatException, PetNotFoundException, EmptyPetNameException, DuplicatePetException,
            InvalidSeparatorException, InvalidPetNameException, InvalidStatValueException {
        for (String line : data) {
            validatePetDataSep(line);
            String petName = getPetDetail(line, "petName");
            String petType = getPetDetail(line, "petType");
            String age = getPetDetail(line, "petAge");
            String weight = getPetDetail(line, "petWeight");

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

    /**
     * Parses data including deadline read from the task output file and loads the parsed data into the task list.
     * If line of data does not contain deadline, calls the method parseTaskWithoutDeadline
     *
     * @param data ArrayList of data read from the task output file
     * @throws InvalidSeparatorException      Line of data contains invalid separator/invalid number of separator
     * @throws DateTimeParseException         Line of data contains invalid date format
     * @throws EmptyTaskNameException         Line of data contains empty task description
     * @throws InvalidMarkTaskSymbolException Line of data contains invalid mark task symbol
     * @throws InvalidTaskNameException       Line of data contains invalid task name with pipes
     */
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
                setTaskStatus(taskStatus);
            } catch (ArrayIndexOutOfBoundsException e) {
                parseTaskWithoutDeadline(line);
            }
        }
    }

    /**
     * Parses data excluding deadline read from the task output file and loads the parsed data into the task list
     *
     * @param line An entry in the ArrayList of data read from the task output file
     * @throws EmptyTaskNameException         Line of data contains empty task description
     * @throws InvalidMarkTaskSymbolException Line of data contains invalid mark task symbol
     * @throws InvalidTaskNameException       ine of data contains invalid task name with pipes
     */
    private void parseTaskWithoutDeadline(String line) throws EmptyTaskNameException,
            InvalidMarkTaskSymbolException, InvalidTaskNameException {
        String taskName = getTaskName(line);
        String taskStatus = getTaskStatus(line);

        TaskList.addTask(taskName);
        setTaskStatus(taskStatus);
    }


    private String getPetDetail(String line, String petStat) {
        String[] words = line.split("\\|", EXPECTED_PET_SEP_COUNT + 1);
        String petDetail = null;

        switch (petStat) {
        case "petName":
            petDetail = words[0];
            break;
        case "petType":
            petDetail = words[1];
            break;
        case "petAge":
            petDetail = words[2];
            break;
        case "petWeight":
            petDetail = words[3];
            break;
        default:
            break;
        }

        return petDetail;
    }

    private String getTaskName(String line) {
        String[] words = line.split("\\|", EXPECTED_TASK_SEP_MAX_COUNT + 1);
        String taskName = words[1];

        return taskName;
    }

    private String getTaskStatus(String line) throws InvalidMarkTaskSymbolException {
        String[] words = line.split("\\|", EXPECTED_TASK_SEP_MAX_COUNT + 1);
        String taskStatus = words[0];

        boolean isMarkTaskSymbolInvalid = !taskStatus.equals("1") && !taskStatus.equals("0");
        if (isMarkTaskSymbolInvalid) {
            throw new InvalidMarkTaskSymbolException();
        }

        return taskStatus;
    }

    private LocalDate getDeadline(String line) throws ArrayIndexOutOfBoundsException, DateTimeParseException {
        String[] words = line.split("\\|", EXPECTED_TASK_SEP_MAX_COUNT + 1);
        LocalDate deadline = LocalDate.parse(words[2]);
        return deadline;
    }

    private static void setTaskStatus(String taskStatus) {
        boolean isTaskDone = taskStatus.equals("1");
        if (isTaskDone) {
            int taskNumber = TaskList.getNumberOfTasks();
            TaskList.markTask(taskNumber, true);
        }
    }

    private void validatePetDataSep(String line) throws InvalidSeparatorException {
        int sepCount = (int) line.chars().filter(ch -> ch == '|').count();

        boolean isSeparatorCountIncorrect = sepCount != EXPECTED_PET_SEP_COUNT;
        if (isSeparatorCountIncorrect) {
            throw new InvalidSeparatorException();
        }
    }

    private void validateTaskDataSep(String line) throws InvalidSeparatorException {
        int sepCount = (int) line.chars().filter(ch -> ch == '|').count();


        boolean isSeparatorCountIncorrect = sepCount != EXPECTED_TASK_SEP_MIN_COUNT
                && sepCount != EXPECTED_TASK_SEP_MAX_COUNT;
        if (isSeparatorCountIncorrect) {
            throw new InvalidSeparatorException();
        }
    }
}
