package commands.staff;

import commands.Command;
import entity.Staff;
import manager.StaffManager;
import ui.TextUi;

public class DeleteStaffCommand extends Command {
    public static final String COMMAND_WORD = "delete_staff";
    private final int staffIndex;

    public DeleteStaffCommand(int staffIndex) {
        this.staffIndex = staffIndex;
    }

    @Override
    public void execute(TextUi ui) {
        Staff deletedStaff = StaffManager.getStaffs().get(staffIndex);
        StaffManager.deleteStaff(this.staffIndex, ui);
        ui.printMessage((staffIndex + 1) + " " + deletedStaff.toString() + " removed");

    }

    @Override
    public boolean isExit() {
        return false;
    }


}
