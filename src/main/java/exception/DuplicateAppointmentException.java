package exception;

import functionalities.appointments.Appointment;

public class DuplicateAppointmentException extends Exception {
    public DuplicateAppointmentException(String e, Appointment appointment) {
        System.out.println(e);
        System.out.println(appointment);
    }
}
