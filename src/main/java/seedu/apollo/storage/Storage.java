package seedu.apollo.storage;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import seedu.apollo.calendar.Calendar;
import seedu.apollo.exception.task.DateOverException;
import seedu.apollo.module.Timetable;
import seedu.apollo.ui.Parser;
import seedu.apollo.ui.Ui;
import seedu.apollo.exception.task.DateOrderException;
import seedu.apollo.exception.task.InvalidDeadline;
import seedu.apollo.exception.task.InvalidEvent;
import seedu.apollo.exception.utils.InvalidSaveFile;
import seedu.apollo.exception.utils.DuplicateModuleInTextFileException;
import seedu.apollo.module.Module;
import seedu.apollo.module.ModuleList;
import seedu.apollo.task.Deadline;
import seedu.apollo.task.Event;
import seedu.apollo.task.Task;
import seedu.apollo.task.TaskList;
import seedu.apollo.task.ToDo;
import seedu.apollo.utils.LoggerInterface;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.lang.reflect.Type;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;



/**
 * Storage class that initialises the task list and updates the save file.
 */
public class Storage implements LoggerInterface {
    // Location of save file
    protected static String filePath;
    protected static String moduleDataFilePath;

    // ints indicating position of terms in each line of the save file
    private static final int TYPE_POS = 0;
    private static final int STATUS_POS = 4;
    private static final int PARAM_POS = 8;
    // chars representing type of Tasks within the save file
    private static final char TXT_TODO_WORD = 'T';
    private static final char TXT_DEADLINE_WORD = 'D';
    private static final char TXT_EVENT_WORD = 'E';

    private static Logger logger = Logger.getLogger("Storage");

    /**
     * Initialise Storage class, set filePath.
     *
     * @param filePath Location of the local save file.
     */
    public Storage(String filePath, String moduleDataFilePath) {
        setUpLogger(logger);
        Storage.filePath = filePath;
        Storage.moduleDataFilePath = moduleDataFilePath;
    }

    /**
     * Overwrites the existing save file based on the current TaskList.
     *
     * @param taskList Contains all stored tasks.
     * @throws IOException If something goes wrong during the overwriting process.
     */
    public void updateTask(TaskList taskList) throws IOException {
        FileWriter overwrite = new FileWriter(filePath);
        for (Task task : taskList) {
            String desc = task.getDescription();
            String type = task.getType();
            String stat = task.getStatus();
            switch (type) {
            case "todo":
                overwrite.write("T | " + stat + " | " + desc + "\n");
                break;
            case "deadline":
                Deadline tempDeadline = (Deadline) task;
                String by = tempDeadline.getBy(Task.storePattern);
                overwrite.write("D | " + stat + " | " + desc + " -by " + by + "\n");
                break;
            case "event":
                Event tempEvent = (Event) task;
                String from = tempEvent.getFrom(Task.storePattern);
                String to = tempEvent.getTo(Task.storePattern);
                overwrite.write("E | " + stat + " | " + desc + " -from " + from + " -to " + to + "\n");
                break;
            default:
                throw new IOException();
            }
        }
        overwrite.close();
    }

    /**
     * Loads data from the save file into a new TaskList of Tasks.
     * If save file is not found, creates a new save file and returns an empty TaskList.
     *
     * @param ui Prints out error messages to user.
     * @return TaskList of Tasks (containing data from save file / empty).
     * @throws IOException If save file is not found, and a new one cannot be created.
     */
    public TaskList loadTaskList(Ui ui) throws IOException {
        TaskList newTaskList = new TaskList();
        File save = new File(filePath);
        try {
            newTaskList = readFileContents(save, ui);
            return newTaskList;
        } catch (FileNotFoundException e) {
            assert (save.createNewFile()) : "Save file creation failed.";
            logger.log(Level.INFO, "File not found, creating new file.");
            return newTaskList;
        }
    }

    /**
     * Updates the moduleData file stored on the hard disk.
     *
     * @param modules Contains all stored modules.
     * @throws IOException If save file is not found.
     */
    public void updateModule(ModuleList modules, Calendar calendar) throws IOException, InvalidSaveFile {
        FileWriter overwrite = new FileWriter(moduleDataFilePath);
        calendar.clearCalendar();
        for (Module module : modules) {
            calendar.addModule(module);
            String code = module.getCode();
            overwrite.write(code + "|");
            writeModules(overwrite, module);
        }
        overwrite.close();
    }

