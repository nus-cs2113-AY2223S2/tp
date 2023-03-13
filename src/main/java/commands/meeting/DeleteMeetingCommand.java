package commands.meeting;

import commands.Command;
import manager.MeetingManager;
import ui.TextUi;

public class DeleteMeetingCommand extends Command {
    public static final String COMMAND_WORD = "delete_meeting";
    public static String issue;

    public DeleteMeetingCommand(String issue) {
        this.issue = issue.substring(2);
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public void execute(TextUi ui) {
        boolean hasDelete=MeetingManager.deleteMeeting(issue);
        if(hasDelete){
            ui.printMessage(issue+" deleted");
        } else{
            ui.printMessage("Sorry! There's no such meeting");
        }
    }
}
