# Developer Guide

## Acknowledgements

- NUS SC2113 Duke project
- [NUS SC2113 tp](https://github.com/nus-cs2113-AY2223S2)

## Design & implementation

==UML HERE==

### [Proposed] Add/Remove feature
The feature allows the librarian to add and remove books from the inventory. A BorrowableItem is instatiated and will be used in the controller.

### [Proposed] Search feature
The feature should allow user to search for a book by title or topic. String matching will be done to check if a book of a similar match is found.

Partial string matching will not be considered in this application. Exact match of title and topic will be done, and if match is found, the book object will be returned.

![](add.puml)

## Product scope
### Target user profile

NUS computer science (CS) students who wish to borrow and read CS related books.

### Value proposition

CS students are incredibly busy and hence having a command line interface (CLI) program without GUI makes finding the books they want quick and efficient. This software will also help them track loans and return dates.

## User Stories

|Version| As a ... | I want to ... | So that I can ...|
|--------|----------|---------------|------------------|
|v1.0|new user|be able to search for a book|borrow a book to read|
|v1.0|libriarian|add or remove book|change the books in the inventory|
|v1.0|user|return book i borrowed|remove the borrow status taged to my account|
|v2.0|libriarian|pay my fine|continue to borrow more books and see my exam results|

## Non-Functional Requirements

1. Data should be stored in text file so that information like borrowed books and inventory are not lost
2. System should run on Java 11

## Glossary

* *glossary item* - Definition

## Instructions for manual testing

{Give instructions on how to do a manual product testing e.g., how to load sample data to be used for testing}
