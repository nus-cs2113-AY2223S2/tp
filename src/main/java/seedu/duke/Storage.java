package seedu.duke;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class Storage implements DatabaseInterface {

    private static final String SAVED_MODULES_FILE_PATH = "data/saved_modules.txt";
    private static final String SAVED_DEADLINES_FILE_PATH = "data/deadlines.txt";
    private ArrayList<Module> modules;
    private ArrayList<Deadline> deadlines;

    public Storage() {
        this.modules = new ArrayList<>();
        this.deadlines = new ArrayList<>();
        try {
            initialiseDatabase();
        } catch (IOException e) {
            System.out.println("Initialise Saved Modules Failure");
        }
        try {
            initialiseDeadlinesDatabase();
        } catch (IOException e) {
            System.out.println("Initialise Deadlines Failure");
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
    }

    public void initialiseDeadlinesDatabase() throws IOException {
        File savedDeadlinesFile = new File(SAVED_DEADLINES_FILE_PATH);
        if (!savedDeadlinesFile.exists()) {
            File directory = new File("data");
            directory.mkdirs();
            savedDeadlinesFile.createNewFile();
            return;
        }
        readDeadlineData(SAVED_DEADLINES_FILE_PATH, deadlines);
    }


    private void readModData(String modulesFilePath, ArrayList<Module> modules) {
        try (BufferedReader br = new BufferedReader(new FileReader(modulesFilePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] row = line.split(",");
                if (row[3].equals("N/A")) {
                    row[3] = "0";
                }
                Module module = new Module(Integer.parseInt(row[0]), row[1], row[2],
                        Integer.parseInt(row[3]), row[4], row[5], Integer.parseInt(row[6]));
                modules.add(module);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void readDeadlineData(String modulesDeadlinePath, ArrayList<Deadline> deadlines) {
        try (BufferedReader br = new BufferedReader(new FileReader(modulesDeadlinePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] row = line.split("//");
                Deadline deadline = new Deadline(row[0], row[1]);
                deadlines.add(deadline);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addModuleToModuleList(Module moduleToAdd) {
        assert (moduleToAdd != null) : "error line 53";
        if (moduleToAdd == null) {
            UI.printAddModuleFailureMessage();
            return;
        }
        modules.add(moduleToAdd);
        try {
            saveModuleToStorage(moduleToAdd.toString());
        } catch (IOException e) {
            UI.printAddModuleFailureMessage();
        }
    }

    private void saveModuleToStorage(String saveModuleString) throws IOException {
        FileWriter fw = new FileWriter(SAVED_MODULES_FILE_PATH, true);
        fw.write(writeTaskPreparation(saveModuleString));
        fw.close();
    }

    /**
     * Deletes the module corresponding to the uni specified by user. Module will the removed from user's
     * saved list of modules.
     *
     * @param indexToDelete Index of that module that is given in user input.
     * @param uniModuleList The corresponding ArrayList of that specified uni.
     * @param database      Database of the user's saved list of modules.
     * @return True if successfully deleted the module, false if unsuccessful.
     */
    public static boolean deleteModule(int indexToDelete, ArrayList<Module> uniModuleList,
                                       Storage database) {
        if (indexToDelete == -1) {
            return false;
        }
        int indexToZeroBased = indexToDelete - 1;
        try {
            uniModuleList.remove(indexToZeroBased);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Index out of bounds");
            return false;
        }
        try {
            database.writeModListToFile(uniModuleList);
        } catch (IOException e) {
            System.out.println("Unable to save to database");
            return false;
        }
        return true;
    }

    /**
     * Deletes the deadline specified by user. Deadline will the removed from user's
     * list of deadlines.
     *
     * @param indexToDelete Index of that deadline that is given in user input.
     * @param deadlines     The ArrayList of deadlines.
     * @param database      Database of the user's list of deadlines.
     * @return True if successfully deleted the module, false if unsuccessful.
     */
    public static boolean deleteDeadline(int indexToDelete, ArrayList<Deadline> deadlines,
                                         Storage database) {
        if (indexToDelete == -1) {
            return false;
        }
        int indexToZeroBased = indexToDelete - 1;
        try {
            deadlines.remove(indexToZeroBased);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Index out of bounds");
            return false;
        }
        try {
            database.writeDeadlinesToFile(deadlines);
        } catch (IOException e) {
            System.out.println("Unable to save to database");
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
     * Adds and overwrites ArrayList of user's deadlines in database.
     *
     * @param deadlines ArrayList of deadlines to be written into database.
     * @throws IOException If input/output operations fail or are interrupted.
     */
    public void writeDeadlinesToFile(ArrayList<Deadline> deadlines) throws IOException {
        FileWriter fw = new FileWriter(SAVED_DEADLINES_FILE_PATH);
        String stringToAdd = "";
        for (Deadline deadline : deadlines) {
            stringToAdd += writeTaskPreparation(deadline.toString());
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

    public ArrayList<Deadline> getDeadlines() {
        return deadlines;
    }

    public void addDeadlineToDeadlines(Deadline deadlineToAdd) {
        if (deadlineToAdd == null) {
            UI.printAddDeadlineFailureMessage();
            return;
        }
        deadlines.add(deadlineToAdd);
        try {
            saveDeadlineToStorage(deadlineToAdd.toString());
        } catch (IOException e) {
            UI.printAddDeadlineFailureMessage();
        }
    }

    private void saveDeadlineToStorage(String saveDeadlineString) throws IOException {
        FileWriter fw = new FileWriter(SAVED_DEADLINES_FILE_PATH, true);
        fw.write(writeTaskPreparation(saveDeadlineString));
        fw.close();
    }

    public void compareDeadlines(ArrayList<Deadline> deadlines) {
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        String todayDate = formatter.format(date);
        int counter = 1;
        try {
            for (Deadline deadline : deadlines) {
                Date today = formatter.parse(todayDate);
                Date deadlineDue = formatter.parse(deadline.getDueDate());
                long timeDiff = Math.abs(deadlineDue.getTime() - today.getTime());
                long daysDiff = TimeUnit.DAYS.convert(timeDiff, TimeUnit.MILLISECONDS);
                if (daysDiff <= 7) {
                    UI.printReminderMessage(deadline, counter);
                    counter++;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
