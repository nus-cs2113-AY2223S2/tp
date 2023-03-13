package commands.meeting;

import commands.Command;
import common.Messages;
import entity.Meeting;
import manager.MeetingManager;
import ui.TextUi;


public class AddMeetingCommand extends Command {
    public static final String COMMAND_WORD="add_meeting";
    public static String time;
    public static String issue;
    public AddMeetingCommand(String time,String issue){
        this.time=time.substring(2);
        this.issue=issue.substring(2);
    }
    @Override
    public boolean isExit() {
        return false;
    }
    @Override
    public void execute(TextUi ui) {
        ui.printMessage(Messages.MESSAGE_ADD_MEETING);
        Meeting m=new Meeting(this.time,this.issue);
        MeetingManager.addMeeting(m);
        ui.printMessage(issue+" at "+time);
    }
}
