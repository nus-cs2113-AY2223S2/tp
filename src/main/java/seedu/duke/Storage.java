package seedu.duke;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class Storage implements DatabaseInterface {

    private static final String SAVED_MODULES_FILE_PATH = "data/saved_modules.txt";
    private ArrayList<Module> modules;

    public Storage() {
        this.modules = new ArrayList<>();
        try {
            initialiseDatabase();
        } catch (IOException e) {
            System.out.println("Initialise Saved Modules Failure");
        }
    }

    public void initialiseDatabase() throws IOException {
        File savedModulesFile = new File(SAVED_MODULES_FILE_PATH);
        if (!savedModulesFile.exists()) {
            File directory = new File("data");
            directory.mkdirs();
            savedModulesFile.createNewFile();
            return;
        }
        readModData(SAVED_MODULES_FILE_PATH, modules);
        boolean isStorageCorrupted = checkDatabaseCorrupted();
        if (isStorageCorrupted) {
            UI.printStorageCorruptedMessage();
        }
        try {
            writeModListToFile(modules);
        } catch (IOException e) {
            UI.printWriteToDatabaseFailureMessage();
        }
    }

    @Override
    public boolean checkDatabaseCorrupted() {
        ArrayList<Module> allModules = new DataReader().getModules();
        boolean isCorrupted = false;
        ArrayList<Module> copyArrayList = new ArrayList<>();
        for (Module module : modules) {
            if (!checkIsValidModule(module, allModules)) {
                isCorrupted = true;
            } else {
                copyArrayList.add(module);
            }
        }
        modules.clear();
        for (Module toCopyModule : copyArrayList) {
            modules.add(toCopyModule);
        }
        return isCorrupted;
    }

    private boolean checkIsValidModule(Module moduleToCheck, ArrayList<Module> allModules) {
        for (Module module : allModules) {
            if (module.toString().equals(moduleToCheck.toString())) {
                return true;
            }
        }
        return false;
    }

    private void readModData(String modulesFilePath, ArrayList<Module> modules) {
        try (BufferedReader br = new BufferedReader(new FileReader(modulesFilePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] row = line.split(",");
                if (row.length != 7) {
                    UI.printModuleCorruptDeleteMessage();
                    continue;
                }
                if (row[3].equals("N/A")) {
                    row[3] = "0";
                }
                try {
                    Module module = new Module(Integer.parseInt(row[0]), row[1], row[2],
                            Integer.parseInt(row[3]), row[4], row[5], Integer.parseInt(row[6]));
                    boolean isDuplicate = doesModuleExist(module);
                    if (isDuplicate) {
                        continue;
                    }
                    modules.add(module);
                } catch (NumberFormatException e) {
                    UI.printModuleCorruptDeleteMessage();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean addModuleToModuleList(Module moduleToAdd) {
        assert (moduleToAdd != null) : "error line 89";
        if (moduleToAdd == null) {
            UI.printAddModuleFailureMessage();
            return false;
        }
        if (doesModuleExist(moduleToAdd)) {
            UI.printModAlreadyExistMessage();
            return false;
        }
        modules.add(moduleToAdd);
        sortModulesAccordingToPrintingLength(modules);
        try {
            saveModuleToStorage(moduleToAdd.toString());
        } catch (IOException e) {
            UI.printAddModuleFailureMessage();
        }
        return true;
    }

    public boolean doesModuleExist(Module moduleToAdd) {
        for (Module module : modules) {
            if ((moduleToAdd.getUnivId() == module.getUnivId()) &&
                    moduleToAdd.toString().equalsIgnoreCase(module.toString())) {
                return true;
            }
        }
        return false;
    }

    private void saveModuleToStorage(String saveModuleString) throws IOException {
        FileWriter fw = new FileWriter(SAVED_MODULES_FILE_PATH, true);
        fw.write(writeTaskPreparation(saveModuleString));
        fw.close();
    }

    /**
     * Deletes the module corresponding to the uni specified by user. Module will the removed from user's
     * saved list of modules. Uses Index relative to specific PU list. Sorts ArrayList modules after deleting
     * module to ensure that modules are still sorted according to printing length
     *
     * @param indexToDeletePuSpecificList Index of that module that is given in user input, relative to PU list.
     * @param modules                     The ArrayList of all modules user saved.
     * @param database                    Database of the user's saved list of modules.
     * @param uniID                       Partner University's ID number.
     * @return True if successfully deleted the module, false if unsuccessful.
     */
    public static boolean deleteModule(int indexToDeletePuSpecificList, ArrayList<Module> modules,
                                       Storage database, int uniID) {
        int indexToDeletePuSpecificListToZeroBased = indexToDeletePuSpecificList - 1;
        int counterUpToIndexToDelete = 0;
        int indexToDelete = -1;
        for (int i = 0; i < modules.size(); i++) {
            Module currentModule = modules.get(i);
            if (currentModule.getUnivId() == uniID) {
                if (counterUpToIndexToDelete == indexToDeletePuSpecificListToZeroBased) {
                    indexToDelete = i;
                    break;
                }
                counterUpToIndexToDelete++;
            }
        }

        try {
            modules.remove(indexToDelete);
        } catch (IndexOutOfBoundsException e) {
            UI.printDeleteNumError();
            return false;
        }
        sortModulesAccordingToPrintingLength(modules);
        try {
            database.writeModListToFile(modules);
        } catch (IOException e) {
            UI.printWriteToDatabaseFailureMessage();
            return false;
        }
        return true;
    }


    /**
     * Adds and overwrites ArrayList of user's saved modules list in database.
     *
     * @param modules ArrayList of modules to be written into database.
     * @throws IOException If input/output operations fail or are interrupted.
     */
    public void writeModListToFile(ArrayList<Module> modules) throws IOException {
        FileWriter fw = new FileWriter(SAVED_MODULES_FILE_PATH);
        String stringToAdd = "";
        for (Module module : modules) {
            stringToAdd += writeTaskPreparation(module.toString());
        }
        fw.write(stringToAdd);
        fw.close();
    }

    /**
     * Returns list of modules in ArrayList type.
     *
     * @return ArrayList of modules.
     */
    public ArrayList<Module> getModules() {
        return modules;
    }

//    public ArrayList<Deadline> getDeadlines() {
//        return deadlines;
//    }
//
//    public void addDeadlineToDeadlines(Deadline deadlineToAdd) {
//        if (deadlineToAdd == null) {
//            UI.printAddDeadlineFailureMessage();
//            return;
//        }
//        deadlines.add(deadlineToAdd);
//        try {
//            saveDeadlineToStorage(deadlineToAdd.toString());
//        } catch (IOException e) {
//            UI.printAddDeadlineFailureMessage();
//        }
//    }
//
//    private void saveDeadlineToStorage(String saveDeadlineString) throws IOException {
//        FileWriter fw = new FileWriter(SAVED_DEADLINES_FILE_PATH, true);
//        fw.write(writeTaskPreparation(saveDeadlineString));
//        fw.close();
//    }
//
//    public void compareDeadlines(ArrayList<Deadline> deadlines) {
//        Date date = new Date();
//        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
//        String todayDate = formatter.format(date);
//        int counter = 1;
//        try {
//            boolean hasReminderMsgPrinted = false;
//            for (Deadline deadline : deadlines) {
//                Date today = formatter.parse(todayDate);
//                Date deadlineDue = formatter.parse(deadline.getDueDate());
//                long timeDiff = Math.abs(deadlineDue.getTime() - today.getTime());
//                long daysDiff = TimeUnit.DAYS.convert(timeDiff, TimeUnit.MILLISECONDS);
//                if (daysDiff <= 7) {
//                    if (!hasReminderMsgPrinted) {
//                        UI.printReminderMessage();
//                        hasReminderMsgPrinted = true;
//                    }
//                    UI.printReminderDeadline(deadline, counter);
//                    counter++;
//                }
//            }
//            if (hasReminderMsgPrinted) {
//                UI.printLine();
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }

    /**
     * Sorts modules according to printing length.
     * @param modules Module to be printed to User Console
     */
    public static void sortModulesAccordingToPrintingLength(ArrayList<Module> modules) {
        modules.sort(Comparator.comparing(Module::getPrintingLength));
    }
}
