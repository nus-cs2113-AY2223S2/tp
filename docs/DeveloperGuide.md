# Developer Guide

## Acknowledgements

{list here sources of all reused/adapted ideas, code, documentation, and third-party libraries -- include links to the
original source as well}

## Design & implementation

{Describe the design and implementation of the product. Use UML diagrams and short code snippets where applicable.}

### Add Flashcard Feature

#### Current implementation

The current add flashcard allows the user to add a flashcard to the list of flashcards,
it is implemented through the following steps:

Step 1:
The input of user is collected by `getUserCommand()` inside class `Ui`.

Step 2:
The input string will be converted into a `Command` object by being passed through
`parseCommand(String userInput)` inside `Paser`.

In this case, an `AddCommand` will be created and returned.

Step 3:
The `execute()` function of `AddCommand` will run, calling `addNewFlashcard(questionText, answerText)`
of class `FlashcardList` to create and add the new flashcard to the list.

Then it will also call `printSuccessfulAddMessage(questionText, answerText)` of class `Ui`
to display text indicating the successful adding function to the user.

At this point, the adding process is completed and the program is ready to take another
command.

The following sequence diagram show how the add operation work:
![AddFlashcard-0.png](umlDiagrams%2FAddFlashcard-0.png)

#### Reason for current implementation

Through using `AddCommand` class, which extends `Command` class it increases the level of
abstraction as the code can now perform the various commands on a class level.

Moreover, since the creating of new `Flashcard` of object and adding of the newly created
flashcard are both done in the same class as where the flashcards are stored, this reduces
coupling in the program as the `AddCommand` will not have access to the inner structure of
`FlashcardList`, which stores the list of flashcards.

#### Alternative implementation

- Alternative 1: Have the add command function directly in `FlashcardList`
    - Pros: Easy to implement
    - Cons: Will require another function in another program to differentiate it from other
      commands
- Alternative 2: Have the constructor of `Flashcard` include adding the card to list of flashcards
    - Pros: Simplifies code
    - Cons: Will cause trouble when temporary flashcard (that need not be stored) are
      created

### Review Flashcard Feature

#### Current implementation

The current review flashcard allows the user to review all the flashcards that are due today or before,
it is implemented through the following steps:

Step 1:
The input of user is collected by `getUserCommand()` inside class `Ui`.

Step 2:
The input string will be converted into a `Command` object by being passed through
`parseCommand(String userInput)` inside `Paser`.

In this case, an `ReviewCommand` will be created and returned.

Step 3:
The `execute()` function of `ReviewCommand` will run, calling `getFlashCards()`
of class `FlashcardList` to get the list of the flashcards.

Then it will iterate through the `FlashcardList` and call the function `isDueBeforeToday()` of class `Flashcard` to
check if the flashcard is due by today.

If the flashcard is due by today, `reviewCurrentFlashcard(Ui display, Flashcard flashcard)` of class `ReviewCommand`
will be called to review the card.

First, the `Ui` will display the question of the current card by calling the `getQuestion()` method of
class `Flashcard`, and ask user if user is ready to view the answer. After user enters any keyboard input, the answer of
the current card will be shown by calling the `getAnswer()` method of class `Flashcard`, and `Ui` will ask the user if
he/she has got the card correct. If the user inputs "y"  or "yes", then the current `Flashcard` is considered to be
cleared and `updateDueDateAfterCorrectAnswer()` of `Flashcard` will be called to update its `dueDate`. Then Clanki will
let user review the next `Flashcard`. If the user inputs "n", then the card is considered to be not cleared
and `updateDueDateAfterIncorrectAnswer()` will be called to update its `dueDate`. Then Clanki will let user review the
next `Flashcard`. This process will repeat until all the `Flashcards` in the `FlashcardList` are iterated.

After the whole `FlashcardList` has been iterated through, a message congratulating the user that he/she has completed
the reviewing task will be displayed.

At this point, the reviewing process is completed and the program is ready to take another command.

The following sequence diagram show how the review operation work:
![ReviewFlashcard-0.png](umlDiagrams%2FReviewFlashcard-0.png)

#### Reason for current implementation

Through using `ReivewCommand` class, which extends `Command` class it increases the level of
abstraction as the code can now perform the various commands on a class level.

Moreover, `ReviewCommand` only has access to the public methods of `FlashcardList` and `Flashcard`, this reduces
coupling in the program as the `ReviewCommand` will not have access to the inner structure of
`FlashcardList` and `Flashcard`.

#### Alternative implementation

- Alternative 1: Have the review command function directly in `FlashcardList`
    - Pros: Easy to implement
    - Cons: Will require another function in another program to differentiate it from other
      commands
- Alternative 2: After entering the `ReviewCommand`, go back to `Clanki.run()` and take further commands for review
  process
    - Pros: Simplifies code in `ReviewCommand`
    - Cons: Will have to pass around a lot of parameters and variables

## Product scope

### Target user profile

{Describe the target user profile}

### Value proposition

{Describe the value proposition: what problem does it solve?}

## User Stories

| Version | As a ... | I want to ...             | So that I can ...                                           |
|---------|----------|---------------------------|-------------------------------------------------------------|
| v1.0    | new user | see usage instructions    | refer to them when I forget how to use the application      |
| v2.0    | user     | find a to-do item by name | locate a to-do without having to go through the entire list |

## Non-Functional Requirements

{Give non-functional requirements}

## Glossary

* *glossary item* - Definition

## Instructions for manual testing

{Give instructions on how to do a manual product testing e.g., how to load sample data to be used for testing}
