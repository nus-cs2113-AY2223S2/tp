package manager;

import common.Messages;
import entity.Staff;
import ui.TextUi;
import utils.StaffStorage;

import java.io.IOException;
import java.util.ArrayList;

public class StaffManager {
    private static ArrayList<Staff> staffs = new ArrayList<Staff>();

    public StaffManager(ArrayList<Staff> staffs) {
        StaffManager.staffs = staffs;
    }

    public static void addStaff(Staff staff, TextUi ui) {
        staffs.add(staff);
        try {
            StaffStorage staffStorage = new StaffStorage();
            staffStorage.writeToStaffFile(staffs);
        } catch (IOException e) {
            ui.printMessage(String.format(Messages.ERROR_STORAGE_INVALID_WRITE_LINE, staff));
        }
    }

    public static String getStaffsString() {
        String staffsString = "";
        for (Staff staff : staffs) {
            staffsString += staff.toString() + System.lineSeparator();
        }
        return staffsString;
    }

    public static void deleteStaffByName(String name, TextUi ui) {
        int staffIndex = findStaffIndexByName(name);
        if (staffIndex != -1) {
            staffs.remove(staffIndex);
        }
        try {
            StaffStorage staffStorage = new StaffStorage();
            staffStorage.writeToStaffFile(staffs);
        } catch (IOException e) {
            ui.printMessage(Messages.ERROR_STORAGE_DELETE_FAILED);
        }
    }

    public static int findStaffIndexByName(String name) {
        for (int i = 0; i < staffs.size(); i++) {
            Staff staff = staffs.get(i);
            if (name.equals(staff.getName())) {
                return i;
            }
        }
        return -1;
    }
}
