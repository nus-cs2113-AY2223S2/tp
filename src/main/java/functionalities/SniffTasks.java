package functionalities;

import exception.SniffException;

import java.util.ArrayList;
import java.util.Objects;

public class SniffTasks {

    private static final ArrayList<Appointment> APPOINTMENTS = new ArrayList<>();

    public void addConsultation(String uid, Animal animal, Owner owner,
                                String date, String time) throws SniffException {
        try {
            APPOINTMENTS.add(new Consultation(uid, animal, owner, date, time));
        } catch (StringIndexOutOfBoundsException e){
            throw new SniffException("Invalid consultation description !!");
        }
    }
    public void addVaccination(String uid, Animal animal, Owner owner,
                               String date, String time, String vaccine) throws SniffException {
        try {
            APPOINTMENTS.add(new Vaccination(uid, animal, owner, date, time, vaccine));
        } catch (StringIndexOutOfBoundsException e){
            throw new SniffException("Invalid vaccination description !!");
        }
    }
    public void addSurgery(String uid, Animal animal, Owner owner,
                           String priority, String startDate, String startTime,
                           String endDate, String endTime) throws SniffException {
        try {
            APPOINTMENTS.add(new Surgery(uid, animal, owner, priority, startDate, startTime, endDate, endTime));
        } catch (StringIndexOutOfBoundsException e){
            throw new SniffException("Invalid surgery description !!");
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

    public void findAppointment(String uId) throws SniffException{
        int counter = 1;
        for (Appointment appointment : APPOINTMENTS) {
            assert appointment.uid != null;
            if (uId.equalsIgnoreCase(appointment.uid)) {
                Ui.formatPrintList(counter, appointment.toString());
                counter++;
            }
        }
        if (uId.equals("")) {
            throw new SniffException(" No appointment ID provided!");
        }
        if (!uId.matches("\\d+")) {
            throw new SniffException(" Appointment ID must consist of integers!");
        }
        if (counter == 1) {
            Ui.showUserMessage(" There are no appointments with this ID!");
        }
    }

    public void findAnimal(String animal) {
        int counter = 1;
        for (Appointment appointment : APPOINTMENTS) {
            assert appointment.animal.type != null;
            if (animal.equalsIgnoreCase(appointment.animal.type)) {
                Ui.formatPrintList(counter, appointment.toString());
                counter++;
            }
        }
        if (counter == 1) {
            Ui.showUserMessage(" There are no appointments for this animal type!");
        }
    }

    public void findType(String type) {
        int counter = 1;
        for (Appointment appointment : APPOINTMENTS) {
            assert appointment != null;
            if (type.equalsIgnoreCase(String.valueOf(appointment))) {
                Ui.formatPrintList(counter, appointment.toString());
                counter++;
            }
        }
        if (counter == 1) {
            Ui.showUserMessage(" There are no appointments of this type!");
        }
    }
}
