package seedu.moneymind;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;

public class Storage {
    /**
     * Divider used to separate details of a task saved in file
     */
    private static final String SAVEFILESEPARATOR = ", ";
    private static String filePath = "EventList.txt";
    private static File textFile;
    private static Scanner textFileScanner;

    public Storage() {
        loadFromFile();
    }

    /**
     * Sets up the file to be read
     */
    private static void setupFile() {
        textFile = new File(filePath);

        try {
            textFileScanner = new Scanner(textFile);
        } catch (FileNotFoundException e) {
            System.out.println("I cannot seem to access the saved tasks. Did you perhaps lock it away?");
        }
    }

    /**
     * Saves an ArrayList of tasks to DukeTaskList.txt file
     *
     * @param list ArrayList of tasks
     */
    public static void saveToFile(ArrayList<Event> list) {
        String writeToFile = "";

        for (Event dukeTasks : list) {
            // extracting details from task object
            String[] dataToTextFile= {"", "", ""};
            dataToTextFile[0] = dukeTasks.toString();

            // save task details split by regex ", "
            writeToFile += dataToTextFile[0] + SAVEFILESEPARATOR + dataToTextFile[1] +
                    SAVEFILESEPARATOR + dataToTextFile[2] + System.lineSeparator();
        }

        // write task list to text file
        try {
            textFile = new File(filePath);
            FileWriter dukeWriter = new FileWriter(textFile);
            dukeWriter.write(writeToFile);
            dukeWriter.close();
        } catch (IOException e) {
            System.out.println("I cannot seem to access the saved file. Did you perhaps delete it?");
        }
    }

    /**
     * Returns an ArrayList of tasks from DukeTaskList.txt file
     *
     * @return ArrayList of tasks
     */
    public static ArrayList<Event> loadFromFile() {
        ArrayList<Event> savedList = new ArrayList<>();
        setupFile();

        while (textFileScanner.hasNext()) {
            String[] loadTaskInfo = new String[3];
            loadTaskInfo = textFileScanner.nextLine().split(SAVEFILESEPARATOR, 3);

            // create tasks individually
            switch (loadTaskInfo[0]) {
                case "T":
                    break;

                case "D":
                    break;

                default:
                    break;
            }
        }

        return savedList;
    }
}