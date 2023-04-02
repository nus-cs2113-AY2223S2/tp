# User Guide

## Introduction

{Give a product intro}

## Quick Start

{Give steps to get started quickly}

1. Ensure that you have Java 11 or above installed.
1. Down the latest version of `Duke` from [here](http://link.to/duke).

## Features 

{Give detailed description of each feature}

### Adding a todo: `todo`
Adds a new item to the list of todo items.

Format: `todo n/TODO_NAME d/DEADLINE`

* The `DEADLINE` can be in a natural language format.
* The `TODO_NAME` cannot contain punctuation.  

Example of usage: 

`todo n/Write the rest of the User Guide d/next week`

`todo n/Refactor the User Guide to remove passive voice d/13/04/2020`

### `list companies` - Shows all current stored companies
Shows all tasks currently stored in the company list.

Example of usage:
`list companies`
Expected outcome: 

```
1
Company name: APPLE
Company contact number: 89934125
Company contact email: STEVENJOBS@APPLE.COM.SG
Company industry: TECHNOLOGY
[Unconfirmed]
2
Company name: KFC
Company contact number: 81213999
Company contact email: FRIEDCHICKEN@KFC.COM.SG
Company industry: ENGINEERING
[Unconfirmed]
3
Company name: DBS
Company contact number: 82920010
Company contact email: IBANKING@GMAIL.COM
Company industry: BANKING AND FINANCE
[Confirmed]
```

### `confirm` - Marks a specific company's attendance as confirmed
Mark the status of a specific company's attendance as confirmed, and 
updates a status icon to [Confirmed] that represents it being marked confirmed.

Example of usage:
`confirm 1`
Expected outcome:

```
Company has been successfully confirmed!

```

### `unconfirm` - Marks a specific company's attendance as unconfirmed
Mark the status of a specific company's attendance as unconfirmed, and
updates a status icon to [Unconfirmed] that represents it being marked unconfirmed.

Example of usage:
`unconfirm 1`
Expected outcome:

```
Company has been successfully uncomfirmed!

```
### `list unconfirmed` - Shows all stored companies marked with unconfirmed attendance
Shows all the unconfirmed companies that are stored in the company list.

Example of usage:
`list unconfirmed`
Expected outcome:

```
2
Company name: KFC
Company contact number: 81213999
Company contact email: FRIEDCHICKEN@KFC.COM.SG
Company industry: ENGINEERING
[Unconfirmed]
3
Company name: DBS
Company contact number: 82920010
Company contact email: IBANKING@GMAIL.COM
Company industry: BANKING AND FINANCE
[Unconfirmed]
```

## FAQ

**Q**: How do I transfer my data to another computer? 

**A**: {your answer here}

## Command Summary

{Give a 'cheat sheet' of commands here}

* Add todo `todo n/TODO_NAME d/DEADLINE`
