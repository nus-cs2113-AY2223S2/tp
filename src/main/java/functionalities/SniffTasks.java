package functionalities;

import exception.SniffException;

import java.util.ArrayList;
import java.util.HashMap;

public class SniffTasks {

    private static final ArrayList<Appointment> APPOINTMENTS = new ArrayList<>();
    private static final HashMap<String, Integer> UIDS = new HashMap<String, Integer>();

    private static int appointmentCount = 0;
    public void addConsultation(Animal animal, Owner owner,
                                String date, String time) throws SniffException {
        try {
            String uid = Uid.uidGenerator("C");
            while (UIDS.containsKey(uid)) { // this loop checks for duplicate appointment ids
                uid = Uid.uidGenerator("C");
            }
            UIDS.put(uid, appointmentCount);
            Appointment newAppointment = new Consultation(uid, animal, owner, date, time);
            APPOINTMENTS.add(newAppointment);
            Ui.printAppointmentAddedMessage(newAppointment);
            appointmentCount++;
        } catch (StringIndexOutOfBoundsException e) {
            throw new SniffException("Invalid consultation description !!");
        }
    }
    public void addVaccination(Animal animal, Owner owner,
                               String date, String time, String vaccine) throws SniffException {
        try {

            String uid = Uid.uidGenerator("V");
            while (UIDS.containsKey(uid)) { // this loop checks for duplicate appointment ids
                uid = Uid.uidGenerator("V");
            }
            Appointment newAppointment = new Vaccination(uid, animal, owner, date, time, vaccine);
            APPOINTMENTS.add(newAppointment);
            UIDS.put(uid, appointmentCount);
            Ui.printAppointmentAddedMessage(newAppointment);
            appointmentCount++;
        } catch (StringIndexOutOfBoundsException e){
            throw new SniffException("Invalid vaccination description !!");
        }
    }
    public void addSurgery(Animal animal, Owner owner,
                           String priority, String startDate, String startTime,
                           String endDate, String endTime) throws SniffException {
        try {
            String uid = Uid.uidGenerator("S");
            while (UIDS.containsKey(uid)) { // this loop checks for duplicate appointment ids
                uid = Uid.uidGenerator("S");
            }
            UIDS.put(uid, appointmentCount);
            Appointment newAppointment = new Surgery(uid, animal, owner, priority, startDate, startTime, endDate, endTime);
            APPOINTMENTS.add(newAppointment);
            Ui.printAppointmentAddedMessage(newAppointment);
            appointmentCount++;
        } catch (StringIndexOutOfBoundsException e){
            throw new SniffException("Invalid surgery description !!");
        }
    }

    public void removeAppointment(String uid) throws SniffException {
        try {
            if (!UIDS.containsKey(uid)) {
                throw new SniffException("There are no appointments with this ID.");
            }
            int index = UIDS.get(uid);
            Appointment temp = APPOINTMENTS.get(index);
            APPOINTMENTS.remove(index);
            UIDS.remove(uid, index);
            Ui.printAppointmentRemovedMessage(temp);
            appointmentCount--;
            assert appointmentCount >= 0 : "Appointment count cannot be less than 0";
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

    public void viewAppointment(String uid) {
        assert uid != null : "Appointment ID should not be null.";
        if (!UIDS.containsKey(uid)) {
            System.out.println("There are no appointments with this ID.");
        }
        int index = UIDS.get(uid);
        System.out.println("Here is the appointment details: ");
        System.out.println(APPOINTMENTS.get(index));
    }

}
