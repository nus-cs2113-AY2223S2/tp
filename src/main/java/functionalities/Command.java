package functionalities;
import exception.SniffException;

public class Command {
    private final String command;
    private final int appointmentNum;
    private final int uId;
    private final String uid;
    private final String type;
    private final String animal;
    private final String name;
    private final String date;


    public Command(String userCommand, int appointmentNum, int userId,String uid, String type, String animal,
                   String name, String date) {
        this.command = userCommand;
        this.appointmentNum = appointmentNum;
        this.uId = userId;
        this.uid = uid;
        this.type = type;
        this.animal = animal;
        this.name = name;
        this.date = date;
    }

    public void executeSniffCommands(SniffTasks tasks, Ui ui) throws SniffException {
        if (command.equalsIgnoreCase("add")) {
            tasks.addAppointment(uid, type, animal, name, date);
            ui.showUserMessage("Task added successfully");
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
