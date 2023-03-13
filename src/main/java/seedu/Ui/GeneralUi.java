package seedu.Ui;

import java.util.Scanner;

public class GeneralUi {

    public static Scanner sc = new Scanner(System.in);

    /**
     * Reads user input
     * @return user input
     */
    public String readLine() {
        String command = "";
        while (command.length() == 0) {
            command = sc.nextLine();
        }
        return command;
    }

    public int readInt() {
        String value = "";
        while (value.length() == 0 || !value.matches("^([+-]?[1-9]\\d*|0)$")) {
            value = sc.nextLine();
        }
        return Integer.parseInt(value);
    }

    /**
     * Helper function to print divider
     */
    public void printLine() {
        System.out.println("------------------------------------------------------------");
    }

    private static String endingMessage = "Bye! Hope to see you again soon!";

    private static String welcomeMessage = "Hello! I am LifeTracker, a program to aid you in keeping fit!";

    /**
     * Prints Goodbye message when Duke exits
     */
    public void printGoodbye() {
        System.out.println();
        this.printLine();
        System.out.println(endingMessage);
        this.printLine();
    }

    private void printLogo() {
        System.out.println(
                " _      _  __  _______             _              \n"
                        + "| |    (_)/ _||__   __|           | |            \n"
                        + "| |     _| |_ ___| |_ __ __ _  ___| | _____ _ __ \n"
                        + "| |    | |  _/ _ \\ | '__/ _` |/ __| |/ / _ \\ '__|\n"
                        + "| |____| | ||  __/ | | | (_| | (__|   <  __/ |   \n"
                        + "|______|_|_| \\___|_|_|  \\__,_|\\___|_|\\_\\___|_|   \n"
        );
    }
    /**
     * Prints Welcome message when Duke first starts
     */
    public void printIntroduction() {
        this.printLine();
        System.out.println("Hello! Welcome to");
        this.printLogo();
        this.printLine();
        System.out.println(welcomeMessage);
    }
    public void requestWeight(){}
    public void showLatestWeight(int weight){}
}
