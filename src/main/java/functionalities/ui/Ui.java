package functionalities.ui;

import exception.SniffException;
import functionalities.appointments.Appointment;

import java.util.Scanner;

public class Ui {

    private static final String DOT_THEN_SPACE = ". ";
    private static final String LINE = "______________________________________________________________________";

    public static void showUserMessage(String s) {
        System.out.println(s);
    }

    public static void printFileCreated(boolean b) {
        showLine();
        if (b) {
            System.out.println("\tFile \"SniffAppointments.txt\" created!");
        } else {
            System.out.println("\tFile \"SniffAppointments.txt\" is not created!");
        }
    }

    public static void printArchiveFileCreated(boolean b) {
        showLine();
        if (b) {
            System.out.println(" Archive File created!");
        } else {
            System.out.println(" Archive File not created!");
        }
    }

    public String readUserCommand() {
        Scanner in = new Scanner(System.in);
        return in.nextLine();
    }

    public void showErrorMessage() {
        System.out.println(" Sorry, an error was encountered! Here is the error description:");
        SniffException.showError();
    }

    public void showWelcomeMessage() {
        showLine();
        System.out.println(" Hello! I'm Sniff, your personal appointment manager.");
        System.out.println(" What can I do for you?");
        showLine();
    }

    public static void showLine() {
        System.out.println(LINE);
    }

    public static void formatPrintList(int count, String description) {
        System.out.println(" " + count + DOT_THEN_SPACE + description);
    }

    public static void printAppointmentAddedMessage(Appointment appointment) {
        System.out.println(" This appointment has been added to your appointment manager: ");
        System.out.println(appointment.toString());
    }

    public static void printAppointmentRemovedMessage(Appointment appointment) {
        System.out.println(" This appointment has been removed your appointment manager: ");
        System.out.println(appointment.toString());
    }

    public static void printAppointmentMarkMessage() {
        System.out.println("The appointment has been marked successfully");
    }

    public static void printAppointmentUnMarkMessage() {
        System.out.println("The appointment has been unmarked successfully");
    }

    public static void showHelpMessage() {
        System.out.println("These are the following Sniff commands available:\n"
                + "Add consultation appointment:\n"
                + " consultation at/ANIMAL_TYPE an/ANIMAL_NAME on/OWNER_NAME cn/CONTACT_NUMBER cd/DATE ct/TIME\n"
                + "Add vaccination appointment:\n"
                + " vaccination at/ANIMAL_TYPE an/ANIMAL_NAME on/OWNER_NAME cn/CONTACT_NUMBER v/VACCINE_TYPE vd/DATE " +
                "vt/TIME\n"
                + "Add surgery appointment:\n"
                + " surgery at/ANIMAL_TYPE an/ANIMAL_NAME on/OWNER_NAME cn/CONTACT_NUMBER sd/START_DATE " +
                "st/START_TIME ed/END_DATE et/END_TIME p/PRIORITY_LEVEL\n"
                + "Listing all appointments:\n"
                + " list\n"
                + "Removing an appointment:\n"
                + " remove uid/UID\n"
                + "Finding an appointment by animal type, appointment type or uid:\n"
                + " find a/ANIMAL_TYPE\n"
                + " find t/APPOINTMENT_TYPE \n"
                + " find uid/UID\n"
                + "Mark or Unmark an appointment:\n"
                + " mark uid/UID\n"
                + " unmark uid/UID\n"
                + "Exiting the program:\n"
                + " bye\n"
                + "Additional notes:\n"
                + " 1. DATES and TIMES format are in (YYYY-MM-DD) and (HH:MM) respectively\n"
                + " 2. PRIORITY_LEVEL format is in (L, M, H)");
    }
}
