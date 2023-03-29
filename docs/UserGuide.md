# GitHub User Guide for BadMaths ➕➖✖️➗

## Introduction 🧮

Hello! Welcome to BadMaths! BadMaths is a quick lookup tool for 
trigonometry graphs and basic matrix operations that aims to help students save
time when performing Mathematical Operations. 

To help you get started, our team has put together this user guide to guide you on
the steps to operate BadMaths for your Mathematical woes.

## Quick Start

{Give steps to get started quickly}

1. Ensure that you have Java 11 or above installed.
1. Down the latest version of `Duke` from [here](http://link.to/duke).

## Features 

### 1) Graph analyser and visualiser: `Graph. `
This feature accepts a trigonometry equation (Sinusoidal signal) and outputs the amplitude, frequency, phase, and vertical shift.
It also displays the image of the corresponding graph.

Format: `Graph. [Amplitude]*[Trigo]([Frequency]*x+[PhaseShift])+[VerticalShift]`

* `[Amplitude]` can be any positive number.
* `[Trigo]` can be sin, cos or tan.
* `[Frequency],[PhaseShift],[VerticalShift]` can be any number.
* `[]` is not needed when entering the input.

Example input:
```
Graph. 2*sin(5*x+2)-8
```

### 2) Store Notes:

Adds a new item to the Notes list.

Format: `Store. <item description>`
Example of usage: `Store. index`

### 3) Display all Notes: 
Display a list of all items stored by user.

Format: `List.`
Example of usage: `List.`

### 4) Display a specific note: 
Display a particular item in the Notes list.

Format: `List. <number>`
Example of usage: `List. 1`

### 5) Delete Notes:
Delete a particular item stored in the Notes list.

Format: `Delete. <number>`
Example of usage: `Delete. 1`

### 6) Mark Notes:
Mark a particular item in Notes List as completed.

Format: `Mark. <number>`
Example of usage: `Mark. 1`

### 7) Unmark Notes:
Unmark a particular item in Notes List as incomplete.

Format: `Unmark. <number>`
Example of usage: `Unmark. 2`

### 8) List all completed items:
Display a list of all note items marked as completed.

Format: `FindMark.`
Example of usage: `FindMark.`

### 9) List all uncompleted notes:
Display a list of all incomplete note items. 

Format: `FindUnmark.`
Example of usage: `FindUnmark.`

### 10) Find notes using keyword:
Find items stored in Notes List by searching for a keyword.

Format: `FindInfo. <description>`
Example of usage: `FindInfo. index`

### 11) Display all notes based on priority types:
Display all items
stored in the Notes List based on the respective priority rankings.

Format: `Rank. Priority`
Example of usage: `Rank. Priority`

### 11) Display all notes of a certain priority type:
Display all items stored in the Notes list 
based on the priority that users are searching for.

Format: `FindPrior. <Priority Type>`
Example of usage: `FindPrior. <LOW>`
Example of usage: `FindPrior. <MEDIUM>`
Example of usage: `FindPrior. <HIGH>`

### 12) Clear all notes stored in Notes List:
Delete all note items stored in Notes List.

Format: `Clear.`
Example of usage: `Clear.`

### 13) Exit BadMaths:
Exit and leave BadMaths:

Format: `Bye.`
Example of usage: `Bye.`

## FAQ

**Q**: How do I start and run BadMaths? 

**A**: {your answer here}

**Q**: How do I exit and leave BadMaths?

**A**: You can terminate the MathHelp programme by simply typing 
`Bye.` in the command.

## Command Summary

{Give a 'cheat sheet' of commands here}

* Add todo `todo n/TODO_NAME d/DEADLINE`
