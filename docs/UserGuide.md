# GitHub User Guide for MathHelp ➕➖✖️➗

## Introduction 🧮

Hello! Welcome to MathHelp! MathHelp is a quick lookup tool for 
trigonometry graphs and basic matrix operations that aims to help students save
time when performing Mathematical Operations. 

To help you get started, our team has put together this user guide to guide you on
the steps to operate MathHelp for your Mathematical woes.

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

### 3) Store notes:

Adds a new item to the Notes list.

Format: `Store. <item description>`
Example of usage: `Store. index`

### 4) Display all notes: 
Display a list of all notes stored by user.

Format: `List.`
Example of usage: `List.`

### 5) Display a specific note: 
Display a particular note in the Notes list.

Format: `List. <number>`
Example of usage: `List. 1`

### 6) Delete a specific note:
Delete a particular note stored in the Notes list

Format: `Delete. <number>`
Example of usage: `Delete. 1`

### 7) Mark a specific note:
Mark a particular note stored in the Notes list as completed

Format: `Mark. <number>`
Example of usage: `Mark. 1`

Example of usage:

`Mark. 1`

Expected outcome:

````
You have marked this note as done:
[HIGH][Y][2]Note 1
````

Example of usage:

`Mark. 2`

Expected outcome:

````
You have marked this note as done:
[HIGH][Y][1]Note 2
````

### 8) Unmark a specific note:
Unmark a particular note stored in the Notes list as incomplete

Format: `Unmark. <number>`

Example of usage:

`Unmark. 1`

Expected outcome:

````
You have unmarked this note:
[HIGH][N][2]Note 1
````

Example of usage:

`Unmark. 2`

Expected outcome:

````
You have unmarked this note:
[HIGH][N][1]Note 2
````


### 9) List all completed notes:
Display a list of all completed notes

Format: `FindMark.`
Example of usage: `FindMark.`

Expected outcome:
````
Here are the notes you are searching for:
1. [HIGH][Y][2]Note 1
2. [HIGH][Y][1]Note 2
````

### 10) List all uncompleted notes:
Display a list of all uncompleted notes

Format: `FindUnmark.`

Example of usage: `FindUnmark.`

Expected outcome:
````
Here are the notes you are searching for:
1. [HIGH][N][2]Note 1
2. [HIGH][N][1]Note 2
````

### 11) Find notes by searching for a keyword:
Find tasks stored in Notes by searching for a keyword.

Format: `FindInfo. <description>`

Example of usage:
`FindInfo. Note`

Expected outcome:
````
Here are the notes you are searching for:
1. [HIGH][N][2]Note 1
2. [HIGH][N][1]Note 2
3. [MEDIUM][N][0]Note 3
4. [MEDIUM][N][0]Note 4
5. [LOW][N][0]Note 5
````

Example of usage:

`FindInfo. 1`

Expected outcome:

````
Here are the notes you are searching for:
1. [HIGH][N][2]Note 1
````

Example of usage:

`FindInfo. X`

Expected outcome:

````
Sorry, no relevant results were found for this query. Please try other keywords.
````

### 12) Display all notes based on priority type:
Find all notes stored in the list based on its respective priority rankings

Format: `Rank. Priority`

Example of usage: `Rank. Priority`

Expected outcome:

````
High priority notes:
[HIGH][N][2]Note 1
[HIGH][N][1]Note 2
Medium priority notes:
[MEDIUM][N][0]Note 3
[MEDIUM][N][0]Note 4
Low priority notes:
[LOW][N][0]Note 5
````

### 13) Display all notes of a certain priority type:
Find all notes stored in the list based on priority that users are searching for

Format: `FindPrior. <Priority Type>`

* The `<Priority Type>` must be one of the three: `High`, `MEDIUM`, `LOW`.

Example of usage: `FindPrior. <LOW>`

Expected outcome:

````
Here are the notes you are searching for:
1. [LOW][N][0]Note 5
````

Example of usage: `FindPrior. <MEDIUM>`

Expected outcome:

````
Here are the notes you are searching for:
1. [MEDIUM][N][0]Note 3
2. [MEDIUM][N][0]Note 4
````

Example of usage: `FindPrior. <HIGH>`

Expected outcome:

````
Here are the notes you are searching for:
1. [HIGH][N][2]Note 1
2. [HIGH][N][1]Note 2
````

### 14) Clear all notes stored in Notes List:
Delete all notes stored in the list

Format: `Clear.`

* The command clears everything stored in the list of notes.

Example of usage: `Clear.`

Expected outcome:

`File content cleared successfully!`

### 15) Prioritize a note
Change the priority of a certain note in the notes list.

Format: `<Priority Type>. Index`

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

### 16) Exit MathHelp:
Exit and leave MathHelp:

Format: `Bye.`

Example of usage: `Bye.`

Expected outcome:

````
Goodbye!
````

### 17) View Help Manual: `Help.`
View the content of Help Manual.

Format: `Help.`

Expected outcome:

````
------------------------------------------------------------------
Hello! What can I do for you?
------------------------------------------------------------------
1. Type -> Graph. equation <- to do graph calculation
2. Type -> Matrix. equation <- to do matrix calculation
3. Type -> Store. any_string <- to add notes
4. Type -> List. <- to list stored notes
5. Type -> Bye. <- to exit program
-------------------------------------------------------------------
For more details, please visit our GitHub website [xxx.github.com].
If you have any queries, please contact [exxxxxxx@u.nus.edu].
-------------------------------------------------------------------
````

## FAQ

**Q**: How do I start and run MathHelp? 

**A**: {your answer here}

**Q**: How do I exit and leave MathHelp?

**A**: You can terminate the MathHelp programme by simply typing 
`Bye.` in the command.

## Command Summary

{Give a 'cheat sheet' of commands here}

* Add todo `todo n/TODO_NAME d/DEADLINE`
