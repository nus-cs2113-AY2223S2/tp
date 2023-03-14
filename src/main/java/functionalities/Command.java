package functionalities;
import exception.SniffException;

public class Command {
    private final String command;
    private final int appointmentNum;
    private final int uId;


    public Command(String userCommand, int appointmentNum, int userId) {
        this.command = userCommand;
        this.appointmentNum = appointmentNum;
        this.uId = userId;
    }

    public void executeSniffCommands(SniffTasks tasks, Ui ui) throws SniffException {
        if (command.equalsIgnoreCase("add")) {
            tasks.addAppointment();
        } else if (command.equalsIgnoreCase("remove")) {
            tasks.removeAppointment(appointmentNum);
        } else if (command.equalsIgnoreCase("list")) {
            tasks.listAppointments();
        } else if (command.equalsIgnoreCase("view")) {
            tasks.viewAppointment(uId);
        } else if (command.equalsIgnoreCase("bye")) {
            ui.showUserMessage(" Bye. Hope to see you again soon!");
        } else {
            throw new SniffException(" Not a recognized Sniff Command!");
        }
    }

    public boolean isExit() {
        return command.equals("bye");
    }
}
