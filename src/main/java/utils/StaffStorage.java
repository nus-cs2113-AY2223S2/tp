package utils;

import common.Messages;
import entity.Staff;
import exceptions.DinerDirectorException;
import manager.StaffManager;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class StaffStorage {
    public static final String FILENAME_STAFF = "staff_list.txt";
    private static final String FILEPATH_STAFF_LIST = Storage.FILE_DIRECTORY + "/" + FILENAME_STAFF;

    /**
     * Reads and Loads data from a file if it exists.
     *
     * @throws FileNotFoundException If file is not found, throw an exception. But file will be created if not found.
     */
    public void readAndLoadFromStaffFile() throws FileNotFoundException {
        File file = new File(FILEPATH_STAFF_LIST);
        Scanner in = new Scanner(file);
        ArrayList<Staff> listOfStaffs = new ArrayList<>();
        Staff staff;

        while (in.hasNext()) {
            String text = in.nextLine();
            String[] components = text.split("~\\|~");
            try {
                if (components.length != 4) {
                    throw new DinerDirectorException(Messages.ERROR_STORAGE_INVALID_READ_LINE);
                }
                String name = components[0].trim();
                String workingDay = components[1].trim();
                String dateOfBirth = components[2].trim();
                String phoneNumber = components[3].trim();

                if (!name.matches("[\\w\\s]+")) {
                    throw new DinerDirectorException("Name does not follow the format");
                }

                if (!workingDay.matches("[\\w\\s]+")) {
                    throw new DinerDirectorException("Working day does not follow the format");
                }

                if (!dateOfBirth.matches("(\\d{4})-(0[1-9]|1[0-2])-(0[1-9]|[12][0-9]|3[01])")) {
                    throw new DinerDirectorException("Date of birth does not follow the format");
                }
                if (!phoneNumber.matches("[\\d\\s]+")) {
                    throw new DinerDirectorException("Phone number does not follow the format");
                }

                staff = new Staff(name, workingDay, dateOfBirth, phoneNumber);
                listOfStaffs.add(staff);

            } catch (DinerDirectorException e) {
                System.out.println(String.format(Messages.ERROR_STORAGE_INVALID_READ_LINE, text));
            }
        }

        new StaffManager(listOfStaffs);
    }

    /**
     * Writes the user tasks into a file.
     *
     * @param listOfStaffs An arraylist storing the list of things the user created.
     * @throws IOException Some IO Exception has occured.
     */
    public void writeToStaffFile(ArrayList<Staff> listOfStaffs) throws IOException {
        FileWriter filewriter = new FileWriter(FILEPATH_STAFF_LIST);
        for (Staff staff : listOfStaffs) {
            filewriter.write(staff.savableString() + System.lineSeparator());
        }
        filewriter.close();
    }
}
