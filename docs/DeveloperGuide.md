# Developer Guide

## Acknowledgements

- NUS SC2113 Duke project
- [NUS SC2113 tp](https://github.com/nus-cs2113-AY2223S2)

## Design & implementation

==UML HERE==

All commands will be fetched by the getUserCommand() method in the TextUi class. The parseCommand() method in the Parser class will then parse the command and pass it to the respective command classes. The command classes will be responsible for executing the relevant features.

### [Proposed] Search book by title

![](search.puml)
Description: Searches for a book using the title
Format: search -title TITLE
Example:
- search -title Python Programming
For successful searches, the program will output the relevant book along with the book details: ISBN, Title, Author, Topic. For unsuccessful searches, the program will output a string informing the user that there is no match with the input title from the inventory.

Note: Partial string matching will not be considered in this application. Exact match of title and topic will be done, and if match is found, the book object will be returned.

### [Proposed]  Search book by topic
Description: Searches for a book by its topic
Format: search -topic TOPIC
Example:
- search -topic Business
For successful searches, the program will output the relevant books along with the book details: ISBN, Title, Author, Topic. For unsuccessful searches, the program will output a string informing the user that there is no match with the input topic from the inventory.


### [Proposed]  Borrow book
Description: Borrow a book from the library
Format: borrow -title TITLE
Example:
- borrow -title Python Programming
For successful borrowing, the program will output a string showing that the action is successful and, at the same time, mark the book as borrowed in the system. For unsuccessful borrow requests, the program will either output that there is no such book in the inventory or a message showing that the book is already on loan at the time of the borrow request.


### [Proposed]  Check book availability
Description: Check if a book is available for borrowing
Format: avail -title TITLE
Example:
- check -title Python Programming
The program will indicate whether the book is available for borrowing. The program will also handle the case that there is no such book in the inventory.

### [Proposed]  Renew borrow period of book
Description: Renew borrowing of books for a fixed duration
Format: renew -title TITLE
Example:
- renew -title C++Primer
For successful renewal of books, the program will output a string showing that the action is successful and also change the due period of borrow in the system. The program will handle error cases such as incorrect titles provided or books not available for renewal.


### [Proposed]  Check borrowing status
Description: Check status of borrowed book
Format: status -title TITLE
Example:
- status -title C++Primer
The program will output the details of the relevant book being borrowed and also show the due date of the loan. The program will handle cases where there is no such book in the borrow history.


### [Proposed]  Return book
Description: Return a book to the library
Format: return -title TITLE
Example:
- return -title C++Primer
For successful returns of books, the program will output a string showing that the action is successful and mark the book as available for borrowing by other users in the system. The program will handle cases of incorrect title input and unsuccessful returns.


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
