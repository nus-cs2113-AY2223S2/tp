package seedu.rainyDay;

public class UI {

    private static String username;

    public static void printLogo() {
        System.out.println("Hello from rainyDay");
        System.out.println("What is your name?");
    }

    public static void greetUser(String name) {
        username = name;
        System.out.println("Welcome " + username);
    }

    public static void sayFarewellToUser() {
        System.out.println("Bye" + username);
    }
}
