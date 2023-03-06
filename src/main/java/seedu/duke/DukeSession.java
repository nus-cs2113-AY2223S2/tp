package seedu.duke;

import seedu.duke.command.ExecutableCommand;
import seedu.duke.command.factory.ExecutableCommandFactory;
import seedu.duke.command.factory.misc.ByeCommandFactory;
import seedu.duke.command.factory.misc.HelloWorldCommandFactory;
import seedu.duke.parser.CommandArguments;
import seedu.duke.parser.CommandTokens;
import seedu.duke.router.CommandRouterNode;
import seedu.duke.ui.DukeUI;

import java.util.Scanner;

public class DukeSession {
    private static final CommandRouterNode COMMAND_TREE =
            new CommandRouterNode()

                    .route("hello", new CommandRouterNode()
                            .route("world", new HelloWorldCommandFactory())
                    )
                    .route("bye", new ByeCommandFactory());

    private final DukeUI ui;
    private final DukeControlFlow controlFlow;

    public DukeSession() {
        this.ui = new DukeUI(new Scanner(System.in));
        this.controlFlow = new DukeControlFlow();
    }

    /**
     * Returns the <code>DukeUI</code> for the current session.
     * @return Handle to control UI.
     */
    public DukeUI getUi() {
        return ui;
    }

    /**
     * Returns the <code>DukeControlFlow</code> for the current session.
     * @return Handle to set the control flow.
     */
    public DukeControlFlow getControlFlow() {
        return controlFlow;
    }

    /**
     * Runs the read, evaluate, print loop for Duke.
     */
    public void runDuke() {
        this.ui.printIntroduction();
        while (this.controlFlow.shouldRun()) {
            String nextCommand = ui.getNextCommandString();
            CommandTokens tokens = new CommandTokens(nextCommand);
            ExecutableCommandFactory commandFactory = this.COMMAND_TREE.resolve(tokens);

            if (commandFactory == null) {
                System.out.println("Not a command!");
                continue;
            }

            ExecutableCommand cmd = commandFactory.buildCommand(this, new CommandArguments(tokens));
            cmd.execute(this);
        }
    }
}
