package commands.meeting;

import commands.Command;
import common.Messages;
import dinerdirector.DinerDirector;
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
        boolean hasMeeting = false;
        for (int i = 0; i < DinerDirector.indexOfMeetings; i++) {
            if (DinerDirector.meetings[i].getIssue().equals(issue)) {
                hasMeeting = true;
                ui.printMessage(Messages.MESSAGE_DELETE_MEETING);
                ui.printMessage(DinerDirector.meetings[i].getIssue() + " at " + DinerDirector.meetings[i].getTime());
                for (int j = i; j < DinerDirector.indexOfMeetings - 1; j++) {
                    DinerDirector.meetings[j] = DinerDirector.meetings[j + 1];
                }
                DinerDirector.indexOfMeetings--;
            }

        }
        if (!hasMeeting) {
            ui.printMessage("Sorry! There's no such meeting.");
        }
    }
}
