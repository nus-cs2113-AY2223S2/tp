package com.clanki.parser;

import com.clanki.commands.AddCommand;
import com.clanki.commands.ByeCommand;
import com.clanki.commands.Command;
import com.clanki.commands.DeleteCommand;
import com.clanki.commands.ReviewCommand;
import com.clanki.commands.UnknownCommand;
import com.clanki.commands.UpdateCommand;
import com.clanki.exceptions.EmptyFlashcardAnswerException;
import com.clanki.exceptions.EmptyFlashcardQuestionException;
import com.clanki.exceptions.InvalidAddFlashcardInputException;

public class Parser {
    private static final String QUESTION_OPTION_IDENTIFIER = "q";
    private static final String ANSWER_OPTION_IDENTIFIER = "a";

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
        }
        return new UnknownCommand();
    }

    public static Command parseCommandStrict(String userInput)
            throws InvalidAddFlashcardInputException, EmptyFlashcardQuestionException,
            EmptyFlashcardAnswerException {
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
        String questionText = parsedInput.getOptionByName(QUESTION_OPTION_IDENTIFIER);
        String answerText = parsedInput.getOptionByName(ANSWER_OPTION_IDENTIFIER);
        if (questionText == null || answerText == null) {
            throw new InvalidAddFlashcardInputException();
        }
        if (questionText.isEmpty()) {
            throw new EmptyFlashcardQuestionException();
        }
        if (answerText.isEmpty()) {
            throw new EmptyFlashcardAnswerException();
        }
        return new AddCommand(questionText, answerText);
    }

    public static ReviewCommand getReviewCommand(ParsedInput parsedInput) {
        return new ReviewCommand();
    }

    public static UpdateCommand getUpdateCommand(ParsedInput parsedInput) {
        return new UpdateCommand(parsedInput.getBody());
    }

    public static DeleteCommand getDeleteCommand(ParsedInput parsedInput) {
        int index = Integer.parseInt(parsedInput.getBody());
        return new DeleteCommand(index);
    }

    public static ByeCommand getByeCommand(ParsedInput parsedInput) {
        return new ByeCommand();
    }
}
