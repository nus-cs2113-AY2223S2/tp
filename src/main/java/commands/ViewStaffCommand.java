package commands;

import ui.TextUi;

public class ViewStaffCommand extends Command {
    public ViewStaffCommand () {}

    @Override
    public void execute (TextUi ui) {
    }

    @Override
    public boolean isExit () {
        return false;
    }
}
