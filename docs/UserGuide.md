# GitHub User Guide for BadMaths ➕➖✖️➗

<!-- TOC -->
* [GitHub User Guide for BadMaths ➕➖✖️➗](#github-user-guide-for-badmaths-)
   * [Introduction 🧮](#introduction-)
   * [Quick Start](#quick-start)
   * [Features](#features)
      * [1) Graph analyser and visualiser: `Graph. `](#1--graph-analyser-and-visualiser--graph-)
      * [2) Matrix calculation: `Matrix. `](#2--matrix-calculation--matrix-)
      * [3) Store Notes:](#3--store-notes-)
      * [4) Display all Notes:](#4--display-all-notes-)
      * [5) Display a specific note:](#5--display-a-specific-note-)
      * [6) Delete Notes:](#6--delete-notes-)
      * [7) Mark Notes:](#7--mark-notes-)
      * [8) Unmark Notes:](#8--unmark-notes-)
      * [9) List all completed items:](#9--list-all-completed-items-)
      * [10) List all uncompleted notes:](#10--list-all-uncompleted-notes-)
      * [11) Find notes using keyword:](#11--find-notes-using-keyword-)
      * [12) Display all notes based on priority types:](#12--display-all-notes-based-on-priority-types-)
      * [13) Display all notes of a certain priority type:](#13--display-all-notes-of-a-certain-priority-type-)
      * [14) Clear all notes stored in Notes List:](#14--clear-all-notes-stored-in-notes-list-)
      * [15) Prioritize a note](#15--prioritize-a-note)
      * [16) Solving Quadratic Equations: `Quadratic.`](#16--solving-quadratic-equations--quadratic)
      * [17) Exit MathHelp:](#17--exit-mathhelp-)
      * [18) View Help Manual: `Help.`](#18--view-help-manual--help)
   * [FAQ](#faq)
   * [Command Summary](#command-summary)
<!-- TOC -->

## Introduction 🧮

Hello! Welcome to BadMaths! BadMaths is a quick lookup tool for
trigonometry graphs and basic matrix operations that aims to help students save
time when performing Mathematical Operations.

To help you get started, our team has put together this user guide to guide you on
the steps to operate BadMaths for your Mathematical woes.

## Quick Start

Before you start using BadMaths, make sure you have the following ready!

1. Ensure that you have Java 11 or above installed.
2. Down the latest version of `BadMaths` from [here](https://github.com/AY2223S2-CS2113-F10-2/tp.git).

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
### 3) Store Notes: `Store. `
Adds a new item to the Notes list.

Format: `Store. <item description>`
Example of usage: `Store. index`

### 4) Display all Notes: `List. `
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

### 7) Clear all notes stored in Notes List:
Delete all note items stored in Notes List.

Format: `Clear.`

### 8) Mark Notes:
Mark a particular item in Notes List as completed.

Format: `Mark. <number>`
Example of usage: `Mark. 1`

### 9) Unmark Notes:
Unmark a particular item in Notes List as incomplete.

Format: `Unmark. <number>`

### 10) List all completed items:
Display a list of all note items marked as completed.

Format: `FindMark.`
Example of usage: `FindMark.`

### 11) List all uncompleted notes:
Display a list of all incomplete note items.

Format: `FindUnmark.`
Example of usage: `FindUnmark.`

### 12) Find notes using keyword:
Find items stored in Notes List by searching for a keyword.

Format: `FindInfo. <description>`

### 15) Prioritize a note
Change the priority of a certain note in the notes list.

Format: `<Priority Type>. Index`
Example of usage: `High .1`

* The `<Priority Type>` must be one of the three: `High`, `MEDIUM`, `LOW`.

Example of usage:

`High. 1`

Expected outcome:

````
You have changed its priority to HIGH
1: [HIGH][N][0]Note 1
````
Example of usage:

`Medium. 3`

Expected outcome:

````
You have changed its priority to MEDIUM
3: [MEDIUM][N][0]Note 3
````

### 13) Display all notes based on all priority types:
Display all items stored in the Notes List based on the respective priority rankings.

Format: `Rank. Priority`
Example of usage: `Rank. Priority`

### 14) Display all notes of a certain priority type:
Find all notes stored in the list based on priority that users are searching for

Format: `FindPrior. <Priority Type>`
* The `<Priority Type>` must be one of the three: `High`, `MEDIUM`, `LOW`.
  Example of usage: `FindPrior. <LOW>`

### 16) Solving Quadratic Equations: `Quadratic.`
Solves for `x` in a quadratic equation.

Format: `Quadratic. [quadratic equation]`

- `Quadratic.` must be in this exact format (With uppercase Q and full stop at the end)
- The quadratic equation must look like this: `2x^2 + 2x + 1` with spaces between each value. Both double and int
  numbers are acceptable (eg. `-2.5x^2 + 3 + 1`). Inputting just the sign is also acceptable (eg. `-x^2 + x - 1`).

Examples:

- Input: `Quadratic. 2x^2 + 2x + 1` Output: `x is imaginary.`
- Input: `Quadratic. x^2 + 4x - 5` Output: `x1 = 1.0 , x2 = -5.0`

### 17) View Help Manual: `Help.`

View the content of Help Manual.
Format: `Help.`


### 18) Exit BadMaths:
Exit and leave BadMaths:

Format: `Bye.`

Example of usage: `Bye.`

Expected outcome:

````
Goodbye!
````

## FAQ

**Q**: How do I start and run BadMaths?

**A**: {your answer here}

**Q**: How do I exit and leave BadMaths?

**A**: You can terminate the MathHelp programme by simply typing
`Bye.` in the command.

## Command Summary

{Give a 'cheat sheet' of commands here}

* Add todo `todo n/TODO_NAME d/DEADLINE`
