package seedu.mealcompanion.router;

import org.junit.jupiter.api.Test;
import seedu.mealcompanion.parser.CommandTokens;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CommandRouterNodeTest {
    private static final CommandRouterNode TEST_COMMAND_TREE =
            new CommandRouterNode()
                    .route("hello", new CommandRouterNode()
                            .route("world", new MockCommandFactory("hello world"))
                    )
                    .route("bye", new MockCommandFactory("bye"));

    @Test
    public void resolveCommandValidOneWords() {
        assertEquals(TEST_COMMAND_TREE.resolve(new CommandTokens("bye")), "bye");
    }

    @Test
    public void resolveCommandValidTwoWords() {
        assertEquals(TEST_COMMAND_TREE.resolve(new CommandTokens("hello world")), "hello world");
    }

    @Test
    public void resolveCommandValidExtra() {
        assertEquals(TEST_COMMAND_TREE.resolve(new CommandTokens("bye extra")), "bye");
    }

    @Test
    public void resolveCommandInvalidOneWords() {
        assertEquals(TEST_COMMAND_TREE.resolve(new CommandTokens("invalid")), null);
    }

    @Test
    public void resolveCommandInvalidTwoWordsMatchOne() {
        assertEquals(TEST_COMMAND_TREE.resolve(new CommandTokens("hello invalid")), null);
    }

    @Test
    public void resolveCommandInvalidTwoWordsMatchNone() {
        assertEquals(TEST_COMMAND_TREE.resolve(new CommandTokens("invalid invalid2")), null);
    }
}
