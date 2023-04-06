package commands.meeting;

import commands.Command;
import common.Messages;
import entity.Meeting;
import manager.MeetingManager;
import ui.TextUi;


public class AddMeetingCommand extends Command {
    public static final String COMMAND_WORD = "add_meeting";
    public static String time;
    public static String issue;

    public AddMeetingCommand(String time, String issue) {
        this.time = time;
        this.issue = issue;
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public void execute(TextUi ui) {
        ui.printMessage(Messages.MESSAGE_MEETING_ADDED);
        Meeting meeting = new Meeting(this.issue, this.time);
        MeetingManager.addMeeting(meeting, ui);
    }
}
