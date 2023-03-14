package manager;

import entity.Staff;

import java.util.ArrayList;

public class StaffManager {
    private static ArrayList<Staff> staffs = new ArrayList<Staff>();

    public static void addStaff(Staff staff) {
        staffs.add(staff);
    }

    public static String getStaffsString() {
        String staffsString = "";
        for (Staff staff : staffs) {
            staffsString += staff.toString() + System.lineSeparator();
        }
        return staffsString;
    }

    public static void deleteStaffByName(String name) {
        int staffIndex = findStaffIndexByName(name);
        if (staffIndex != -1) {
            staffs.remove(staffIndex);
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