    /**
     * Reads all lines in the moduleData file, initialises them as an ModuleList of Modules.
     */
    private void writeModules(FileWriter overwrite, Module module) throws IOException {
        ArrayList<Timetable> timetableList = module.getModuleTimetable();
        if (timetableList != null) {
            for (Timetable timetable : timetableList) {
                overwrite.write(timetable.getLessonType() + ":" + timetable.getClassNumber() + "|");
            }
        }
        overwrite.write("\n");
    }

    /**
     * Reads all lines in the moduleData file, initialises them as an ModuleList of Modules.
     *
     * @param ui         Prints out error messages to user.
     * @param allModules Contains all stored modules.
     * @return ModuleList of Tasks (containing data from save file / empty).
     * @throws IOException If save file is not found.
     */
    public ModuleList loadModuleList(Ui ui, ModuleList allModules, Calendar calendar) throws IOException {
        ModuleList newModuleList = new ModuleList();
        File save = new File(moduleDataFilePath);
        try {
            newModuleList = readModuleFileContents(save, ui, allModules, calendar);
            return newModuleList;
        } catch (FileNotFoundException e) {
            logger.log(Level.INFO, "File for ModuleList not found, creating new file.");
            assert (save.createNewFile()) : "Save file creation failed.";
            return newModuleList;
        }
    }

    /**
     * Reads all lines in the save file, initialises them as an TaskList of Tasks.
     * Loads data from the data file into a new ArrayList of Modules.
     *
     * @return ArrayList of Modules (containing data from save file / empty).
     * @throws FileNotFoundException If save file is not found.
     */
    public ModuleList loadModuleData() throws FileNotFoundException {

        try {
            ClassLoader classloader = Thread.currentThread().getContextClassLoader();
            Reader reader = new InputStreamReader(classloader.getResourceAsStream("data.json"));
            Type moduleDataType = new TypeToken<ModuleList>() {
            }.getType();
            Gson gson = new Gson();
            ModuleList moduleDataList = gson.fromJson(reader, moduleDataType);
            System.out.println("Module Data loaded");
            return moduleDataList;
        } catch (NullPointerException e) {
            throw new FileNotFoundException();
        }

    }

    /**
     * Reads all lines in the save file, initialises them as an ArrayList of Tasks.
     *
     * @param save Save file.
     * @return TaskList of initialised Tasks based on uncorrupted data in save file.
     * @throws FileNotFoundException If the save file cannot be found at filePath.
     */
    private static TaskList readFileContents(File save, Ui ui) throws FileNotFoundException {
        Scanner s = new Scanner(save);
        TaskList newTaskList = new TaskList();
        int counter = 0;
        while (s.hasNext()) {
            try {
                counter++;
                newTaskList.add(newTask(s.nextLine()));
            } catch (InvalidSaveFile e) {
                ui.printInvalidSaveFile(counter, filePath);
                logger.log(Level.INFO, "Error in reading data from file");
            } catch (DateOverException e) {
                ui.printExistingDateOver(e);
            }
        }
        logger.log(Level.INFO, "Successfully read " + counter + " tasks from save file.");
        return newTaskList;
    }

    private static ModuleList readModuleFileContents(File save, Ui ui, ModuleList allModules, Calendar calendar)
            throws FileNotFoundException {
        Scanner s = new Scanner(save);
        ModuleList newModuleList = new ModuleList();
        int counter = 0;
        while (s.hasNext()) {
            try {
                String moduleInfo = s.nextLine();
                String[] moduleInfoArgs = moduleInfo.split("\\|");
                String moduleCode = moduleInfoArgs[0];
                if (moduleCode == null) {
                    throw new InvalidSaveFile();
                }
                Module newModule = allModules.findModule(moduleCode);
                if (newModule == null) {
                    throw new InvalidSaveFile();
                }

                Module module = new Module(newModule.getCode(), newModule.getTitle(), newModule.getModuleCredits());
                if (isAdded(newModuleList, module)) {
                    throw new DuplicateModuleInTextFileException();
                }
                addLessons(module, newModule, moduleInfoArgs);
                calendar.addModule(module);
                newModuleList.add(module);
                counter++;
            } catch (InvalidSaveFile e) {
                ui.printInvalidSaveFile(counter, filePath);
            } catch (DuplicateModuleInTextFileException e) {
                ui.printDuplicateModuleInTextFile(counter);
            }
        }
        return newModuleList;
    }

