package seedu.badmaths.matrix;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * This class runs the given Matrix command.
 */
public class Calculator {
    Logger logger = Logger.getLogger("matrix");

    /**
     * Run the Matrix command.
     *
     * @param toDo given matrix expression.
     */
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
