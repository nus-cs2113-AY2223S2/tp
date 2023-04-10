package com.clanki.parser;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ParsedInputTest {
    @Test
    public void getCommand_emptyInput_emptyString() {
        ParsedInput parsedInput = new ParsedInput("");
        assertEquals("", parsedInput.getCommand());
    }

    @Test
    public void getCommand_oneWordInput_oneWord() {
        ParsedInput parsedInput = new ParsedInput("command");
        assertEquals("command", parsedInput.getCommand());
    }

    @Test
    public void getCommand_multipleWordsInput_firstWord() {
        ParsedInput parsedInput = new ParsedInput("command blah blah");
        assertEquals("command", parsedInput.getCommand());
    }

    @Test
    public void getCommand_withSlash_hasTheSlash() {
        ParsedInput parsedInput = new ParsedInput("command/blah blah");
        assertEquals("command/blah", parsedInput.getCommand());
    }

    @Test
    public void getBody_emptyInput_emptyString() {
        ParsedInput parsedInput = new ParsedInput("");
        assertEquals("", parsedInput.getBody());
    }

    @Test
    public void getBody_oneWordInput_emptyString() {
        ParsedInput parsedInput = new ParsedInput("command");
        assertEquals("", parsedInput.getBody());
    }

    @Test
    public void getBody_multipleWordsInput_allWords() {
        ParsedInput parsedInput = new ParsedInput("command blah blah");
        assertEquals("blah blah", parsedInput.getBody());
    }

    @Test
    public void getBody_optionsWithoutBody_beNull() {
        ParsedInput parsedInput = new ParsedInput("command             /blah blah");
        assertEquals("", parsedInput.getBody());
    }

    @Test
    public void getBody_optionsWithBody_beCorrect() {
        ParsedInput parsedInput = new ParsedInput("command       a b      /blah blah");
        assertEquals("a b", parsedInput.getBody());
    }

    @Test
    public void getBody_optionsWithBodyAndTrailingSpaces_beCorrect() {
        ParsedInput parsedInput = new ParsedInput("command       a b      /blah blah    ");
        assertEquals("a b", parsedInput.getBody());
    }

    @Test
    public void getOptionByName_normalScenarios_beCorrect() {
        ParsedInput parsedInput = new ParsedInput(
                "command   /opt1   blah   /opt2   /opt3   bleh     ");
        assertEquals("blah", parsedInput.getOptionByName("opt1"));
        assertEquals("", parsedInput.getOptionByName("opt2"));
        assertEquals("bleh", parsedInput.getOptionByName("opt3"));
        assertEquals(null, parsedInput.getOptionByName("opt4"));
    }

    @Test
    public void getOptionByName_sameOptionMultipleTimes_selectLast() {
        ParsedInput parsedInput = new ParsedInput(
                "command   /opt1   blah   /opt2   /opt3   bleh   /opt1   bleh     ");
        assertEquals("bleh", parsedInput.getOptionByName("opt1"));
    }
}
