package pocketpal.frontend.parser;

import java.util.logging.Logger;
import java.util.regex.Pattern;

public class ParserConstants {
    public static final String ZERO_VALUE = "0";
    public static final String MAX_VALUE = "999999999";
    public static final int DETAIL_GROUP = 2;
    public static final int OPTION_GROUP = 1;
    public static final boolean REQUIRED = true;
    public static final boolean NOT_REQUIRED = false;
    public static final String COMMAND_ADD = "/add";
    public static final String COMMAND_VIEW = "/view";
    public static final String COMMAND_EDIT = "/edit";
    public static final String COMMAND_DELETE = "/delete";
    public static final String COMMAND_HELP = "/help";
    public static final String COMMAND_BYE = "/bye";
    public static final String PRICE_OPTION = "-p|-price";
    public static final String DESCRIPTION_OPTION = "-d|-description";
    public static final String START_DATE_OPTION = "-sd|-startdate";
    public static final String END_DATE_OPTION = "-ed|-enddate";
    public static final String START_PRICE_OPTION = "-sp|-startprice";
    public static final String END_PRICE_OPTION = "-ep|-endprice";

    public static final String CATEGORY_OPTION = "-c|-category";
    public static final Pattern DESCRIPTION_PATTERN = Pattern.compile("(-d|-description)(\\s+.*?)?(-|$)");
    public static final Pattern CATEGORY_PATTERN = Pattern.compile("(-c|-category)(\\s+.*?)?(-|$)");
    public static final Pattern PRICE_PATTERN = Pattern.compile("(-p|-price)(\\s+.*?)?(-|$)");
    public static final Pattern ID_PATTERN = Pattern.compile("(\\s+)?(.+?)(-|$)");
    public static final Pattern START_DATE_PATTERN = Pattern.compile("(-sd|-startdate)(\\s+.*?)?(-|$)");
    public static final Pattern END_DATE_PATTERN = Pattern.compile("(-ed|-enddate)(\\s+.*?)?(-|$)");
    public static final Pattern START_PRICE_PATTERN = Pattern.compile("(-sp|-startprice)(\\s+.*?)?(-|$)");
    public static final Pattern END_PRICE_PATTERN = Pattern.compile("(-ep|-endprice)(\\s+.*?)?(-|$)");
//    ublic static final Pattern START_DATE_PATTERN = Pattern.compile("(-sd|-startdate)\\s+(0*\\d+/0*\\d+/\\d{2,})");
//    public static final Pattern END_DATE_PATTERN = Pattern.compile("(-ed|-enddate)\\s+(0*\\d+/0*\\d+/\\d{2,})");


}
