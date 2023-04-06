package com.clanki.parser;

import com.clanki.commands.AddCommand;
import com.clanki.commands.ByeCommand;
import com.clanki.commands.ClearCommand;
import com.clanki.commands.Command;
import com.clanki.commands.DeleteCommand;
import com.clanki.commands.HelpCommand;
import com.clanki.commands.ListCommand;
import com.clanki.commands.ReviewCommand;
import com.clanki.commands.UnknownCommand;
import com.clanki.commands.UpdateCommand;
import com.clanki.commands.VoidCommand;
import com.clanki.exceptions.EmptyFlashcardAnswerException;
import com.clanki.exceptions.EmptyFlashcardQuestionException;
import com.clanki.exceptions.InputIsEmptyException;
import com.clanki.exceptions.InvalidAddFlashcardInputException;
import com.clanki.exceptions.NoQueryInInputException;
import com.clanki.exceptions.UpdatedContentIsEmptyException;


public class Parser {
    private static final String QUESTION_OPTION_IDENTIFIER = "q";
    private static final String ANSWER_OPTION_IDENTIFIER = "a";
    private static final String DATE_OPTION_IDENTIFIER = "d";
    //private static Logger logger = Logger.getLogger("Parser");

    public static Command parseCommand(String userInput) {
        try {
            return parseCommandStrict(userInput);
        } catch (InvalidAddFlashcardInputException e) {
            System.out.println(
                    "The input is in an incorrect format, please follow the format in user guide");
        } catch (EmptyFlashcardQuestionException e) {
            System.out.println("The question of this card is empty, please enter one.");
        } catch (EmptyFlashcardAnswerException e) {
            System.out.println("The answer for this flashcard is empty, please enter one.");
        } catch (NoQueryInInputException e) {
            System.out.println("Please enter a query to be searched in the list of flashcards.");
        } catch (InputIsEmptyException e) {
            System.out.println("Please enter the command in the format shown in the user guide");
        }
        return new VoidCommand();
    }

    public static Command parseCommandStrict(String userInput)
            throws InvalidAddFlashcardInputException, EmptyFlashcardQuestionException,
            EmptyFlashcardAnswerException, NoQueryInInputException, InputIsEmptyException {
        ParsedInput parsedInput = new ParsedInput(userInput);
        String command = parsedInput.getCommand();
        assert !command.isEmpty() : "The command string must not be empty";
        switch (command) {
        case "add":
            return getAddCommand(parsedInput);
        case "review":
            return getReviewCommand(parsedInput);
        case "update":
            return getUpdateCommand(parsedInput);
        case "del":
            return getDeleteCommand(parsedInput);
        case "bye":
            return getByeCommand(parsedInput);
        case "help":
            return new HelpCommand();
        case "list":
            return getListCommand(parsedInput);
        case "clear":
            return new ClearCommand();
        default:
            return new UnknownCommand();
        }
    }

    /**
     * Constructs an AddCommand from the input of the user, if the input is of an
     * incorrect format, a respective exception will be thrown.
     *
     * @param parsedInput The input collected by Ui from the user, after being
     *                    parsed with the ParsedInput class.
     * @return An AddCommand with the question and answer text extracted from user input
     * @throws InvalidAddFlashcardInputException If the start indicators cannot be
     *                                           found.
     * @throws EmptyFlashcardQuestionException   If the string is empty after
     *                                           QUESTION_OPTION_IDENTIFIER.
     * @throws EmptyFlashcardAnswerException     If the string is empty after
     *                                           ANSWER_OPTION_IDENTIFIER.
     */
    public static AddCommand getAddCommand(ParsedInput parsedInput)
            throws InvalidAddFlashcardInputException, EmptyFlashcardQuestionException,
            EmptyFlashcardAnswerException {
        //logger.log(Level.INFO, "Start to parse input to obtain question text.");
        String questionText = parsedInput.getOptionByName(QUESTION_OPTION_IDENTIFIER);
        //logger.log(Level.INFO, "Start to parse input to obtain answer text.");
        String answerText = parsedInput.getOptionByName(ANSWER_OPTION_IDENTIFIER);
        if (questionText == null || answerText == null) {
            throw new InvalidAddFlashcardInputException();
        }
        if (questionText.isEmpty()) {
            //logger.log(Level.INFO, "No input at question text detected.");
            throw new EmptyFlashcardQuestionException();
        }
        if (answerText.isEmpty()) {
            //logger.log(Level.INFO, "No input at answer text detected.");
            throw new EmptyFlashcardAnswerException();
        }
        return new AddCommand(questionText, answerText);
    }

    public static ReviewCommand getReviewCommand(ParsedInput parsedInput) {
        return new ReviewCommand();
    }

    public static UpdateCommand getUpdateCommand(ParsedInput parsedInput) throws NoQueryInInputException {
        String query = parsedInput.getBody();
        if (query.isEmpty()) {
            throw new NoQueryInInputException();
        }
        return new UpdateCommand(parsedInput.getBody());
    }

    public static DeleteCommand getDeleteCommand(ParsedInput parsedInput) {
        return new DeleteCommand(parsedInput.getBody());
    }

    public static ListCommand getListCommand(ParsedInput parsedInput) throws InputIsEmptyException {
        String date = parsedInput.getBody();
        if (date.isEmpty()) {
            throw new InputIsEmptyException();
        }
        return new ListCommand(parsedInput.getBody());
    }

    public static ByeCommand getByeCommand(ParsedInput parsedInput) {
        return new ByeCommand();
    }

    public static int getIndexForUpdateCommand(String userInput) {
        ParsedInput parsedInput = new ParsedInput(userInput);
        String index = parsedInput.getCommand();
        return Integer.parseInt(index) - 1;
    }

    public static String getIdentifierForUpdateCommand(String userInput) throws InvalidIdentifierException {
        String[] userTexts = userInput.split(" ", 3);
        String identifier = null;
        if (userTexts[1] == null) {
            throw new ArrayIndexOutOfBoundsException();
        }
        if (userTexts[1].equals("/q")) {
            identifier = "q";
        }
        if (userTexts[1].equals("/a")) {
            identifier = "a";
        }
        if (userTexts[1].equals("/d")) {
            identifier = "d";
        }
        return identifier;
    }

    public static String parseInputForUpdateCommand(String userInput) throws InvalidIdentifierException,
            UpdatedContentIsEmptyException {
        ParsedInput parsedInput = new ParsedInput(userInput);
        String identifier = getIdentifierForUpdateCommand(userInput);
        String updatedContent = null;
        if (identifier == null) {
            throw new InvalidIdentifierException();
        }
        if (identifier.equals(QUESTION_OPTION_IDENTIFIER)) {
            updatedContent = parsedInput.getOptionByName(QUESTION_OPTION_IDENTIFIER);
        }
        if (identifier.equals(ANSWER_OPTION_IDENTIFIER)) {
            updatedContent = parsedInput.getOptionByName(ANSWER_OPTION_IDENTIFIER);
        }
        if (identifier.equals(DATE_OPTION_IDENTIFIER)) {
            updatedContent = parsedInput.getOptionByName(DATE_OPTION_IDENTIFIER);
        }
        if (updatedContent.isEmpty()) {
            throw new UpdatedContentIsEmptyException();
        }
        if (updatedContent == null) {
            throw new InvalidIdentifierException();
        }
        return updatedContent;
    }

}
