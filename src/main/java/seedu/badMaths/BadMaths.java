package seedu.badMaths;

import java.util.Scanner;

public class BadMaths {

    public static void main(String[] args) {
        System.out.println("Input math question please.");

        Scanner scanner = new Scanner(System.in);
        String userInput = scanner.nextLine();
        Parser parser = new Parser(userInput);

    }
}
