package utils;

import common.Messages;
import entity.Meeting;
import exceptions.DinerDirectorException;
import manager.MeetingManager;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class MeetingStorage {
    public static final String FILENAME_MEETING = "meeting_list.txt";
    private static final String FILEPATH_MEETING_LIST = Storage.FILE_DIRECTORY + "/" + FILENAME_MEETING;

    /**
     * Reads and Loads data from a file if it exists.
     *
     * @throws FileNotFoundException If file is not found, throw an exception. But file will be created if not found.
     */
    public void readAndLoadFromMeetingFile() throws FileNotFoundException {
        File file = new File(FILEPATH_MEETING_LIST);
        Scanner in = new Scanner(file);
        ArrayList<Meeting> listOfMeetings = new ArrayList<>();
        Meeting meeting;

        while (in.hasNext()) {
            String text = in.nextLine();
            String[] components = text.split("~\\|~");
            try {
                if (components.length != 2 || components[0].equals("") || components[1].equals("")) {
                    throw new DinerDirectorException(Messages.ERROR_STORAGE_INVALID_READ_LINE);
                } else {
                    meeting = new Meeting(components[0], components[1]);
                    listOfMeetings.add(meeting);
                }
            } catch (DinerDirectorException e) {
                System.out.println(String.format(Messages.ERROR_STORAGE_INVALID_READ_LINE, text));
            }
        }

        new MeetingManager(listOfMeetings);
    }

    /**
     * Writes the user tasks into a file.
     *
     * @param listOfMeetings An arraylist storing the list of things the user created.
     * @throws IOException Some IO Exception has occured.
     */
    public void writeToDeadlineFile(ArrayList<Meeting> listOfMeetings) throws IOException {
        FileWriter filewriter = new FileWriter(FILEPATH_MEETING_LIST);
        for (Meeting meeting : listOfMeetings) {
            filewriter.write(meeting.savableString() + System.lineSeparator());
        }
        filewriter.close();
    }
}
