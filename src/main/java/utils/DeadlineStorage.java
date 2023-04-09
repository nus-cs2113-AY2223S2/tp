package utils;

import common.Messages;
import entity.Deadline;
import exceptions.DinerDirectorException;
import manager.DeadlineManager;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class DeadlineStorage {
    public static final String FILENAME_DEADLINE = "deadline_list.txt";
    private static final String FILEPATH_DEADLINE_LIST = Storage.FILE_DIRECTORY + "/" + FILENAME_DEADLINE;

    /**
     * Reads and Loads data from a file if it exists.
     *
     * @throws FileNotFoundException If file is not found, throw an exception. But file will be created if not found.
     */
    public void readAndLoadFromDeadlineFile() throws FileNotFoundException {
        File file = new File(FILEPATH_DEADLINE_LIST);
        Scanner in = new Scanner(file);
        ArrayList<Deadline> listOfDeadlines = new ArrayList<>();
        Deadline deadline;

        while (in.hasNext()) {
            String text = in.nextLine();
            String[] components = text.split("~\\|~");
            try {
                if (components.length != 2 || components[0].equals("") || components[1].equals("")) {
                    throw new DinerDirectorException(Messages.ERROR_STORAGE_INVALID_READ_LINE);
                } else {
                    deadline = new Deadline(components[0], components[1]);
                    listOfDeadlines.add(deadline);
                }
            } catch (DinerDirectorException e) {
                System.out.println(String.format(Messages.ERROR_STORAGE_INVALID_READ_LINE, text));
            }
        }

        new DeadlineManager(listOfDeadlines);
    }

    /**
     * Writes the user tasks into a file.
     *
     * @param listOfDeadlines An arraylist storing the list of things the user created.
     * @throws IOException Some IO Exception has occured.
     */
    public void writeToDeadlineFile(ArrayList<Deadline> listOfDeadlines) throws IOException {
        FileWriter filewriter = new FileWriter(FILEPATH_DEADLINE_LIST);
        for (Deadline deadline : listOfDeadlines) {
            filewriter.write(deadline.savableString() + System.lineSeparator());
        }
        filewriter.close();
    }
}
