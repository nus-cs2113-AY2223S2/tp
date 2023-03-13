package seedu.badMaths;

import java.util.Scanner;
public class BadMaths {

    public static void main(String[] args) {

        System.out.println("Input math question please.");
        System.out.println("You can type 'Help' to learn what I can do for you :)");
        Command inputCommand = null;

        while (true) {
            Scanner scanner = new Scanner(System.in);
            String userInput = scanner.nextLine();

            Parser parser = new Parser(userInput);
            String command = parser.getCommand();
            String toDo = parser.getToDo();

            if (inputCommand == null) {
                inputCommand = new Command(command, toDo);
            } else {
                inputCommand.setCommand(command);
                inputCommand.setToDo(toDo);
            }

            inputCommand.executeCommand();
            if (userInput.equals("Bye")) {
                break;
            }
        }
    }
}
