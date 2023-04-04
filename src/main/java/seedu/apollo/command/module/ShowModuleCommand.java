package seedu.apollo.command.module;

import seedu.apollo.calendar.Calendar;
import seedu.apollo.command.Command;
import seedu.apollo.exception.module.InvalidModule;
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

public class ShowModuleCommand extends Command implements LoggerInterface {
    private static Logger logger = Logger.getLogger("ShowModuleCommand");
    private String[] args;
    private Module module;

    /**
     * Constructor for ShowModuleCommand.
     *
     * @param params     The parameters the user passes into the command.
     * @param allModules The list of all modules.
     * @throws InvalidModule If the module code is invalid.
     */

    public ShowModuleCommand(String params, ModuleList allModules) throws InvalidModule, IllegalCommandException {

        setUpLogger();
        assert (params != null) : "ShowModuleCommand: ModuleCode should not be null!";
        assert (allModules != null) : "ShowModuleCommand: Module list should not be null!";

        args = params.split("\\s+");

        if (args.length != 2 && args.length != 1) {
            throw new IllegalCommandException();
        }

        String moduleCode = args[0];
        Module checkMod = allModules.findModule(moduleCode);
        if (checkMod == null) {
            throw new InvalidModule();
        }

        module = checkMod;

    }

    /**
     * Sets up logger for ShowModuleCommand class.
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
                        Calendar calendar)  {

        assert (module != null) : "ShowModuleCommand: Module should not be null!";
        try {
            if (args.length == 2) {
                handleMultiCommand(ui);
            } else {
                Module referenceModule = allModules.findModule(module.getCode());
                ArrayList<Timetable> copyList = new ArrayList<>(referenceModule.getModuleTimetable());
                ArrayList<Timetable> parseList = sortTimetable(copyList);
                ui.printShowModuleMessage(module, getLessonTypes(referenceModule), parseList);
            }
        } catch (IllegalCommandException e) {
            ui.printInvalidCommand();
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
     * Handles the command when the user wants to see the timetable of a specific lesson type.
     *
     * @param ui The Ui object to print the timetable.
     */
    private void handleMultiCommand(Ui ui) throws IllegalCommandException {
        String type = args[1];

        LessonType lessonType = getCommand(type);
        if (lessonType == null) {
            throw new IllegalCommandException();
        }
        ArrayList<Timetable> copyList = new ArrayList<>();
        ArrayList<Timetable> timetableList = module.getModuleTimetable();
        if (timetableList == null) {
            throw new IllegalCommandException();
        }
        for (Timetable timetable : timetableList) {
            LessonType checkType = determineLessonType(timetable.getLessonType());
            assert (checkType != null) : "ShowModuleCommand: Lesson type should not be null!";

            if (checkType.equals(lessonType)) {
                copyList.add(timetable);
            }
        }

        if (copyList.size() == 0) {
            ui.printNoLessonType();
        } else {
            sortTimetable(copyList);
            ui.printModuleLessonTimetable(module, lessonType, copyList);
        }


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

}
