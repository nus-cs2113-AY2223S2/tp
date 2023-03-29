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

### 2) Store notes:
Adds a new item to the Notes list.

Format: `Store. <item description>`
Example of usage: `Store. index`

### 3) Display all notes: 
Display a list of all notes stored by user.

Format: `List.`
Example of usage: `List.`

### 4) Display a specific note: 
Display a particular note in the Notes list.

Format: `List. <number>`
Example of usage: `List. 1`

### 5) Delete a specific note:
Delete a particular note stored in the Notes list

Format: `Delete. <number>`
Example of usage: `Delete. 1`

### 6) Mark a specific note:
Delete a particular note stored in the Notes list

Format: `Delete. <number>`
Example of usage: `Delete. 1`

## FAQ

**Q**: How do I transfer my data to another computer? 

**A**: {your answer here}

## Command Summary

{Give a 'cheat sheet' of commands here}

* Add todo `todo n/TODO_NAME d/DEADLINE`
