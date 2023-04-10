# Lee Tze Han's Project Portfolio Page

## Project: Inka

Inka is a CLI-based software that allows users to add Cards containing questions and answers, attach tags into each Card and put groups of cards into a deck. Inka aims to help students revise for their exam by providing a flashcard-like experience.

## Code Contribution

[Reposense Report](https://nus-cs2113-ay2223s2.github.io/tp-dashboard/?search=&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2023-02-17&tabOpen=true&tabType=authorship&tabAuthor=ltzehan&tabRepo=AY2223S2-CS2113-F10-1%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code~other&authorshipIsBinaryFileTypeChecked=false&authorshipIsIgnoredFilesChecked=false)

## Enhancements Implemented

Refactored parser into a hierarchial parser structure using the `Parser` and `KeywordParser` classes.

- `Parser` dispatches the appropriate `KeywordParser` derived class according to the keyword in the user's input
- `KeywordParser` is the interface between Apache Commons CLI's parser and Inka's parsers. The choice to integrate a third-party parser is to avoid regex-based parsers that are difficult to maintain, and allows for more features such as required/optional flags, flexible flag ordering and etc.
- Parsing for flag arguments with whitespaces (e.g. card questions & answers) are more involved, requiring logic encapsulated in `OptionsBuilder::buildMultipleTokenOption`
- `InvalidSyntaxException` provides useful error messages with formatted flags to the user, which is converted from Apache's exceptions in `KeywordParser`
- Initial implementation for creating `Options` for card and tag parsing
- Attempt to make Apache's help formatter less poor

Design for selectors (`CardSelector`, `TagSelector`)

- Implemented `CardSelector` to allow the user to specify a card either by its `CardUUID` or corresponding index in `card list`
- Main difficulty is that converting between the two card representations may require `CardList`, which is only available in `Command::execute`, so this serves as a container to defer card selection

Support for simple comparison of `InkaUUID` classes
- For our custom UUID classes to work with Java container, `Object::equals` and `Object::hashCode` have to  since previous matching was done on underlying `UUID`

## UG Contributions

- Quick start
- Example use case
- Standardization for syntax formatting [#145](https://github.com/AY2223S2-CS2113-F10-1/tp/issues/145)
- Fixes for formatting

## DG Contributions

- Architecture diagram
- Parser design considerations, class diagram, and sequence diagram
- CardSelector/TagSelector design considerations and class diagram

![](../img/Architecture.svg)
![](../img/ParserSequence.svg)
![](../img/ParserClass.svg)
![](../img/SelectorClass.svg)

## Review Contributions

PRs with design comments:

- [#41](https://github.com/AY2223S2-CS2113-F10-1/tp/pull/41)
- [#43](https://github.com/AY2223S2-CS2113-F10-1/tp/pull/43)
- [#153](https://github.com/AY2223S2-CS2113-F10-1/tp/pull/153)

## Team-based tasks Contribution

- Refactoring and offering comments for possible refactoring
- Cleaning up GitHub issues
- Test-driven bug fixing ([#188](https://github.com/AY2223S2-CS2113-F10-1/tp/pull/188)) and pushed for implementing regression tests
- Set up IntelliJ formatter configuration file
- Teaching them the wonders of IntelliJ shortcuts
