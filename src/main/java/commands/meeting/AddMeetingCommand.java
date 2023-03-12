package commands.meeting;

import commands.Command;
import common.Messages;
import dinerdirector.DinerDirector;
import manager.Meeting;
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
        DinerDirector.meetings[DinerDirector.indexOfMeetings]=new Meeting(this.time,this.issue);
        ui.printMessage(issue+" : "+time);
        DinerDirector.indexOfMeetings+=1;
    }
}
