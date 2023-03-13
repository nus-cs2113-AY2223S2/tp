package commands;

import manager.StaffManager;
import ui.TextUi;

public class DeleteStaffCommand extends Command{
    private String[] userInputSplit;
    public DeleteStaffCommand (String[] userInputString) {
        this.userInputSplit = userInputSplit;
    }

    @Override
    public void execute (TextUi ui){
        String staffName = userInputSplit[1];

        StaffManager.deleteStaffByName(staffName);

        ui.printMessage(staffName + " removed");

    }

    @Override
    public boolean isExit (){
        return false;
    }


}
