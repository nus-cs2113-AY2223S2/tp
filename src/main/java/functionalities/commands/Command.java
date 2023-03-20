package functionalities;
import exception.SniffException;

public class Command {
    private final String command;
    private final String uid;
    private final String type;
    private final String animal;
    private final String name;
    private final String date;


    public Command(String userCommand, String uId, String type, String animal, String name, String date) {
        this.command = userCommand;
        this.uid = uid;
        this.type = type;
        this.animal = animal;
        this.name = name;
        this.date = date;
    }

    public void executeSniffCommands(SniffTasks tasks) throws SniffException {
        if (command.equalsIgnoreCase("c")) {
            tasks.addConsultation(uid, animal, owner, date, time);
            Ui.showUserMessage("Consultation added successfully");
        } else if (command.equalsIgnoreCase("v")) {
            tasks.addVaccination(uid, animal, owner, date, time););
            Ui.showUserMessage(" Vaccination added successfully");
        } else if (command.equalsIgnoreCase("s")) {
            tasks.addSurgery(uid, animal, owner, date, time););
            Ui.showUserMessage(" Surgery added successfully");
        } else if (command.equalsIgnoreCase("remove")) {
            tasks.removeAppointment(Integer.parseInt(uId));
            Ui.showUserMessage(" Task removed successfully");
        } else if (command.equalsIgnoreCase("list")) {
            tasks.listAppointments();
        } else if (command.equalsIgnoreCase("view")) {
            tasks.viewAppointment(uid);
        } else if (command.equalsIgnoreCase("bye")) {
            Ui.showUserMessage(" Bye. Hope to see you again soon!");
        } else {
            throw new SniffException(" Not a recognized Sniff Command!");
        }
    }

    public boolean isExit() {
        return command.equals("bye");
    }
}
