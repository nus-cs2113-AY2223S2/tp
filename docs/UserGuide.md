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

### 2) Matrix calculation: `Matrix. `
 This feature accepts matrix equation and outputs calculation result.

 Format: `Matrix. [Matrix] [operator] [Matrix]`

 * `[Matrix]` is the 2 dimensional matrix with integer elements. When you declare the matrix, you should follow the matrix format below:
   ```
   [1,2;3,4]
   ```
   * You should separate the elements with comma (,) in the single row.
   * You should separate the rows with the semicolons.

   ```
   [1,2;3,4].T
   ```
   * You can declare transposed matrix with the transpose annotation `.T`.
   * Transposed matrix above is equal with matrix `[1,3;2,4]`.

 * `[operator]` is the matrix operator. You can use 4 operators below:
   * `.*` : matrix multiplication
   * `*` : element wise product
   * `+` : matrix addition
   * `-` : matrix subtraction

 Example input:
 ```
 Matrix. [1,2;3,4] .* [4,5;6,7]
 ```
### 3) Store Notes:
Adds a new item to the Notes list.

Format: `Store. <item description>`
Example of usage: `Store. index`

### 4) Display all Notes: 
Display a list of all items stored by user.

Format: `List.`
Example of usage: `List.`

### 5) Display a specific note: 
Display a particular item in the Notes list.

Format: `List. <number>`
Example of usage: `List. 1`

### 6) Delete Notes:
Delete a particular item stored in the Notes list.

Format: `Delete. <number>`
Example of usage: `Delete. 1`

### 7) Mark Notes:
Mark a particular item in Notes List as completed.

Format: `Mark. <number>`
Example of usage: `Mark. 1`

### 8) Unmark Notes:
Unmark a particular item in Notes List as incomplete.

Format: `Unmark. <number>`

### 9) List all completed items:
Display a list of all note items marked as completed.

Format: `FindMark.`
Example of usage: `FindMark.`

### 10) List all uncompleted notes:
Display a list of all incomplete note items. 

Format: `FindUnmark.`
Example of usage: `FindUnmark.`

### 11) Find notes using keyword:
Find items stored in Notes List by searching for a keyword.

Format: `FindInfo. <description>`

### 12) Display all notes based on priority types:
Display all items stored in the Notes List based on the respective priority rankings.

Format: `Rank. Priority`
Example of usage: `Rank. Priority`

### 13) Display all notes of a certain priority type:
Find all notes stored in the list based on priority that users are searching for

Format: `FindPrior. <Priority Type>`
* The `<Priority Type>` must be one of the three: `High`, `MEDIUM`, `LOW`.
Example of usage: `FindPrior. <LOW>`

### 14) Clear all notes stored in Notes List:
Delete all note items stored in Notes List.

Format: `Clear.`

### 15) Exit BadMaths:
Exit and leave BadMaths:

Format: `Bye.`

### 15) Prioritize a note
Change the priority of a certain note in the notes list.

Format: `<Priority Type>. Index`
Example of usage: `High .1`

### 16) View Help Manual: `Help.`
View the content of Help Manual.
Format: `Help.`

## FAQ

**Q**: How do I start and run BadMaths? 

**A**: {your answer here}

**Q**: How do I exit and leave BadMaths?

**A**: You can terminate the MathHelp programme by simply typing 
`Bye.` in the command.

## Command Summary

{Give a 'cheat sheet' of commands here}

* Add todo `todo n/TODO_NAME d/DEADLINE`
