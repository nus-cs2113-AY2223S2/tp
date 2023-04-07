package functionalities;

import functionalities.appointments.Appointment;
import functionalities.appointments.Consultation;
import functionalities.appointments.Surgery;
import functionalities.appointments.Vaccination;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Comparator;

/**
 * This class implements the compare method of the Comparator class, using
 * date and time of the appointments we are comparing.
 */
public class DateTimeComparator implements Comparator<Appointment> {
    /**
     * Compares between two appointments, and returns a set of values depending on
     * which appointment precedes the other.
     *
     * @param appt1 the first appointment to be compared.
     * @param appt2 the second appointment to be compared.
     * @return -1 if the first appointment precedes the second appointment.
     *         0 if the two appointments have the same date and time.
     *         1 if the second appointment precedes the first appointment.
     */
    @Override
    public int compare(Appointment appt1, Appointment appt2) {
        LocalDate date1 = LocalDate.now();
        LocalDate date2 = LocalDate.now();
        LocalTime time1 = LocalTime.now();
        LocalTime time2 = LocalTime.now();
        char appt1Type = appt1.uid.charAt(0); //Appointment UIDs start with the denoted appointment type
        char appt2Type = appt2.uid.charAt(0);
        if (appt1Type == 'C') {
            Consultation tempC = (Consultation) appt1;
            date1 = tempC.getDate();
            time1 = tempC.getTime();
        } else if (appt1Type == 'V') {
            Vaccination tempV = (Vaccination) appt1;
            date1 = tempV.getDate();
            time1 = tempV.getTime();
        } else if (appt1Type == 'S') {
            Surgery tempS = (Surgery) appt1;
            date1 = tempS.getStartDate();
            time1 = tempS.getStartTime();
        }
        if (appt2Type == 'C') {
            Consultation tempC = (Consultation) appt2;
            date2 = tempC.getDate();
            time2 = tempC.getTime();
        } else if (appt2Type == 'V') {
            Vaccination tempV = (Vaccination) appt2;
            date2 = tempV.getDate();
            time2 = tempV.getTime();
        } else if (appt2Type == 'S') {
            Surgery tempS = (Surgery) appt2;
            date2 = tempS.getStartDate();
            time2 = tempS.getStartTime();
        }
        if (date1.isEqual(date2) && time1.equals(time2)) {
            return 0;
        }
        if (date1.isEqual(date2)) {
            if (time1.isBefore(time2)) {
                return -1;
            }
            return 1;
        } else if (date1.isBefore(date2)) {
            return -1;
        }
        return 1;
    }
}
