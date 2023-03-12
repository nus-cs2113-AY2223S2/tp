package commands.meeting;

import commands.Command;
import common.Messages;
import dinerdirector.DinerDirector;
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
        for(int i=0;i< DinerDirector.indexOfMeetings;i++){
            ui.printMessage((i+1)+". "+DinerDirector.meetings[i].getIssue()+
                    " at "+DinerDirector.meetings[i].getTime());
        }
    }
}
