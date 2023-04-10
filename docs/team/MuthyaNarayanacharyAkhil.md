# Muthya Narayanachary Akhil - Project Portfolio Page

## Overview
Inka is a CLI-based software that allows users to add Cards containing questions and answers,
attach tags into each Card and group cards into decks. Inka aims to help students revise for their exams
by providing a flashcard-like experience

## Summary of Contributions

I have mainly implemented 2 features, and have assisted in the implementation of some others.
### Features Implemented

#### Deck Feature
- **What it does** : This feature allows users to group Card(s) and Tag(s) under a common structure called the Deck.
The core purpose of the Deck is to allow users to aggregate their existing Tag(s) and Card(s) into a singular block of
data which can be constantly referred to! The Deck has many features, they are listed below:

  - `card deck {-c CARD_UUID | -i CARD_INDEX} -d DECK_NAME` --> _Put the Card into a Deck_
  - `tag deck -d DECK_NAME {-t TAG_NAME | -x TAG_INDEX}` --> _Put the Tag into the Deck_
  - `deck edit -o OLD_DECK_NAME -n NEW_DECK_NAME` --> _Edit the name_ 
  - `deck delete -d DECK_NAME [{-c CARD_UUID | -i CARD_INDEX} | {-t TAG_NAME | -x TAG_INDEX}]` --> 
Deleting a Card/Tag from Deck
  - `deck list [-d DECK_NAME]` --> List all decks/ list all Card(s) and Tag(s) under Deck
  - `deck run -d DECK_NAME` --> Run the Deck for the user to revise the questions


- **Justification** : This feature is another layer of grouping cards. Tags can group similar cards and Decks can group
similar Tag(s) and Card(s). This also allows users to generate viable groupings they would wish to test-themselves on.
More often than not, topics are usually related to each other, having three different levels of abstracting data would
allow users to customize their learning experience.


- **Highlights** : Since Deck is an overarching feature, one notable effect is its permeability in the program. Each 
Deck makes use of an `ArrayList`, `HashSet` and `HashMap`. 
  - The purpose of the `ArrayList` is to keep an account
  of all the cards which are individually added to the Deck. 
  - The purpose of the `HashSet` is to aggregate all the cards
  stored under both the `HashMap` and the `ArrayList`. In gist, the `HashSet` contains every single card in the deck,
  stored directly or indirectly through Tag(s). This is used to run the Deck. 
  - The purposed of the `HashMap` is to keep track of 
  all the cards under the various tags in a deck, and keep track of cards which could be shared between decks. 
  This ensures that even if shared cards are deleted from tag or tags are deleted from the deck, the `HashSet` is
updated.


    

### Features Contributed

#### _Tag Feature_

Due to the permeability of the Deck, I contributed to the Tag Feature in the following ways:
- `Tag::addCardIntoDeckHashSet(DeckList, CardUUID)` : Whenever a card is added into the Tag, if the Tag is under any
Deck, the card is added into the `Hashset` and `HashMap`.
- `Tag::addDeck(DeckUUID)` : This function adds the `DeckUUID` of the deck the tag is under to the Tag's internal 
storage of `DeckUUID`.
- `Tag::removeDeck(DeckUUID)` : This function removes the `DeckUUID` from a tag if the tag is deleted or removed from
the Deck.
- `Tag::cardIsInTag(CardUUID)` : This card returns a boolean and lets the user know if the card with the `CardUUID` is
in the Tag
- `Tag::getDecks()` : This command returns an `ArraList<DeckUUID>`, which is representative of the decks the tag is
under.

#### _Card Feature_
I have contributed to Card through the following methods:
- `Card::getDecksUUID()` : This method returns an `ArraList<DeckUUID>`, which is representative of the decks the card 
is under.
- `Card::addDeck(DeckUUID)` : This method adds the `DeckUUID` to the Card's internal storage list of `DeckUUID`
- `Card::removeDecks(DeckUUID)` : This method removes the `DeckUUID` from the Card's internal storage list of 
`DeckUUID`.

### Miscellaneous Contributions

#### _Testing Code_
Here are some tests I wrote in order to gauge the logic:
- `LogicTest::logic_deleteSharedCard()` : Tests if the `HashSet` is able to detect when two tags sharing the same card 
are added to the `Deck` and randomly deleted.
- `LogicTest::logic_unTagCardWhileInDeck()` : Tests if the cards under a tag are removed from the HashSet if the Tag is
removed from the `Deck`.
- `LogicTest::logic_tagCardWhileInDeck()` : Tests if the cards are added to a `HashSet` if new cards are added to a Tag
under the `Deck`.
- `LogicTest::logic_deleteMultipleTags()` : Tests overall functionality of `Deck`.

