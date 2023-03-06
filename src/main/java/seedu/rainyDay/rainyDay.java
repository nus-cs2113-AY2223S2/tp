package seedu.rainyDay;

import java.util.Scanner;

public class rainyDay {
    public static void main(String[] args) {
        System.out.println("Hello from rainyDay\n");
        System.out.println("What is your name?");

        Scanner in = new Scanner(System.in);
        System.out.println("Hello " + in.nextLine());
    }
}
