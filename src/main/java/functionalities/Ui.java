package functionalities;
import exception.SniffException;
import java.util.Scanner;

public class Ui {
    private static final String SPACE = " ";
    private static final String DOT_THEN_SPACE = ". ";
    public void showUserMessage(String s) {
        System.out.println(s);
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

    public void showLine() {
        String dividerLine = "______________________________________________________________________";
        System.out.println(dividerLine);
    }

    public static void formatPrintList(String count, String type, String animal, String name, String date) {
        System.out.println(count + DOT_THEN_SPACE + type + SPACE + animal + SPACE + name + SPACE + date);
    }

    public static void printEmptyListMessage() {
        System.out.println("Appointment list is empty.");
    }
}
