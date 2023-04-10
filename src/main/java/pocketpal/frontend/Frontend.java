package pocketpal.frontend;

import pocketpal.backend.Backend;
import pocketpal.frontend.commands.Command;
import pocketpal.frontend.parser.Parser;
import pocketpal.frontend.ui.UI;

import java.util.Scanner;
import java.util.logging.Logger;

public class Frontend {
    private static final Logger logger = Logger.getLogger(Frontend.class.getName());
    private final Backend backend;

    public Frontend(Backend backend) {
        this.backend = backend;
    }

    public void start() {
        logger.info("Application started.");
        UI ui = new UI();
        Command command;
        Parser parser = new Parser();

        Scanner in = new Scanner(System.in);
        ui.printWelcome();
        boolean isExit = false;
        do {
            ui.printAwaitUserInput();
            String userInput = in.nextLine();
            logger.info("> User entered: " + userInput);
            ui.printLine();
            try {
                command = parser.parseUserInput(userInput);
                logger.info("Executing command: " + command.getClass().getName());
                command.execute(ui, backend);
                isExit = command.getIsExit();
            } catch (Exception e) {
                ui.printException(e);
            }
        } while (!isExit);

        logger.info("Exiting application");
        ui.printExit();
    }
}
