package seedu.duke;

public class Ui {
    public static final String LINE = "____________________________________________________________";

    public static final String EXIT_MESSAGE = "Hope you had an enjoyable experience. See you next time!";
    public static final String LOGO =
            "    /|    //| |     // | |     //   ) )  //   / / //   ) )\n"
            +"   //|   // | |    //__| |    //        //   / / ((        \n"
            +"  // |  //  | |   / ___  |   //  ____  //   / /    \\\\\\\\      \n"
            +" //  | //   | |  //    | |  //    / / //   / /       ) )   \n"
            +"//   |//    | | //     | | ((____/ / ((___/ / ((___ / /    ";
    public static final String GREET_MESSAGE = "Welcome to MagusStock. How may I assist you today?";

    public Ui(){
        greetUser();
    }
    public static void printExitMessage(){
        System.out.println(LINE);
        System.out.println(EXIT_MESSAGE);
        System.out.println(LINE);
    }
    public static void greetUser(){
        System.out.println(LINE);
        System.out.println(LOGO);
        System.out.println(GREET_MESSAGE);
        System.out.println(LINE);
    }
}
