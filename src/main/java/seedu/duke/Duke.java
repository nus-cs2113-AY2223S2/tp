package seedu.duke;

public class Duke {
    private DukeUI ui;
    public Duke() {
        this.ui = new DukeUI();

    }
    public void run() {
        ui.printGreeting();
        System.out.println("What is your name?");
        System.out.println("Hello " + ui.readCommand());
    }
    public static void main(String[] args) {
        new Duke().run();
    }
}
