package seedu.duke;


import seedu.duke.commands.CommandHandler;
import seedu.duke.exercisegenerator.GenerateExercise;
import seedu.duke.ui.Ui;

import java.util.Scanner;

public class Duke {

    private final Ui ui;
    private final GenerateExercise exerciseGenerator;


    public Duke(){
        ui = new Ui();
        exerciseGenerator = new GenerateExercise();
    }

    @SuppressWarnings("InfiniteLoopStatement")
    public void run(){
        CommandHandler commandHandler = new CommandHandler();
        Scanner in = new Scanner(System.in);
        ui.greetUser();
        while(true){
            commandHandler.handleUserCommands(in.nextLine(),ui,exerciseGenerator);
        }
    }

    public static void main(String[] args) {
        new Duke().run();
    }
}
