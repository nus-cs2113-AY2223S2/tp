package pocketpal.frontend.constants;

import java.util.regex.Pattern;

public class ParserConstants {
    public static final char OPTION_INDICATOR = '-';
    public static final String PIPE_INDICATOR = "|";
    public static final String ZERO_VALUE = "0";
    public static final String MAX_VALUE = "999999999";
    public static final int START_END_ARRAY_SIZE = 2;
    public static final int DETAIL_GROUP = 2;
    public static final int OPTION_GROUP = 1;
    public static final String VALID_PRICE_REGEX = "[0-9.]*";

    public static final String DATE_FORMATTER = "^\\d{2}/\\d{2}/\\d{4}$";
    public static final String ADD_OPTIONS = ParserConstants.CATEGORY_OPTION + ParserConstants.PIPE_INDICATOR
        + ParserConstants.DESCRIPTION_OPTION + ParserConstants.PIPE_INDICATOR + ParserConstants.PRICE_OPTION;

    public static final String EDIT_OPTIONS = ADD_OPTIONS;
    public static final String VIEW_OPTIONS = ParserConstants.CATEGORY_OPTION + ParserConstants.PIPE_INDICATOR
        + ParserConstants.START_DATE_OPTION + ParserConstants.PIPE_INDICATOR + ParserConstants.END_DATE_OPTION
        + ParserConstants.PIPE_INDICATOR + ParserConstants.START_PRICE_OPTION + ParserConstants.PIPE_INDICATOR
        + ParserConstants.END_PRICE_OPTION;
    public static final String PRICE_OPTION = "-p|-price";
    public static final String DESCRIPTION_OPTION = "-d|-description";
    public static final String START_DATE_OPTION = "-sd|-startdate";
    public static final String END_DATE_OPTION = "-ed|-enddate";
    public static final String START_PRICE_OPTION = "-sp|-startprice";
    public static final String END_PRICE_OPTION = "-ep|-endprice";
    public static final String CATEGORY_OPTION = "-c|-category";
    public static final String COMMAND_ADD = "/add";
    public static final String COMMAND_VIEW = "/view";
    public static final String COMMAND_EDIT = "/edit";
    public static final String COMMAND_DELETE = "/delete";
    public static final String COMMAND_HELP = "/help";
    public static final String COMMAND_BYE = "/bye";
    public static final Pattern DESCRIPTION_PATTERN = Pattern.compile("\\s+(-d|-description)(\\s+.*?)?(\\s+-|$)");
    public static final Pattern CATEGORY_PATTERN = Pattern.compile("\\s+(-c|-category)(\\s+.*?)?(\\s+-|$)");
    public static final Pattern PRICE_PATTERN = Pattern.compile("\\s+(-p|-price)(\\s+.*?)?(\\s*-|$)");
    public static final Pattern ID_PATTERN = Pattern.compile("(.*?)(\\s*-|$)");
    public static final Pattern START_DATE_PATTERN = Pattern.compile("\\s+(-sd|-startdate)(\\s+.*?)?(\\s+-|$)");
    public static final Pattern END_DATE_PATTERN = Pattern.compile("\\s+(-ed|-enddate)(\\s+.*?)?(\\s+-|$)");
    public static final Pattern START_PRICE_PATTERN = Pattern.compile("\\s+(-sp|-startprice)(\\s+.*?)?(\\s+-|$)");
    public static final Pattern END_PRICE_PATTERN = Pattern.compile("\\s+(-ep|-endprice)(\\s+.*?)?(\\s+-|$)");


}
