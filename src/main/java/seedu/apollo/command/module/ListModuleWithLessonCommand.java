package seedu.apollo.command.module;

import seedu.apollo.calendar.Calendar;
import seedu.apollo.command.Command;
import seedu.apollo.exception.module.EmptyLessonTypesInTimetable;
import seedu.apollo.exception.module.InvalidModule;
import seedu.apollo.exception.module.LessonTypeNotAddedException;
import seedu.apollo.exception.module.LessonTypeNotInModuleException;
import seedu.apollo.exception.module.ModuleNotAddedException;
import seedu.apollo.exception.utils.IllegalCommandException;
import seedu.apollo.module.LessonType;
import seedu.apollo.module.Module;
import seedu.apollo.module.ModuleList;
import seedu.apollo.module.Timetable;
import seedu.apollo.storage.Storage;
import seedu.apollo.task.TaskList;
import seedu.apollo.ui.Ui;
import seedu.apollo.utils.LoggerInterface;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import static seedu.apollo.utils.LessonTypeUtil.determineLessonType;

public class ListModuleWithLessonCommand extends Command implements LoggerInterface {

    private static Logger logger = Logger.getLogger("ListModuleCommand");
    private String[] args;
    private Module module;

    /**
     * Constructor for ListModuleWithLessonCommand.
     *
     * @param params     The parameters the user passes into the command.
     * @param allModules The list of all modules.
     * @throws InvalidModule If the module code is invalid.
     */
    public ListModuleWithLessonCommand(String params, ModuleList allModules) throws InvalidModule,
            IllegalCommandException {

        setUpLogger();
        assert (params != null) : "ListModuleWithLessonCommand: ModuleCode should not be null!";

        args = params.split("\\s+");

        if (args.length != 1 && args.length != 2) {
            throw new IllegalCommandException();
        }

        String moduleCode = args[0];

        Module checkMod = allModules.findModule(moduleCode);
        if (checkMod == null) {
            throw new InvalidModule();
        }

        module = new Module(checkMod.getCode(), checkMod.getTitle(), checkMod.getModuleCredits());

    }

    /**
     * Sets up logger for ListModuleWithLessonCommand class.
     *
     * @throws IOException If logger file cannot be created.
     */
    @Override
    public void setUpLogger() {
        LogManager.getLogManager().reset();
        logger.setLevel(Level.ALL);
        ConsoleHandler logConsole = new ConsoleHandler();
        logConsole.setLevel(Level.SEVERE);
        logger.addHandler(logConsole);
        try {

            if (!new File("apollo.log").exists()) {
                new File("apollo.log").createNewFile();
            }

            FileHandler logFile = new FileHandler("apollo.log", true);
            logFile.setLevel(Level.FINE);
            logger.addHandler(logFile);

        } catch (IOException e) {
            logger.log(Level.SEVERE, "File logger not working.", e);
        }

    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage, ModuleList moduleList, ModuleList allModules,
                        Calendar calendar) {

        try {
            copyModuleListData(moduleList);

            if (args.length == 2) {
                handleMultiCommand(ui, allModules);
            } else {
                handleSingleCommand(ui);
            }

        } catch (ModuleNotAddedException e) {
            ui.printLessonNotInList(module.getCode());
        } catch (IllegalCommandException e) {
            ui.printInvalidCommand();
        } catch (LessonTypeNotAddedException e) {
            ui.printLessonsNotAdded(module.getCode());
        } catch (LessonTypeNotInModuleException e) {
            ui.printLessonTypeNotInModule();
        } catch (EmptyLessonTypesInTimetable e) {
            ui.printEmptyLessonTypeInModuleList();
        }

    }

    /**
     * Handles the command when user wants to see the timetable added of a module.
     *
     * @param ui The Ui object to print the timetable.
     */
    private void handleSingleCommand(Ui ui) throws EmptyLessonTypesInTimetable {
        ArrayList<LessonType> checkLessonTypes = getLessonTypes(module);
        if (checkLessonTypes.isEmpty()) {
            throw new EmptyLessonTypesInTimetable();
        }
        ArrayList<Timetable> copyList = new ArrayList<>(module.getModuleTimetable());
        ArrayList<Timetable> parseList = sortTimetable(copyList);
        ui.printModuleListWithLesson(module, parseList);
    }

