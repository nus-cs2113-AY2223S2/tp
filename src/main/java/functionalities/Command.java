package functionalities;
import exception.SniffException;

public class Command {
    private final String command;
    private final int apptNum;
    public Command(String userCommand, int apptNum) {
        this.command = userCommand;
        this.apptNum = apptNum;
        //add variables that are read from parser class and used for adding, removing, and viewing appointments.
    }

    //Implement the features
    public void executeSniffCommands(SniffTasks tasks, Ui ui) throws SniffException {
        if (command.equalsIgnoreCase("add")) {
            tasks.addAppointment();
        } else if (command.equalsIgnoreCase("remove")) {
            tasks.removeAppointment(apptNum);
        } else if (command.equalsIgnoreCase("list")) {
            tasks.listAppointments();
        } else if (command.equalsIgnoreCase("view")) {
            tasks.viewAppointment();
        } else if (command.equalsIgnoreCase("bye")) {
            ui.showUserMessage(" Bye. Hope to see you again soon!");
        } else {
            throw new SniffException(" Not a recognized Sniff Command!");
        }
    }

    public boolean isExit() {
        if (command == "bye") {
            return true;
        }
        return false;
    }
}
