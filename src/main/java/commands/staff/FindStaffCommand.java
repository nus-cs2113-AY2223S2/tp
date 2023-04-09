package commands.staff;

import commands.Command;
import manager.StaffManager;
import ui.TextUi;

public class FindStaffCommand extends Command {
    public static final String COMMAND_WORD = "find_staff";
    private final String keyword;

    /**
     * Constructor of FindStaffCommand.
     *
     * @param keyword Keyword that represents a substring of Staff's name to be searched.
     */
    public FindStaffCommand(String keyword){
        this.keyword = keyword;
    }

    /**
     * Execute the command of finding staff.
     *
     * @param ui Ui object in if there is anything to be printed.
     */
    @Override
    public void execute(TextUi ui){
        StaffManager.findStaff(keyword,ui);
    }
    /**
     * Indicates whether the program should exit or not.
     *
     * @return Boolean to indicate whether exist is true or not
     */
    @Override
    public boolean isExit(){
        return false;
    }

}
