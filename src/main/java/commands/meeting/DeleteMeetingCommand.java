package commands.meeting;

import commands.Command;
import manager.MeetingManager;
import ui.TextUi;

public class DeleteMeetingCommand extends Command {
    public static final String COMMAND_WORD = "delete_meeting";
    private int indexToDelete;

    public DeleteMeetingCommand(int index) {
        this.indexToDelete = index;
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public void execute(TextUi ui) {
        MeetingManager.deleteMeeting(indexToDelete, ui);
    }
}
