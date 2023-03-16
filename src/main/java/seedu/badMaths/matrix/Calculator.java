package seedu.badMaths.matrix;

import java.util.HashMap;
import java.util.Map;

public class Calculator {
    public void run(){
        Ui ui = new Ui();
        Execute e = new Execute();
        String command;

        ui.printBeginning();
        while (true) {
            command = ui.readCommand();
            if(command.equals("exit"))
                break;
            e.parse(command);
        }
        ui.printEnding();
    }
}
