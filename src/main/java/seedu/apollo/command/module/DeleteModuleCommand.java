package seedu.apollo.command.module;
import seedu.apollo.calendar.Calendar;
import seedu.apollo.exception.module.ClassNotFoundException;
import seedu.apollo.exception.utils.IllegalCommandException;
import seedu.apollo.exception.utils.InvalidSaveFile;
import seedu.apollo.module.LessonType;
import seedu.apollo.module.Timetable;
import seedu.apollo.storage.Storage;
import seedu.apollo.command.Command;
import seedu.apollo.exception.module.ModuleNotFoundException;
import seedu.apollo.module.Module;
import seedu.apollo.module.ModuleList;
import seedu.apollo.ui.Ui;
import seedu.apollo.task.TaskList;

import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Logger;

import static seedu.apollo.utils.LessonTypeUtil.determineLessonType;

/**
 * For {@code delmod} command.
 * Delete Module Command class that finds the module using moduleCode and removes it from the ModuleList
 */
public class DeleteModuleCommand extends Command {
    private static Logger logger = Logger.getLogger("DeleteModuleCommand");
    protected String[] args;

    public DeleteModuleCommand(String params) throws IllegalCommandException {
        super(logger);
        String[] args = params.split("\\s+");

        if (args.length != 1 && args.length != 3) {
            throw new IllegalCommandException();
        }
        this.args = args;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage, ModuleList moduleList, ModuleList allModules,
                        Calendar calendar){
        try {
            if (args.length == 3) {
                handleMultiCommand(moduleList, ui);
            } else {
                // module code is the only argument
                String listParam = args[0];
                String moduleCode;
                if (isInteger(listParam)) {
                    int index = Integer.parseInt(listParam);
                    if (index > moduleList.size() || index < 1) {
                        throw new NumberFormatException();
                    }
                    moduleCode = moduleList.get(index - 1).getCode();
                    moduleList.remove(index - 1);
                } else {
                    moduleCode = listParam;
                    Module toDelete = moduleList.findModule(listParam);
                    if (toDelete == null) {
                        throw new ModuleNotFoundException();
                    }
                    moduleList.remove(toDelete);
                }

                ui.printModuleDeleteMessage(moduleCode, moduleList);
            }

            storage.updateModule(moduleList, calendar);

        } catch (ModuleNotFoundException e) {
            ui.printUnsuccessfulModuleDelete(args[0]);
            ui.printTotalModularCredits(moduleList);
        } catch (IOException | InvalidSaveFile e) {
            ui.printErrorForIO();
        } catch (NumberFormatException e) {
            ui.printErrorForModIdx(moduleList.size());
        }
    }

    /**
     * Checks if the string is an integer.
     *
     * @param param String to be checked.
     * @return Boolean value of whether the string is an integer.
     */
    private Boolean isInteger(String param) {
        try {
            Integer.parseInt(param);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }

    /**
     * Handles the deletion of a lesson from a module.
     *
     * @param moduleList ModuleList to be deleted from.
     * @param ui Ui to print messages.
     * @throws ModuleNotFoundException If the module is not found.
     */
    private void handleMultiCommand(ModuleList moduleList, Ui ui) throws ModuleNotFoundException {
        String moduleCode = args[0];
        String command = args[1];
        String lessonNumber = args[2];

        try {
            LessonType lessonType = getCommand(command);
            Module toDelete = moduleList.findModule(moduleCode);

            if (toDelete == null) {
                throw new ModuleNotFoundException();
            }

            this.deleteTimetable(toDelete, lessonType, lessonNumber);
            ui.printModuleLessonDeleteMessage(moduleCode, lessonType, lessonNumber);
        } catch (IllegalCommandException e) {
            ui.printInvalidCommand();
        } catch (ClassNotFoundException e) {
            ui.printClassNotAdded();
        }
    }

    /**
     * Deletes the lesson from the module.
     *
     * @param module Module to be deleted from.
     * @param lessonType Type of lesson to be deleted.
     * @param lessonNumber Number of lesson to be deleted.
     * @throws ClassNotFoundException If the lesson is not found.
     */
    private void deleteTimetable(Module module, LessonType lessonType, String lessonNumber)
            throws ClassNotFoundException {

        Boolean isFound = false;

        if (module.getModuleTimetable() == null) {
            throw new ClassNotFoundException();
        }

        ArrayList<Timetable> copyList = new ArrayList<>(module.getModuleTimetable());

        //searches for lesson of specified type and number
        for (Timetable timetable :copyList) {
            String classNumber = timetable.getClassNumber();
            String type = timetable.getLessonType();
            LessonType lessonType1 = determineLessonType(type);

            if (lessonType1 == lessonType && classNumber.equals(lessonNumber)) {
                module.getModuleTimetable().remove(timetable);
                isFound = true;
            }
        }

        // if the lesson is not found, throws an exception
        if (!isFound) {
            throw new ClassNotFoundException();
        }
    }


}
