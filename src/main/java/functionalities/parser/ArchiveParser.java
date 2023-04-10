package functionalities.parser;

import exception.SniffException;
import functionalities.Animal;
import functionalities.Owner;
import functionalities.SniffTasks;
import functionalities.appointments.Appointment;
import functionalities.appointments.Consultation;
import functionalities.appointments.Surgery;
import functionalities.appointments.Vaccination;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalTime;

/**
 * Parser Class for Archive Functionality
 * */
public class ArchiveParser {

    static String uID;
    static String vaccine;
    static String priority;
    static Animal animal;
    static Owner owner;
    static LocalDate date;
    static LocalTime time;
    static LocalDate startDate;
    static LocalDate endDate;
    static LocalTime startTime;
    static LocalTime endTime;

    /**
     * Parser to add Consultation Type Appointments
     *
     * @param content the consultation type task to add to the Appointment task list
     * @throws SniffException when incorrect date and time format is stored
     * */
    public static void addConsult(String content) throws SniffException {
        try {
            String[] consultationAppointment = content.split(" \\| ");
            uID = consultationAppointment[0];
            assert uID != null: "uID cannot be empty";
            date = LocalDate.parse(consultationAppointment[1]);
            time = LocalTime.parse(consultationAppointment[2]);
            animal = new Animal(consultationAppointment[4], consultationAppointment[3]);
            owner = new Owner(consultationAppointment[5], consultationAppointment[6]);
        } catch (DateTimeException e) {
            throw new SniffException(" Incorrect Date and Time Format!");
        }
        SniffTasks.addAppointmentUID(uID);
        Appointment consultation = new Consultation(uID, animal, owner, date, time);
        consultation.setIsDone(true);
        SniffTasks.addAppointment(consultation);
    }

    /**
     * Parser to add Vaccination Type Appointments
     *
     * @param content the vaccination type task to add to the Appointment task list
     * @throws SniffException when incorrect date and time format is stored
     * */
    public static void addVaccine(String content) throws SniffException {
        try {
            String[] vaccinationAppointment = content.split(" \\| ");
            uID = vaccinationAppointment[0];
            assert uID != null: "uID cannot be empty";
            date = LocalDate.parse(vaccinationAppointment[2]);
            time = LocalTime.parse(vaccinationAppointment[3]);
            animal = new Animal(vaccinationAppointment[5], vaccinationAppointment[4]);
            owner = new Owner(vaccinationAppointment[6], vaccinationAppointment[7]);
            vaccine = vaccinationAppointment[1];
        } catch (DateTimeException e) {
            throw new SniffException(" Incorrect Date and Time Format!");
        }
        SniffTasks.addAppointmentUID(uID);
        Appointment vaccination = new Vaccination(uID, animal, owner, date, time, vaccine);
        vaccination.setIsDone(true);
        SniffTasks.addAppointment(vaccination);
    }

    /**
     * Parser to add Surgery Type Appointments
     *
     * @param content the surgery type task to add to the Appointment task list
     * @throws SniffException when incorrect date and time format is stored
     * */
    public static void addSurgery(String content) throws SniffException {
        try {
            String[] surgeryAppointment = content.split(" \\| ");
            uID = surgeryAppointment[0];
            assert uID != null: "uID cannot be empty";
            startDate = LocalDate.parse(surgeryAppointment[6]);
            endDate = LocalDate.parse(surgeryAppointment[8]);
            startTime = LocalTime.parse(surgeryAppointment[7]);
            endTime = LocalTime.parse(surgeryAppointment[9]);
            animal = new Animal(surgeryAppointment[3], surgeryAppointment[2]);
            owner = new Owner(surgeryAppointment[4], surgeryAppointment[5]);
            priority = surgeryAppointment[1];
        } catch (DateTimeException e) {
            throw new SniffException(" Incorrect Date and Time Format!");
        }
        SniffTasks.addAppointmentUID(uID);
        Appointment surgery = new Surgery(uID, animal, owner, priority, startDate, startTime, endDate, endTime);
        surgery.setIsDone(true);
        SniffTasks.addAppointment(surgery);
    }
}
