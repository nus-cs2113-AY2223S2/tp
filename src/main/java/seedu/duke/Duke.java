package seedu.duke;

import java.util.Scanner;
//import seedu.duke.ui.Ui;
//import seedu.duke.EventList;
//import seedu.duke.parser.Parser;


public class Duke {
    /**
     * Main entry-point for the java.duke.Duke application.
     */
    //private final TaskList tasks;
    //private final Ui ui;
    //private final Parser parser;
    public Duke (){
        //ui = new Ui();
        //tasks = new TaskList();
        //parser = new Parser();
    }
    public void run(){
        //ui.showWelcome();
        //boolean isExit = false;
        //while(!isExit){
            //String fullCommand = ui.getUserCommand();
            //parser.parseInput(fullCommand, tasks);
            //if (fullCommand.equalsIgnoreCase("bye")){
                //isExit = true;
            //}
        //}
    }

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("What is your name?");

        Scanner in = new Scanner(System.in);
        System.out.println("Hello " + in.nextLine());
    }
}

