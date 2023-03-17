package seedu.badMaths.matrix;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Calculator {

    Logger logger = Logger.getLogger("matrix");

    public void run(){
        logger.log(Level.INFO, "Start to open calculator.\n");

        Ui ui = new Ui();
        Execute e = new Execute();
        String command;

        ui.printBeginning();
        while (true) {
            command = ui.readCommand();
            if(command.equals("exit")) {
                break;
            }
            e.parse(command);
        }
        ui.printEnding();
    }
}
