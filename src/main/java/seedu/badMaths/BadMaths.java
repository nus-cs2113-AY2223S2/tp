package seedu.badMaths;

import seedu.badMaths.UI.Ui;

import java.util.Scanner;

public class BadMaths {

    public static void commandChecker(String userInput, String command) {
        try {
            if (!(command.equals("Graph") || command.equals("Bye") || command.equals("List") || command.equals("Notes") || command.equals("Matrix") || command.equals("Help"))) {
                throw new IllegalArgumentException();
            }
            if (!userInput.contains(".") && !(userInput.equals("List") || userInput.equals("Bye") || userInput.equals("Help"))) {
                throw new IllegalArgumentException();
            }
        } catch (IllegalArgumentException e) {
            Ui.printIncorrectFormatEntered();
        }
    }

    public static void main(String[] args) {

        HelpManual.readHelpManual();
        System.out.println("You can type 'Help' to learn what I can do for you :)");
        Command inputCommand = null;

        while (true) {
            Scanner scanner = new Scanner(System.in);
            String userInput = scanner.nextLine();

            Parser parser = new Parser(userInput);
            String command = parser.getCommand();
            String toDo = parser.getToDo();
            commandChecker(userInput, command);

            if (inputCommand == null) {
                inputCommand = new Command(command, toDo);
            } else {
                inputCommand.setCommand(command);
                assert inputCommand.getCommand().equals(command) : "command in inputCommand should be the same as command";
                inputCommand.setToDo(toDo);
            }

            inputCommand.executeCommand();
            if (userInput.equals("Bye")) {
                break;
            }
        }
    }
}
