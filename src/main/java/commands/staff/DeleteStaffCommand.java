package commands.staff;

import commands.Command;
import entity.Staff;
import manager.StaffManager;
import ui.TextUi;

public class DeleteStaffCommand extends Command {
    public static final String COMMAND_WORD = "delete_staff";
    private final int staffIndex;

    /**
     * Constructor of Delete Staff Command
     * @param staffIndex The staff index to be deleted.
     */
    public DeleteStaffCommand(int staffIndex) {
        this.staffIndex = staffIndex;
    }

    /**
     * Execute the command of deleting staff.
     * @param ui Ui object in if there is anything to be printed.
     */
    @Override
    public void execute(TextUi ui) {
        StaffManager.deleteStaff(this.staffIndex, ui);
    }
    /**
     * Indicates whether the program should exit or not
     * @return Boolean to indicate whether exist is true or not
     */
    @Override
    public boolean isExit() {
        return false;
    }


}
