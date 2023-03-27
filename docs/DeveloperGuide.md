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
`parseCommand(String userInput)` inside `Parser`.

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

### Delete Flashcard Feature

#### Current implementation

The current delete flashcard allows the user to remove a flashcard from the list of flashcards,
it is implemented through the following steps:

Step 1: The input of user is collected by `getUserCommand()` inside class `Ui`.

Step 2: The input string will be converted into a `Command` object by being passed through
`parseCommand(String userInput)` inside `Parser`.

In this case, a `DeleteCommnad` will be created and returned.

Step 3: The `execute()` function of `DeleteCommand` will run, creating a copy of the list of flashcards.
Then `findFlashcards(flashcards, query)` is called to find the flashcards with questions matching the query,
before calling `printFlashcardList(matchingFlashcards)` to display the found flashcards.

User input is taken to get the index of the flashcard to be removed. `deleteFlashcard` is called from
class `flashcardList` to remove the flashcard from the original list of flashcards. Finally 
`printSuccessfulDelete` is called from class `Ui` to indicate a successful removal of the flashcard.

The deletion process is now completed and the program will await another command.

#### Reason for current implementation

Through using `DeleteCommand` class, which extends `Command` class it increases the level of
abstraction as the code can now perform the various commands on a class level.

In order to minimise the time for users to search for the flashcard to delete, they are able
to first search for a sub-list of flashcards with matching questions as the query. This method
makes the deletion process simple even if the user does not remember the index of the flashcard.

#### Alternative implementation

- Alternative 1: Delete flashcard by index from the start
    - Pros: Easy to implement and simplifies code
    - Cons: Cumbersome to delete if user forgets the flashcard's index and has to search
            through the whole list of flashcards.

### Update Flashcard Feature

#### Current implementation

The current update flashcard feature allows users search for a specific flashcard and update the contents of this
flashcard.
It is implemented through the following steps:

Step 1:
The input of user is collected by `getUserCommand()` inside class `Ui`.

Step 2:
The input string will be converted into a `Command` object by being passed through
`parseCommand(String userInput)` inside `Parser`.

In this case, an `UpdateCommand` will be created and returned.

Step 3:
The `execute()` function of `UpdateCommand` will run, creating an ArrayList of flashcards. Then
`findFlashcard(flashcards, query)` is called to find flashcards that contain questions or answers that match `query`,
after which it will call `printFlashCards(matchingFlashcards)` in `UpdateCommand` class to display the flashcards
found.

The index of the flashcard to be updated is taken from the user input, which is collected by `getUserCommand()`.
`implementUpdate(flashcards, userText)` is then called to update the question or answer of the flashcard. Finally,
`printFlashCard(flashcards.get(index))` will be called which prints the flashcard that was updated with its new content.

At this point, the update flashcard process is completed and the program is read to take another command.

#### Reason for current implementation

Implementing the update flashcard in an `UpdateCommand` class makes it easier during the debugging process related to
update flashcard feature alone as most of the methods and attributes are within this `UpdateCommand` class.

#### Alternative implementation

- Alternative 1: Instead of creating a new arrayList `matchingFlashcards` that store flashcards containing the
  `query` and then printing the list of flashcards, directly print the flashcards when there is a match with the query`
    - Pros: Easier to implement
    - Cons: Harder to track the total number of flashcards that has `query` and will need to have another way to track
      the index of the matching flashcards. it will also be more confusing as the index of the user input is not
      aligned with the index of the arrayList that contains all the flashcards

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
