package seedu.brokeMan.common;

public class Messages {

    public static final String MESSAGE_LOGO =
            "$$$$$___ ______ _______ $$_____ _______ $$___$$_ _______ _______\n" +
            "|  $$__$$__ ______ _______ $$__$$_ _$$$$__ $$$_$$$_ $$$$$__ _______\n" +
            "|  $$$$$___ $$_$$_ _$$$$__ $$_$$__ $$__$$_ $$$$$$$_ ____$$_ $$$$$__\n" +
            "|  $$___$$_ $$$_$_ $$__$$_ $$$$___ $$$$$$_ $$_$_$$_ _$$$$$_ $$__$$_\n" +
            "|  $$___$$_ $$____ $$__$$_ $$_$$__ $$_____ $$___$$_ $$__$$_ $$__$$_\n" +
            "|  $$$$$$__ $$____ _$$$$__ $$__$$_ _$$$$$_ $$___$$_ _$$$$$_ $$__$$_";

    public static final String MESSAGE_GOODBYE =
            "   ___                       _              ___     _  _\n" +
            "|    / __|    ___     ___    __| |     o O O  | _ )   | || |   ___\n" +
            "|   | (_ |   / _ \\   / _ \\  / _` |    o       | _ \\    \\_, |  / -_)\n" +
            "|    \\___|   \\___/   \\___/  \\__,_|   TS__[O]  |___/   _|__/   \\___|\n" +
            "|  _|\"\"\"\"\"|_|\"\"\"\"\"|_|\"\"\"\"\"|_|\"\"\"\"\"| {======|_|\"\"\"\"\"|_| \"\"\"\"|_|\"\"\"\"\"|\n" +
            "|  \"`-0-0-'\"`-0-0-'\"`-0-0-'\"`-0-0-'./o--000'\"`-0-0-'\"`-0-0-'\"`-0-0-'";

    public static final String MESSAGE_WELCOME = "Welcome to BrokeMan!\n" +
            "|  Your personal budget manager to prevent you to become broke like me...";
    public static final String MESSAGE_INVALID_ADD_COMMAND = "Invalid add command format.";
    public static final String MESSAGE_INVALID_EDIT_COMMAND = "Invalid edit command format.";
    public static final String MESSAGE_AMOUNT_NOT_DOUBLE = "Amount is not a double.";
    public static final String MESSAGE_INDEX_NOT_INTEGER = "Index is not an integer.";
    public static final String MESSAGE_INDEX_NOT_SPECIFIED_EXCEPTION = "Index is not specified.";
    public static final String MESSAGE_INCORRECT_TYPE = "Type specified is incorrect.";
    public static final String MESSAGE_AMOUNT_LESS_THAN_OR_EQUALS_ZERO = "Amount cannot be less than or equals to 0.";
    public static final String MESSAGE_ARGUMENTS_NOT_SPECIFIED = "Arguments not specified.";
    public static final String MESSAGE_INVALID_TIME = "Invalid time information. " +
            "Please present your time as 'YYYY MM DD HH mm'\n" +
            "|  Do not enter invalid dates, such as entering 14 for MM.\n" +
            "|  The earliest date available is 2000/01, and the latest date available is 9999/12.";
    public static final String MESSAGE_INVALID_MONTH = "Invalid time information. " +
            "Please present your time as [YYYY/MM]\n" +
            "|  Do not enter invalid dates, such as entering 14 for MM. Only months 1~12 are accepted.\n" +
            "|  The earliest date available is 2000/01, and the latest date available is 9999/12.";

    public static final String MESSAGE_INVALID_OPTIONAL_TIME_FLAG = "Invalid optional time flag format.";
    public static final String MESSAGE_WRONG_FLAG_ORDER = "Wrong flags order.";

    public static final String MESSAGE_INVALID_CATEGORY = "Invalid category tag.\n|  You can add category tags: " +
            "FOOD, SHOPPING, GROCERIES, TRANSPORTATION, ENTERTAINMENT, TRAVEL, " +
            "SALARY, INVESTMENT, " +
            "OTHERS";

    public static final String MESSAGE_CONTAIN_DUPLICATED_FLAGS = "Duplicated flags are not allowed.";

    public static final String MESSAGE_NEW_DESCRIPTION_CONTAIN_FLAGS = "Edited description cannot contain any flags" +
            " such as 'a/', 'd/', and 'c/'.";

    public static final String MESSAGE_EXCEED_MAXIMUM_LENGTH_FOR_AMOUNT = "The maximum amount that can be" +
            " entered is $9999999999.99";
}
