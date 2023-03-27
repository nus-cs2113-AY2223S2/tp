package commands.staff;

import commands.Command;
import manager.StaffManager;
import ui.TextUi;

public class DeleteStaffCommand extends Command {
    public static final String COMMAND_WORD = "delete_staff";
    private String staffName;

    public DeleteStaffCommand(String staffName) {
        this.staffName = staffName;
    }

    @Override
    public void execute(TextUi ui) {
        StaffManager.deleteStaffByName(this.staffName, ui);
        ui.printMessage(staffName + " removed");

    }

    @Override
    public boolean isExit() {
        return false;
    }


}
