package seedu.badmaths.matrix;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Calculator {
    Logger logger = Logger.getLogger("matrix");

    public void run(String toDo) {
        logger.log(Level.INFO, "Start to open calculator.");

        Ui ui = new Ui();
        Parser p = new Parser();
        Tensor2D result;

        result = p.parse(toDo);
        if(result != null) {
            ui.printResult(result);
        }
    }
}
