package seedu.duke;

import java.util.Scanner;
public class Parser {
    private static Scanner in = new Scanner(System.in);
    public void parse(){
        String command = in.nextLine().trim();
        if(command.equals("bye")||command.equals("exit")){
            Ui.printExitMessage();
            System.exit(0);
        }
        return;
    }
}
