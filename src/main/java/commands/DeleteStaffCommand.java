package commands;

import manager.StaffManager;
import ui.TextUi;

public class DeleteStaffCommand extends Command {
    private String staffName;

    public DeleteStaffCommand(String staffName) {
        this.staffName = staffName;
    }

    @Override
    public void execute(TextUi ui) {
        StaffManager.deleteStaffByName(this.staffName);
        ui.printMessage(staffName + " removed");

    }

    @Override
    public boolean isExit() {
        return false;
    }


}
