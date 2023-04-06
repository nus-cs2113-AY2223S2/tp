# Developer Guide

- [Acknowledgements](#acknowledgements)
- [Setting up, getting started](#setting-up-getting-started)
- [Design](#design)
  - [Architecture](#architecture)
  - [UI Component](#ui-component)
  - [Parser Component](#parser-component)
  - [Storage Component](#storage-component)
  - [CardList Component](#cardlist-component)
  - [TagList Component](#taglist-component)
- [Implementation](#implementation)

  - [card feature](#card-feature)
  - [tag feature](#tag-feature)
  - [export feature](#export-feature)
  - [[Proposed] deck feature](#deck-feature)

- [Appendix: Requirements](#appendix-requirements)
  - [Product scope](#product-scope)
  - [Target user profile](#target-user-profile)
  - [Value proposition](#value-proposition)
  - [User Stories](#user-stories)
  - [Non-Functional Requirements](#non-functional-requirements)
  - [Glossary](#glossary)

---

## Acknowledgements

Third-party libraries:

- [Apache Commons CLI](https://commons.apache.org/proper/commons-cli/)
- [gson](https://github.com/google/gson)

{list here sources of all reused/adapted ideas, code, documentation, and third-party libraries --
include links to the original source as well}

---

## Setting up, getting started

---

## Design

- [Architecture](#architecture)
- [UI Component](#ui-component)
- [Parser Component](#parser-component)
- [Storage Component](#storage-component)
- [CardList Component](#cardlist-component)
- [TagList Component](#taglist-component)

### Architecture

The following diagram describes the architecture of Inka:
![Architecture Diagram](img/Architecture.svg)

- Red arrows show the path taken to parse user input
- Blue arrows show possible side-effects of executing the command

### UI Component

API: Ui.java

### Parser Component

API: `Parser.java`

Inka's parser has a hierarchical design. 

{TODO: Class diagram}

The user's command is interpreted with the following structure:

```
<keyword> <action> [flag(s)]
```

For example, a command such as `card add -q QN -a ANS` would be broken down as:

- Keyword: `card`
- Action: `add`
- Flags
  - `-q`: Question text
  - `-a`: Answer text

When the user's input is passed to `Parser`:

1. User input is split into a series of _tokens_
2. `Parser` looks at the keyword token and dispatches the appropriate `KeywordParser`
3. `KeywordParser` looks at the action token and calls `OptionBuilder` to create an `Options` object for the flags it expects
4. Apache Commons CLI Parser is called with `Options`
5. The Apache Parser returns the arguments of the flags
6. The respective `Command` is constructed and returned through the parser chain
7. The `Command` is executed to modify state (`CardList`, `TagList` and `DeckList`), and may interact with the `UserInterface` and `Storage` objects to print output or save the program state respectively.

The following is the sequence diagram for parsing `card add -q QN -a ANS`:
![Parser Sequence Diagram](img/ParserSequence.svg)

### Storage Component

API: Storage.java

### CardList Component

API: CardList.java

### TagList Component

API: TagList.java

## Implementation

- [card feature](#card-feature)
- [tag feature](#tag-feature)
- [export feature](#export-feature)
- [[Proposed] deck feature](#deck-feature)

### Card Feature

The current functionalities:

- add card to the cardlist
- delete card from the cardlist
- show all cards the cardlist contains
- add tag to a card
- view the card by its uuid

The implementation of the features are shown below:

- Adding a card

1. When the user enters `card add -q ... -a ...`, the input is passed to `Parser` class which calls `Parser#parseCommand()`.
2. The parser detects the keyword "card", then calls the `Parser#CardKeywordParser()` on the user inputs excluding the "card" keyword.
3. The `Parser#CardKeywordParser()` uses the Apache Commons CLI library to parse the remaining user input.

The sequence diagram below shows how this feature works:
{UML will be added here.}

### Tag Feature

### Export Feature

### Deck Feature

---

## Appendix: Requirements

### Product scope

### Target user profile

- Person who wants a convenient, versatile and portable way to revise concepts while doing coding assignments
- Person who wants to effectively learn a new language while doing coding assignments
- Person who wants to be challenged to come up with answers quickly against time pressure.

### Value proposition

User will be able to revise while coding or using the terminal so they don&#39;t have to switch between apps. This will maximize their productivity and refresh their memory instantly whenever they want to recall some topics suddenly appear on their mind.

### User Stories

| Version | As a ... | I want to ...                                   | So that I could ...                                              |
| ------- | -------- | ----------------------------------------------- | ---------------------------------------------------------------- |
| v1.0    | new user | add a card containing the questions and answers | store the question in the system for later revision              |
| v1.0    | new user | see a list of all cards                         | I can see what are all the cards I have regardless of their tags |
| v1.0    | new user | delete some cards                               | I can remove unwanted cards                                      |
| v1.0    | new user | save cards to a local file                      | I can reuse the cards the next time I use the software           |
| v1.0    | new user | load questions and answers from a file          | I can use the database of flashcards                             |
| v1.0    | new user | save cards to a local file                      | I can reuse the cards the next time I use the software           |
| v1.0    | new user | load questions and answers from a file          | I can use the database of flashcards                             |
| v2.0    | new user | update the content of the card                  | I can ensure that the content of the card is up-to-date          |
| v2.0    | new user | group my cards according to my will into "Deck" | I can easily access all of my cards in a certain category        |
| v2.0    | new user | tag each card into one or more topics           | I can organize my cards according to the topics associated       |
| v2.0    | new user | remove the tag from a card                      | I can ensure that my cards have the correct tag                  |
| v2.0    | new user | see a list of existing tags                     | I can know what are the tags I have                              |
| v2.0    | new user | see the list of cards under a certain tag       | I can know what are the cards I have under a certain tag         |
| v2.0    | new user | rename an existing tag                          | I can update the tag with a more accurate naming                 |

### Non-Functional Requirements

{Give non-functional requirements}

### Glossary

- _glossary item_ - Definition

### Instructions for manual testing

{Give instructions on how to do a manual product testing e.g., how to load sample data to be used
for testing}
