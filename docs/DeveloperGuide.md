# Developer Guide

<!-- TOC -->

- [Developer Guide](#developer-guide)
    - [Acknowledgements](#acknowledgements)
    - [Design & implementation](#design--implementation)
        - [Add Flashcard Feature](#add-flashcard-feature)
        - [Delete Flashcard Feature](#delete-flashcard-feature)
        - [Update Flashcard Feature](#update-flashcard-feature)
        - [Review Flashcard Feature](#review-flashcard-feature)
        - [Parser](#parser)
    - [Product scope](#product-scope)
        - [Target user profile](#target-user-profile)
        - [Value proposition](#value-proposition)
    - [User Stories](#user-stories)
    - [Non-Functional Requirements](#non-functional-requirements)
    - [Glossary](#glossary)
    - [Instructions for manual testing](#instructions-for-manual-testing)
  <!-- TOC -->

## Acknowledgements

{list here sources of all reused/adapted ideas, code, documentation, and third-party libraries -- include links to the
original source as well}

## Design & implementation

{Describe the design and implementation of the product. Use UML diagrams and short code snippets where applicable.}

### Add Flashcard Feature

The image below shows a partial class diagram involving only the relevant classes when an AddCommand is
created and executed:
![AddFlashcardClassDiagram.png](umlDiagrams%2FAddFlashcardClassDiagram.png)

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

The following sequence diagram show how the add operation works:
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

An overview of how the Delete operation works is shown with the following sequence diagram
![DeleteFlashcard.png](umlDiagrams%2FDeleteFlashcard.png)

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

An overview of how the Update command works is shown with the following sequence diagram
![UpdateFlashcard.png](umlDiagrams%2FUpdateFlashcard.png)

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
he/she has got the card correct. If the user inputs "y", then the current `Flashcard` is considered to be
cleared and `updateDueDateAfterCorrectAnswer()` of `Flashcard` will be called to update its `dueDate`. Then Clanki will
let user review the next `Flashcard`. If the user inputs "n", then the card is considered to be not cleared
and `updateDueDateAfterIncorrectAnswer()` will be called to update its `dueDate`. Then Clanki will let user review the
next `Flashcard`. This process will repeat until all the `Flashcards` in the `FlashcardList` are iterated.

After the whole `FlashcardList` has been iterated through, a message congratulating the user that he/she has completed
the reviewing task will be displayed.

At this point, the reviewing process is completed and the program is ready to take another command.

The following sequence diagram show how the review operation work:
![ReviewFlashcard-0.png](umlDiagrams%2FReviewFlashcard-1.png)

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

### Parser

#### Current implementation

The parser mostly relies on the `ParsedInput` class, which can parse any user provided string input in the format of
Windows command prompt commands (`command body /opt-key opt-value`).

##### `ParsedInput`

Initiated with a string `input`, it splits the input to sections that are of use. From there it splits each section
further to a "title" (denoted with `=` below) and a "body" (denoted with `-` below) part.

```
command blah blah /opt1 hello /opt2 world blah bleh
|    Part 1     |  | Part 2 |  |      Part 3      |
|=====| |-------|  |==| |---|  |==| |-------------|
```

Then these small subparts are grouped together to a format where the command part of the command, the body part and the
options can be retrieved programmatically.

The command and body can be read with `getCommand()` and `getBody()` respectively. `getCommand()` is guaranteed to be
non-null.

The options can be read with `getOptionByName(optionKeyName)`. The reason we don't have specific `getDate`
or `getQuestion` command is because we don't know what the user will input and what options we will require for each
command. So depending on the command, we retrieve the option accordingly with e.g.

```java
"command blah blah /opt1 hello /opt2 world blah bleh"
        getOptionByName("opt2") // -> "world blah bleh"
        getOptionByName("opt3") // -> null
```

##### `Parser`

This is now just a matter of wrapping `ParsedInput` with suitable error handling and logic such that each command will
be used to initiated a corresponding command class (e.g. `AddCommand`), while errors are handled gracefully.

#### Reason for current implementation

We need an intuitive, safe and declarative way to parse the user input. Alternative implementations that can only parse
specific commands with specific options are more imperative, less readable, less maintainable and overall just a pain to
handle. That's why the two classes are here.

#### Alternative implementation

No.

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

- _glossary item_ - Definition

## Instructions for manual testing

{Give instructions on how to do a manual product testing e.g., how to load sample data to be used for testing}
