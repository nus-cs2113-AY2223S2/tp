package seedu.badMaths;

import java.util.Scanner;
import java.util.ArrayList;
public class BadMaths {

    public static void main(String[] args) {

        ArrayList<String> listNotes = new ArrayList<>();
        System.out.println("Input math question please.");

        while (true) {
            Scanner scanner = new Scanner(System.in);
            String userInput = scanner.nextLine();
            if (userInput.equals("list")) {
                System.out.println("Here are the notes that you have stored:");
                for (int i = 0; i < listNotes.size(); i += 1) {
                    String notesItem = listNotes.get(i);
                    System.out.println((i + 1) + ". " + notesItem);
                }
                continue;
            }
            Parser parser = new Parser(userInput);
            String command = parser.getCommand();
            String toDo = parser.getToDo();
            // System.out.println(command); (store)
           // System.out.println(toDo); (index)
            // e.g. store. index
            if (command.equals("store")) {
                listNotes.add(toDo);
                System.out.println("You have stored " + toDo + " to the list");
                continue;
            }

            Command inputCommand = new Command(command, toDo);
            inputCommand.executeCommand();
            if (userInput.equals("Bye")) {
                break;
            }
        }
    }
}