    /**
     * Handles the command when user wants to see the timetable added of a specific lesson type.
     *
     * @author gohyixuan
     * @param ui The Ui object to print the timetable.
     * @throws IllegalCommandException If the command is invalid.
     */
    private void handleMultiCommand(Ui ui, ModuleList allModules) throws IllegalCommandException,
            LessonTypeNotInModuleException {

        String type = args[1];

        LessonType lessonType = getCommand(type);
        if (lessonType == null) {
            throw new IllegalCommandException();
        }

        if (!isExistLessonType(allModules, lessonType)) {
            throw new LessonTypeNotInModuleException();
        }

        ArrayList<Timetable> timetableList = new ArrayList<>(module.getModuleTimetable());

        ArrayList<Timetable> timetableInModuleList = new ArrayList<>();

        for (Timetable timetable : timetableList) {
            LessonType checkType = determineLessonType(timetable.getLessonType());
            assert (checkType != null) : "ListModuleWithLessonCommand: Lesson type should not be null!";

            if (checkType.equals(lessonType)) {
                timetableInModuleList.add(timetable);
            }
        }

        if (timetableInModuleList.size() == 0) {
            ui.printLessonTypeNotAdded(module.getCode(), lessonType);
        } else {
            sortTimetable(timetableInModuleList);
            ui.printSpecificTimetable(module, lessonType, timetableInModuleList);
        }

    }

    /**
     * Copies the timetable information into the private module.
     *
     * @param moduleList The list of modules.
     * @throws LessonTypeNotAddedException If a lesson type has not been added into module list.
     * @throws ModuleNotAddedException If the lessons have not been added into module list.
     */
    private void copyModuleListData(ModuleList moduleList) throws LessonTypeNotAddedException,
            ModuleNotAddedException {

        if (!isInModuleList(moduleList, module)) {
            throw new ModuleNotAddedException();
        }

        int index = 0;
        for (Module module1 : moduleList) {
            if (module1.getCode().equals(module.getCode())) {
                module.setTimetable(module1.getModuleTimetable());
                break;
            }
            index++;
        }
        module.setTimetable(moduleList.get(index).getModuleTimetable());
        if (module.getModuleTimetable() == null) {
            throw new LessonTypeNotAddedException();
        }

    }

    /**
     * Returns the available lesson type of the module.
     *
     * @param module The module being checked.
     * @return The lesson types available for this module.
     */
    private ArrayList<LessonType> getLessonTypes(Module module) {
        ArrayList<LessonType> lessonTypes = new ArrayList<>();
        for (Timetable timetable : module.getModuleTimetable()) {
            LessonType lessonType = determineLessonType(timetable.getLessonType());
            if (!lessonTypes.contains(lessonType) && lessonType != null) {
                lessonTypes.add(lessonType);
            }
        }
        return lessonTypes;
    }

    /**
     * Sorts the timetable by lesson type, class number and class time .
     *
     * @param copyList The list of lessons to be sorted.
     * @return The sorted list of lessons.
     */
    private ArrayList<Timetable> sortTimetable(ArrayList<Timetable> copyList) {
        Comparator<Timetable> compareByLessonType = Comparator.comparing(Timetable::getLessonType);
        Comparator<Timetable> compareByClassNumber = Comparator.comparing(Timetable::getClassNumber);
        Comparator<Timetable> compareAll = compareByLessonType.thenComparing(compareByClassNumber);
        ArrayList<Timetable> parseList =
                copyList.stream().sorted(compareAll).collect(Collectors.toCollection(ArrayList::new));
        return parseList;
    }

    /**
     * Checks if the module is in the module list.
     *
     * @param moduleList The list of modules.
     * @param module     The module to be checked.
     * @return True if the module is in the list of modules.
     */
    private boolean isInModuleList(ModuleList moduleList, Module module) {
        for (Module moduleCheck : moduleList) {
            if (moduleCheck.getCode().equals(module.getCode())) {
                return true;
            }
        }
        return false;
    }

    private boolean isExistLessonType(ModuleList allModules, LessonType lessonType) {
        Module checkMod = allModules.findModule(args[0]);
        ArrayList<LessonType> checkLesson = getLessonTypes(checkMod);
        for (LessonType toCheckType : checkLesson) {
            if (toCheckType.equals(lessonType)) {
                return true;
            }
        }
        return false;
    }

}
