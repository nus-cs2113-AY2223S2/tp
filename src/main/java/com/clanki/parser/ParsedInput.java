package com.clanki.parser;

class Option {
    private static final String BODY_SEPARATOR = " ";

    private String name;
    private String value;

    public Option(String optionString) {
        String[] parts = optionString.split(BODY_SEPARATOR, 2);
        name = parts[0];
        value = parts.length > 1 ? parts[1] : null;
    }

    public String getName() {
        return name;
    }

    public String getValue() {
        return value;
    }

    /**
     * For testing purposes.
     */
    @Override
    public String toString() {
        return "Option{" + "name='" + name + '\'' + ", value='" + value + '\'' + '}';
    }
}

public class ParsedInput {
    private static final String OPTION_INDICATOR = "/";
    private static final String OPTION_SEPARATOR = " " + OPTION_INDICATOR;

    private String command;
    private String body;
    private Option[] options;

    public ParsedInput(String input) {
        // Assuming input = "command blah blah /opt1 hello /opt2 world blah bleh"

        // After trimming:
        // parts = ["command blah blah", "opt1 hello", "opt2 world blah bleh"]
        String[] parts = input.split(OPTION_SEPARATOR);
        options = new Option[parts.length - 1];

        for (int i = 0; i < parts.length; i++) {
            Option option = new Option(parts[i]);
            if (i == 0) {
                command = option.getName();
                body = option.getValue();
            } else {
                options[i - 1] = option;
            }
        }
    }

    public String getCommand() {
        assert command != null;
        return command;
    }

    public String getBody() {
        return body;
    }

    /**
     * Returns the value of the option with the given name.
     * 
     * Example: "command blah blah /opt1 hello /opt2 world blah bleh" then
     * getOptionByName("opt2") returns "world blah bleh" and getOptionByName("foo")
     * returns null.
     * 
     * @param name The name of the option.
     * @return The (trimmed) value of the option, or null if the option does not
     *         exist. Be careful that the option can be an empty string.
     */
    public String getOptionByName(String name) {
        for (Option option : options) {
            if (option.getName().equals(name)) {
                return option.getValue();
            }
        }
        return null;
    }

    /**
     * For testing purposes.
     */
    public String[] getOptionsAsStrings() {
        String[] optionsAsStrings = new String[options.length];
        for (int i = 0; i < options.length; i++) {
            optionsAsStrings[i] = options[i].toString();
        }
        return optionsAsStrings;
    }
}
