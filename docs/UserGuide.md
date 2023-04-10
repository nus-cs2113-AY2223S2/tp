# User Guide

## Introduction

Inka is a CLI-based software that allows users to add Cards containing questions and answers, attach tags into each Card
and put groups of cards into a deck. Inka aims to help students revise for their exam by providing a flashcard-like
experience.

## Table of Contents

Use the hyperlinks for ease of access!

- [Quick Start](#quick-start)
    - [Use Case](#use-case)
- [Usage of Flags](#usage-of-flags)
- [Features](#features)
    - [Get Help Manual](#get-help-manual)
    - [Adding a card](#adding-a-card)
    - [Listing all the cards](#listing-all-cards)
    - [Deleting a card](#deleting-a-card)
    - [Tagging a card](#tagging-a-card)
    - [Removing a tag from a card](#removing-a-tag-from-a-card)
    - [Putting a card into a deck](#putting-a-card-into-a-deck)
    - [Viewing a card](#viewing-a-card)
    - [Editing a tag](#editing-a-tag)
    - [Deleting a tag](#deleting-a-card)
    - [List all tags](#list-all-tags)
    - [Put all cards under a tag into a deck](#put-all-cards-under-a-tag-into-a-deck)
    - [Edit a deck](#edit-a-deck)
    - [Delete a card/tag from deck](#delete-a-cardtag-from-deck)
    - [List all decks](#list-all-decks)
    - [Run the deck](#run-the-deck)
    - [Exit the Program](#exit-the-program)
- [FAQ](#faq)
- [Command Summary](#command-summary)

## Quick Start

1. Ensure that you have Java 11 or above installed.
2. Download the latest `.jar` file from [here](https://github.com/AY2223S2-CS2113-F10-1/tp/releases).
3. Copy the JAR file to the folder you wish to use as the Inka home folder -- your data will be saved here!
4. Open the JAR file, either by clicking or running `java -jar <FILENAME>.jar`. A command line interface should appear:

![Inka interface](img/quick-start.png)

Enjoy your revision!

### Use Case

Cards that you add to Inka can be organized using tags, and decks can be flexibly constructed either by adding **individual cards** and/or multiple cards with the **same tag**.

For example, suppose you had the following cards:

|            | **Question**                 | **Answer**                  | **Tags**                 |
|------------|------------------------------|-----------------------------|--------------------------|
| **Card 1** | What does UML stand for?     | Unified Markup Language     | _cs2113-wk8_             |
| **Card 2** | What is my favourite module? | CS2113                      | _cs2113-wk1, freq-wrong_ |
| **Card 3** | Is P = NP?                   | Most definitely not         | _freq-wrong_             |
| **Card 4** | What does OOP stand for?     | Object-Oriented Programming | _cs2113-wk8_             |

You might organize your cards in the following fashion:

- Adding the `cs2113-wk1` and `cs2113-wk8` tags to group together cards of similar topics
- Adding the `freq-wrong` tag to cards that you wish to review more frequently

This allows you to create the following decks easily:

| **Deck Name**    | **Added Card(s)** | **Added Tag(s)**         | **Cards in Deck** |
|------------------|-------------------|--------------------------|-------------------|
| CS2113           | -                 | _cs2113-wk1, cs2113-wk8_ | Cards 1, 2, 4     |
| Frequently-Wrong | -                 | _freq-wrong_             | Cards 2, 3        |
| Acronyms         | Card 3            | _cs2113-wk8_             | Cards 1, 3, 4     |

## Usage of flags

Inka's commands makes substantial use of flags to indicate certain parameters that users enter. In this guide, we will adopt the following syntax similar to many Unix CLI tools:

- Required arguments are indicated by `-f ARG`
- Optional arguments are indicated by `[-f ARG]`
- Mutually exclusive required arguments are `{-a ARG | -b ARG}`
- Mutually exclusive optional arguments are `[-a ARG | -b ARG]`

There will be many instances where users have to identify a card, tag or deck in a command. For ease of use, there are multiple ways to select a card or tag:
- Selecting a card
  - By UUID: `-c CARD_UUID`
  - By index: `-i CARD_INDEX`:
- Selecting a tag
  - By tag name: `-t TAG_NAME`
  - By index: `-x TAG_INDEX`
- Selecting a deck
  - Only by deck name: `-d DECK_NAME`

For instance, in `card untag` command, since users are required to specify a card and a tag, the format of syntax in the documentation below will be written as:

`card untag {-c CARD_UUID | -i CARD_INDEX} {-t TAG_NAME | -x TAG_INDEX}`

The user can then specify the card of their choice **either** via the card's UUID or index, and similarly for the tag. For more information, refer to `card list`, `tag list` or `deck list`.

## Features

### Get help manual

Users can run `help` command to see the list of commands available for each feature.

***Sample output :***

```
____________________________________________________________________________________________________
Welcome to Inka! Type help at any time to show this message
== Deck Management (run `<keyword> help` for more info) ===
card         - Card-related functionality
tag          - Tag-related functionality
deck         - Deck-related functionality
================== Miscellaneous Commands =================
export       - Manually exports to save file
bye          - Exits Inka
____________________________________________________________________________________________________
```

Users can then run :

- `card help` for features related to cards
- `tag help` for features related to tags
- `deck help` for features related to decks

***Sample output for `tag help` :***

```
____________________________________________________________________________________________________
usage: tag edit -o OLD_TAG_NAME -n NEW_TAG_NAME
Edit existing tags
-n,--new <arg>          New tag name
-o,--old <arg>          Old tag name

usage: tag delete {-t TAG_NAME | -x TAG_INDEX}
Delete tags
-t,--tag <arg>               tag name
-x,--tagindex <arg>          tag index

usage: tag list [-t TAG_NAME | -x TAG_INDEX]
List tags
-t,--tag <arg>               tag name (optional)
-x,--tagindex <arg>          tag index (optional)

usage: tag deck -d DECK_NAME {-t TAG_NAME | -x TAG_INDEX}
Adding tag to deck
-d,--deck <arg>              deck name
-t,--tag <arg>               tag name
-x,--tagindex <arg>          tag index
____________________________________________________________________________________________________
```

### Adding a card

`card add -q QUESTION -a ANSWER`

Adds a new Card with its question and answer.

***Lists of flags (in any order)*** :

- `-q` followed by a String representing the question
- `-a` followed by a String representing the answer

Users can refer to this [section](#usage-of-flags) to recap on how the flag works.

While there is no restriction on then length of the question/answer that the user can enter, these will be truncated in the display of `card list` command if longer than 50 characters. The full version can be viewed using the `card view` command below.

***Example of usage:***

`card add -q how do i use this command? -a by referring to this user guide`

***Sample Output :***

```
That's a good question for revision later!
You now have 4 questions in the bank.
```

---
**Note:** To avoid confusion, `card add` does not support the question/answer being "-q" or "-a". If the user so wishes to use such characters in their question/answer, it is recommended that they provide it as such:
```
# Do not do this!
card add -q What is the flag for question? -a -q

# Instead, do this
card add -q What is the flag for question? -a "-q"
```

### Listing all cards

`card list`

List all existing cards in Inka.

***Lists of flags (in any order)*** :

- ***NONE***

***Sample output:***

```
Here is a list of your cards :

	1.	[1ddd9a67-f56c-4914-99c0-2f90c580f0e9]
	Qn:	What is the formula of force?
	Ans:    F = ma

	2.	[619c689d-395a-4bb8-ab00-6ae9972bb929]
	Qn:	How efficient is binary search?
	Ans:    O(log n)

	3.	[29bea83e-d864-48c4-bb9a-7fa817114fe1]
	Qn:	how do i use this command?
	Ans:	by referring to this user guide

	4.	[19d859b1-cede-467e-b384-7d6e690cdae6]
	Qn:	Lorem ipsum dolor sit amet, consectetur adipiscing
	Ans:	ans
	Note:	Actual question or answer is too long, string truncated
```

In `1.    [1ddd9a67-f56c-4914-99c0-2f90c580f0e9]` above, the `1` refers the card index and the
`1ddd9a67-f56c-4914-99c0-2f90c580f0e9` refers the UUID of the card. User can choose whether to refer to the card by UUID
or card index.

### Deleting a card

`card delete {-c CARD_UUID | -i CARD_INDEX}`

Delete an existing card based on its UUID or its card index. The card will be removed from Inka as well as from
any tag and deck
that it was previously attached to.

***Lists of flags (in any order)*** :

- `-c` the UUID of the card to be deleted
- `-i` the index of the card to be deleted

Users can refer to this [section](#usage-of-flags) to recap on how the flag works.

***Example of usage :***

`card delete -c f8c3de3d-1fea-4d7c-a8b0-29f63c4c3454`

`card delete -i 3`

***Sample output :***

```
Too easy ha? You won't see that question again!
You now have 2 questions in the bank.
```

### Tagging a card

`card tag {-c CARD_UUID | -i CARD_INDEX} {-t TAG_NAME | -x TAG_INDEX}`

Tags a card by its UUID or card index with a specific tag name. If the tag does not exist, Inka will create a new one.
Otherwise, Inka will just tag the card.

***Lists of flags (in any order)*** :

- `-c` the UUID of the card to be tagged
- `-i` the index of the card to be tagged
- `-t` the name of the tag ***(no whitespaces allowed), must not*** exceed 50 characters
- `-x` the index of the tag to be removed

Users can refer to this [section](#usage-of-flags) to recap on how the flag works.

***Example of usage :***

`card tag -c f8c3de3d-1fea-4d7c-a8b0-29f63c4c3454 -t physics`

`card tag -i 1 -t physics`

***Sample output :***

```
Tag does not exist.. creating a new tag: physics
Successfully added tag physics to card f8c3de3d-1fea-4d7c-a8b0-29f63c4c3454
```

### Removing a tag from a card

`card untag {-c CARD_UUID | -i CARD_INDEX} {-t TAG_NAME | -x TAG_INDEX}`

Removes the specified Tag based on tag name or tag index from a specified Card based on its uuid or card index. The
tag index can be found in the later section from `tag list`.

***Lists of flags (in any order)*** :

- `-c` the UUID of the card to be untagged
- `-i` the index of the card to be untagged
- `-t` the name of the tag to be removed ***(no whitespaces allowed)***, ***must not*** exceed 50 characters
- `-x` the index of the tag to be removed

Users can refer to this [section](#usage-of-flags) to recap on how the flag works.

***Example of usage :***

`card untag -c f8c3de3d-1fea-4d7c-a8b0-29f63c4c3454 -t physics`

`card untag -i 1 -x 1`

***Sample output :***

```
Successfully removed tag physics from card f8c3de3d-1fea-4d7c-a8b0-29f63c4c3454
```

### Putting a card into a deck

`card deck {-c CARD_UUID | -i CARD_INDEX} -d DECK_NAME`

Specify the card based on its UUID or card index and put it in the deck specified by deck name. If the deck does not
exist, Inka will create a new one.
Otherwise, Inka will just put the card in the deck

***Lists of flags (in any order)*** :

- `-c` the UUID of the card to be tagged
- `-i` the index of the card to be tagged
- `-d` the name of the deck ***(no whitespaces allowed)***, ***must not*** exceed 50 characters

Users can refer to this [section](#usage-of-flags) to recap on how the flag works.

***Example of usage :***

`card deck -c f8c3de3d-1fea-4d7c-a8b0-29f63c4c3454 -d deckTest`

`card deck -i 1 -d midterms`

***Sample output :***

```
Deck does not exist.. creating a new one
Successfully added card f8c3de3d-1fea-4d7c-a8b0-29f63c4c3454 to deck midterms
```

### Viewing a card

`card view {-c CARD_UUID | -i CARD_INDEX}`

View the content of a Card based on its UUID or card index. This feature will allow users to have a more comprehensive
view of the card that was
not shown previously in `card list` such as the full version of the questions and answers if they are too long, and the
list of `tags` and `decks` that
the card belongs to.

***Lists of flags (in any order)*** :

- `-c` the UUID of the card to be viewed.
- `-i` the index of the card to be viewed.

Users can refer to this [section](#usage-of-flags) to recap on how the flag works.

***Example of usage :***

`card view -c f8c3de3d-1fea-4d7c-a8b0-29f63c4c3454`

`card view -i 3`

***Sample output :***

```
    [19d859b1-cede-467e-b384-7d6e690cdae6]
	Qn:  asdf sdaf
	Ans:  asdfs

	Here are your tags:
	1.	Tag name : tag

	There is no deck.
```

Similar to before `[19d859b1-cede-467e-b384-7d6e690cdae6]` refers to the UUID of the card that the user is currently
viewing.

---

### Editing a tag

`tag edit -o OLD_TAG_NAME -n NEW_TAG_NAME`

Edit the name of an existing tag by specifying the old name and new name. This change of tag name will also take effect
in `tag list` and `card view`

***Lists of flags (in any order)*** :

- `-o` the old name of the tag,  ***(no whitespaces allowed)***, ***must not*** exceed 50 characters
- `-n` the new name of the tag,  ***(no whitespaces allowed)***, ***must not*** exceed 50 characters

Users can refer to this [section](#usage-of-flags) to recap on how the flag works.

and they cannot be empty.

Example of usage :
`tag edit -o CS2113 -n CS2113T`

Sample output :

```
Tag name has been changed from CS2113 to CS2113T
```

### Deleting a tag

`tag delete {-t TAG_NAME | -x TAG_INDEX}`

Delete an existing tag by name or by index and delete this tag under all cards that it was previously attached to.

***Lists of flags (in any order)*** :

- `-t` the name of the tag to be deleted,  ***(no whitespaces allowed)***, ***must not*** exceed 50 characters
- `-x` the index of the tag to be deleted

Users can refer to this [section](#usage-of-flags) to recap on how the flag works.

***Example of usage :***

`tag delete -t physics`

`tag delete -x 1`

***Sample output :***

```
Successfully removed tag physics from card 3b86b31c-6289-4716-a5c6-5afd43b9bbd3
Successfully removed tag physics from the tag list.
```

### List all tags

`tag list [-t TAG_NAME | -x TAG_INDEX]`

List all current tags in Inka ***(with no flags)***. Users can also list all the cards that fall under this tag by
specifying the
tag either through its tag name or tag index

***Lists of flags (in any order)*** :

- [OPTIONAL] `-t` the name of the tag,  ***(no whitespaces allowed)***, ***must not*** exceed 50
  characters
- [OPTIONAL] `-x` the index of the tag

Users can refer to this [section](#usage-of-flags) to recap on how the flag works.

***Example of usage :***

`tag list`

`tag list -t physics`

`tag list -x 1`

***Sample output :***

```
Here is your current list of tags:
1. Tag name : physics
2. Tag name : chem
3. Tag name : math
4. Tag name : bio
```

```
Here is a list of your cards :

1.	[1ddd9a67-f56c-4914-99c0-2f90c580f0e9]
Qn:	What is the formula of force?
Ans:	F = ma

2.	[619c689d-395a-4bb8-ab00-6ae9972bb929]
Qn:	How efficient is binary search?
Ans:	O(log n)
```

### Put all cards under a tag into a deck

```tag deck -d DECK_NAME {-t TAG_NAME | -x TAG_INDEX}```

This feature conveniently allows users to insert all the cards that fall under the specified tag (either by tag name or
tag index) into a specified deck.

***Lists of flags (in any order)*** :

- `-t` the name of the tag to be inserted into the deck,  ***(no whitespaces allowed)***, ***must not*** exceed 50
  characters
- `-x` the index of the tag to be inserted into the deck
- `-d` the name of the deck to insert into, ***(no whitespaces allowed)***, ***must not*** exceed 50

Users can refer to this [section](#usage-of-flags) to recap on how the flag works.

***Example of usage :***

`tag deck -d midterm -t physics`

`tag deck -d midterm -x 1`

***Sample output:***

```
Deck does not exist.. creating a new one
Successfully added tag physics to deck midterm
```

---

### Edit a deck

`deck edit -o OLD_DECK_NAME -n NEW_DECK_NAME`

Rename an existing deck

***Lists of flags (in any order)*** :

- `-o` the old name of the deck,  ***(no whitespaces allowed)***, ***must not*** exceed 50
  characters
- `-n` the new name of the deck,  ***(no whitespaces allowed)***, ***must not*** exceed 50
  characters

Users can refer to this [section](#usage-of-flags) to recap on how the flag works.

***Example of usage:***

```
deck edit -o old-deck-name -n new-deck-name
```

***Sample output:***

```
Deck name has been changed from old-deck-name to new-deck-name
```

### Delete a card/tag from deck
`deck delete -d DECK_NAME [{-c CARD_UUID | -i CARD_INDEX} | {-t TAG_NAME | -x TAG_INDEX}]`

***Lists of flags (in any order)*** :

- `-d` the name of the deck to be deleted (the entire deck and its content will be deleted),
  ***(no whitespaces allowed)***, ***must not*** exceed 50
  characters
- [OPTIONAL] `-t` the tag name to be removed from the deck,  ***(no whitespaces allowed)***, ***must not*** exceed 50
  characters
- [OPTIONAL] `-c` the uuid of the card to be removed from the deck

Users can refer to this [section](#usage-of-flags) to recap on how the flag works.

Users are given the following options depending on the flags specified above:

- Remove a Card from an existing deck
- Remove a Tag and all the Cards under the Tag from an existing deck
- Remove the whole deck and all its content

To delete a card from an existing deck

***Example of usage:***

`deck delete -c c2c61475-df53-4656-94c4-c2e36933d359 -d my-deck`

***Sample output:***

```
Successfully removed card c2c61475-df53-4656-94c4-c2e36933d359 from deck my-deck
```

Alternatively, delete a tag from an existing deck

***Example of usage:***

`deck delete -t physics -d my-deck`

***Sample output:***

```
Successfully removed tag physics from deck my-deck
```

The entire deck can also be deleted (cards and tags that were in the deck are not deleted)

***Example of usage:***

`deck delete -d my-deck`

***Sample output:***

```
Successfully removed deck my-deck from card c2c61475-df53-4656-94c4-c2e36933d359
Successfully removed deck my-deck from the deck list.
```

### List all decks

`deck list [-d DECK_NAME]`

List all current decks in Inka ***(with no flags)***. Users can also list all the cards and tags that fall under this
deck by
specifying the deck name

***Lists of flags (in any order)*** :

- [OPTIONAL] `-d` the content of the name of the deck to be displayed,  ***(no whitespaces allowed)***, ***must not***
  exceed 50
  characters

Users can refer to this [section](#usage-of-flags) to recap on how the flag works.

To list all decks that have been created :

***Example usage:***

`deck list`

***Sample output:***

```
Here is your current list of decks:
1. Deck name : test-deck
2. Deck name : another-deck
```

To list all the Cards and Tags under the deck:

***Example of usage:***

```
deck list -d physicsdeck
```

***Sample output:***

```
Here is a list of your cards :

	1.	  [924119c1-a807-4df2-b311-080be9ee8522]
	Qn:	  What is the formula of GPE?
	Ans:  E = mgh

Here is your current list of tags:
1. Tag name : physics
```

### Run the Deck

`deck run -d DECK_NAME`

In order to use `deck run`, the following key pointers must be kept in mind:

1. Once you type the command in, the question would be printed first. Do note that **you are only required to hit press
   enter. Pressing enter would inform the program to display the answer**
2. If you wish to exit, the type _exit_, and the program should jump out of `run mode` and bring you back to normal
   functioning
3. If a user were to type anything, Inka would prompt the user not to type anything and display the answer.

Here are some examples:

- _Example where the user only presses an enter!_

```
> deck run -d physicsdeck
	Q: What is the formula of GPE?

	A: E = mgh

	Q: How efficient is binary search?

	A: O(log n)

	Q: What is the formula of force?

	A: F = ma

```

- _Example where the user wishes to exit_:

```
> deck run -d physicsdeck
	Q: What is the formula of GPE?

	A: E =mgh

	Q: How efficient is binary search?
exit
Exiting run mode!

```

- _Example where the user enters something other than exit or enter_:

```
> deck run -d physicsdeck
	Q: What is the formula of GPE?
asgsdfgsfdgsdfg
There is no need to input any characters!
Just hitting enter is sufficient to show the answer! Anyway, here is the answer!

	A: E =  mgh

	Q: How efficient is binary search?
sfgsdfgsdfgsdfg
There is no need to input any characters!
Just hitting enter is sufficient to show the answer! Anyway, here is the answer!

	A: O(log n)

	Q: What is the formula of force?
dfgsdfgsdfgsdfgsdfg
There is no need to input any characters!
Just hitting enter is sufficient to show the answer! Anyway, here is the answer!

	A: F = ma
```

---

### Exit the program

User simply needs to run `bye`

Sample output :

```
 ____  _  _  ____    _   
(  _ \( \/ )(  __)  / \  
 ) _ ( )  /  ) _)   \_/  
(____/(__/  (____)  (_) 

 Bye! All the best for your exams man!!!
```

## FAQ

**Q**: Will deleting a card from a tag delete it from multiple decks?

**A**: Deleting a card from a tag deletes the card from all decks that contain its tag.

**Q**: Can I modify savedata.json to manually add cards?

**A**: No, data as the card ID is generated by the app. Please add cards through the app instead.

**Q**: Can I add multiple cards at the same time?

**A**: No, please add them one at a time using `card add`

**Q**: Can I transfer my cards to another computer?

**A**: You do so by following these steps:

1. Export the deck as a Json file using the command "export". It may take a moment for the file to be written, so please wait, or try refreshing your file explorer!
2. Navigate to the directory of your Inka installation.
3. The file will be named savedata.json. Copy this file.
4. Paste this file in the Inka's directory in the new computer

Your file will be loaded the next time you run Inka!

## Command Summary
**Note:** Flags can be given in any order!

| **Command**                      | **Format**                                                                 |
|----------------------------------|----------------------------------------------------------------------------|
| Create a card                    | `card add -q QUESTION -a ANSWER`                                           |
| Create a tag                     | `card tag {-c CARD_UUID \| -i CARD_INDEX} {-t TAG_NAME \|  -x TAG_INDEX}`  |
| Create a deck (using card)       | `card deck {-c CARD_UUID \| -i CARD_INDEX} -d DECK_NAME`                   |
| Create a deck (using tag)        | `card deck {-t TAG_NAME \| -x TAG_INDEX} -d DECK_NAME`                     |
| Add a tag to a card              | `card tag {-c CARD_UUID \| -i CARD_INDEX} {-t TAG_NAME \| -x TAG_INDEX}`   |
| Untag a card                     | `card untag {-c CARD_UUID \| -i CARD_INDEX} {-t TAG_NAME \| -x TAG_INDEX}` |
| Add a card to a deck             | `card deck {-c CARD_UUID \| -i CARD_INDEX} -d DECK_NAME`                   |
| Add a tag to a deck              | `tag deck -d DECK_NAME {-t TAG_NAME \| -x TAG_INDEX}`                      |
| Delete a card                    | `card delete {-c CARD_UUID \| -i CARD_INDEX}`                              |
| Delete a tag                     | `tag delete {-t TAG_NAME \| -x TAG_INDEX}`                                 |
| Delete a deck                    | `deck delete -d DECK_NAME`                                                 |
| Delete a card from a deck        | `deck delete -d DECK_NAME {-c CARD_UUID \| -i CARD_INDEX}`                 |
| Delete a tag from a deck         | `deck delete -d DECK_NAME {-t TAG_NAME \| -x TAG_INDEX}`                   |
| List cards                       | `card list`                                                                |
| List tags                        | `tag list`                                                                 |
| List cards under a tag           | `tag list {-t TAG_NAME \| -x TAG_INDEX}`                                   |
| List decks                       | `deck list`                                                                |
| List cards and tags under a deck | `deck list -d DECK_NAME`                                                   |
| View a card                      | `card view {-c CARD_UUID \| -i CARD_INDEX}`                                |
| Run the `deck`                   | `deck run -d DECK_NAME`                                                    |
| Edit a card                      | `card edit -n NEW_CARD_NAME -o OLD_CARD_NAME`                              |
| Edit a tag                       | `tag edit -n NEW_TAG_NAME -o OLD_TAG_NAME`                                 |
| Edit a deck                      | `deck edit -n NEW_DECK_NAME -o OLD_DECK_NAME`                              |
| Exits the program                | `bye`                                                                      |
| Help about deck                  | `deck help`                                                                |
| Help about tag                   | `tag help`                                                                 |
| Help about card                  | `card help`                                                                |
| Help in general                  | `help`                                                                     |
