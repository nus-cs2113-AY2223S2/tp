package seedu.badMaths.matrix;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Calculator {
    Logger logger = Logger.getLogger("matrix");

    public void run(){
        logger.log(Level.INFO, "Start to open calculator.\n");

        Ui ui = new Ui();
        Parser p = new Parser();
        String command;
        Tensor2D result;

        ui.printBeginning();
        command = ui.readCommand();
        result = p.parse(command);

        if(result != null) {
            ui.printResult(result);
        }
    }
}
