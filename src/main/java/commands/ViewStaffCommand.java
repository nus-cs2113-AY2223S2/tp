package commands;

import manager.StaffManager;
import ui.TextUi;

public class ViewStaffCommand extends Command {
    public ViewStaffCommand () {}

    @Override
    public void execute (TextUi ui) {
        ui.printMessage(StaffManager.getStaffsString());
    }

    @Override
    public boolean isExit () {
        return false;
    }
}
