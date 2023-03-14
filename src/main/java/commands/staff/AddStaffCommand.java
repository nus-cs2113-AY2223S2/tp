package commands.staff;

import commands.Command;
import entity.Staff;
import manager.StaffManager;
import ui.TextUi;

public class AddStaffCommand extends Command {
    public static final String COMMAND_WORD = "add_staff";
    private String name;
    private String workingDay;
    private String phoneNumber;
    private String dateOfBirth;
    public AddStaffCommand(String name, String workingDay, String phoneNumber, String dateOfBirth){
        this.name = name;
        this.workingDay = workingDay;
        this.phoneNumber = phoneNumber;
        this.dateOfBirth = dateOfBirth;
    }

    @Override
    public void execute (TextUi ui){
        Staff staff = new Staff(this.name, this.workingDay, this.phoneNumber, this.dateOfBirth);
        StaffManager.addStaff(staff);
        ui.printMessage(staff + " added!");
    }

    @Override
    public boolean isExit (){
        return false;
    }
}
