package commands.staff;

import commands.Command;
import manager.StaffManager;
import ui.TextUi;

public class ViewStaffCommand extends Command {
    public static final String COMMAND_WORD = "view_staff";

    public ViewStaffCommand() {
    }

    @Override
    public void execute(TextUi ui) {
        ui.printMessage(StaffManager.getStaffsString());
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
