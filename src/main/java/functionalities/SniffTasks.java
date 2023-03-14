package functionalities;

import exception.SniffException;

import java.util.ArrayList;

public class SniffTasks {

    private static final ArrayList<Appointment> APPOINTMENTS = new ArrayList<>();
    private static int appointmentCount = 0;

    public void addAppointment(String uid, String type, String animal, String name, String date) throws SniffException {
        try {
            APPOINTMENTS.add(new Appointment(uid, type, animal, name, date));
            appointmentCount++;
        }catch (StringIndexOutOfBoundsException e){
            throw new SniffException("Invalid add description !!");
        }
    }

    public void removeAppointment(int appointmentNumber) throws SniffException {
        try {
            APPOINTMENTS.remove(appointmentNumber);
            appointmentCount--;
        } catch (IndexOutOfBoundsException e) {
            throw new SniffException(" The remove command description is invalid!");
        }
    }

    public Appointment getAppointment(int appointmentNumber) {
        return APPOINTMENTS.get(appointmentNumber);
    }

    /**
     * Lists out all the appointment currently in the appointment list.
     */
    public void listAppointments() {
        if (appointmentCount == 0) {
            Ui.printEmptyListMessage();
        }
        for (int count = 0; count < appointmentCount; count++) {
            Appointment appointment = getAppointment(count);
            String type = appointment.getType();
            String animal = appointment.getAnimal();
            String name = appointment.getName();
            String date = appointment.getDate();
            Ui.formatPrintList(Integer.toString(count + 1), type, animal, name, date);
            assert count <= appointmentCount : "Counter should be less than total appointments.";
        }
    }

    public void viewAppointment(int uId) {
        for (Appointment appointment : APPOINTMENTS) {
            assert appointment.uid != null;
            if (uId == Integer.parseInt(appointment.uid)) {
                String type = appointment.getType();
                String animal = appointment.getAnimal();
                String name = appointment.getName();
                String date = appointment.getDate();
                Ui.formatPrintList(Integer.toString(1), type, animal, name, date);
            }
        }
    }
}
