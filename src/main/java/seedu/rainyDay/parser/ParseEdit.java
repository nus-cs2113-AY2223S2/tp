package seedu.rainyDay.parser;

import seedu.rainyDay.RainyDay;
import seedu.rainyDay.command.Command;
import seedu.rainyDay.command.EditCommand;
import seedu.rainyDay.exceptions.ErrorMessage;
import seedu.rainyDay.exceptions.RainyDayException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

//@@author ChongQiRong
public class ParseEdit extends Parser {
    public static final double MAX_AMOUNT = 21474836.47;
    private static final Logger logger = Logger.getLogger(ParseEdit.class.getName());

    public Command editStatement(String userInput) throws RainyDayException {
        String[] tokens = userInput.split("\\s+", 3);
        if (tokens.length < 3) {
            logger.warning("invalid edit index from user");
            throw new RainyDayException(ErrorMessage.WRONG_EDIT_FORMAT.toString());
        }

        int lengthOfReport = RainyDay.savedData.getFinancialReport().getStatementCount();
        if (lengthOfReport == 0) {
            throw new RainyDayException(ErrorMessage.EMPTY_FINANCIAL_REPORT.toString());
        }

        int index;
        try {
            index = Integer.parseInt(tokens[1]);
        } catch (Exception e) {
            logger.warning("edit index not a valid number");
            throw new RainyDayException(String.format(ErrorMessage.WRONG_EDIT_INDEX.toString(),
                    RainyDay.savedData.getFinancialReport().getStatementCount() + "!"));
        }

        if (index > lengthOfReport || index <= 0) {
            logger.warning("invalid edit index from user");
            throw new RainyDayException(String.format(ErrorMessage.WRONG_EDIT_INDEX.toString(),
                    lengthOfReport + "!"));
        }

        int sizeOfEditFlagAndField = 0;
        String[] flagAndField = tokens[2].split("\\s");
        for (String s : flagAndField) {
            if (s.equals("-in")) {
                sizeOfEditFlagAndField += 1;
            } else if (s.equals("-out")) {
                sizeOfEditFlagAndField += 1;
            } else if (s.equals("-d")) {
                sizeOfEditFlagAndField += 2;
            } else if (s.equals("-v")) {
                sizeOfEditFlagAndField += 2;
            } else if (s.equals("-c")) {
                sizeOfEditFlagAndField += 2;
            } else if (s.equals("-date")) {
                sizeOfEditFlagAndField += 2;
            }
        }

        if (tokens[2].startsWith("-in") | tokens[2].startsWith("-out") | tokens[2].startsWith("-d")
                | tokens[2].startsWith("-c") | tokens[2].startsWith("-v") | tokens[2].startsWith("-date")) {
            ArrayList<String> editFlagAndField = parseEditMultipleFlags(tokens[2], sizeOfEditFlagAndField);
            return new EditCommand(index, editFlagAndField);
        } else {
            logger.warning("unrecognised edit input from user!");
            throw new RainyDayException(ErrorMessage.WRONG_EDIT_FORMAT.toString());
        }
    }

    private ArrayList<String> parseEditMultipleFlags(String input, int sizeOfEditFlagAndField) throws
            RainyDayException {
        Pattern pattern = Pattern.compile("(?:(-in|-out)\\s*)?\\s*" +
                "(?:(-d)\\s+([^\\s-]+(?:\\s+[^\\s-]+)*)\\s*)?" +
                "(?:(-v)\\s+\\$([\\d]+(?:\\.?[\\d]){0,2})\\s*)?" +
                "(?:(-c)\\s+([^\\s-]+(?:\\s+[^\\s-]+)*)\\s*)?" +
                "(.*)$");
        Matcher matcher = pattern.matcher(input);
        ArrayList<String> editFlagAndField = new ArrayList<>();

        if (matcher.find()) {
            if (matcher.group(8).trim().indexOf("-date") != 0 && !matcher.group(8).equals("")) {
                throw new RainyDayException(ErrorMessage.WRONG_EDIT_FORMAT.toString());
            }
            for (int i = 1; i <= 7; i += 1) {
                if (matcher.group(i) == null) {
                    continue;
                }
                editFlagAndField.add(matcher.group(i));
            }

            if (matcher.group(8).contains("-date")) {
                try {
                    LocalDate date = setDate(matcher.group(8));
                    DateTimeFormatter formatters = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                    String dateString = date.format(formatters);
                    editFlagAndField.add("-date");
                    editFlagAndField.add(dateString);
                } catch (NumberFormatException e) {
                    throw new RainyDayException(ErrorMessage.EDIT_FORMAT.toString());
                } catch (RainyDayException e) {
                    throw new RainyDayException(e.getMessage() + ErrorMessage.EDIT_FORMAT);
                }
            }

            if (editFlagAndField.size() == 0 || editFlagAndField.size() != sizeOfEditFlagAndField) {
                logger.warning("edit command given by user in the wrong format");
                throw new RainyDayException(ErrorMessage.WRONG_EDIT_FORMAT.toString());
            }
            if (editFlagAndField.contains("-v")) {
                int valueFlagIndex = editFlagAndField.indexOf("-v");
                double valueToChange = Double.parseDouble(editFlagAndField.get(valueFlagIndex + 1));
                if (valueToChange <= 0 || valueToChange > MAX_AMOUNT) {
                    logger.warning("edit value is less than 0");
                    throw new RainyDayException(ErrorMessage.WRONG_EDIT_FORMAT.toString());
                }
            }
            if (editFlagAndField.contains("-c")) {
                int categoryToChangeIndex = editFlagAndField.indexOf("-c");
                String categoryToChange = editFlagAndField.get(categoryToChangeIndex);
                if (categoryToChange.contains("$")) {
                    logger.warning("edit category contains $");
                    throw new RainyDayException(ErrorMessage.WRONG_EDIT_FORMAT.toString());
                }
            }
            return editFlagAndField;
        } else {
            logger.warning("edit command given by user in the wrong format");
            throw new RainyDayException(ErrorMessage.WRONG_EDIT_FORMAT.toString());
        }
    }
}
