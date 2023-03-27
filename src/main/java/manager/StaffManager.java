package manager;

import common.Messages;
import entity.Staff;
import ui.TextUi;
import utils.StaffStorage;

import java.io.IOException;
import java.util.ArrayList;

public class StaffManager {
    private static ArrayList<Staff> staffs = new ArrayList<>();

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
        int counter = 1;
        for (Staff staff : staffs) {
            staffsString += (counter++) + ". " + staff.toString() + System.lineSeparator();
        }
        return staffsString;
    }

    public static void deleteStaff(int staffIndex, TextUi ui) {
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

    public static void findStaff(String name,TextUi ui) {
        ArrayList<Staff> staffFound=new ArrayList<>();
        for(Staff m:staffs){
            if( m.getName().contains(name)){
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
    public static ArrayList<Staff> getStaffs() {
        return staffs;
    }
}
