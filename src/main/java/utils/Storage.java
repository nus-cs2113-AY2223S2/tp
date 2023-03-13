package utils;

import common.Messages;
import dinerDeadline.Deadline;
import dinerDeadline.DeadlineList;
import ui.TextUi;

import java.io.*;
import java.util.Scanner;

/**
 * Parser to tokenize the input
 */
public class Storage {
    public static final String DEADLINE_FILEPATH = "deadline.txt";

    /**
     * Takes the list of tasks the user has input and writes it to the data file at the default file path.
     * @param deadlineList the list of deadlines to save to file.
     */
    public static void writeToFile(DeadlineList deadlineList) {
        try {
            BufferedWriter outputWriter;
            outputWriter = new BufferedWriter(new FileWriter(DEADLINE_FILEPATH));
            for (Deadline x : deadlineList.deadlines) {
                outputWriter.write(x.toString() + System.lineSeparator());
            }
            outputWriter.flush();
            outputWriter.close();
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }

    private static void readFile(DeadlineList deadlineList) throws IOException {
        String line;
        File f = new File(DEADLINE_FILEPATH);
        Scanner s = new Scanner(f);
        while (s.hasNext()) {
            line = s.nextLine();
            String[] description = line.split(" by: ", 2);
            Deadline deadline = new Deadline(description[0], description[1]);
            (deadlineList.deadlines).add(deadline);
        }
    }

    private static void createFile() throws IOException {
        File file = new File(DEADLINE_FILEPATH);
        if (file.createNewFile()) {
            System.out.println(Messages.MESSAGE_FILE_CREATED);
        } else {
            System.out.println(Messages.MESSAGE_FILE_LOADED);
        }

    }

    /**
     * Checks if the data file exists. Creates it if it does not.
     * Then it will read the data stored in the data file and add
     * data to task list accordingly.
     * @param deadlineList the deadlinelist.
     */
    public static void loadStorage(DeadlineList deadlineList){
        try {
            createFile();
            readFile(deadlineList);
        } catch (IOException e) {
            System.out.println(Messages.MESSAGE_FILE_MISSING);
        }
    }
}
