package commands;

import entity.Staff;
import manager.StaffManager;
import ui.TextUi;

public class AddStaffCommand extends Command{
    private String[] userInputSplit;
    public AddStaffCommand(String[] userInputSplit){
        this.userInputSplit = userInputSplit;
    }

    @Override
    public void execute (TextUi ui){
        String staffName = userInputSplit[1];
        String staffworkingDay = userInputSplit[2];
        String staffPhoneNumber = userInputSplit[3];
        String staffDateOfBirth = userInputSplit[4];

        Staff staff = new Staff(staffName, staffworkingDay, staffPhoneNumber, staffPhoneNumber);
        StaffManager.addStaff(staff);
        ui.printMessage(staff + " added!");
    }

    @Override
    public boolean isExit (){
        return false;
    }
}
