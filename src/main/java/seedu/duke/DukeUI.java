package seedu.duke;

import java.util.Scanner;

public class DukeUI {
    private static final String LINE = "____________________________________________________________";
    private static final String DUKE_LOGO = "Hello from\n" +
            " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";
    public Scanner in;

    public DukeUI() {
        in = new Scanner(System.in);
    }
    public String readCommand() {
        return in.nextLine();
    }
    public void printDukeLogo() {
        System.out.println(DUKE_LOGO);
    }
    public void printGreeting(){
        printDukeLogo();
    }
    public void printLine(){
        System.out.println(LINE);
    }
}
