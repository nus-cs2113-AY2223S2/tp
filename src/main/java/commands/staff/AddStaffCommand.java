package commands.staff;

import commands.Command;
import entity.Staff;
import manager.StaffManager;
import ui.TextUi;

public class AddStaffCommand extends Command {
    public static final String COMMAND_WORD = "add_staff";
    private final String name;
    private final String workingDay;
    private final String phoneNumber;
    private final String dateOfBirth;

    /**
     * Constructor of AddStaffCommand that is going to be executed.
     * @param name The name of the Staff to be added.
     * @param workingDay The working day of the Staff to be added.
     * @param dateOfBirth The date of birth of the Staff to be added.
     * @param phoneNumber The phone number of the Staff to be added.
     */
    public AddStaffCommand(String name, String workingDay, String dateOfBirth, String phoneNumber) {
        this.name = name;
        this.workingDay = workingDay;
        this.phoneNumber = phoneNumber;
        this.dateOfBirth = dateOfBirth;
    }

    /**
     * Execute the command of adding staff.
     * @param ui Ui object in if there is anything to be printed.
     */
    @Override
    public void execute(TextUi ui) {
        Staff staff = new Staff(this.name, this.workingDay, this.dateOfBirth, this.phoneNumber);
        StaffManager.addStaff(staff, ui);
    }

    /**
     * Indicates whether the program should exit or not
     * @return Boolean to indicate whether exist is true or not
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
