package commands.meeting;
import commands.Command;
import manager.MeetingManager;
import ui.TextUi;
public class FindMeetingCommand extends Command{
    public static final String COMMAND_WORD = "find_meeting";
    private String keyword;
    public FindMeetingCommand(String keyword){
        this.keyword=keyword;
    }
    @Override
    public boolean isExit(){
        return false;
    }
    @Override
    public void execute(TextUi ui){
        MeetingManager.findMeeting(keyword,ui);
    }
}
