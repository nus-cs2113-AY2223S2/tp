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
     * Parser to add Consultation
     *
     * @param content the consultation type task to add to the Appointment task list
     * @throws SniffException when incorrect date and time format is stored
     * */
    public static void addConsult(String content) throws SniffException {
        try {
            String[] Consult = content.split(" \\| ");
            uID = Consult[0];
            date = LocalDate.parse(Consult[1]);
            time = LocalTime.parse(Consult[2]);
            animal = new Animal(Consult[4], Consult[3]);
            owner = new Owner(Consult[5], Consult[6]);
        } catch (DateTimeException e) {
            throw new SniffException(" Incorrect Date and Time Format!");
        }
        SniffTasks.addAppointmentUID(uID);
        Appointment consultation = new Consultation(uID, animal, owner, date, time);
        consultation.setIsDone(true);
        SniffTasks.addAppointment(consultation);
    }

    /**
     * Parser to add Vaccination
     *
     * @param content the vaccination type task to add to the Appointment task list
     * @throws SniffException when incorrect date and time format is stored
     * */
    public static void addVaccine(String content) throws SniffException {
        try {
            String[] Vaccine = content.split(" \\| ");
            uID = Vaccine[0];
            date = LocalDate.parse(Vaccine[2]);
            time = LocalTime.parse(Vaccine[3]);
            animal = new Animal(Vaccine[5], Vaccine[4]);
            owner = new Owner(Vaccine[6], Vaccine[7]);
            vaccine = Vaccine[1];
        } catch (DateTimeException e) {
            throw new SniffException(" Incorrect Date and Time Format!");
        }
        SniffTasks.addAppointmentUID(uID);
        Appointment vaccination = new Vaccination(uID, animal, owner, date, time, vaccine);
        vaccination.setIsDone(true);
        SniffTasks.addAppointment(vaccination);
    }

    /**
     * Parser to add Surgery
     *
     * @param content the surgery type task to add to the Appointment task list
     * @throws SniffException when incorrect date and time format is stored
     * */
    public static void addSurgery(String content) throws SniffException {
        try {
            String[] Surgery = content.split(" \\| ");
            uID = Surgery[0];
            startDate = LocalDate.parse(Surgery[6]);
            endDate = LocalDate.parse(Surgery[8]);
            startTime = LocalTime.parse(Surgery[7]);
            endTime = LocalTime.parse(Surgery[9]);
            animal = new Animal(Surgery[3], Surgery[2]);
            owner = new Owner(Surgery[4], Surgery[5]);
            priority = Surgery[1];
        } catch (DateTimeException e) {
            throw new SniffException(" Incorrect Date and Time Format!");
        }
        SniffTasks.addAppointmentUID(uID);
        Appointment surgery = new Surgery(uID, animal, owner, priority, startDate, startTime, endDate, endTime);
        surgery.setIsDone(true);
        SniffTasks.addAppointment(surgery);
    }
}
