package commands.staff;

import commands.Command;
import manager.StaffManager;
import ui.TextUi;

public class ViewStaffCommand extends Command {
    public static final String COMMAND_WORD = "view_staff";

    /**
     * Constructor of ViewStaffCommand
     *
     */
    public ViewStaffCommand() {
    }

    /**
     * Execute the command of viewing all staffs.
     *
     * @param ui Ui object in if there is anything to be printed.
     */
    @Override
    public void execute(TextUi ui) {
        StaffManager.viewStaff(ui);
    }

    /**
     * Indicates whether the program should exit or not
     *
     * @return Boolean to indicate whether exist is true or not
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
