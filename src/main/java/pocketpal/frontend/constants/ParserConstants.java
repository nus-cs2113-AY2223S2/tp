package pocketpal.frontend.constants;

import java.util.regex.Pattern;

public final class ParserConstants {
    public static final char OPTION_INDICATOR = '-';
    public static final String PIPE_INDICATOR = "|";
    public static final double MIN_VALUE = 0.01;
    public static final double MAX_VALUE = 999999999.99;

    public static final String MIN_VALUE_STR = "0.01";
    public static final String MAX_VALUE_STR = "999999999.99";
    public static final int START_END_ARRAY_SIZE = 2;
    public static final int DETAIL_GROUP = 2;
    public static final int OPTION_GROUP = 1;

    public static final int ARGUMENTS_GROUP = 1;
    public static final String VALID_PRICE_REGEX = "^(\\d{0,9}\\.(\\d{1,2}0*))$|^(\\d{1,9}(\\.\\d{0,2}0*)?)$";

    public static final Pattern DATE_FORMATTER = Pattern.compile("\\d{2}/\\d{2}/\\d{2}$");

    public static final String DATE_FORMAT = "dd/MM/yy HH:mm";
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
    public static final Pattern DESCRIPTION_PATTERN = Pattern.compile("\\s+(-description|-d)(.*?)(\\s+-|$)");
    public static final Pattern CATEGORY_PATTERN = Pattern.compile("\\s+(-category|-c)(.*?)(\\s+-|$)");
    public static final Pattern PRICE_PATTERN = Pattern.compile("\\s+(-price|-p)(.*?)(\\s+-|$)");
    public static final Pattern ID_PATTERN = Pattern.compile("(.*?)(\\s*-|$)");
    public static final Pattern START_DATE_PATTERN = Pattern.compile("\\s+(-startdate|-sd)(.*?)(\\s+-|$)");
    public static final Pattern END_DATE_PATTERN = Pattern.compile("\\s+(-enddate|-ed)(.*?)(\\s+-|$)");
    public static final Pattern START_PRICE_PATTERN = Pattern.compile("\\s+(-startprice|-sp)(.*?)(\\s+-|$)");
    public static final Pattern END_PRICE_PATTERN = Pattern.compile("\\s+(-endprice|-ep)(.*?)(\\s+-|$)");


}
