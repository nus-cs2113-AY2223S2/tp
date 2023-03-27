package commands.staff;

import commands.Command;
import manager.StaffManager;
import ui.TextUi;

public class FindStaffCommand extends Command {
    public static final String COMMAND_WORD = "find_staff";
    private String keyword;
    public FindStaffCommand(String keyword){
        this.keyword=keyword;
    }
    @Override
    public boolean isExit(){
        return false;
    }
    @Override
    public void execute(TextUi ui){
        StaffManager.findStaff(keyword,ui);
    }
}
