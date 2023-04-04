# User Guide

## Introduction

Product Name: Duke of Books

### Target user profile:
NUS computer science (CS) students who wish to borrow and read CS related books.

### Problem addressed:
CS students are incredibly busy and hence having a command line interface (CLI) program without GUI makes finding the books they want quick and efficient. This software will also help them track loans and return dates.


## Quick Start

1. Ensure that you have Java 11 or above installed.
1. Down the latest version of `DukeOfBooks` from [here](https://github.com/AY2223S2-CS2113-F10-4/tp).

## Features 

### Searching for book: `search`
Search for a book with matching title or topic. Only strings with exact match will be returned.

Format:
- search -title TITLE
- search -topic TOPIC

Examples:
- search -title Introduction to OOP
- search -topic java

### Adding/Deleting book: `librarian`
Adds a new book to the library for users. This is a superuser method and should only be used by the librarian.

Format:
- librarian -title TITLE -topic TOPIC -author AUTHOR -isbn ISBN -action add
- librarian -title TITLE -topic TOPIC -author AUTHOR -isbn ISBN -action delete

Examples:
- librarian -title Introduction to OOP -topic java -author Java legends -isbn 1234567890123 -action add
- librarian -title Introduction to OOP -topic java -author Java legends -isbn 1234567890123 -action delete

## FAQ

**Q**: How do I transfer my data to another computer? 

**A**: Currently data is not stored and there is no way to transfer data at the moment

## Command Summary

{Give a 'cheat sheet' of commands here}

* Add todo `todo n/TODO_NAME d/DEADLINE`
