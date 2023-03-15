package functionalities;

import exception.SniffException;

import java.util.ArrayList;

public class SniffTasks {

    private static final ArrayList<Appointment> APPOINTMENTS = new ArrayList<>();

    public void addAppointment(String uid, String type, String animal, String name, String date) throws SniffException {
        try {
            APPOINTMENTS.add(new Appointment(uid, type, animal, name, date));
        } catch (StringIndexOutOfBoundsException e){
            throw new SniffException("Invalid add description !!");
        }
    }

    public void removeAppointment(int appointmentNumber) throws SniffException {
        try {
            assert appointmentNumber != 0;
            APPOINTMENTS.remove(appointmentNumber - 1);
        } catch (IndexOutOfBoundsException e) {
            throw new SniffException(" The remove command entry is invalid!");
        }
    }

    /**
     * Lists out all the appointment currently in the appointment list.
     */
    public void listAppointments() {
        int counter = 1;
        if (APPOINTMENTS.isEmpty()) {
            Ui.showUserMessage("No entries found!");
        }
        for (Appointment appointment : APPOINTMENTS) {
            Ui.formatPrintList(counter, appointment.toString());
            counter++;
        }
    }

    public void viewAppointment(String uId) {
        int counter = 1;
        for (Appointment appointment : APPOINTMENTS) {
            assert appointment.uid != null;
            if (uId.equalsIgnoreCase(appointment.uid)) {
                Ui.formatPrintList(counter, appointment.toString());
                counter++;
            }
        }
        if (counter == 1) {
            Ui.showUserMessage(" There are no appointments with this ID!");
        }
    }

}