### Code Contribution
The code contribution analyzed by repo-sense can be found
[here.](https://nus-cs2113-ay2223s2.github.io/tp-dashboard/?search=&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2023-02-17&tabOpen=true&tabType=authorship&tabAuthor=lihka1202&tabRepo=AY2223S2-CS2113-F10-1%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code~other&authorshipIsBinaryFileTypeChecked=false&authorshipIsIgnoredFilesChecked=false)

### Team-based Tasks
Here are the Issues that were assigned to me and have been closed:
- [Refactoring Command and ExceptionHandler #12](https://github.com/AY2223S2-CS2113-F10-1/tp/issues/12)
- [Create exception handler for parsing-related error #24](https://github.com/AY2223S2-CS2113-F10-1/tp/issues/24)
- [Create exception handler for cardList related error (index out of bounds etc) #25](https://github.com/AY2223S2-CS2113-F10-1/tp/issues/25)
- [Create exception class storing various types of exceptions for Inka #26](https://github.com/AY2223S2-CS2113-F10-1/tp/issues/26)
- [Implement the Deck object #50](https://github.com/AY2223S2-CS2113-F10-1/tp/issues/50)
- [Deck should be able to aggregate Card based on a list of CardUUID #51](https://github.com/AY2223S2-CS2113-F10-1/tp/issues/51)
- [(Stretch Goal) Deck should also be able to aggregate Card based on a list of TagUUID #52](https://github.com/AY2223S2-CS2113-F10-1/tp/issues/52)
- [Implement removal of Card from Deck #57](https://github.com/AY2223S2-CS2113-F10-1/tp/issues/57)
- [If an instance of Card has been deleted, its UUID could be deleted from all instances of Decks and Tags it is under #59](https://github.com/AY2223S2-CS2113-F10-1/tp/issues/59)
- [Implement Card instance knowledge of which Deck instance it belongs to #60](https://github.com/AY2223S2-CS2113-F10-1/tp/issues/60)
- [Implement Tag instance knowledge of which Deck it belongs to #61](https://github.com/AY2223S2-CS2113-F10-1/tp/issues/61)
- [Meet Week 10 tP requirements #73](https://github.com/AY2223S2-CS2113-F10-1/tp/issues/73)
- [Ensure that the same Card cannot be added to the Deck #74](https://github.com/AY2223S2-CS2113-F10-1/tp/issues/74)
- [Ensure that the same Card cannot be added to the same Tag. #75](https://github.com/AY2223S2-CS2113-F10-1/tp/issues/75)
- [Ensure that the same Tag cannot be added to the Deck again #76](https://github.com/AY2223S2-CS2113-F10-1/tp/issues/76)
- [[PE-D][Tester D] Error Message for creating deck #116](https://github.com/AY2223S2-CS2113-F10-1/tp/issues/116)
- [[PE-D][Tester D] Fail to delete deck #123](https://github.com/AY2223S2-CS2113-F10-1/tp/issues/123)
- [[PE-D][Tester A] List tags under a deck possible documentation error #134](https://github.com/AY2223S2-CS2113-F10-1/tp/issues/134)
- [DG deck feature #171](https://github.com/AY2223S2-CS2113-F10-1/tp/issues/171)
- [Combine add/delete cards for Deck #177](https://github.com/AY2223S2-CS2113-F10-1/tp/issues/177)
- [Check if the Help command is up to date #182](https://github.com/AY2223S2-CS2113-F10-1/tp/issues/182)
- [deck run should warn if the user enters any input #187](https://github.com/AY2223S2-CS2113-F10-1/tp/issues/187)
- [Fix logic issues #189](https://github.com/AY2223S2-CS2113-F10-1/tp/issues/189)
- [Modify deck list behaviour #194](https://github.com/AY2223S2-CS2113-F10-1/tp/issues/194)
- [Settle up UG for Deck #198](https://github.com/AY2223S2-CS2113-F10-1/tp/issues/198)

Here is a list of PRs of mine that have been merged:
- [refactor: refactored exceptions into InkaExceptions #34](https://github.com/AY2223S2-CS2113-F10-1/tp/pull/34)
- [feat: added Deck #68](https://github.com/AY2223S2-CS2113-F10-1/tp/pull/68)
- [feat: Added Card and Tag removal from Deck #70](https://github.com/AY2223S2-CS2113-F10-1/tp/pull/70)
- [feat: Added Deck under the Help Command #85](https://github.com/AY2223S2-CS2113-F10-1/tp/pull/85)
- [feat: Added Command Summary #96](https://github.com/AY2223S2-CS2113-F10-1/tp/pull/96)
- [feat: Added Card and Tag aggregation into the Deck and Added Run Mode #176](https://github.com/AY2223S2-CS2113-F10-1/tp/pull/176)
- [feat: resolved #189, #187 #191](https://github.com/AY2223S2-CS2113-F10-1/tp/pull/191)
- [feat: resolved issues(#194, #134, #182) and added some tests for Tags and Decks #206](https://github.com/AY2223S2-CS2113-F10-1/tp/pull/206)
- [feat: DeckList and Deck Feature Added into the DG, UG checked #211](https://github.com/AY2223S2-CS2113-F10-1/tp/pull/211)


### Documentation

#### User Guide
I contributed the documentation with the following features:

- `card deck {-c CARD_UUID | -i CARD_INDEX} -d DECK_NAME` --> _Put the Card into a Deck_
- `tag deck -d DECK_NAME {-t TAG_NAME | -x TAG_INDEX}` --> _Put the Tag into the Deck_
- `deck edit -o OLD_DECK_NAME -n NEW_DECK_NAME` --> _Edit the name_
- `deck delete -d DECK_NAME [{-c CARD_UUID | -i CARD_INDEX} | {-t TAG_NAME | -x TAG_INDEX}]` -->
  Deleting a Card/Tag from Deck
- `deck list [-d DECK_NAME]` --> List all decks/ list all Card(s) and Tag(s) under Deck
- `deck run -d DECK_NAME` --> Run the Deck for the user to revise the questions

I also contributed by creating and formatting the **Command Summary**, which can be seen below:

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




#### Developer Guide
