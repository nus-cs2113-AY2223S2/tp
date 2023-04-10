package com.clanki.parser;

class Option {
    private static final String BODY_SEPARATOR = " ";

    private String name;
    private String value;

    public Option(String optionString) {
        String[] parts = optionString.split(BODY_SEPARATOR, 2);
        name = parts[0].trim();
        value = parts.length > 1 ? parts[1].trim() : "";
    }

    public String getName() {
        return name;
    }

    public String getValue() {
        return value;
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
            Option option = new Option(parts[i].trim());
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
     *         exist. Be careful that the option can be an empty string. If an
     *         option is specified multiple times, the last one will be returned.
     */
    public String getOptionByName(String name) {
        for (int i = options.length - 1; i >= 0; i--) {
            if (options[i].getName().equals(name)) {
                return options[i].getValue();
            }
        }
        return null;
    }
}
