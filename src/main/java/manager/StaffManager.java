package manager;

import common.Messages;
import entity.Staff;
import exceptions.DinerDirectorException;
import ui.TextUi;
import utils.StaffStorage;

import java.io.IOException;
import java.util.ArrayList;

public class StaffManager {
    private static ArrayList<Staff> staffs = new ArrayList<>();
    public StaffManager(ArrayList<Staff> staffs) {
        StaffManager.staffs = staffs;
    }

    /**
     * Add a staff to the staffs array list inside of staff manager.
     * @param staff Staff to be added.
     * @param ui Ui object in if there is anything to be printed.
     */
    public static void addStaff(Staff staff, TextUi ui) {
        try {
            for(Staff currStaff: staffs) {
                if (currStaff.getName().equals(staff.getName())) {
                    throw new DinerDirectorException(Messages.ERROR_STAFF_ADD_ALREADY_EXISTS);
                }
            }
            staffs.add(staff);
            ui.printMessage(staff + " added!");
            StaffStorage staffStorage = new StaffStorage();
            staffStorage.writeToStaffFile(staffs);
        } catch (IOException e) {
            ui.printMessage(String.format(Messages.ERROR_STORAGE_INVALID_WRITE_LINE, staff));
        } catch (DinerDirectorException e) {
            ui.printMessage(e.getMessage());
        }
    }

    /**
     * Format all staff into readable list of staffs and display it
     * @param ui Ui object in if there is anything to be printed.
     */
    public static void viewStaff(TextUi ui) {
        String staffsString = "";
        int counter = 1;
        for (Staff staff : staffs) {
            staffsString += (counter++) + ". " + staff.toString() + System.lineSeparator();
        }
        ui.printMessage(staffsString);
    }

    /**
     * Delete a staff of a certain index.
     * @param staffIndex Staff index to be deleted.
     * @param ui Ui object in if there is anything to be printed.
     */
    public static void deleteStaff(int staffIndex, TextUi ui) {
        try {
            Staff deletedStaff = staffs.get(staffIndex);
            staffs.remove(staffIndex);
            ui.printMessage(deletedStaff + " removed!");
            StaffStorage staffStorage = new StaffStorage();
            staffStorage.writeToStaffFile(staffs);
        } catch (IOException e) {
            ui.printMessage(Messages.ERROR_STORAGE_DELETE_FAILED);
        }
    }

    /**
     * Find a staff based on its name.
     * @param name The name of the staff to be found.
     * @param ui Ui object in if there is anything to be printed.
     */
    public static void findStaff(String name, TextUi ui) {
        ArrayList<Staff> staffFound = new ArrayList<>();
        for(Staff m:staffs){
            if(m.getName().contains(name)){
                staffFound.add(m);
            }
        }
        if(staffFound.isEmpty()){
            ui.printMessage(Messages.MESSAGE_STAFF_NOT_FOUND);
        } else {
            ui.printMessage(Messages.MESSAGE_STAFF_FOUND);
            for(Staff n:staffFound){
                ui.printMessage(n.toString());
            }
        }
    }

    /**
     * Get all staffs.
     * @return An Array List of Staffs object.
     */
    public static ArrayList<Staff> getStaffs() {
        return staffs;
    }
}
