package seedu.duke;

import java.util.Scanner;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import static seedu.duke.Print.printFarewellMessage;

public class Duke {
    /**
     * Main entry-point for the java.duke.Duke application.
     */
    public static final Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
    private Parser parser;
    private ModuleList moduleList;

    public Duke() {
        parser = new Parser();
        moduleList = new ModuleList();
    }

    public static boolean isNameBye (String name) {
        return name.equalsIgnoreCase("bye");
    }

    public static void run(Duke chatBot) {
        initialiseLogger();
        LOGGER.log(Level.INFO, "Modganiser is starting up!");
        Scanner in = new Scanner(System.in);
        Print.printLogo();
        System.out.println("What is your name?");
        String name = in.nextLine();
        if (isNameBye(name.trim())) {
            printFarewellMessage();
            LOGGER.log(Level.INFO, "Name was given as 'bye', exiting Modganiser.");
            return;
        }
        Print.printHelloMessage(name);
        while (in.hasNextLine()) {
            String line = in.nextLine();
            chatBot.parser.checkUserInput(line, chatBot.moduleList);
        }
    }

    public static void main(String[] args) {
        Duke chatBot = new Duke();
        Duke.run(chatBot);
    }

    private static void initialiseLogger() {
        LOGGER.setUseParentHandlers(false);
        LOGGER.setLevel(Level.ALL);
        ConsoleHandler consoleHandler = new ConsoleHandler();
        consoleHandler.setLevel(Level.SEVERE);
        LOGGER.addHandler(consoleHandler);
        try {
            FileHandler fh = new FileHandler("modganiserLog.txt");
            fh.setLevel(Level.FINE);
            SimpleFormatter formatter = new SimpleFormatter();
            fh.setFormatter(formatter);
            LOGGER.addHandler(fh);
        } catch (java.io.IOException e) {
            LOGGER.log(Level.SEVERE, "Error in file logger!", e);
        }
    }

}