    /**
     * Checks if the module is already in the module file.
     *
     * @param moduleList The list of modules.
     * @param module The module to be checked.
     * @return True if the module is already in the module file.
     */
    public static boolean isAdded(ModuleList moduleList, Module module) {
        for (Module mod: moduleList) {
            if (mod.getCode().equals(module.getCode())) {
                return true;
            }
        }
        return false;
    }
    private static void addLessons(Module module, Module searchModule, String[] moduleInfo) {
        module.createNewTimeTable();

        for (int i = 1; i < moduleInfo.length; i++) {
            String[] lessonInfo = moduleInfo[i].split(":");

            for (Timetable timetable: searchModule.getModuleTimetable()) {
                if (timetable.getLessonType().equals(lessonInfo[0])
                        && timetable.getClassNumber().equals(lessonInfo[1])) {

                    if (!module.getModuleTimetable().contains(timetable)) {
                        module.getModuleTimetable().add(timetable);
                    }

                }
            }
        }


    }

    /**
     * Interprets a line from the save file, returns it as a new Task.
     *
     * @param text Line from the save file.
     * @return Corresponding Task to data stored in {@code text}.
     * @throws InvalidSaveFile If any line in the input data is not of the right format.
     */
    private static Task newTask(String text) throws InvalidSaveFile, DateOverException {
        char type;
        Boolean isDone;
        String param;
        try {
            type = getType(text);
            isDone = isStatusDone(text);
            param = getParam(text);
        } catch (StringIndexOutOfBoundsException e) {
            throw new InvalidSaveFile();
        }
        switch (type) {
        case TXT_TODO_WORD:
            return newToDo(isDone, param);
        case TXT_DEADLINE_WORD:
            return newDeadline(isDone, param);
        case TXT_EVENT_WORD:
            return newEvent(isDone, param);
        default:
            throw new InvalidSaveFile();
        }
    }

    private static char getType(String text) throws InvalidSaveFile {
        char type = text.charAt(TYPE_POS);
        if (type != TXT_TODO_WORD & type != TXT_DEADLINE_WORD & type != TXT_EVENT_WORD) {
            throw new InvalidSaveFile();
        }
        return type;
    }

    private static Boolean isStatusDone(String text) throws InvalidSaveFile {
        char status = text.charAt(STATUS_POS);
        if (status == 'X') {
            return true;
        } else if (status == ' ') {
            return false;
        } else {
            throw new InvalidSaveFile();
        }
    }

    private static String getParam(String text) {
        return text.substring(PARAM_POS);
    }

    private static ToDo newToDo(Boolean isDone, String param) {
        ToDo newToDo = new ToDo(param);
        newToDo.setDone(isDone);
        return newToDo;
    }

    private static Deadline newDeadline(Boolean isDone, String param) throws InvalidSaveFile, DateOverException {
        final String[] paramAndBy;
        try {
            paramAndBy = Parser.parseDeadline(param);
        } catch (InvalidDeadline e) {
            throw new InvalidSaveFile();
        }
        try {
            Deadline newDeadline = new Deadline(paramAndBy[0], paramAndBy[1]);
            newDeadline.setDone(isDone);
            return newDeadline;
        } catch (DateTimeParseException e) {
            throw new InvalidSaveFile();
        }
    }

    private static Event newEvent(Boolean isDone, String param) throws InvalidSaveFile, DateOverException {
        final String[] paramAndFromTo;
        try {
            paramAndFromTo = Parser.parseEvent(param);
        } catch (InvalidEvent e) {
            throw new InvalidSaveFile();
        }
        try {
            Event newEvent = new Event(paramAndFromTo[0], paramAndFromTo[1], paramAndFromTo[2]);
            newEvent.setDone(isDone);
            return newEvent;
        } catch (DateTimeParseException | DateOrderException e) {
            throw new InvalidSaveFile();
        }
    }

}
