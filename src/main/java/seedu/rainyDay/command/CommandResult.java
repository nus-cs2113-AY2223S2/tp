package seedu.rainyDay.command;

public class CommandResult {

    public final String output;

    public CommandResult(String output) {
        this.output = output;
    }

    public void printResult() {
        System.out.println(this.output);
    }
}
