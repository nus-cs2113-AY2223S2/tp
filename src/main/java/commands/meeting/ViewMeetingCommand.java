package commands.meeting;

import commands.Command;
import common.Messages;
import manager.MeetingManager;
import ui.TextUi;

public class ViewMeetingCommand extends Command {
    public static final String COMMAND_WORD="view_meetings";
    @Override
    public boolean isExit() {
        return false;
    }
    @Override
    public void execute(TextUi ui) {
        ui.printMessage(Messages.MESSAGE_VIEW_MEETINGS);
        ui.printMessage(MeetingManager.printMeetings());
    }
}
