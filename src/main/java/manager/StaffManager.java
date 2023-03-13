package manager;
import entity.Staff;

import java.util.ArrayList;

public class StaffManager {
    private ArrayList<Staff> staffs;

    public StaffManager(){
        staffs = new ArrayList<Staff>();
    }

    public void addStaff(Staff staff) {
        staffs.add(staff);
    }

    public void viewStaffs(){
        for(Staff staff: staffs){
            System.out.println(staff);
        }
    }

    public void deleteStaffByName(String name)  {
        int staffIndex = findStaffIndexByName(name);
        if (staffIndex != -1){
            staffs.remove(staffIndex);
        }
    }

    public int findStaffIndexByName (String name) {
        for(int i = 0; i < staffs.size(); i++){
            Staff staff = staffs.get(i);
            if (name.equals(staff.getName())){
                return i;
            }
        }
        return -1;
    }
}
