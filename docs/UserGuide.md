# User Guide

## Introduction

{Give a product intro}

## Quick Start

{Give steps to get started quickly}

1. Ensure that you have Java 11 or above installed.
1. Down the latest version of `Duke` from [here](http://link.to/duke).

## Features 



### Graph analyser and visualiser: `Graph. `
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

### Matrix calculation: `Matrix. `
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

### Store notes:
Adds a new item to the Notes list.

Format: `Store. <item description>`
Example of usage: `Store. index`

### Display all notes: 
Display a list of all notes stored by user.

Format: `List.`
Example of usage: `List.`

### Display a specific note: 
Display a particular note in the Notes list.

Format: `List. <number>`
Example of usage: `List. 1`

### Delete a specific note:
Delete a particular note stored in the Notes list

Format: `Delete. <number>`
Example of usage: `Delete. 1`


## FAQ

**Q**: How do I transfer my data to another computer? 

**A**: {your answer here}

## Command Summary

{Give a 'cheat sheet' of commands here}

* Add todo `todo n/TODO_NAME d/DEADLINE`
