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

## Access Management
The application requires user to login before performing other actions.

### Login: `login`
Login the system using username and password. 

Format:
* login -username USERNAME -password PASSWORD

Examples:
* login -username me -password my$password

### Signup: `signup`
Sign up a new account with a unique username, password and user's actual name.

Format: 
* signup -username USERNAME -password PASSWORD -name NAME

Examples:
* signup -username me -password my$password -name My Name

### Change password: `password`
Change the password of a user.

Format: 
* password -username USERNAME -old OLD_PASSWORD -new NEW_PASSWORD

Examples:
* password -username me -old old$password -new new$password

### Logout: `logout`
Logout the user. This command can only be used after logging in. The user can 
then log in again with the same / different account.  

Format:
* logout

Examples: 
* logout

### Exit the application: `exit`
Exits the application. This command should be executed only after logging out 
of the system.  

Format: 
* exit

Examples:
* exit

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
